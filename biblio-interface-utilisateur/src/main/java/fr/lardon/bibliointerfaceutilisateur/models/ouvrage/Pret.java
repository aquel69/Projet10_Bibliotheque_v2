package fr.lardon.bibliointerfaceutilisateur.models.ouvrage;

import fr.lardon.bibliointerfaceutilisateur.models.gestionutilisateur.Abonne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
/**
 * classe représentant un prêt
 */
public class Pret {

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
     * status de l'emprunt
     */
    private String statut;

    /**
     * status de l'emprunt
     */
    private String statutPriorite;

    /**
     * l'abonné a le droit de prolonger l'ouvrage emprunté une fois
     */
    private boolean prolongation;

    /**
     * l'abonné a rendu l'ouvrage
     */
    private boolean rendu;

    /**
     * abonné du prêt
     */
    private Abonne abonnePret;

    /**
     * ouvrage du prêt
     */
    private Ouvrage ouvragePret;

}
