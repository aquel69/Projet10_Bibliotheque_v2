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
@Table(name=("abonne"))
/**
 * classe représentant un abonné
 */
public class Abonne {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_generator")
    @SequenceGenerator(name="project_generator", sequenceName="abonne_id_abonne_seq", initialValue = 1, allocationSize = 1)
    @Column(name="id_abonne")
    private int idAbonne;

    /**
     * nom de l'abonné
     */
    @NonNull
    @Column(name="nom")
    private String nom;

    /**
     * prénom de l'abonné
     */
    @NonNull
    @Column(name="prenom")
    private String prenom;

    /**
     * pseudo de l'abonné
     */
    @NonNull
    @Column(name="pseudo")
    private String pseudo;

    /**
     * email de l'abonné
     */
    @NonNull
    @Column(name="email")
    private String email;

    /**
     * mot de passe de l'abonné
     */
    @NonNull
    @Column(name="mot_de_passe")
    private String motDePasse;

    /**
     * numero adhérent de l'abonné
     */
    @NonNull
    @Column(name="numero_abonne")
    private String numeroAbonne;

    /**
     * date de création du compte
     */
    @NonNull
    @Column(name="date_creation_du_compte")
    @Temporal(TemporalType.DATE)
    private Date dateDeCreationDuCompte;

    /**
     * Role de l'abonné
     */
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role")

    private Role role;

}
