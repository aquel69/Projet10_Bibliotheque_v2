package fr.lardon.bibliobatch.starterBatch;

import fr.lardon.bibliobatch.controller.BatchController;
import fr.lardon.bibliobatch.dao.*;
import fr.lardon.bibliobatch.model.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Component
/**
 * classe permettant l'envoi des mails et permettant de fixer un horaire d'envoi
 */
public class SchedulingConfiguration {

    private AbonnePretOuvrage abonnePretOuvrage;
    private Ouvrage ouvrage;
    private Mail mail;
    private List<Pret> pretList;
    private List<AbonnePret> abonnePretList;
    private Pret pretAEnvoyer;
    private String typeEmail = null;
    int idDernierOuvrageRestitue = 0;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private DaoAbonnePret daoAbonnePret;

    @Autowired
    private DaoOuvrage daoOuvrage;

    @Autowired
    private DaoAbonneOuvrageReservation daoAbonneOuvrageReservation;

    @Autowired
    private BatchController batchController;

    @Autowired
    private Configuration freemarkerConfig;

    /*@Scheduled(cron = "0 0 0 * * *")*/
    @Scheduled(fixedRate = 90000L)
    public void startBatchEmprunt() throws MessagingException, IOException, TemplateException {
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("/templates/");

        System.out.println("Sending Email...");
        abonnePretOuvrage = new AbonnePretOuvrage();
        ouvrage = new Ouvrage();
        mail = new Mail();
        pretAEnvoyer = new Pret();

        //récuperation de tous les abonnés
        abonnePretList = daoAbonnePret.findAll();

        for(AbonnePret abonnePret : abonnePretList){
            abonnePretOuvrage = batchController.abonnePretSelonSonId(abonnePret.getIdAbonne());
            pretList = abonnePretOuvrage.getListePret();

            for(Pret pret : pretList){
                //envoi de l'email
                if(pret.getStatutPriorite() == "1" || pret.getStatutPriorite() == "2") {
                    //type d'email à envoyé
                    if(pret.getStatutPriorite() == "1"){
                        typeEmail = "Email.ftl";
                    }else if(pret.getStatutPriorite() == "2"){
                        typeEmail = "EmailRappel.ftl";
                    }

                    pretAEnvoyer = pret;

                    //récupération de l'ouvrage
                    ouvrage = daoOuvrage.findByCodeBibliotheque(pretAEnvoyer.getOuvragePret().getCodeBibliotheque());

                    mail.setTo(abonnePretOuvrage.getAbonne().getEmail());
                    mail.setSubject("Rappel de restitution du livre '" + ouvrage.getLivre().getTitre() + "'");
                    mail.setOuvrage(ouvrage);
                    mail.setPret(pretAEnvoyer);

                    envoiEmail(mail);
                }
            }
        }
        System.out.println("Done");
    }

    @Scheduled(cron = "*/1 * * * * *")
    public void startBatchReservation() throws MessagingException, IOException, TemplateException{
        FreeMarkerConfigurationFactoryBean bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath("/templates/");
        System.out.println("entre dans le batch reservation");
        List<AbonneOuvrageReservation> abonneOuvrageReservationList;
        List<Bibliotheque> bibliothequeList;
        Bibliotheque bibliothequeDeLOuvrage;
        Ouvrage ouvrageRestitue;
        Abonne abonne;
        mail = new Mail();
        //ajout des 48h de délai pour récupérer l'ouvrage
        LocalDateTime dateTime = LocalDateTime.now().plusDays(2);

        //récupération, de la liste des bibliothèques
        bibliothequeList = batchController.BibliothequeListe();

        //parcours de la liste bibliothèque pour savoir si un ouvrage a été restitué
        for (Bibliotheque bibliotheque : bibliothequeList) {
            if (bibliotheque.isNouveauDernierOuvrage() && idDernierOuvrageRestitue == 0){
                System.out.println("un ouvrage a été restitué");
                idDernierOuvrageRestitue = bibliotheque.getDernierOuvrageRestitue();

                bibliotheque.setNouveauDernierOuvrage(false);
                bibliotheque.setDernierOuvrageRestitue(0);
                batchController.modifierBibliotheque(bibliotheque);

                break;
            }
        }

        if (idDernierOuvrageRestitue != 0) {
            bibliothequeDeLOuvrage = batchController.bibliothequeSelonOuvrage(idDernierOuvrageRestitue);

            //ouvrage restitué
            ouvrageRestitue = batchController.ouvrageParSonId(idDernierOuvrageRestitue);

            //récupération liste des réservations selon l'ouvrage
            abonneOuvrageReservationList = batchController.listeReservationSelonOuvrage
                    (idDernierOuvrageRestitue, ouvrageRestitue.getNombreExemplairesTotal() * 2);

            if (abonneOuvrageReservationList.size() > 0 && abonneOuvrageReservationList.get(0).isOuvrageRecupere() == false) {
                System.out.println("envoi de l'email reservation");
                //type Email
                typeEmail = "EmailReservation.ftl";

                //récupération de l'abonné
                abonne = batchController.AbonneSelonId(abonneOuvrageReservationList.get(0).getAbonneReservation().getIdAbonne());

                mail.setTo(abonne.getEmail());
                mail.setSubject("L'ouvrage '" + ouvrageRestitue.getLivre().getTitre() + "' est disponible");
                mail.setOuvrage(ouvrageRestitue);
                mail.setBibliotheque(bibliothequeDeLOuvrage);

                envoiEmail(mail);

                //ajout dans la base de données de la date limite pour recuperer l'ouvrage
                abonneOuvrageReservationList.get(0).setDateDeDelaiRecuperation(dateTime);
                abonneOuvrageReservationList.get(0).setOuvrageRecupere(true);
                batchController.modifierReservation(abonneOuvrageReservationList.get(0));

                idDernierOuvrageRestitue = 0;
            }
        }

    }

    @Scheduled(cron = "*/1 * * * * *")
    public void startSupprimerReservation() {
        List<AbonneOuvrageReservation> abonneOuvrageReservationList;
        LocalDateTime date = LocalDateTime.now();

        abonneOuvrageReservationList = batchController.listeReservationTotale();

        for (AbonneOuvrageReservation abonneOuvrageReservation : abonneOuvrageReservationList){
            if (abonneOuvrageReservation.isOuvrageRecupere() && abonneOuvrageReservation.getDateDeDelaiRecuperation().isBefore(date)){
                List<AbonneOuvrageReservation> abonneOuvrageReservationListBoucle;
                Ouvrage ouvrageBoucle = abonneOuvrageReservation.getOuvrageReservation();

                batchController.supprimerReservation(abonneOuvrageReservation.getIdAbonneOuvrageReservation());

                abonneOuvrageReservationListBoucle = batchController.listeReservationSelonOuvrage
                       (ouvrageBoucle.getIdOuvrage(), ouvrageBoucle.getNombreExemplairesTotal() * 2);

                if (abonneOuvrageReservationListBoucle.size() != 0){
                   idDernierOuvrageRestitue = ouvrageBoucle.getIdOuvrage();
                }
            }
        }
    }

    public void envoiEmail(Mail mail) throws MessagingException, IOException, TemplateException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.addAttachment("logo.png", new ClassPathResource("static/logo/Logo_bibliothèque.png"));

        Template template = freemarkerConfig.getTemplate(typeEmail);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail);

        helper.setTo(mail.getTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());

        javaMailSender.send(message);
    }
}
