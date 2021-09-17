package fr.lardon.bibliogestionutilisateur.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name=("employe"))
/**
 * classe représentant un employé
 */
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_employe")
    private int idEmploye;

    /**
     * nom de l'employé
     */
    @NonNull
    @Column(name="nom")
    private String nom;

    /**
     * prénom de l'employé
     */
    @NonNull
    @Column(name="prenom")
    private String prenom;

    /**
     * pseudo de l'employé
     */
    @NonNull
    @Column(name="matricule")
    private String matricule;

    /**
     * date d'embauche de l'employé
     */
    @NonNull
    @Column(name="date_embauche")
    @Temporal(TemporalType.DATE)
    private Date dateDEmbauche;

    /**
     * date de départ(fin de contrat) de l'employé
     */
    @NonNull
    @Column(name="date_depart")
    @Temporal(TemporalType.DATE)
    private Date dateDeDepart;

    /**
     * email de l'employé
     */
    @NonNull
    @Column(name="email")
    private String email;

    /**
     * mot de passe de l'employé
     */
    @NonNull
    @Column(name="mot_de_passe")
    private String motDePasse;

    /**
     * Adresse de l'employé
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_adresse")
    private Adresse adresse;

    /**
     * Bibliothèque dont l'employé dépend
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bibliotheque")
    private Bibliotheque bibliotheque;

    /**
     * Role de l'employé
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role")
    private Role role;

}
