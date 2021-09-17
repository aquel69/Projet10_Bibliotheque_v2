package fr.lardon.bibliocataloguelivres.dao;

import fr.lardon.bibliocataloguelivres.model.OuvrageAModifie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoOuvrageAModifie extends JpaRepository<OuvrageAModifie, Integer> {

}
