package fr.lardon.bibliobatch.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
