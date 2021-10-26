package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.*;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.AbonneOuvrageReservation;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.AbonnePretOuvrage;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceGestionUtilisateur;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceLivresProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
/**
 * classe regroupant les méthodes permettant de traiter, envoyer et récupérer les données avec les page html de la création et la modification des comptes d'un abonné
 */
public class GestionUtilisateurController {

    private static final int STRENGTH = 12;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private BCryptPasswordEncoder bCryptPasswordMatche;
    private Abonne abonne = null;
    private Abonne abonneSecurisation = null;
    private List<AbonneOuvrageReservation> abonneReservation;
    private AbonnePretOuvrage abonnePret = null;
    private Adresse adresse = new Adresse();
    private Bibliotheque bibliotheque = null;
    private Role role = null;
    private Abonne utilisateurAuthentifie = new Abonne();
    private Abonne abonneAModifier = null;
    private List<Abonne> abonnes;
    private boolean resultatEmailExistant = false;
    private int codeRole = 0;


    @Autowired
    private MicroserviceGestionUtilisateur gestionUtilisateur;

    @Autowired
    private CatalogueController catalogueController;

    @Autowired
    private MicroserviceLivresProxy livresProxy;

    /**
     * permet de renvoyer à la page inscription
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/Inscription", method = RequestMethod.GET)
    public String inscription(Model model) {

        abonne = new Abonne();
        adresse = new Adresse();

        //ajout dans le model
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("abonne", abonne);
        model.addAttribute("adresse", adresse);

        return "Inscription";
    }

    /**
     * permet de récupérer toutes les données saisies par l'utilisateur et de créer son compte
     *
     * @param model
     * @param abonnePost
     * @param bindingResult
     * @param adressePost
     * @param bindingResult1
     * @return
     */
    @RequestMapping(value = "/Inscription", method = RequestMethod.POST)
    public String validationInscription(Model model, @ModelAttribute("abonne") @Valid Abonne abonnePost, BindingResult bindingResult, @ModelAttribute("adresse") @Valid Adresse adressePost, BindingResult bindingResult1, @RequestParam String confirmationEmail, @RequestParam String confirmationMotDePasse) {

        abonne = new Abonne();
        abonneSecurisation = new Abonne();
        adresse = new Adresse();
        bibliotheque = new Bibliotheque();
        role = new Role();
        bCryptPasswordEncoder = new BCryptPasswordEncoder(STRENGTH, new SecureRandom());
        String numeroAbonne;
        String messageErreur;
        resultatEmailExistant = false;
        String messageErreurEmail;
        String messageErreurMotDePasse;

        //vérification de la saisie identique du mot de passe et des emails
        if(!abonnePost.getEmail().equals(confirmationEmail)){
            messageErreurEmail = "Les emails ne sont pas identiques";

            model.addAttribute("messageErreurEmail", messageErreurEmail);
            model.addAttribute("codeRole", codeRole);
            model.addAttribute("abonneBean", abonnePost);
            model.addAttribute("adresseBean", adressePost);

            return "Inscription";
        }else if(!abonnePost.getMotDePasse().equals(confirmationMotDePasse)){
            messageErreurMotDePasse = "Les mots de passe ne sont pas identiques";

            model.addAttribute("messageErreurMotDePasse", messageErreurMotDePasse);
            model.addAttribute("codeRole", codeRole);
            model.addAttribute("abonne", abonnePost);
            model.addAttribute("adresse", adressePost);

            return "Inscription";
        }

        abonnes = gestionUtilisateur.listeAbonnes();

        for (Abonne abonne1 : abonnes) {
            if (abonne1.getEmail().equals(abonnePost.getEmail())) {
                resultatEmailExistant = true;
            }
        }

        if (bindingResult.hasErrors() || bindingResult1.hasErrors() || resultatEmailExistant == true) {
            if (resultatEmailExistant == true) {
                messageErreur = "L'email existe déjà";
                model.addAttribute("messageErreur", messageErreur);
            }

            //ajout dans le model
            model.addAttribute("codeRole", codeRole);
            model.addAttribute("abonne", abonnePost);
            model.addAttribute("adresse", adressePost);

            return "Inscription";
        }

        //attribution du role aux abonnés
        role = gestionUtilisateur.recupererRole(1);

        //attribution de la bibliothèque aux abonnés
        bibliotheque = gestionUtilisateur.recupererBibliotheque("18004625200177");

        //attribution de la date pour l'ouverture du compte de l'abonnée
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());

        //attribution du numéro d'abonné
        abonne = gestionUtilisateur.recupererDernierAbonne();
        numeroAbonne = abonne.getNumeroAbonne();
        int numeroAbonneInt = Integer.parseInt(numeroAbonne) + 1;

        //ajout de l'adresse dans la base de données et récupération de l'id
        gestionUtilisateur.ajouterAdresse(adressePost);
        adresse = gestionUtilisateur.recupererDernierAdresse();

        //sécurisation du mot de passe
        abonnePost.setMotDePasse(bCryptPasswordEncoder.encode(abonnePost.getMotDePasse()));

        //alimentation de l'objet abonné
        abonnePost.setDateDeCreationDuCompte(date);
        abonnePost.setNumeroAbonne(String.valueOf(numeroAbonneInt));
        abonnePost.setBibliotheque(bibliotheque);
        abonnePost.setRole(role);
        abonnePost.setAdresse(adresse);

        //ajout de l'abonné dans la base de données
        gestionUtilisateur.ajouterAbonne(abonnePost);

        //affichage du message
        String message = abonnePost.getPseudo() + "! Félicitation! Vous avez créé votre compte !";
        model.addAttribute("message", message);

        //récupération des livres à afficher dans la page accueil
        catalogueController.accueil(model);

        return "Accueil";

    }

    /**
     * permet de renvoyer à la page modification du compte
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/ModificationCompte", method = RequestMethod.GET)
    public String modificationCompte(Model model) {
        abonnePret = new AbonnePretOuvrage();

        abonneAModifier = new Abonne();

        //récupération du code role
        if (model.getAttribute("codeRole") != null) codeRole = (int) model.getAttribute("codeRole");

        //récupération des prêt de l'abonné pour la gestion de ses emprunts
        if (model.getAttribute("utilisateurAuthentifie") != null) {
            utilisateurAuthentifie = (Abonne) model.getAttribute("utilisateurAuthentifie");
            abonnePret = livresProxy.abonnePretSelonSonId(utilisateurAuthentifie.getIdAbonne());
            abonneReservation = livresProxy.listeReservationSelonAbonne(utilisateurAuthentifie.getIdAbonne());
        }

        abonneAModifier = gestionUtilisateur.recupererAbonne(utilisateurAuthentifie.getIdAbonne());

        //ajout dans le model
        model.addAttribute("abonnePret", abonnePret);
        model.addAttribute("abonneAModifier", abonneAModifier);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("abonneReservation", abonneReservation);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("adresse", adresse);

        return "ModificationCompte";
    }

    /**
     * permet de récupérer les données modifiés de l'abonné et de les sauvegarder
     *
     * @param model
     * @param abonneBeanModifier
     * @return
     */
    @RequestMapping(value = "/ModificationCompte", method = RequestMethod.POST)
    public String validationModificationCompte(Model model, @RequestParam("ancienMotDePasse") String ancienMotDePasse, @ModelAttribute("abonneAModifier") @Valid AbonneModifie abonneBeanModifier, BindingResult bindingResult) {
        String messageErreur;
        String messageErreurMotDePasse = null;
        String messageErreurNouveauMotDePasse = null;
        resultatEmailExistant = false;
        bCryptPasswordMatche = new BCryptPasswordEncoder();
        bCryptPasswordEncoder = new BCryptPasswordEncoder(STRENGTH, new SecureRandom());

        abonnes = gestionUtilisateur.listeAbonnes();

        //vérification que l'email est diffèrent que celui dans le compte de l'abonné
        if(!abonneAModifier.getEmail().equals(abonneBeanModifier.getEmail())){
            //vérification que l'email n'existe pas déjà
            for(int i=0; i < abonnes.size(); i++) {
                if (abonneBeanModifier.getEmail().equals(abonnes.get(i).getEmail())) {
                    resultatEmailExistant = true;
                }
            }
        }

        if (bindingResult.hasErrors() ||  resultatEmailExistant == true) {
            if (resultatEmailExistant == true) {
                messageErreur = "L'email existe déjà";
                model.addAttribute("messageErreur", messageErreur);
            }

            model.addAttribute("abonneAModifier", abonneBeanModifier);
            model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
            model.addAttribute("codeRole", codeRole);

            return "ModificationCompte";
        }

        if(!abonneBeanModifier.getMotDePasse().isEmpty()) {
            //vérification que le mot de passe actuel correspond au mot de passe saisie par l'abonné
            if (bCryptPasswordMatche.matches(ancienMotDePasse, abonneAModifier.getMotDePasse()) && abonneBeanModifier.getMotDePasse().length() >= 6) {
                //sécurisation du nouveau mot de passe
                abonneAModifier.setMotDePasse(bCryptPasswordEncoder.encode(abonneBeanModifier.getMotDePasse()));
            } else {
                if(!bCryptPasswordMatche.matches(ancienMotDePasse, abonneAModifier.getMotDePasse())){
                    messageErreurMotDePasse = "Le mot de passe ne correspond pas avec le mot de passe de votre compte";
                }else{
                    messageErreurNouveauMotDePasse = "Le nouveau mot de passe doit être composé de 6 caractères minimum";
                }

                model.addAttribute("abonnePret", abonnePret);
                model.addAttribute("messageErreurMotDePasse", messageErreurMotDePasse);
                model.addAttribute("messageErreurNouveauMotDePasse", messageErreurNouveauMotDePasse);
                model.addAttribute("abonneAModifier", abonneBeanModifier);
                model.addAttribute("abonneReservation", abonneReservation);
                model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
                model.addAttribute("codeRole", codeRole);

                return "ModificationCompte";
            }
        }

        //modifier utilisateur
        abonneAModifier.setNom(abonneBeanModifier.getNom());
        abonneAModifier.setPrenom(abonneBeanModifier.getPrenom());
        abonneAModifier.setPseudo(abonneBeanModifier.getPseudo());
        abonneAModifier.setEmail(abonneBeanModifier.getEmail());
        abonneAModifier.getAdresse().setNumero(abonneBeanModifier.getAdresse().getNumero());
        abonneAModifier.getAdresse().setComplement(abonneBeanModifier.getAdresse().getComplement());
        abonneAModifier.getAdresse().setRue(abonneBeanModifier.getAdresse().getRue());
        abonneAModifier.getAdresse().setCodePostal(abonneBeanModifier.getAdresse().getCodePostal());
        abonneAModifier.getAdresse().setVille(abonneBeanModifier.getAdresse().getVille());

        gestionUtilisateur.modifierAbonne(abonneAModifier);

        //affichage du message
        String message = "Vous avez modifié votre compte !";

        model.addAttribute("abonnePret", abonnePret);
        model.addAttribute("message", message);
        model.addAttribute("abonneAModifier", abonneAModifier);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("abonneReservation", abonneReservation);
        model.addAttribute("codeRole", codeRole);

        return "ModificationCompte";
    }

}