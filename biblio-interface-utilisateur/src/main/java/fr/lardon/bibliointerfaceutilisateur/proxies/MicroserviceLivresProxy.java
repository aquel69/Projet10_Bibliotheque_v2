package fr.lardon.bibliointerfaceutilisateur.proxies;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Abonne;
import fr.lardon.bibliointerfaceutilisateur.models.ouvrage.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    //ouvrage selon son code ouvrage
    @GetMapping(value = "/Ouvrage/{codeBibliotheque}")
    Ouvrage ouvrageSelonCodeBibliotheque(@PathVariable String codeBibliotheque);

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


}
