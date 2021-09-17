package fr.lardon.bibliogestionutilisateur.controller;

import fr.lardon.bibliogestionutilisateur.dao.DaoAbonne;
import fr.lardon.bibliogestionutilisateur.dao.DaoAdresse;
import fr.lardon.bibliogestionutilisateur.dao.DaoBibliotheque;
import fr.lardon.bibliogestionutilisateur.dao.DaoRole;
import fr.lardon.bibliogestionutilisateur.model.Abonne;
import fr.lardon.bibliogestionutilisateur.model.Adresse;
import fr.lardon.bibliogestionutilisateur.model.Bibliotheque;
import fr.lardon.bibliogestionutilisateur.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
/**
 * classe regroupant les méthodes permettant de retourner les objets en fonction des données souhaités pour la gestion des utilisateurs
 */
public class GestionAbonneController {

    @Autowired
    private DaoAbonne daoAbonne;

    @Autowired
    private DaoAdresse daoAdresse;

    @Autowired
    private DaoRole daoRole;

    @Autowired
    private DaoBibliotheque daoBibliotheque;

    @GetMapping(value="/Abonnes")
    public List<Abonne> listeAbonnes(){
        List<Abonne> listeAbonne = daoAbonne.findAll();

        return listeAbonne;
    }

    /**
     * modifier un abonné dans la base de données
     * @param abonne
     */
    @PutMapping(value="/ModifierAbonne")
    public void modifierAbonne(@RequestBody Abonne abonne) {
        daoAbonne.save(abonne);
    }

    /**
     * ajouter un abonné dans la base de données
     * @param abonne
     */
    @PostMapping(value = "/AjouterAbonne")
    public void ajouterAbonne(@RequestBody Abonne abonne) {daoAbonne.save(abonne);}

    /**
     * récupérer un abonné dans la base de données selon son id
     * @param id
     * @return
     */
    @GetMapping(value = "/Abonne/{id}")
    public Abonne recupererAbonne(@PathVariable int id) {
        Abonne abonne = daoAbonne.findById(id).get();

        return abonne;
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
     * récupérer un role à partir de son id
     * @param id
     * @return
     */
    @GetMapping(value = "/Role/{id}")
    public Role recupererRole(@PathVariable int id) {
        Role role = daoRole.findById(id).get();

        return role;
    }

    /**
     * récupérer une bibliothèque à partir de son numéro de siret
     * @param siret
     * @return
     */
    @GetMapping(value = "/Bibliotheque/{siret}")
    public Bibliotheque recupererBibliotheque(@PathVariable String siret) {
        Bibliotheque bibliotheque = daoBibliotheque.findByNumeroSiret(siret);

        return bibliotheque;
    }

    /**
     * ajouter une adresse dans la base de données
     * @param adresse
     */
    @PostMapping(value = "/AjouterAdresse")
    public void ajouterAdresse(@RequestBody Adresse adresse) {
        daoAdresse.save(adresse);
    }

    /**
     * récupérer une adresse dans la base de données
     * @param id
     * @return
     */
    @GetMapping(value = "/Adresse/{id}")
    public Adresse recupererAdresse(@PathVariable int id) {
        Adresse adresse = daoAdresse.findById(id).get();

        return adresse;
    }

    /**
     * récupérer la dernière adresse enregistrée dans la base de données
     * @return
     */
    @GetMapping(value = "/DerniereAdresse")
    public Adresse recupererDernierAdresse() {
        int dernierIdAdresse = daoAdresse.recupererDernierAdresse();

        Adresse adresse = recupererAdresse(dernierIdAdresse);

        return adresse;
    }

    /**
     * récupérer le dernier abonné enregistrée dans la base de données
     * @return
     */
    @GetMapping(value = "/DerniereAbonne")
    public Abonne recupererDernierAbonne() {
        int dernierIdAbonne = daoAbonne.recupererDernierAbonne();

        Abonne abonne = recupererAbonne(dernierIdAbonne);

        return abonne;
    }

    /**
     * vérifier si l'email à la création du compte n'est pas identique avec un autre existant das la base de données
     * @param abonne
     * @return
     */
    @GetMapping(value = "/DoublonEmail")
    public Boolean verificationSiEmailDoublon(Abonne abonne){
        boolean resultat = false;
        List<Abonne> abonnes;

        abonnes = daoAbonne.findAll();

        for (Abonne abonne1 : abonnes){
            if(abonne1.getEmail().equals(abonne.getEmail())){
                resultat = true;
            }
        }

        return resultat;
    }



}
