package fr.lardon.bibliocataloguelivres.controller;

import fr.lardon.bibliocataloguelivres.dao.*;
import fr.lardon.bibliocataloguelivres.model.*;
import fr.lardon.bibliocataloguelivres.services.ServiceAbonnePretOuvrage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * classe regroupant les méthodes permettant de retourner les objets en fonction des données souhaités pour la gestion des livres, ouvrages et prêts
 */
@RestController
public class LivreController {

    @Autowired
    private DaoLivre daoLivre;

    @Autowired
    private DaoOuvrage daoOuvrage;

    @Autowired
    private DaoPret daoPret;

    @Autowired
    private DaoAbonnePret daoAbonnePret;

    @Autowired
    private DaoAbonne daoAbonne;

    @Autowired
    private DaoPretAModifie daoPretAModifie;

    @Autowired
    private DaoOuvrageAModifie daoOuvrageAModifie;

    @Autowired
    private daoLivreTop daoLivreTop;

    @Autowired
    private ServiceAbonnePretOuvrage serviceAbonnePretOuvrage;

    /**
     * retourne la liste des ouvrages en fonction de leurs nombre de fois qu'ils ont été empruntés
     * @return
     */
    @GetMapping(value = "/Top")
    public List<Livre> listeOuvrageSelonNombreDEmprunt(){
        List<LivreTop> livresTop = daoLivreTop.listeNombreDePretParLivre();
        List<Livre> livres = new ArrayList<>();
        Livre livre;

        for(LivreTop livreTop : livresTop){
            livre = recupererUnLivre(livreTop.getIdLivre());
            livres.add(livre);
        }

        return livres;
    }

    /**
     * retourne la liste de tous les ouvrages
     * @return
     */
    @GetMapping(value = "/ListeOuvrage")
    public List<Ouvrage> listeDesOuvrages(){
        List<Ouvrage> listeOuvrage = daoOuvrage.recupererTousLesOuvrages();

        return listeOuvrage;
    }

    /**
     * retourne la liste de tous les livres
     * @return
     */
    @GetMapping(value = "/Livres")
    public List<Livre> listeLivre(){
        List<Livre> livres = daoLivre.findAll();

        return livres;
    }

    /**
     * retourne la liste des livres sous forme de pagination
     * @param noPage
     * @param nbLivresParPage
     * @return
     */
    @GetMapping(value="/Catalogue/{noPage}/{nbLivresParPage}")
    public List<Livre> catalogueListeLivrePagination(@PathVariable int noPage, @PathVariable int nbLivresParPage){
        Pageable pageable = PageRequest.of(noPage, nbLivresParPage);
        List<Livre> listeLivrePagination = daoLivre.listeLivrePagination(pageable);

        return listeLivrePagination;
    }

    /**
     * retourne la liste des livres sous forme de pagination en fonction de la recherche
     * @param noPage
     * @param nbLivresParPage
     * @param recherche
     * @return
     */
    @GetMapping(value="/Recherche/{noPage}/{nbLivresParPage}/{recherche}")
    public List<Livre> catalogueListeLivrePaginationRecherche(@PathVariable int noPage, @PathVariable int nbLivresParPage, @PathVariable String recherche){
        Pageable pageable = PageRequest.of(noPage, nbLivresParPage);
        List<Livre> listeLivrePagination = daoLivre.listeLivreRecherchePagination(recherche, recherche,  pageable);

        return listeLivrePagination;
    }

    /**
     * retourne la liste des livres en fonction de la recherche
     * @param recherche
     * @return
     */
    @GetMapping(value="/Recherche/{recherche}")
    public List<Livre> catalogueListeLivrePaginationRecherche(@PathVariable String recherche){
        List<Livre> listeLivrePagination = daoLivre.listeLivreRecherchePagination(recherche, recherche);

        return listeLivrePagination;
    }

    /**
     * récupérer un livre en fonction de son id
     * @param id
     * @return
     */
    @GetMapping( value = "/Livres/{id}")
    public Livre recupererUnLivre(@PathVariable int id) {
        Livre livre = daoLivre.findById(id).get();

        return livre;
    }

    /**
     * récupérer un ouvrage en fonction de l'id du livre
     * @param id
     * @return
     */
    @GetMapping( value = "/OuvrageSelonIdLivre/{id}")
    public Ouvrage recupererUnOuvrageSelonIdLivre(@PathVariable int id) {
        Ouvrage ouvrage = daoOuvrage.findById(id).get();

        return ouvrage;
    }

    /**
     * retourne la liste des livres en fonction de leurs anciennetés
     * @return
     */
    @GetMapping(value = "/Livres/Nouveau")
    public List<Ouvrage> listeOuvrageNouveaute(){
        List<Ouvrage> ouvrages = daoOuvrage.trouverDerniereOuvrage();

        return ouvrages;
    }

    /**
     * ajouter un prêt dans la base de données
     * @param pret
     */
    @PostMapping(value = "/AjouterPret")
    public void ajouterPret(@RequestBody Pret pret) {
        daoPret.save(pret);
    }

    /**
     * ajouter un prêt dans la base de données
     * @param ouvrageAModifie
     */
    @PostMapping(value = "/SauvegarderOuvrage")
    public void sauvegarderOuvrage(@RequestBody OuvrageAModifie ouvrageAModifie) {
        daoOuvrageAModifie.save(ouvrageAModifie);
    }

    /**
     * renvoi l'ouvrage selon son code bibliothèque
     * @param codeBibliotheque
     * @return
     */
    @GetMapping(value = "/Ouvrage/{codeBibliotheque}")
    public Ouvrage ouvrageSelonCodeBibliotheque(@PathVariable String codeBibliotheque){
        Ouvrage ouvrage = daoOuvrage.findByCodeBibliotheque(codeBibliotheque);

        return ouvrage;
    }

    /**
     * ajouter un prêt pour l'abonné dans la base de données
     * @param pret
     */
    @PostMapping(value = "/SauvegarderPret}")
    public void sauvegarderPret(@RequestBody Pret pret) {
        daoPret.save(pret);
    }

    /**
     * ajouter un prêt pour l'abonné dans la base de données
     * @param abonnePret
     */
    @PostMapping(value = "/SauvegarderAbonne")
    public void sauvegarderAbonnePret(@RequestBody AbonnePret abonnePret) {
        daoAbonnePret.save(abonnePret);
    }

    /**
     * récupérer un abonné dans la base de données selon son numéro d'abonné
     * @param numeroAbonne
     * @return
     */
    @GetMapping(value = "/AbonnePret/{numeroAbonne}")
    public Abonne recupererAbonneSelonNumeroAbonne(@PathVariable String numeroAbonne) {
        Abonne abonne = daoAbonne.findByNumeroAbonne(numeroAbonne);

        return abonne;
    }

    /**
     * retourne une liste de prêts en fonction de l'id de l'abonné
     * @param id
     * @return
     */
    @GetMapping(value = "/PretsSelonAbonne/{id}")
    public List<Pret> listeDesPretsSelonAbonne(@PathVariable int id){
        List<Pret> prets = daoPret.listePretSelonAbonne(id);

        return prets;
    }

    /**
     * retourne un prêt selon son id
     * @param id
     * @return
     */
    @GetMapping(value = "/PretSelonSonId/{id}")
    public Pret pretSelonSonId(@PathVariable int id){
        Pret pret = daoPret.findById(id).get();

        return pret;
    }

    /**
     * retourne et sauvegarde une liste de prêts d'un abonné selon son id
     * @param id
     * @return
     */
    @GetMapping(value = "/AbonnePretSelonId/{id}")
    public AbonnePretOuvrage abonnePretSelonSonId(@PathVariable int id){
        List<Pret> pretList;

        AbonnePretOuvrage abonnePret = serviceAbonnePretOuvrage.getAbonnePretOuvrage(id);
        pretList = abonnePret.getListePret();

        //sauvegarde des prêts avec leurs nouveaux statuts
        for(Pret pret : pretList){
            daoPret.save(pret);
        }

        //rappel de la liste des prêts pour la mise en ordre selon priorité
        pretList = listeDesPretsSelonAbonne(abonnePret.getAbonne().getIdAbonne());
        abonnePret.setListePret(pretList);

        return abonnePret;
    }

    /**
     * retourne un abonné selon son id
     * @param id
     * @return
     */
    @GetMapping(value = "/AbonneSelonSonId/{id}")
    public Abonne abonneSelonSonId(@PathVariable int id){
        Abonne abonne = daoAbonne.findById(id).get();

        return abonne;
    }

    /**
     * sauvegarde un prêt que l'on a modifié
     * @param pretAModifie
     */
    @PostMapping(value = "/SauvegarderPretAModifie")
    public void sauvegardePretAModifie(@RequestBody PretAModifie pretAModifie){
        daoPretAModifie.save(pretAModifie);
    }

    /**
     * retourne un prêt modifié selon son id
     * @param id
     * @return
     */
    @GetMapping(value = "/PretAModifieSelonSonId/{id}")
    public PretAModifie pretAModifieSelonSonId(@PathVariable int id){
        PretAModifie pretAModifie = daoPretAModifie.findById(id).get();

        return pretAModifie;
    }

    /**
     * retourne la liste des ouvrages selon l'id du livre
     * @param id
     * @return
     */
    @GetMapping(value = "/OuvragesSelonIdLivre/{id}")
    public List<Ouvrage> listeDesOuvragesSelonIdLivre(@PathVariable int id){
        List<Ouvrage> ouvrages;

        ouvrages = daoOuvrage.listeOuvragesSelonIdLivre(id);

        return ouvrages;
    }
}
