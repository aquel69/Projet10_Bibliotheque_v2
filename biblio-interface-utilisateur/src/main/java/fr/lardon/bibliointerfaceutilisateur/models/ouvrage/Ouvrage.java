package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.*;

import java.time.LocalDateTime;
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
     * date de la réservation
     */
    private LocalDateTime dateDeRetourPrevue;

    /**
     * code bibliothèque de l'ouvrage
     */
    private String codeBibliotheque;

    /**
     * nombre d'exemplaire de l'ouvrage
     */
    private int nombreExemplaires;

    /**
     * nombre réél d'exemplaires totales dans la bibliothèque
     */
    private int nombreExemplairesTotal;

    /**
     * Livre
     */
    private Livre livre;

    /**
     * Bibliothèque
     */
    private String siretBibliotheque;

}
