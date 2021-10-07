package fr.lardon.bibliocataloguelivres.dao;

import fr.lardon.bibliocataloguelivres.model.AbonneOuvrageReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DaoAbonneOuvrageReservation extends JpaRepository<AbonneOuvrageReservation, Integer> {

    @Query(value = "SELECT * FROM abonne_ouvrage_reservation WHERE id_ouvrage = ? ORDER BY date_reservation LIMIT ?", nativeQuery = true)
    List<AbonneOuvrageReservation> listeReservationSelonOuvrage(int id, int nombreLimiteDeReservation);

    @Query(value = "SELECT * FROM abonne_ouvrage_reservation", nativeQuery = true)
    List<AbonneOuvrageReservation> listeReservationTotale();

    @Query(value = "SELECT * FROM abonne_ouvrage_reservation WHERE id_abonne = ?", nativeQuery = true)
    List<AbonneOuvrageReservation> listeReservationSelonAbonne(int id);

    @Query(value = "SELECT currval('abonne_ouvrage_reservation_id_abonne_ouvrage_resservation_seq')", nativeQuery = true)
    int recuperationDernierIdReservation();

    @Query(value = "SELECT * FROM abonne_ouvrage_reservation WHERE id_abonne_ouvrage_reservation = ?", nativeQuery = true)
    AbonneOuvrageReservation reservationAbonneSelonidReservation(int id);



}
