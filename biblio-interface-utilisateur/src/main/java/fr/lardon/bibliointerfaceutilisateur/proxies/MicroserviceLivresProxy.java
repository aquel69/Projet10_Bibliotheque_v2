package fr.lardon.bibliointerfaceutilisateur.proxies;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Abonne;
import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Bibliotheque;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-livres", url = "localhost:9090")
public interface MicroserviceLivresProxy {

    //liste des livres
    @GetMapping(value = "/Livres")
    List<Livre> listeLivre();

    //liste des ouvrages
    @GetMapping(value = "/ListeOuvrage")
    List<Ouvrage> listeDesOuvrages();

    //catalogue avec pagination
    @GetMapping(value="/Catalogue/{noPage}/{nbLivresParPage}")
    List<Livre> catalogueListeLivrePagination(@PathVariable("noPage") int noPage, @PathVariable("nbLivresParPage") int nbLivresParPage);

    //récupérer un livre selon son id
    @GetMapping( value = "/Livres/{id}")
    Livre recupererUnLivre(@PathVariable("id") int id);

    //liste d'ouvrage des nouveautés
    @GetMapping(value = "/Livres/Nouveau")
    List<Ouvrage> listeOuvrageNouveaute();

    //liste des ouvrages selon le nombre de fois ou ils ont été empruntés
    @GetMapping(value = "/Top")
    List<Livre> listeOuvrageSelonNombreDEmprunt();

    //recherche des ouvrages pour pagination
    @GetMapping(value="/Recherche/{noPage}/{nbLivresParPage}/{recherche}")
    List<Livre> catalogueListeLivrePaginationRecherche(@PathVariable int noPage, @PathVariable int nbLivresParPage, @PathVariable String recherche);

    //recherche d'ouvrage par rapport à la saisie utilisateur
    @GetMapping(value="/Recherche/{recherche}")
    List<Livre> catalogueListeLivrePaginationRecherche(@PathVariable String recherche);

    //ajouter un prêt
    @PostMapping(value = "/AjouterPret")
    void ajouterPret(@RequestBody Pret pret);

    //ajouter une réservation
    @PostMapping(value = "/AjouterReservation")
    void ajouterReservation(@RequestBody AbonneOuvrageReservation abonneOuvrageReservation);

    //ouvrage selon son code ouvrage
    @GetMapping(value = "/Ouvrage/{codeBibliotheque}")
    Ouvrage ouvrageSelonCodeBibliotheque(@PathVariable String codeBibliotheque);

    //ouvrage selon id livre et N° de siret
    @GetMapping( value = "/OuvrageSelonIdLivreEtSiret/{id}/{siret}")
    Ouvrage ouvragesSelonIdLivreEtSiret(@PathVariable int id, @PathVariable String siret);

    //sauvegarder un prêt
    @PostMapping(value = "/SauvegarderPret}")
    void sauvegarderPret(@RequestBody Pret pret);

    //abonné Prêt selon son numéro d'abonné
    @GetMapping(value = "/AbonnePret/{numeroAbonne}")
    Abonne recupererAbonneSelonNumeroAbonne(@PathVariable String numeroAbonne);

    //sauvegarder un abonné
    @PostMapping(value = "/SauvegarderAbonne")
    void sauvegarderAbonnePret(@RequestBody AbonnePret abonnePret);

    //liste des prêts selon l'id de l'abonné
    @GetMapping(value = "/PretsSelonAbonne/{id}")
    List<Pret> listeDesPretsSelonAbonne(@PathVariable int id);

    //abonné Prêt selon son id
    @GetMapping(value = "/AbonnePretSelonId/{id}")
    AbonnePretOuvrage abonnePretSelonSonId(@PathVariable int id);

    //prêt selon son id
    @GetMapping(value = "/PretSelonSonId/{id}")
    Pret pretSelonSonId(@PathVariable int id);

    //saugarder un prêt modifié
    @PostMapping(value = "/SauvegarderPretAModifie")
    void sauvegardePretAModifie(@RequestBody PretAModifie pretAModifie);

    //prêt à modifié selon son id
    @GetMapping(value = "/PretAModifieSelonSonId/{id}")
    PretAModifie pretAModifieSelonSonId(@PathVariable int id);

    //ouvrage selon l'id du livre
    @GetMapping( value = "/OuvrageSelonIdLivre/{id}")
    Ouvrage recupererUnOuvrageSelonIdLivre(@PathVariable int id);

    //sauvegarder un ouvrage
    @PostMapping(value = "/SauvegarderOuvrage")
    void sauvegarderOuvrage(@RequestBody OuvrageAModifie ouvrageAModifie);

    //liste des ouvrages selon l'id du livre
    @GetMapping(value = "/OuvragesSelonIdLivre/{id}")
    List<Ouvrage> listeDesOuvragesSelonIdLivre(@PathVariable int id);

    //dernier prêt selon l'ouvrage
    @GetMapping(value = "/DernierPretSelonOuvrage/{id}")
    Pret dernierPretSelonOuvrage(@PathVariable int id);

    //Liste des réservations selon l'ouvrage
    @GetMapping(value = "/ListeReservationSelonOuvrage/{id}/{nombreLimiteReservation}")
    List<AbonneOuvrageReservation> listeReservationSelonOuvrage(@PathVariable int id, @PathVariable int nombreLimiteReservation);

    //Liste de toutes les réservations
    @GetMapping(value = "/ListeReservationTotale")
    List<AbonneOuvrageReservation> listeReservationTotale();

    //liste des ouvrages réservés selon l'abonné
    @GetMapping(value = "/ListeReservationSelonAbonne/{id}")
    List<AbonneOuvrageReservation> listeReservationSelonAbonne(@PathVariable int id);

    //supprimer une réservation
    @DeleteMapping(value = "/SupprimerReservation/{id}")
    void supprimerReservation(@PathVariable int id);

    //renvoi la bibliothèque selon l'ouvrage
    @GetMapping(value = "/bibliothequeSelonOuvrage/{id}")
    Bibliotheque bibliothequeSelonOuvrage(@PathVariable int id);

    //modifier une bibliothèque
    @PutMapping(value="/ModifierBibliotheque")
    void modifierBibliotheque(@RequestBody Bibliotheque bibliotheque);

    //modifier un ouvrage
    @PutMapping(value="/ModifierOuvrage")
    void modifierOuvrage(@RequestBody OuvrageAModifie ouvrage);

    //Modifier AbonneOuvrageReservation
    @PutMapping(value="/ModifierAbonneReservation")
    void modifierAbonneReservation(@RequestBody AbonneOuvrageReservationAModifie abonneOuvrageReservationAModifie);

    //liste des prêts
    @GetMapping(value = "/ListeDesPrets")
    List<Pret> listeDesPrets();

    //liste des prêts non rendus
    @GetMapping(value = "/ListeDesPretsNonRendu")
    List<Pret> listeDesPretsNonRendu();

    //position de l'abonne dans la file d'attente
    @GetMapping(value = "/positionAbonneReservationSelonOuvrage/{id}/{nombreLimiteReservation}/{idAbonne}")
    int positionAbonneReservationSelonOuvrage(@PathVariable int id, @PathVariable int nombreLimiteReservation, @PathVariable int idAbonne);

    //récupération dernière id de la réservation
    @PostMapping(value = "/recuperationDernierIdReservation")
    int recuperationDernierIdReservation();

    //AbonneOuvrageReservation selon son id
    @GetMapping(value = "/AbonneOuvrageReservationSelonId/{id}")
    AbonneOuvrageReservation AbonneOuvrageReservationSelonId(@PathVariable int id);




}
