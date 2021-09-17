package fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur;

import lombok.Data;

import java.util.Date;

@Data
/**
 * classe représentant un employé
 */
public class Employe {

    private int idEmploye;

    /**
     * nom de l'employé
     */
    private String nom;

    /**
     * prénom de l'employé
     */
    private String prenom;

    /**
     * pseudo de l'employé
     */
    private String matricule;

    /**
     * date d'embauche de l'employé
     */
    private Date dateDEmbauche;

    /**
     * date de départ(fin de contrat) de l'employé
     */
    private Date dateDeDepart;

    /**
     * email de l'employé
     */
    private String email;

    /**
     * mot de passe de l'employé
     */
    private String motDePasse;

    /**
     * Adresse de l'employé
     */
    private Adresse adresse;

    /**
     * Bibliothèque dont l'employé dépend
     */
    private Bibliotheque bibliotheque;

    /**
     * Role de l'employé
     */
    private Role role;

}
