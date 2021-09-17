package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Abonne;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.*;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceGestionUtilisateur;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceLivresProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.List;

@Controller
/**
 * classe regroupant les méthodes permettant de traiter, envoyer et récupérer les données avec les page html de l'employé
 */
public class EmployeController {

    private Abonne utilisateurAuthentifie;
    private Pret pret;
    private Abonne abonne = null;
    private Ouvrage ouvrage = null;
    private OuvrageAModifie ouvrageAModifie = null;
    private int codeRole = 5;
    private boolean isRestitue;
    private  String message = null;
    private  String messageRestitution = null;

    @Autowired
    private MicroserviceLivresProxy livresProxy;

    @Autowired
    private MicroserviceGestionUtilisateur gestionUtilisateur;

    /**
     * permet de renvoyer sur la page d'emprunt des employés
     * @param model
     * @return
     */
    @RequestMapping(value = "/Emprunt", method = RequestMethod.GET)
    public String employe(Model model){
        utilisateurAuthentifie = new Abonne();
        pret = new Pret();
        ouvrage = new Ouvrage();
        ouvrageAModifie = new OuvrageAModifie();
        abonne = new Abonne();

        utilisateurAuthentifie.setPseudo("Régis");

        ajoutDansLeModel(model);

        return "Employe";
    }

    /**
     * permet de récupérer l'ouvrage et l'abonné et d'ajouter son prêt dans sa liste
     * @param model
     * @param ouvrage
     * @param abonne
     * @return
     */
    @RequestMapping(value = "/Emprunt", method = RequestMethod.POST)
    public String emprunt(Model model, @ModelAttribute("ouvrage") Ouvrage ouvrage, @ModelAttribute("abonne") Abonne abonne){

        //attibution de la date d'emprunt
        LocalDateTime localDateTime = LocalDateTime.now();

        if(!verificationOuvrageExistant(ouvrage) || !verificationAbonneExistant(abonne)){
            //ajout erreur
            message = "L'ouvrage ou l'abonné ne sont pas existant";

            //ajout dans le model
            model.addAttribute("message", message);
            ajoutDansLeModel(model);
            effacementDesChampsDeSaisies();

            return "Employe";
        }

        //récupération de l'ouvrage
        this.ouvrage = livresProxy.ouvrageSelonCodeBibliotheque(ouvrage.getCodeBibliotheque());

        //récupération de l'abonné
        this.abonne = livresProxy.recupererAbonneSelonNumeroAbonne(abonne.getNumeroAbonne());

        if(verificationDEmpruntIdentique(this.ouvrage)){
            //ajout erreur
            message = "L'ouvrage est déjà emprunté par cet abonné";

            //ajout dans le model
            model.addAttribute("message", message);
            ajoutDansLeModel(model);
            effacementDesChampsDeSaisies();

            return "Employe";
        }

        //alimentation de l'objet prêt
        pret.setDateDEmprunt(localDateTime);
        pret.setDateDeRestitution(localDateTime.plusMonths(1));
        pret.setStatutPriorite("4");
        pret.setProlongation(false);
        pret.setOuvragePret(this.ouvrage);
        pret.setAbonnePret(this.abonne);
        pret.setStatut("non prolongé");

        //reajustement du nombre d'exemplaires et sauvegarde de l'ouvrage
        if(this.ouvrage.getNombreExemplaires() > 0) {
            ouvrageAModifie.setNombreExemplaires(this.ouvrage.getNombreExemplaires() - 1);
            ouvrageAModifie.setIdOuvrage(this.ouvrage.getIdOuvrage());
            livresProxy.sauvegarderOuvrage(ouvrageAModifie);
        }else{
            //ajout erreur
            message = "Il n'y a plus d'ouvrage disponible";

            //ajout dans le model
            model.addAttribute("message", message);
            effacementDesChampsDeSaisies();
            ajoutDansLeModel(model);

            return "Employe";
        }

        //sauvegarder le prêt
        livresProxy.sauvegarderPret(pret);

        effacementDesChampsDeSaisies();

        //ajout message validation emprunt
        message = "Le livre '" + this.ouvrage.getLivre().getTitre()  + "' emprunté par '" + this.abonne.getPrenom()  + " " + this.abonne.getNom() + "' est enregistré";

        //ajout dans le model
        model.addAttribute("message", message);
        ajoutDansLeModel(model);

        return "Employe";
    }

    /**
     * permet la restitution du livre et d'affiché la page employé
     * @param model
     * @param ouvrage
     * @param abonne
     * @return
     */
    @RequestMapping(value = "/Restitution", method = RequestMethod.POST)
    public String restitution(Model model, @ModelAttribute("ouvrage") Ouvrage ouvrage, @ModelAttribute("abonne") Abonne abonne){
        AbonnePretOuvrage abonnePretOuvrage;
        List<Pret> pretList;
        isRestitue = false;

        if(!verificationOuvrageExistant(ouvrage) || !verificationAbonneExistant(abonne)){
            //ajout erreur
            messageRestitution = "L'ouvrage ou l'abonné ne sont pas existant";

            //ajout dans le model
            model.addAttribute("messageRestitution", messageRestitution);
            ajoutDansLeModel(model);
            effacementDesChampsDeSaisies();

            return "Employe";
        }

        //récupération de l'ouvrage
        this.ouvrage = livresProxy.ouvrageSelonCodeBibliotheque(ouvrage.getCodeBibliotheque());

        //récupération de l'abonné
        this.abonne = livresProxy.recupererAbonneSelonNumeroAbonne(abonne.getNumeroAbonne());

        //récupération de la liste des prêt
        abonnePretOuvrage = livresProxy.abonnePretSelonSonId(this.abonne.getIdAbonne());
        pretList = abonnePretOuvrage.getListePret();

        restitutionDeLOuvrage(pretList, ouvrage);

        if(isRestitue) {
            //ajout validation
            messageRestitution = "L'ouvrage '" + this.ouvrage.getLivre().getTitre() + "' emprunté par '" + this.abonne.getPrenom()  + " " + this.abonne.getNom() + "' a été restitué";

            //ajout dans le model
            model.addAttribute("messageRestitution", messageRestitution);
            ajoutDansLeModel(model);

        }else{
            //ajout erreur
            messageRestitution = "L'ouvrage n'a pas pu être restitué";

            //ajout dans le model
            model.addAttribute("messageRestitution", messageRestitution);
            ajoutDansLeModel(model);
            effacementDesChampsDeSaisies();

            return "Employe";
        }

        //reajustement du nombre d'exemplaires et sauvegarde de l'ouvrage
        ouvrageAModifie.setNombreExemplaires(this.ouvrage.getNombreExemplaires() + 1);
        ouvrageAModifie.setIdOuvrage(this.ouvrage.getIdOuvrage());
        livresProxy.sauvegarderOuvrage(ouvrageAModifie);

        effacementDesChampsDeSaisies();

        /*model.addAttribute("messageRestitution", messageRestitution);*/
        ajoutDansLeModel(model);

        return "Employe";
    }


    /**
     * permet d'empêcher le remplissage des champs automatiquement après validation
     */
    private void effacementDesChampsDeSaisies(){

        this.ouvrage.setCodeBibliotheque("");
        this.abonne.setNumeroAbonne("");
    }

    /**
     * permet de vérifier si l'ouvrage est existant
     * @param ouvrage
     * @return
     */
    public boolean verificationOuvrageExistant(Ouvrage ouvrage){
        //Récupération de la liste des ouvrages
        List<Ouvrage> ouvrageList;
        ouvrageList = livresProxy.listeDesOuvrages();

        for(Ouvrage ouvrageBoucle : ouvrageList){
            if(ouvrageBoucle.getCodeBibliotheque().equals(ouvrage.getCodeBibliotheque())){
               return true;
            }
        }
        return false;
    }

    /**
     * permet de vérifier si l'abonné est existant
     * @param abonne
     * @return
     */
    public boolean verificationAbonneExistant(Abonne abonne){
        //Récupération de la liste des abonnés
        List<Abonne> abonneList;
        abonneList = gestionUtilisateur.listeAbonnes();

        for(Abonne abonneBoucle : abonneList){
            if(abonneBoucle.getNumeroAbonne().equals(abonne.getNumeroAbonne())){
                return true;
            }
        }
        return false;
    }

    /**
     * permet de vérifier que l'abonné n'emprunte pas feux fois le même livre
     * @param ouvrage
     * @return
     */
    public boolean verificationDEmpruntIdentique(Ouvrage ouvrage) {
        AbonnePretOuvrage abonnePretOuvrage;
        List<Pret> pretList;

        //récupération de la liste des prêt
        abonnePretOuvrage = livresProxy.abonnePretSelonSonId(this.abonne.getIdAbonne());
        pretList = abonnePretOuvrage.getListePret();

        for(Pret pret : pretList){
            if(pret.getOuvragePret().getLivre().getIdLivre() == ouvrage.getLivre().getIdLivre() && pret.isRendu() == false){
                return true;
            }
        }
        return false;
    }

    /**
     * permet la restitution de l'ouvrage
     * @param pretList
     * @param ouvrage
     */
    public void restitutionDeLOuvrage(List<Pret> pretList, Ouvrage ouvrage){
        //attibution de la date de restitution
        LocalDateTime localDateTime = LocalDateTime.now();

        for(Pret pretDeLaListe : pretList){
            if(pretDeLaListe.getOuvragePret().getCodeBibliotheque().equals(ouvrage.getCodeBibliotheque()) && pretDeLaListe.isRendu() == false){
                //récupération du prêt à modifier
                PretAModifie pretAModifie = livresProxy.pretAModifieSelonSonId(pretDeLaListe.getIdPret());

                //modification du prêt
                pretAModifie.setStatut("Rendu");
                pretAModifie.setStatutPriorite("5");
                pretAModifie.setProlongation(true);
                pretAModifie.setRendu(true);
                pretAModifie.setDateDeRestitution(localDateTime);
                pretAModifie.setDateDEmprunt(pretDeLaListe.getDateDEmprunt());

                //sauvegarder le prêt
                livresProxy.sauvegardePretAModifie(pretAModifie);

                isRestitue = true;
            }
        }
    }

    /**
     * permet d'ajouter les different models envoyés à la page html
     * @param model
     */
    public void ajoutDansLeModel(Model model){

        model.addAttribute("ouvrage", this.ouvrage);
        model.addAttribute("abonne", this.abonne);
        model.addAttribute("codeRole", this.codeRole);
        model.addAttribute("utilisateurAuthentifie", this.utilisateurAuthentifie);
    }

}
