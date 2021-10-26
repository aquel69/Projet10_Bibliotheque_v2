package fr.lardon.bibliogestionutilisateur.dao;

import fr.lardon.bibliogestionutilisateur.model.Bibliotheque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoBibliotheque extends JpaRepository<Bibliotheque, String> {

    Bibliotheque findByNumeroSiret(String numeroSiret);

}
