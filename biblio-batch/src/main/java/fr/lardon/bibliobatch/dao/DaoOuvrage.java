package fr.lardon.bibliobatch.dao;

import fr.lardon.bibliobatch.model.Ouvrage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoOuvrage extends JpaRepository<Ouvrage, Integer> {

    Ouvrage findByCodeBibliotheque(String codeBibliotheque);

    Ouvrage findById(int id);

}
