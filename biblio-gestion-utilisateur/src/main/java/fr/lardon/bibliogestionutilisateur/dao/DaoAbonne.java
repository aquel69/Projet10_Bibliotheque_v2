package fr.lardon.bibliogestionutilisateur.dao;

import fr.lardon.bibliogestionutilisateur.model.Abonne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoAbonne extends JpaRepository<Abonne, Integer> {

    @Query(value = "SELECT MAX(idAbonne) FROM Abonne")
    Integer recupererDernierAbonne();

    Abonne findByNumeroAbonne(String numeroAbonne);

}
