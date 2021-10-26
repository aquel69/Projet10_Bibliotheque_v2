package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Abonne;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Bibliotheque;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Role;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.*;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceGestionUtilisateur;
import fr.lardon.bibliointerfaceutilisateur.proxies.MicroserviceLivresProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
/**
 * classe regroupant les méthodes permettant de traiter, envoyer et récupérer les données avec les page html de l'accueil et du catalogue
 */
public class CatalogueController {
    private List<Ouvrage> ouvragesNouveaute;
    private ArrayList<Livre> livreTop;
    private List<Ouvrage> ouvragesPremierePartie = new ArrayList<>();
    private List<Ouvrage> ouvragesSecondePartie = new ArrayList<>();
    private List<Livre> livres;
    private List<Ouvrage> ouvrages;
    private List<Ouvrage> listeTotalOuvrages;
    private List<Pret> listeTotalPretNonRendu;
    private AbonnePretOuvrage abonnePret;
    private List<AbonneOuvrageReservation> abonneReservation;
    private Role role = new Role();
    private Abonne utilisateurAuthentifie = new Abonne();
    private int index = 0;
    private int codeRole = 0;
    private boolean isRecherche = false;
    private List<Livre> listeLivresPagination = null;
    private String recherche = null;
    private double nbTotalPages = 0;
    private Pret pretDateRetourBruce = null;
    private Pret pretDateRetourRobin = null;
    private Pret pretDateRetourAlfred = null;
    private Pret pret;
    private OuvrageAModifie ouvrageAModifie;
    private int positionFileAbonneReservation;

    @Autowired
    private MicroserviceLivresProxy livresProxy;

    @Autowired
    private MicroserviceGestionUtilisateur utilisateurProxy;

    /**
     * permet de renvoyer sur la page accueil et de récupérer les différentes listes de livres
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String accueil(Model model){
        livres = new ArrayList<>();
        abonneReservation = new ArrayList<>();
        listeTotalOuvrages = new ArrayList<>();
        listeTotalPretNonRendu = new ArrayList<>();
        pret = new Pret();
        positionFileAbonneReservation = 0;

        //mise à jour des dates de retour de prêt
        listeTotalOuvrages = livresProxy.listeDesOuvrages();
        listeTotalPretNonRendu = livresProxy.listeDesPretsNonRendu();

        ouvrageAModifie = new OuvrageAModifie();

        for (Ouvrage ouvrageBoucle : listeTotalOuvrages){
            for (Pret pretBoucleInterieur : listeTotalPretNonRendu) {
                if (ouvrageBoucle.getIdOuvrage() == pretBoucleInterieur.getOuvragePret().getIdOuvrage()){
                    pret = livresProxy.dernierPretSelonOuvrage(ouvrageBoucle.getIdOuvrage());
                    ouvrageAModifie.setIdOuvrage(ouvrageBoucle.getIdOuvrage());
                    ouvrageAModifie.setDateDeRetourPrevue(pret.getDateDeRestitution());
                    livresProxy.modifierOuvrage(ouvrageAModifie);
                }
            }
        }

        //récupération du message
        String message = (String) model.getAttribute("message");

        //récupération du code role
        if(model.getAttribute("codeRole") != null) {
            codeRole = (int) model.getAttribute("codeRole");
        }
        //récupération des prêt de l'abonné pour la gestion de ses emprunts
        if(model.getAttribute("utilisateurAuthentifie") != null){
            utilisateurAuthentifie = (Abonne) model.getAttribute("utilisateurAuthentifie");
            abonnePret = livresProxy.abonnePretSelonSonId(utilisateurAuthentifie.getIdAbonne());
            abonneReservation = livresProxy.listeReservationSelonAbonne(utilisateurAuthentifie.getIdAbonne());

        }

        for (AbonneOuvrageReservation abonneOuvrageReservation : abonneReservation){
            System.out.println(abonneOuvrageReservation);
        }


        ouvrages = livresProxy.listeDesOuvrages();

        livres = livresProxy.listeOuvrageSelonNombreDEmprunt();

        /*livres = livresProxy.topLivre();*/
        livreTop = new ArrayList<>(livres.subList(0, 10));

        //récupération des nouveautés
        ouvragesNouveaute = livresProxy.listeOuvrageNouveaute();

        //séparation en deux listes pour l'affichage
        for(Ouvrage separationOuvrage : ouvragesNouveaute){
            if(index < 3)
                ouvragesPremierePartie.add(separationOuvrage);
            else if(index < 6)
                ouvragesSecondePartie.add(separationOuvrage);

            index++;
        }

        //ajout dans le model
        model.addAttribute("message", message);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("abonnePret", abonnePret);
        model.addAttribute("abonneReservation", abonneReservation);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("livres", livreTop);
        model.addAttribute("ouvragesPartieUne", ouvragesPremierePartie);
        model.addAttribute("ouvragesPartieDeux", ouvragesSecondePartie);

        return "Accueil";
    }

    /**
     * permet de récupérer les données pour qu'un ouvrage soit prolongé et renvoie à l'accueil avec un message
     * @param model
     * @param pretProlongation
     * @return
     */
    //PROLONGATION : Méthode post qui récupère l'ouvrage à prolongé et donne le message correspondant
    @RequestMapping(value = "/Prolongation", method = RequestMethod.POST)
    public String accueilPost(Model model,@RequestParam int pretProlongation){
        String message;
        Pret pret;
        PretAModifie pretAModifie = new PretAModifie();

        //récupération du prêt en fonction de l'id
        pret = livresProxy.pretSelonSonId(pretProlongation);

        //modification du prêt
        pretAModifie.setIdPret(pret.getIdPret());
        pretAModifie.setRendu(false);
        pretAModifie.setStatut("Déjà Prolongé");
        pretAModifie.setStatutPriorite("3");
        pretAModifie.setDateDEmprunt(pret.getDateDEmprunt());
        pretAModifie.setProlongation(true);
        pretAModifie.setDateDeRestitution(pret.getDateDeRestitution().plusMonths(1));

        //sauvegarde du prêt
        livresProxy.sauvegardePretAModifie(pretAModifie);

        //récupération de l'abonné après les modifications effectuées
        abonnePret = livresProxy.abonnePretSelonSonId(utilisateurAuthentifie.getIdAbonne());

        //PROLONGATION : modification du message
        //ajout du message
        message = "Le livre : " + pret.getOuvragePret().getLivre().getTitre() + " est prolongé d'un mois";

        //ajout dans le model
        model.addAttribute("message", message);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("abonnePretModal", abonnePret);
        model.addAttribute("abonneReservation", abonneReservation);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("livres", livreTop);
        model.addAttribute("ouvragesPartieUne", ouvragesPremierePartie);
        model.addAttribute("ouvragesPartieDeux", ouvragesSecondePartie);
        accueil(model);

        return "Accueil";
    }

    /**
     * Permet de supprimer une réservation
     * @param model
     * @param supprimerReservation
     * @return
     */
    @RequestMapping(value = "/Reservation", method = RequestMethod.POST)
    public String reservation(Model model,@RequestParam(required=true,defaultValue="0") int supprimerReservation){
        String message;
        AbonneOuvrageReservation abonneSupprime = new AbonneOuvrageReservation();
        List<AbonneOuvrageReservation> abonneOuvrageReservationList = new ArrayList<>();
        AbonneOuvrageReservationAModifie abonneModifie = new AbonneOuvrageReservationAModifie();
        Bibliotheque bibliotheque;

        if(supprimerReservation == 0) {
            message = "Vous n'avez pas sélectionné d'ouvrage";
        }else {
            //-----réajustement des positions dans la file d'attente
            //récupération de l'abonné qui vient d'être supprimé
            abonneSupprime = livresProxy.AbonneOuvrageReservationSelonId(supprimerReservation);

            //à partir de l'abonné supprimé, on récupère la liste des des réservations restantes
            abonneOuvrageReservationList = livresProxy.listeReservationSelonOuvrage
                    (abonneSupprime.getOuvrageReservation().getIdOuvrage()
                            , abonneSupprime.getOuvrageReservation().getNombreExemplairesTotal() * 2);

            if (abonneOuvrageReservationList.size() != 0) {
               for (AbonneOuvrageReservation abonneOuvrageReservationBoucle : abonneOuvrageReservationList) {
                    abonneModifie.setIdAbonneOuvrageReservation(abonneOuvrageReservationBoucle.getIdAbonneOuvrageReservation());
                    abonneModifie.setPositionAttente(abonneOuvrageReservationBoucle.getPositionAttente() - 1);

                    livresProxy.modifierAbonneReservation(abonneModifie);
               }

                //ajout du dernier numéo d'ouvrage dans la table bibliotheque de la base de donnée
                bibliotheque = livresProxy.bibliothequeSelonOuvrage(abonneOuvrageReservationList.get(0).getOuvrageReservation().getIdOuvrage());
                bibliotheque.setDernierOuvrageRestitue(abonneOuvrageReservationList.get(0).getOuvrageReservation().getIdOuvrage());
                bibliotheque.setNouveauDernierOuvrage(true);
                livresProxy.modifierBibliotheque(bibliotheque);
           }

            //réservation supprimée
            livresProxy.supprimerReservation(supprimerReservation);



            //ajout du message
            message = "La réservation est supprimé";
        }

        //ajout dans le model
        model.addAttribute("message", message);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("abonnePretModal", abonnePret);
        model.addAttribute("abonneReservation", abonneReservation);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("livres", livreTop);
        model.addAttribute("ouvragesPartieUne", ouvragesPremierePartie);
        model.addAttribute("ouvragesPartieDeux", ouvragesSecondePartie);
        accueil(model);

        return "Accueil";
    }

    /**
     * permet de renvoyer sur la page détail du livre et de récupérer l'id correspondant au livre
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/Detail/{id}", method = RequestMethod.GET)
    public String detailLivre(@PathVariable int id, Model model){
        //récuperation du livre en fonction de son id
        Livre livre = livresProxy.recupererUnLivre(id);
        //liste des auteurs correspondant au livre
        List<Auteur> auteurs = livre.getAuteurs();

        ouvrages = livresProxy.listeDesOuvragesSelonIdLivre(id);

        //Récuperation des prêts et des réservations pour affichage dans page détail
        //---Récupération de la date du prochain retour de l'ouvrage
        pretDateRetourBruce = livresProxy.dernierPretSelonOuvrage(ouvrages.get(1).getIdOuvrage());
        pretDateRetourRobin = livresProxy.dernierPretSelonOuvrage(ouvrages.get(2).getIdOuvrage());
        pretDateRetourAlfred = livresProxy.dernierPretSelonOuvrage(ouvrages.get(0).getIdOuvrage());

        //---Récupération du nombre de personnes dans la liste d'attente de réservation
        List<AbonneOuvrageReservation> listReservationSelonOuvrageBruce = livresProxy.listeReservationSelonOuvrage
                (ouvrages.get(1).getIdOuvrage(),ouvrages.get(1).getNombreExemplairesTotal() * 2);
        List<AbonneOuvrageReservation> listReservationSelonOuvrageRobin = livresProxy.listeReservationSelonOuvrage
                (ouvrages.get(2).getIdOuvrage(),ouvrages.get(2).getNombreExemplairesTotal() * 2);
        List<AbonneOuvrageReservation> listReservationSelonOuvrageAlfred = livresProxy.listeReservationSelonOuvrage
                (ouvrages.get(0).getIdOuvrage(),ouvrages.get(0).getNombreExemplairesTotal() * 2);


        //ajout dans le model
        model.addAttribute("ouvrages", ouvrages);
        model.addAttribute("abonnePret", abonnePret);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("abonneReservation", abonneReservation);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("detailLivre", livre);
        model.addAttribute("auteurs",auteurs);
        model.addAttribute("pretRetourBruce",pretDateRetourBruce);
        model.addAttribute("pretRetourRobin",pretDateRetourRobin);
        model.addAttribute("pretRetourAlfred",pretDateRetourAlfred);
        model.addAttribute("listeReservationBruce",listReservationSelonOuvrageBruce);
        model.addAttribute("listeReservationRobin",listReservationSelonOuvrageRobin);
        model.addAttribute("listeReservationAlfred",listReservationSelonOuvrageAlfred);

        return "DetailLivre";
    }

    /**
     * permet de renvoyer sur la page détail du livre et de récupérer l'id correspondant au livre
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/DetailPost/{id}", method = RequestMethod.POST)
    public String detailLivrePost(Model model, @PathVariable int id, @RequestParam(value="action") String action){
        AbonneOuvrageReservation abonneOuvrageReservation = new AbonneOuvrageReservation();
        boolean dejaReserve = false;
        String message;
        AbonneOuvrageReservationAModifie abonneOuvrageReservationAModifie = new AbonneOuvrageReservationAModifie();
        AbonneOuvrageReservation dernierAbonneOuvrageReservation;

        //récuperation du livre en fonction de son id
        Livre livre = livresProxy.recupererUnLivre(id);
        //liste des auteurs correspondant au livre
        List<Auteur> auteurs = livre.getAuteurs();

        ouvrages = livresProxy.listeDesOuvragesSelonIdLivre(id);

        //Récuperation des prêts et des réservations pour affichage dans page détail
        //---Récupération de la date du prochain retour de l'ouvrage
        Pret pretDateRetourBruce = livresProxy.dernierPretSelonOuvrage(ouvrages.get(1).getIdOuvrage());
        Pret pretDateRetourRobin = livresProxy.dernierPretSelonOuvrage(ouvrages.get(2).getIdOuvrage());
        Pret pretDateRetourAlfred = livresProxy.dernierPretSelonOuvrage(ouvrages.get(0).getIdOuvrage());

        //---Récupération du nombre de personnes dans la liste d'attente de réservation
        List<AbonneOuvrageReservation> listReservationSelonOuvrageBruce = livresProxy.listeReservationSelonOuvrage
                (ouvrages.get(1).getIdOuvrage(),ouvrages.get(1).getNombreExemplairesTotal() * 2);
        List<AbonneOuvrageReservation> listReservationSelonOuvrageRobin = livresProxy.listeReservationSelonOuvrage
                (ouvrages.get(2).getIdOuvrage(),ouvrages.get(2).getNombreExemplairesTotal() * 2);
        List<AbonneOuvrageReservation> listReservationSelonOuvrageAlfred = livresProxy.listeReservationSelonOuvrage
                (ouvrages.get(0).getIdOuvrage(),ouvrages.get(0).getNombreExemplairesTotal() * 2);

        //----ajout de la réservation----
        //récuperation de l'abonné
        Abonne abonne = utilisateurProxy.recupererAbonne(utilisateurAuthentifie.getIdAbonne());
        List<Pret> listeDesPretsAbonne = livresProxy.listeDesPretsSelonAbonne(utilisateurAuthentifie.getIdAbonne());
        Ouvrage ouvrageReservation = livresProxy.ouvragesSelonIdLivreEtSiret(id, action);

        abonneOuvrageReservation.setAbonneReservation(abonne);
        abonneOuvrageReservation.setOuvrageReservation(ouvrageReservation);
        abonneOuvrageReservation.setDateDeReservation(LocalDateTime.now());
        abonneOuvrageReservation.setOuvrageRecupere(false);

        Ouvrage ouvrageSelectionne = livresProxy.ouvragesSelonIdLivreEtSiret(id, action);
        List<AbonneOuvrageReservation> listeOuvrageReserveAbonne = livresProxy.listeReservationSelonAbonne
                (utilisateurAuthentifie.getIdAbonne());
        List<AbonneOuvrageReservation> listReservationSelonOuvrage = livresProxy.listeReservationSelonOuvrage
                (livresProxy.ouvragesSelonIdLivreEtSiret(id, action).getIdOuvrage(),ouvrageSelectionne.getNombreExemplairesTotal() * 2);

        //l'ouvrage est il déjà réservé
        dejaReserve = isDejaReserve(dejaReserve, ouvrageSelectionne, listeOuvrageReserveAbonne);

        //l'ouvrage est il déjà emprunté
        dejaReserve = isDejaEmprunte(dejaReserve, listeDesPretsAbonne, ouvrageSelectionne);

        if (dejaReserve){
            message = "L'ouvrage est déjà réservé ou en votre possession";
        }else if (listReservationSelonOuvrage.size() >= ouvrageSelectionne.getNombreExemplairesTotal() * 2) {
            message = "Il y a trop d'abonné en attente de cet ouvrage";
        }else{
            livresProxy.ajouterReservation(abonneOuvrageReservation);

            //ajout de la position
            int dernierePosition = livresProxy.recuperationDernierIdReservation();

            dernierAbonneOuvrageReservation = livresProxy.AbonneOuvrageReservationSelonId(dernierePosition);
            positionFileAbonneReservation = livresProxy.positionAbonneReservationSelonOuvrage(dernierAbonneOuvrageReservation.getOuvrageReservation().getIdOuvrage()
                    , ouvrageReservation.getNombreExemplairesTotal() * 2, abonne.getIdAbonne());

            abonneOuvrageReservationAModifie.setIdAbonneOuvrageReservation(dernierAbonneOuvrageReservation.getIdAbonneOuvrageReservation());
            abonneOuvrageReservationAModifie.setPositionAttente(positionFileAbonneReservation);

            livresProxy.modifierAbonneReservation(abonneOuvrageReservationAModifie);
            message = "L'ouvrage a été réservé";

            model.addAttribute("message", message);
            model.addAttribute("codeRole", codeRole);
            model.addAttribute("abonnePretModal", abonnePret);
            model.addAttribute("abonneReservation", abonneReservation);
            model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
            model.addAttribute("livres", livreTop);
            model.addAttribute("ouvragesPartieUne", ouvragesPremierePartie);
            model.addAttribute("ouvragesPartieDeux", ouvragesSecondePartie);
            accueil(model);

            return "Accueil";
        }

        //ajout dans le model
        model.addAttribute("message", message);
        model.addAttribute("ouvrages", ouvrages);
        model.addAttribute("abonnePret", abonnePret);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("abonneReservation", abonneReservation);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("detailLivre", livre);
        model.addAttribute("auteurs",auteurs);
        model.addAttribute("pretRetourBruce",pretDateRetourBruce);
        model.addAttribute("pretRetourRobin",pretDateRetourRobin);
        model.addAttribute("pretRetourAlfred",pretDateRetourAlfred);
        model.addAttribute("listeReservationBruce",listReservationSelonOuvrageBruce);
        model.addAttribute("listeReservationRobin",listReservationSelonOuvrageRobin);
        model.addAttribute("listeReservationAlfred",listReservationSelonOuvrageAlfred);

        return "DetailLivre";
    }


    /**
     * permet de renvoyer sur la page du catalogue et de récupérer la liste des livres
     * @param noPage
     * @param nbLivresParPage
     * @param accesCatalogue
     * @param model
     * @return
     */
    @RequestMapping(value="/Catalogue/{noPage}/{nbLivresParPage}/{accesCatalogue}", method = RequestMethod.GET)
    public String listeLivrePagination(@PathVariable int noPage, @PathVariable int nbLivresParPage, @PathVariable boolean accesCatalogue, Model model){
        if(isRecherche == false || accesCatalogue == true) {
            listeLivresPagination = new ArrayList<>();
            List<Livre> listeTotaleDesLivres = livresProxy.listeLivre();

            //calcule du nombre de total de pages
            nbTotalPages = (double) listeTotaleDesLivres.size() / nbLivresParPage;
            nbTotalPages = Math.ceil(nbTotalPages);

            //récupération des livres en fonction du numéro de la page
            listeLivresPagination = livresProxy.catalogueListeLivrePagination(noPage, nbLivresParPage);
            isRecherche = false;
        }else{
            if(this.recherche != null) recuperationDesLivresRecherche(noPage, nbLivresParPage);
        }

        //ajout dans le model
        model.addAttribute("abonnePret", abonnePret);
        model.addAttribute("abonneReservation", abonneReservation);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("listeLivresPagination", listeLivresPagination);
        model.addAttribute("noPage", noPage);
        model.addAttribute("nbTotalPages", nbTotalPages);
        model.addAttribute("nbLivresParPage", nbLivresParPage);

        return "Catalogue";
    }

    /**
     * permet de renvoyer sur la page du catalogue et de récupérer la liste des livres correspondant à la recherche
     * @param model
     * @param noPage
     * @param nbLivresParPage
     * @param recherche
     * @return
     */
    @RequestMapping(value = "/Catalogue/{noPage}/{nbLivresParPage}", method = RequestMethod.POST)
    public String recherche(Model model, @PathVariable int noPage, @PathVariable int nbLivresParPage, @RequestParam String recherche){

        if(!recherche.isEmpty()) {
            //savoir que nous sommes en recherche et récupérons la recherche dans une variable de classe
            isRecherche = true;
            this.recherche = recherche;

            //nous effaçons la liste de livre du catalogue afin de la remplacer par celle de la recherche
            listeLivresPagination.clear();

            recuperationDesLivresRecherche(noPage, nbLivresParPage);
        }

        //ajout dans le model
        model.addAttribute("abonnePret", abonnePret);
        model.addAttribute("abonneReservation", abonneReservation);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("listeLivresPagination", listeLivresPagination);
        model.addAttribute("noPage", noPage);
        model.addAttribute("nbTotalPages", nbTotalPages);
        model.addAttribute("nbLivresParPage", nbLivresParPage);

        return "Catalogue";
    }


    /**
     * récupérer les livres recherchés et calcul le nombre de pages pour la pagination
     * @param noPage
     * @param nbLivresParPage
     */
    public void recuperationDesLivresRecherche(int noPage, int nbLivresParPage){
        //récuperation des livres en fonction du numéro de la page
        listeLivresPagination = livresProxy.catalogueListeLivrePaginationRecherche(noPage, nbLivresParPage,recherche);

        //récuperation de la liste des livres correspondant à la recherche
        List<Livre> listeLivresRecherche = livresProxy.catalogueListeLivrePaginationRecherche(recherche);

        //calcule du nombre de total de pages
        nbTotalPages = (double) listeLivresRecherche.size() / nbLivresParPage;
        nbTotalPages = Math.ceil(nbTotalPages);
    }


    private boolean isDejaEmprunte(boolean dejaReserve, List<Pret> listeDesPretsAbonne, Ouvrage ouvrageSelectionne) {
        for (Pret abonnePret : listeDesPretsAbonne){
            if (abonnePret.getOuvragePret().getIdOuvrage() == ouvrageSelectionne.getIdOuvrage() && abonnePret.isRendu() == false) {
                dejaReserve = true;
                System.out.println("ouvrage déjà en votre possesion");
                break;
            }
        }
        return dejaReserve;
    }

    private boolean isDejaReserve(boolean dejaReserve, Ouvrage ouvrageSelectionne, List<AbonneOuvrageReservation> listeOuvrageAbonne) {
        for (AbonneOuvrageReservation abonneReservation : listeOuvrageAbonne){
            if (abonneReservation.getOuvrageReservation().getIdOuvrage() == ouvrageSelectionne.getIdOuvrage()) {
                dejaReserve = true;
                break;
            }
        }
        return dejaReserve;
    }

}
