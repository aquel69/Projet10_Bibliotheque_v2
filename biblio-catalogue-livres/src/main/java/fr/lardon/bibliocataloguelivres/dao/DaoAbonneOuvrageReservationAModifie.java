package fr.lardon.bibliocataloguelivres.dao;

import fr.lardon.bibliocataloguelivres.model.AbonneOuvrageReservationAModifie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoAbonneOuvrageReservationAModifie extends JpaRepository<AbonneOuvrageReservationAModifie, Integer> {

    @Query(value = "UPDATE abonne_ouvrage_reservation SET position_reservation = ? WHERE id_abonne_ouvrage_reservation = ?", nativeQuery = true)
    void modifierAbonneReservation(int position, int idAbonneReservation);

}
