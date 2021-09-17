package fr.lardon.bibliobatch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name=("auteur"))
/**
 * classe représentant un auteur
 */
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_auteur")
    private int idAuteur;
    /**
     * nom de l'auteur
     */
    @NonNull
    @Column(name="nom")
    private String nom;
    /**
     * prénom de l'auteur
     */
    @NonNull
    @Column(name="prenom")
    private String prenom;
    /**
     * date de naissance de l'auteur
     */
    @NonNull
    @Column(name="date_de_naissance")
    @Temporal(TemporalType.DATE)
    private Date dateDeNaissance;
    /**
     * date de décès de l'auteur(si décédé)
     */
    @NonNull
    @JsonProperty("date_deces")
    @Column(name="date_deces")
    @Temporal(TemporalType.DATE)
    private Date dateDeces;
    /**
     * commentaire sur l'auteur
     */
    @NonNull
    @Column(name="commentaire")
    private String commentaire;
    /**
     * liste des livres en fonction de l'auteur
     */
    @ToString.Exclude
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "livre_auteurs",
            joinColumns = @JoinColumn(name = "id_auteur"),
            inverseJoinColumns = @JoinColumn(name = "id_livre"))
    @JsonIgnore
    private List<Livre> livres;

}
