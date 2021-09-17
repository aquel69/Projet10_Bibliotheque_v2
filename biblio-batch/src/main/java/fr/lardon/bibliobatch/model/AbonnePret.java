package fr.lardon.bibliobatch.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idAbonne", scope = AbonnePret.class)
@Table(name=("abonne"))
/**
 * classe représentant un abonné pour la gestion des prêts
 */
public class AbonnePret {

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


}
