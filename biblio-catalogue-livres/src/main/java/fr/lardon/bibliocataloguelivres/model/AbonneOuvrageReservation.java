package fr.lardon.bibliocataloguelivres.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name=("abonne_ouvrage_reservation"))
/**
 * classe représentant une réservation d'un ouvrage pour un abonné
 */
public class AbonneOuvrageReservation {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_generator")
    @SequenceGenerator(name="project_generator", sequenceName="abonne_ouvrage_reservation_id_abonne_ouvrage_resservation_seq", initialValue = 1, allocationSize = 1)
    @Column(name="id_abonne_ouvrage_reservation")
    private int idAbonneOuvrageReservation;

    /**
     * abonné de la réservation
     */
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="id_abonne")
    private Abonne abonneReservation;

    /**
     * ouvrage de la réservation
     */
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="id_ouvrage")
    private Ouvrage ouvrageReservation;

    /**
     * date de la réservation
     */
    @NonNull
    @Column(name="date_reservation")
    private LocalDateTime dateDeReservation;

    /**
     * ouvrage récupéré
     */
    @NonNull
    @Column(name="recupere")
    private boolean ouvrageRecupere;

    /**
     * position dans la file d'attente des réservation de l'ouvrage
     */
    @Column(name="position_reservation")
    private int positionAttente;


}
