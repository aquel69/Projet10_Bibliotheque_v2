package fr.lardon.bibliocataloguelivres.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name=("livre"))
/**
 * classe ne permettant de récupérer juste l'id d'un livre pour la gestion des tops
 */
public class LivreTop {

    @Id
    @Column(name="id_livre")
    private int idLivre;

}
