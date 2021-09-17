package fr.lardon.biblioauthentificationutilisateur.dao;

import fr.lardon.biblioauthentificationutilisateur.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoEmploye extends JpaRepository<Employe, Integer> {

}
