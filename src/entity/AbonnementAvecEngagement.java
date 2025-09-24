package entity;

import java.time.LocalDate;

import static util.DateUtils.nextMonth;

public class AbonnementAvecEngagement extends Abonnement {
    private int dureeEngagementMois;

    public AbonnementAvecEngagement(){

    }
    public AbonnementAvecEngagement(String nomService, double montantMensuel, LocalDate dateDebut, StatutAbonnement statut, int dureeEngagementMois) {
        super(nomService, montantMensuel, dateDebut, statut);
        this.dureeEngagementMois = dureeEngagementMois;
        this.dateFin = nextMonth(dateDebut,dureeEngagementMois);
    }

    public void setDureeEngagementMois(int d){
        this.dureeEngagementMois = d;
    }

    public int getDureeEngagementMois() { return dureeEngagementMois; }
}
