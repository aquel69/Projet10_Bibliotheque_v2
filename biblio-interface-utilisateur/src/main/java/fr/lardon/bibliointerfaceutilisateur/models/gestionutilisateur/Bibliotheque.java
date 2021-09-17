package fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur;

import lombok.Data;

@Data
/**
 * classe représentant une bibliothèque
 */
public class Bibliotheque {

    private String numeroSiret;

    /**
     * nom de la bibliothèque
     */
    private String nom;

    /**
     * code de la bibliothèque
     */
    private String code;

    /**
     * Role de l'abonné
     */
    private Adresse adresse;

}
