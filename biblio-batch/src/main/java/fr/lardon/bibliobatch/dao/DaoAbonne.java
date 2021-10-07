package fr.lardon.bibliobatch.dao;

import fr.lardon.bibliobatch.model.Abonne;
import fr.lardon.bibliobatch.model.AbonneOuvrageReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoAbonne extends JpaRepository<Abonne, Integer> {

    @Query(value = "SELECT * FROM abonne WHERE id_abonne = ?", nativeQuery = true)
    Abonne abonneSelonSonId(int id);

}
