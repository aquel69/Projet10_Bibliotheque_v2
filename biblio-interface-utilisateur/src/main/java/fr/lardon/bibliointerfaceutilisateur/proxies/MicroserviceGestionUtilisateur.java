package fr.lardon.bibliointerfaceutilisateur.proxies;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Abonne;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Adresse;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Bibliotheque;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-gestion-utilisateur", url = "localhost:9091")
public interface MicroserviceGestionUtilisateur {

    @GetMapping(value="/Abonnes")
    List<Abonne> listeAbonnes();

    //récuperer abonné en fonction de son id
    @GetMapping(value = "/Abonne/{id}")
    Abonne recupererAbonne(@PathVariable int id);

    //récuperer abonné en fonction de son numéro d'abonné
    @GetMapping(value = "/AbonnePret/{numeroAbonne}")
    Abonne recupererAbonneSelonNumeroAbonne(@PathVariable String numeroAbonne);

    //modifier un abonné
    @PutMapping(value="/ModifierAbonne")
    void modifierAbonne(@RequestBody Abonne abonne);

    //ajouter une adresse
    @PostMapping(value = "/AjouterAdresse")
    void ajouterAdresse(@RequestBody Adresse adresse);

    //ajouter un abonné
    @PostMapping(value = "/AjouterAbonne")
    void ajouterAbonne(@RequestBody Abonne abonne);

    //récuperer la bibliothèque de l'abonné
    @GetMapping(value = "/Bibliotheque/{siret}")
    Bibliotheque recupererBibliotheque(@PathVariable String siret);

    //récuperer le role de l'abonné
    @GetMapping(value = "/Role/{id}")
    Role recupererRole(@PathVariable int id);

    //récuperer l'adresse par l'id
    @GetMapping(value = "/Adresse/{id}")
    Adresse recupererAdresse(@PathVariable int id);

    //récuperer la derniere adresse de la base de données
    @GetMapping(value = "/DerniereAdresse")
    Adresse recupererDernierAdresse();

    //récuperer le dernier abonné de la base de données
    @GetMapping(value = "/DerniereAbonne")
    Abonne recupererDernierAbonne();

    //vérification si l'email n'existe pas
    @GetMapping(value = "/DoublonEmail")
    Boolean verificationSiEmailDoublon(Abonne abonne);

}
