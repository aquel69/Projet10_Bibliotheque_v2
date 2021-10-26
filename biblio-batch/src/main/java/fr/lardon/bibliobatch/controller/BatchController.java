package fr.lardon.bibliobatch.controller;

import fr.lardon.bibliobatch.dao.*;
import fr.lardon.bibliobatch.model.*;
import fr.lardon.bibliobatch.services.ServiceAbonnePretOuvrage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
/**
 * classe regroupant les méthodes permettant de retourner les objets en fonction des données souhaités pour la gestion des batchs
 */
public class BatchController {

    @Autowired
    private DaoPret daoPret;

    @Autowired
    private DaoOuvrage daoOuvrage;

    @Autowired
    private ServiceAbonnePretOuvrage serviceAbonnePretOuvrage;

    @Autowired
    private DaoAbonneOuvrageReservation daoAbonneOuvrageReservation;

    @Autowired
    private DaoBibliotheque daoBibliotheque;

    @Autowired
    private DaoAbonne daoAbonne;

    @Autowired
    private DaoAbonneOuvrageReservationAModifie daoAbonneOuvrageReservationAModifie;

    /**
     * renvoi l'abonnéPret selon son id
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
        abonnePret.setListePret(pretList);

        return abonnePret;
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
     * liste de toutes les réservations
     */
    @GetMapping(value = "/ListeReservationTotale")
    public List<AbonneOuvrageReservation> listeReservationTotale(){
        List<AbonneOuvrageReservation> list = daoAbonneOuvrageReservation.listeReservationTotale();

        return list;
    }

    /**
     * liste des réservations selon l'ouvrage
     * @param id de l'ouvrage
     * @return la liste des réservations selon l'ouvrage
     */
    @GetMapping(value = "/ListeReservationSelonOuvrage/{id}/{nombreLimiteReservation}")
    public List<AbonneOuvrageReservation> listeReservationSelonOuvrage(@PathVariable int id, @PathVariable int nombreLimiteReservation){
        List<AbonneOuvrageReservation> list = daoAbonneOuvrageReservation.listeReservationSelonOuvrage(id, nombreLimiteReservation);

        return list;
    }

    /**
     * ouvrage par son id
     * @param id
     * @return
     */
    @GetMapping(value="/OuvrageParSonId")
    public Ouvrage ouvrageParSonId(int id){
        Ouvrage ouvrage = daoOuvrage.findById(id);

        return ouvrage;
    }

    @GetMapping(value="/BibliothequeParSiret")
    public Bibliotheque bibliothequeParSiret(String siret){
        Bibliotheque bibliotheque = daoBibliotheque.findByNumeroSiret(siret);

        return bibliotheque;
    }

    /*SELECT * FROM bibliotheque*/
    @GetMapping(value="/BibliothequeListe")
    public List<Bibliotheque> BibliothequeListe(){
        List<Bibliotheque> listBibliotheque = daoBibliotheque.findAll();

        return listBibliotheque;
    }

    /**
     * Modifier une bibliotheque
     * @param bibliotheque
     */
    @PutMapping(value="/ModifierBibliotheque")
    public void modifierBibliotheque(@RequestBody Bibliotheque bibliotheque) {
        daoBibliotheque.save(bibliotheque);
    }

    /**
     * modifier un abonné dans la base de données
     * @param abonne
     */
    @PutMapping(value="/ModifierReservation")
    public void modifierReservation(@RequestBody AbonneOuvrageReservation abonne) {
        daoAbonneOuvrageReservation.save(abonne);
    }

    /**
     * supprimer une réservation
     * @param id
     */
    @DeleteMapping(value = "/SupprimerReservation/{id}")
    public void supprimerReservation(@PathVariable int id) {
        daoAbonneOuvrageReservation.deleteById(id);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value="/AbonneSelonId")
    public Abonne AbonneSelonId(int id){
        Abonne abonne = daoAbonne.abonneSelonSonId(id);

        return abonne;
    }

    /**
     * renvoi la bibliothèque selon l'ouvrage
     * @param id
     * @return
     */
    @GetMapping(value = "/bibliothequeSelonOuvrage/{id}")
    public Bibliotheque bibliothequeSelonOuvrage(@PathVariable int id){
        Bibliotheque bibliotheque = daoBibliotheque.bibliothequeSelonOuvrage(id);

        return bibliotheque;
    }

    /**
     * modifier AbonneOuvrageReservation
     * @param abonneOuvrageReservationAModifie
     */
    @PutMapping(value="/ModifierAbonneReservation")
    public void modifierAbonneReservation(@RequestBody AbonneOuvrageReservationAModifie abonneOuvrageReservationAModifie) {
        daoAbonneOuvrageReservationAModifie.save(abonneOuvrageReservationAModifie);
    }

}
