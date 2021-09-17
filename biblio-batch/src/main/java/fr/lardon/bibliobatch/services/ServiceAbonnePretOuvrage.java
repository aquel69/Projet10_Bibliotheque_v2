package fr.lardon.bibliobatch.services;

import fr.lardon.bibliobatch.dao.DaoAbonne;
import fr.lardon.bibliobatch.dao.DaoPret;
import fr.lardon.bibliobatch.model.Abonne;
import fr.lardon.bibliobatch.model.AbonnePretOuvrage;

import fr.lardon.bibliobatch.model.Ouvrage;
import fr.lardon.bibliobatch.model.Pret;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
/**
 * classe regroupant les méthodes permettant de retourner les objets en fonction des données souhaitées pour la gestion des batchs
 */
public class ServiceAbonnePretOuvrage {

    @Autowired
    private DaoPret daoPret;

    @Autowired
    private DaoAbonne daoAbonne;

    public AbonnePretOuvrage getAbonnePretOuvrage(int id){
        AbonnePretOuvrage abonnePretOuvrage = new AbonnePretOuvrage();

        LocalDateTime localDateTime = LocalDateTime.now();

        Abonne abonne = daoAbonne.findById(id).get();
        List<Pret> listePret = daoPret.listePretSelonAbonne(id);
        List<Ouvrage> listeOuvrage = new ArrayList<>();

        //ajout des prêt dans la liste d'ouvrage
        for(Pret pret : listePret){
            listeOuvrage.add(pret.getOuvragePret());
        }

        //changement du status suivant la période
        for(Pret pret : listePret){
            long chrono = ChronoUnit.DAYS.between(localDateTime, pret.getDateDeRestitution());
            if(pret.isRendu() == true){
                pret.setStatut("Rendu");
                pret.setStatutPriorite("5");
            }else if(chrono > 14 && pret.isRendu() == false && pret.isProlongation() ==false){
                pret.setStatut("Prêt en cours");
                pret.setStatutPriorite("4");
            }else if(chrono > 14 && pret.isRendu() == false && pret.isProlongation() ==true){
                pret.setStatut("Prolongation");
                pret.setStatutPriorite("3");
            }else if(chrono <= 7 && chrono >= 0 && pret.isRendu() == false){
                pret.setStatut("Dernière semaine");
                pret.setStatutPriorite("2");
            }else if(chrono < 0 && pret.isRendu() == false ){
                pret.setStatut("A rendre");
                pret.setStatutPriorite("1");
                pret.setProlongation(true);
            }
        }

        abonnePretOuvrage.setAbonne(abonne);
        abonnePretOuvrage.setListeOuvrage(listeOuvrage);
        abonnePretOuvrage.setListePret(listePret);

        return abonnePretOuvrage;
    }

}
