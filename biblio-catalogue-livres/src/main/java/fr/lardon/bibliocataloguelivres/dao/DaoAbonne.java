package fr.lardon.bibliocataloguelivres.dao;

import fr.lardon.bibliocataloguelivres.model.Abonne;
import fr.lardon.bibliocataloguelivres.model.AbonnePret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoAbonne extends JpaRepository<Abonne, Integer> {

    Abonne findByNumeroAbonne(String numeroAbonne);

}
