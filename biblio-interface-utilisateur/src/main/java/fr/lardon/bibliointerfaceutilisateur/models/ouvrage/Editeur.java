package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
/**
 * classe représentant une maison d'édition
 */
public class Editeur {

    private int idEditeur;

    /**
     * nom de la maison d'édition
     */
     private String nomMaisonEdition;
}
