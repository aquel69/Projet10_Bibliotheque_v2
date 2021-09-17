package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
/**
 * classe représentant un abonné pour la gestion des prêts
 */
public class AbonnePret {

    private int idAbonne;

    /**
     * nom de l'abonné
     */
    private String nom;

    /**
     * prénom de l'abonné
     */
    private String prenom;

    /**
     * pseudo de l'abonné
     */
    private String pseudo;

    /**
     * email de l'abonné
     */
    private String email;

    /**
     * mot de passe de l'abonné
     */
    private String motDePasse;

    /**
     * numero adhérent de l'abonné
     */
    private String numeroAbonne;

    /**
     * date de création du compte
     */
    private Date dateDeCreationDuCompte;

    /**
     * liste des prêts de l'abonné
     */
    private List<Pret> listePretAbonnes;

}
