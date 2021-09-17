package fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur;

import lombok.Data;

@Data
/**
 * classe représentant le role de l'utilisateur
 */
public class Role {

    private int code;

    /**
     * status de l'utilisateur
     */
    private String status;

    /**
     * description du status
     */
    private String description;

}
