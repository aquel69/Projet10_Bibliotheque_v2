package fr.lardon.bibliogestionutilisateur.dao;

import fr.lardon.bibliogestionutilisateur.model.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface DaoAdresse extends JpaRepository<Adresse, Integer> {

    @Query(value = "SELECT MAX(idAdresse) FROM Adresse")
    Integer recupererDernierAdresse();

}

