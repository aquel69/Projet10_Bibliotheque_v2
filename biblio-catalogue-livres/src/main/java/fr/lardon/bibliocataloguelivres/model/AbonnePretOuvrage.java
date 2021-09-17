package fr.lardon.bibliocataloguelivres.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
/**
 * classe permettant de récupérer un abonné avec sa liste de prêts et d'ouvrages lui correspondent
 */
public class AbonnePretOuvrage {

    /**
     * abonné
     */
    private Abonne abonne;

    /**
     * liste de prêt correspondant à l'abonné
     */
    private List<Pret> listePret;

    /**
     * liste d'ouvrage correspondant à l'abonné
     */
    private List<Ouvrage> listeOuvrage;

}
