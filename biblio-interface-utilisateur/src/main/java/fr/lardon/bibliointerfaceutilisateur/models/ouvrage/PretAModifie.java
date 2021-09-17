package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
/**
 * classe représentant le prêt a modifié
 */
public class PretAModifie {


    private int idPret;

    /**
     * date d'emprunt
     */
    private LocalDateTime dateDEmprunt;

    /**
     * date de restitution
     */
    private LocalDateTime dateDeRestitution;

    /**
     * l'abonné a le droit de prolonger l'ouvrage emprunté une fois
     */
    private boolean prolongation;

    /**
     * l'abonné a rendu l'ouvrage
     */
    private boolean rendu;

    /**
     * status de l'emprunt
     */
    private String statut;

    /**
     * status de l'emprunt
     */
    private String statutPriorite;

}
