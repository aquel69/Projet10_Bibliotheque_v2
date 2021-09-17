package fr.lardon.bibliobatch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name=("genre"))
/**
 * classe représentant un genre littéraire
 */
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_genre")
    private int idGenre;
    /**
     * Nom du genre littéraire.
     */
    @NonNull
    @Column(name="nom")
    private String nom;
    /**
     * Description du genre littéraire.
     */
    @NonNull
    @Column(name="description")
    private String description;

    /**
     * Liste des livres en fonction du genre
     */
    @ToString.Exclude
    @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "liste_genre",
            joinColumns = @JoinColumn(name = "id_genre"),
            inverseJoinColumns = @JoinColumn(name = "id_livre"))
    @JsonIgnore
    private List<Livre> livres;
}
