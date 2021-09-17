package fr.lardon.bibliobatch.controller;

import fr.lardon.bibliobatch.dao.DaoOuvrage;
import fr.lardon.bibliobatch.dao.DaoPret;
import fr.lardon.bibliobatch.model.AbonnePretOuvrage;
import fr.lardon.bibliobatch.model.Ouvrage;
import fr.lardon.bibliobatch.model.Pret;
import fr.lardon.bibliobatch.services.ServiceAbonnePretOuvrage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
