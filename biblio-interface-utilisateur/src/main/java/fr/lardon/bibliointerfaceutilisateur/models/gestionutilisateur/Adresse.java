package fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
/**
 * classe représentant une adresse
 */
public class Adresse {

    private int idAdresse;

    /**
     * numéro de l'adresse
     */
    @Size(max=10, message="Entre 1 et 10 chiffres")
    @NotEmpty(message = "le champ est vide")
    private String numero;

    /**
     * rue de l'adresse
     */
    @Size(max=100, message="Entre 1 et 100 caractères")
    @NotEmpty(message = "le champ est vide")
    private String rue;

    /**
     * complément de l'adresse
     */
    @Size(max=150, message="Entre 1 et 150 caractères")
    private String complement;

    /**
     * code postal de l'adresse
     */
    @Size(max=10, message="Entre 1 et 10 chiffres")
    @NotEmpty(message = "le champ est vide")
    private String codePostal;

    /**
     * ville de l'adresse
     */
    @Size(max=30, message="Entre 1 et 30 caractères")
    @NotEmpty(message = "le champ est vide")
    private String ville;

}
