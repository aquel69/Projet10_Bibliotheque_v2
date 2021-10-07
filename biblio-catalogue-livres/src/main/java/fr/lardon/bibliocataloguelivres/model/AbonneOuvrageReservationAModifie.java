package fr.lardon.bibliocataloguelivres.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name=("abonne_ouvrage_reservation"))
/**
 * classe représentant une réservation d'un ouvrage pour un abonné
 */
public class AbonneOuvrageReservationAModifie {

    @Id
    @Column(name="id_abonne_ouvrage_reservation")
    private int idAbonneOuvrageReservation;

    /**
     * position dans la file d'attente des réservation de l'ouvrage
     */
    @Column(name="position_reservation")
    private int positionAttente;

}
