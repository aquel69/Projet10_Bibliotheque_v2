package fr.lardon.bibliocataloguelivres.dao;

import fr.lardon.bibliocataloguelivres.model.Bibliotheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoBibliotheque extends JpaRepository<Bibliotheque, String> {

    @Query(value = "SELECT bib.numero_siret, bib.dernier_ouvrage_rendu, bib.code, bib.id_adresse, bib.nom, bib.nouveau_dernier_ouvrage FROM bibliotheque as bib " +
            "INNER JOIN ouvrage as ouv on bib.numero_siret = ouv.siret_bibliotheque WHERE ouv.id_ouvrage = ?;", nativeQuery = true)
    Bibliotheque bibliothequeSelonOuvrage(int id);

}
