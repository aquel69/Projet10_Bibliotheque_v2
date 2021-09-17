package fr.lardon.bibliocataloguelivres.dao;

import fr.lardon.bibliocataloguelivres.model.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoPret extends JpaRepository<Pret, Integer> {

    @Query(value = "SELECT * FROM abonne as abo\n" +
            "    INNER JOIN pret as pre on abo.id_abonne = pre.id_abonne\n" +
            "    INNER JOIN ouvrage as ouv on pre.id_ouvrage = ouv.id_ouvrage\n" +
            "    WHERE abo.id_abonne = ? ORDER BY pre.statut_priorite", nativeQuery = true)
    List<Pret> listePretSelonAbonne(int idAbonne);

}
