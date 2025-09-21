package entity;

import java.time.LocalDate;

public class AbonnementAvecEngagement {
    private int dureeEngagementMois;

    public AbonnementAvecEngagement(String nomService, double montantMensuel, LocalDate dateDebut, LocalDate dateFin, StatutAbonnement statut, int dureeEngagementMois) {
        super();
        this.dureeEngagementMois = dureeEngagementMois;
    }

    public int getDureeEngagementMois() { return dureeEngagementMois; }
}
