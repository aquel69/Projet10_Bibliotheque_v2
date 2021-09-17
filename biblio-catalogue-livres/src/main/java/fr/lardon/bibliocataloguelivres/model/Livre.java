package fr.lardon.bibliocataloguelivres.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"auteurs", "genres", "editeur"})
@Entity
@Table(name=("livre"))
/**
 * classe représentant un livre
 */
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_livre")
    private int idLivre;

    /**
     * Titre du livre
     */
    @NonNull
    @Column(name="titre")
    private String titre;

    /**
     * Résumé du livre.
     */
    @NonNull
    @Column(name="resume")
    private String resume;

    /**
     * Liste des auteurs du livre sachant qu'un livre a souvent un auteur mais peut être co-ecrit. Auquel cas, il y a plusieurs auteurs.
     */
    @ToString.Exclude
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "livre_auteurs",
            joinColumns = @JoinColumn(name = "id_livre"),
            inverseJoinColumns = @JoinColumn(name = "id_auteur"))
    private List<Auteur> auteurs;

    /**
     * Genre associé à cet ouvrage.
     */
    @ToString.Exclude
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "liste_genre",
            joinColumns = @JoinColumn(name = "id_livre"),
            inverseJoinColumns = @JoinColumn(name = "id_genre"))
    private List<Genre> genres;

    /**
     * Editeur du livre.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_editeur")
    private Editeur editeur;

    /**
     * Date d'édition du livre.
     */
    @NonNull
    @Column(name="date_edition")
    @Temporal(TemporalType.DATE)
    private Date dateEdition;

    /**
     * Numéro ISBN de l'ouvrage
     */
    @NonNull
    @Column(name="num_isbn13")
    private String numISBN13;

    /**
     * Langue de cet ouvrage.
     */
    @NonNull
    @Column(name="langue")
    private String langue;

    /**
     * nombre de pages
     */
    @NonNull
    @Column(name="nombre_de_pages")
    private int nombreDePages;

    /**
     * photo de la couverture liste
     */
    @NonNull
    @Column(name="petite_photo_couverture")
    private String petitePhotoCouverture;

    /**
     * photo de la couverture détail
     */
    @NonNull
    @Column(name = "grande_photo_couverture")
    private String grandePhotoCouverture;

    /**
     * photo de la couverture catalogue
     */
    @NonNull
    @Column(name = "moyenne_photo_couverture")
    private String moyennePhotoCouverture;


}
