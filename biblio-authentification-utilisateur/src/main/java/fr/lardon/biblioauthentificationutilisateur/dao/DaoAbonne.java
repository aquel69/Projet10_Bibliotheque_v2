package fr.lardon.biblioauthentificationutilisateur.dao;

import fr.lardon.biblioauthentificationutilisateur.model.Abonne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoAbonne extends JpaRepository<Abonne, Integer> {

    Abonne findByEmail(String email);

}
