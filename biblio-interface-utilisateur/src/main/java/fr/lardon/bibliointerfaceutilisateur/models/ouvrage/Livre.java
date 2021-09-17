package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
/**
 * classe représentant un livre
 */
public class Livre {

    private int idLivre;

    /**
     * Titre du livre
     */
    private String titre;

    /**
     * Résumé du livre.
     */
    private String resume;

    /**
     * Liste des auteurs du livre sachant qu'un livre a souvent un auteur mais peut être co-ecrit. Auquel cas, il y a plusieurs auteurs.
     */
    private List<Auteur> auteurs;

    /**
     * Editeur du livre.
     */
    private Editeur editeur;

    /**
     * Date d'édition du livre.
     */
    private Date dateEdition;

    /**
     * Numéro ISBN de l'ouvrage
     */
    private String numISBN13;

    /**
     * Langue de cet ouvrage.
     */
    private String langue;

    /**
     * Genre associé à cet ouvrage.
     */
    private List<Genre> genres;

    /**
     * nombre de pages
     */

   private int nombreDePages;

    /**
     * photo de la couverture liste
     */
    private String petitePhotoCouverture;

    /**
     * photo de la couverture détail
     */

   private String grandePhotoCouverture;

    /**
     * photo de la couverture catalogue
     */
    private String moyennePhotoCouverture;

}
