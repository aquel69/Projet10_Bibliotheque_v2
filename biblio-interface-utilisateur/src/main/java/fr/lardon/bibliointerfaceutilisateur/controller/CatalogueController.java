package fr.lardon.bibliointerfaceutilisateur.controller;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Abonne;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Role;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.*;
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
    private AbonnePretOuvrage abonnePret;
    private Role role = new Role();
    private Abonne utilisateurAuthentifie = new Abonne();
    private int index = 0;
    private int codeRole = 0;
    private boolean isRecherche = false;
    private List<Livre> listeLivresPagination = null;
    private String recherche = null;
    private double nbTotalPages = 0;

    @Autowired
    private MicroserviceLivresProxy livresProxy;

    /**
     * permet de renvoyer sur la page accueil et de récupérer les différentes listes de livres
     * @param model
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String accueil(Model model){
        livres = new ArrayList<>();
        Ouvrage ouvrage = new Ouvrage();

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

        //ajout dans le model
        model.addAttribute("ouvrages", ouvrages);
        model.addAttribute("abonnePret", abonnePret);
        model.addAttribute("utilisateurAuthentifie", utilisateurAuthentifie);
        model.addAttribute("codeRole", codeRole);
        model.addAttribute("detailLivre", livre);
        model.addAttribute("auteurs",auteurs);

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

}
