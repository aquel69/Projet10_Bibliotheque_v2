package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.Data;
import lombok.NoArgsConstructor;

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

}
