package fr.lardon.bibliocataloguelivres.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@EqualsAndHashCode
@Entity
@Table(name=("ouvrage"))
/**
 * classe représentant l'ouvrage et le nombre d'exemplaires disponible
 */
public class OuvrageAModifie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_ouvrage")
    private int idOuvrage;

    /**
     * nombre d'exemplaire de l'ouvrage
     */
    @Column(name = "nombre_exemplaires")
    private int nombreExemplaires;

    /**
     * date de la réservation
     */
    @Column(name="date_retour_prevue")
    private LocalDateTime dateDeRetourPrevue;

}
