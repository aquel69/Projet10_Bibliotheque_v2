package fr.lardon.bibliocataloguelivres.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"abonnePret", "ouvragePret"})
@Entity
@Table(name=("pret"))
/**
 * classe représentant le prêt
 */
public class Pret {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_generator")
    @SequenceGenerator(name="project_generator", sequenceName="pret_id_pret_seq", initialValue = 3, allocationSize = 1)
    @Column(name="id_pret")
    private int idPret;

    /**
     * date d'emprunt
     */
    @NonNull
    @Column(name="date_emprunt")
    private LocalDateTime dateDEmprunt;

    /**
     * date de restitution
     */
    @NonNull
    @Column(name="date_restitution")
    private LocalDateTime dateDeRestitution;

    /**
     * l'abonné a le droit de prolonger l'ouvrage emprunté une fois
     */
    @NonNull
    @Column(name="prolongation")
    private boolean prolongation;

    /**
     * l'abonné a rendu l'ouvrage
     */
    @NonNull
    @Column(name="rendu")
    private boolean rendu;

    /**
     * status de l'emprunt
     */
    @NonNull
    @Column(name="statut")
    private String statut;

    /**
     * status de l'emprunt
     */
    @NonNull
    @Column(name="statut_priorite")
    private String statutPriorite;

    /**
     * ouvrage du prêt
     */
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="id_abonne")
    private Abonne abonnePret;

    /**
     * ouvrage du prêt
     */
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="id_ouvrage")
    private Ouvrage ouvragePret;

}
