package entity;

import java.time.LocalDate;

public class AbonnementAvecEngagement extends Abonnement {
    private int dureeEngagementMois;

    public AbonnementAvecEngagement(){}
    public AbonnementAvecEngagement(String nomService, double montantMensuel, LocalDate dateDebut, LocalDate dateFin, StatutAbonnement statut, int dureeEngagementMois) {
        super(nomService, montantMensuel, dateDebut, dateFin, statut);
        this.dureeEngagementMois = dureeEngagementMois;
    }

    public void setDureeEngagementMois(int d){
        this.dureeEngagementMois = d;
    }

    public int getDureeEngagementMois() { return dureeEngagementMois; }
}
