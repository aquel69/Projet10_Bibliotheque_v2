package fr.lardon.bibliocataloguelivres.dao;

import fr.lardon.bibliocataloguelivres.model.LivreTop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoLivreTop extends JpaRepository<LivreTop, Integer> {

    @Query(value = "SELECT liv.id_livre FROM pret as pre\n" +
            "INNER JOIN ouvrage as ouv on pre.id_ouvrage = ouv.id_ouvrage\n" +
            "INNER JOIN livre as liv on liv.id_livre = ouv.id_livre\n" +
            "GROUP BY liv.id_livre ORDER BY COUNT(liv.id_livre) DESC", nativeQuery = true)
    List<LivreTop> listeNombreDePretParLivre();

}
