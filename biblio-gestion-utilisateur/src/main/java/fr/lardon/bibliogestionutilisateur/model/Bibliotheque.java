package fr.lardon.bibliogestionutilisateur.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name=("bibliotheque"))
/**
 * classe représentant une bibliothèque
 */
public class Bibliotheque {

    @Id
    @Column(name="numero_siret")
    private String numeroSiret;

    /**
     * nom de la bibliothèque
     */
    @NonNull
    @Column(name="nom")
    private String nom;

    /**
     * code de la bibliothèque
     */
    @NonNull
    @Column(name="code")
    private String code;

    /**
     * Role de l'abonné
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_adresse")
    private Adresse adresse;

}
