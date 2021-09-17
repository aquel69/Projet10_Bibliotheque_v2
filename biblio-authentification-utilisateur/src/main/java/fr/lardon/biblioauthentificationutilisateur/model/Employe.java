package fr.lardon.biblioauthentificationutilisateur.model;

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
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_generator")
    @SequenceGenerator(name="project_generator", sequenceName="abonne_id_abonne_seq", initialValue = 1, allocationSize = 1)
    @Column(name="id_employe")
    private int idAbonne;

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
    private String pseudo;

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
     * date d'embauche
     */
    @Column(name="date_embauche")
    @Temporal(TemporalType.DATE)
    private Date dateDEmbauche;

    /**
     * date de départ
     */
    @Column(name="date_depart")
    @Temporal(TemporalType.DATE)
    private Date dateDepart;

    /**
     * Role de l'employé
     */
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role")
    private Role role;

}
