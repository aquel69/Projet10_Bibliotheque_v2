package fr.lardon.bibliocataloguelivres.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name="adresse", schema = "public")
/**
 * classe représentant une adresse
 */
public class Adresse {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="project_generator")
    @SequenceGenerator(name="project_generator", sequenceName="adresse_id_seq", initialValue = 5, allocationSize = 1)
    @Column(name="id_adresse")
    private int idAdresse;

    /**
     * numéro de l'adresse
     */
    @Column(name = "numero")
    private String numero;

    /**
     * rue de l'adresse
     */
    @Column(name = "rue")
    private String rue;

    /**
     * complément de l'adresse
     */
    @Column(name = "complement")
    private String complement;

    /**
     * code postal de l'adresse
     */
    @Column(name = "code_postal")
    private String codePostal;

    /**
     * ville de l'adresse
     */
    @Column(name = "ville")
    private String ville;

}
