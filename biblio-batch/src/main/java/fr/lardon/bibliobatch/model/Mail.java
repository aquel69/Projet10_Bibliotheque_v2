package fr.lardon.bibliobatch.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
/**
 * classe représentant le mail a envoyé
 */
public class Mail {

    private String from;
    /**
     * destinataire du mail
     */
    private String to;
    /**
     * objet du mail
     */
    private String subject;
/*    private String content;*/
    /**
     * ouvrage
     */
    private Ouvrage ouvrage;
    /**
     * abonné
     */
    private AbonnePret abonnePret;
    /**
     * prêt
     */
    private Pret pret;
    /**
     * bibliothèque
     */
    private Bibliotheque bibliotheque;

}
