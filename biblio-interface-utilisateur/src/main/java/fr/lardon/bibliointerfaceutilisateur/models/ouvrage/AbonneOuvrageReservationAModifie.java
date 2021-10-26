package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor

/**
 * classe représentant une réservation d'un ouvrage pour un abonné
 */
public class AbonneOuvrageReservationAModifie {

    private int idAbonneOuvrageReservation;

    /**
     * position dans la file d'attente des réservation de l'ouvrage
     */
    private int positionAttente;

}
