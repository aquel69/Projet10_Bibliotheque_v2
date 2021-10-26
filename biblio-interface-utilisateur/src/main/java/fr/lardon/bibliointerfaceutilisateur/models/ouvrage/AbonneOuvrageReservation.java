package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Abonne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
/**
 * classe représentant un abonné pour la réservation d'ouvrage
 */
public class AbonneOuvrageReservation {

    private int idAbonneOuvrageReservation;

    /**
     * abonné de la réservation
     */
    private Abonne abonneReservation;

    /**
     * ouvrage de la réservation
     */
    private Ouvrage ouvrageReservation;

    /**
     * date de reservation
     */
    private LocalDateTime dateDeReservation;

    /**
     * ouvrage récupéré
     */
    private boolean ouvrageRecupere;

    /**
     * position dans la file d'attente des réservation de l'ouvrage
     */
    private int positionAttente;

}
