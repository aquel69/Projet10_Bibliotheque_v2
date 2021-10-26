package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
/**
 * classe représentant l'ouvrage et le nombre d'exemplaires disponible
 */
public class OuvrageAModifie {

    private int idOuvrage;

    /**
     * nombre d'exemplaire de l'ouvrage
     */
    private int nombreExemplaires;

    /**
     * date de la réservation
     */
    private LocalDateTime dateDeRetourPrevue;

}
