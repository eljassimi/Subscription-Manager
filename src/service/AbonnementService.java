package service;

import dao.AbonnementDAO;
import dao.implement.AbonnementDAOImpl;
import entity.Abonnement;
import entity.AbonnementAvecEngagement;
import util.DateUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static util.DateUtils.nextMonth;

public class AbonnementService {

    private AbonnementDAO dao = new AbonnementDAOImpl();

    public void Enregistrer(Abonnement a) throws Exception { dao.create(a); }
    public void update(Abonnement a,String id) throws Exception {dao.update(a,id);}
    public void delete(String id) throws Exception {dao.delete(id);}
    public Optional<Abonnement> findById(String id) throws Exception { return dao.findById(id); }
    public List<Abonnement> findAll() throws Exception { return dao.findAll(); }
    public List<Abonnement> findActive() throws Exception {
        return dao.findAll().stream().filter(ab -> ab.getStatut().name().equals("ACTIVE")).collect(Collectors.toList());
    }
    public List<LocalDate> genererEcheances(Abonnement a) {
        List<LocalDate> echeances = new ArrayList<>();

        LocalDate start = a.getDateDebut();

        if (a instanceof AbonnementAvecEngagement) {
            int duree = ((AbonnementAvecEngagement) a).getDureeEngagementMois();

            LocalDate current = start.plusMonths(1);
            for (int i = 0; i < duree; i++) {
                echeances.add(current);
                current = current.plusMonths(1);
            }
        } else {
            echeances.add(start.plusMonths(1));
        }
        return echeances;
    }


}
