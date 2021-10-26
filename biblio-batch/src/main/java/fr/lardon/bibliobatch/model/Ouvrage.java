package fr.lardon.bibliobatch.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"livre", "listePretAbonnes"})
@Entity
@Table(name=("ouvrage"))
/**
 * classe représentant l'ouvrage
 */
public class Ouvrage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_ouvrage")
    private int idOuvrage;

    /**
     * date d'ajout de l'ouvrage'
     */
    @NonNull
    @Column(name="date_ajout")
    @Temporal(TemporalType.DATE)
    private Date dateAjoutOuvrage;

    /**
     * code bibliothèque de l'ouvrage
     */
    @NonNull
    @Column(name = "code_bibliotheque")
    private String codeBibliotheque;

    /**
     * nombre d'exemplaire de l'ouvrage
     */
    @NonNull
    @Column(name = "nombre_exemplaires")
    private int nombreExemplaires;

    /**
     * nombre réél d'exemplaire totales dans la bibliothèque
     */
    @NonNull
    @Column(name = "nombre_total_exemplaires")
    private int nombreExemplairesTotal;

    /**
     * nombre code siret de la bibliothèque
     */
    @NonNull
    @Column(name = "siret_bibliotheque")
    private String siretBibliotheque;

    /**
     * Livre
     */
    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_livre")
    private Livre livre;

}
