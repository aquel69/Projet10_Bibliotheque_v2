package fr.lardon.bibliobatch.dao;

import fr.lardon.bibliobatch.model.Abonne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoAbonne extends JpaRepository<Abonne, Integer> {

}
