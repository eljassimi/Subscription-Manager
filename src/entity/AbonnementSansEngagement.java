package entity;

import java.time.LocalDate;

import static util.DateUtils.nextMonth;

public class AbonnementSansEngagement extends Abonnement {

    public AbonnementSansEngagement(String nomService, double montantMensuel, LocalDate dateDebut, StatutAbonnement statut){
        super(nomService, montantMensuel, dateDebut, statut);
        this.dateFin = nextMonth(this.dateDebut,1);
    }
}
