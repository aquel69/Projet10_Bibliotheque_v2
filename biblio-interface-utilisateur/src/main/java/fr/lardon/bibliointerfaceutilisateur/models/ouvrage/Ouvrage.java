package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
/**
 * classe représentant un ouvrage
 */
public class Ouvrage {

    private int idOuvrage;

    /**
     * date d'ajout de l'ouvrage'
     */
    private Date dateAjoutOuvrage;

    /**
     * code bibliothèque de l'ouvrage
     */
    private String codeBibliotheque;

    /**
     * nombre d'exemplaire de l'ouvrage
     */
    private int nombreExemplaires;

    /**
     * Livre
     */
    private Livre livre;

    /**
     * Bibliothèque
     */
    private String siretBibliotheque;

}
