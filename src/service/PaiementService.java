package service;

import dao.AbonnementDAO;
import dao.PaiementDAO;
import dao.implement.AbonnementDAOImpl;
import dao.implement.PaimentDAOImpl;
import entity.Abonnement;
import entity.Paiement;
import entity.StatutPaiement;

import java.util.List;
import java.util.stream.Collectors;

public class PaiementService {

    private PaiementDAO PaiementDAO =  new PaimentDAOImpl();
    private AbonnementDAO abonnementDAO = new AbonnementDAOImpl();

    public void Enregistrer(Paiement p) throws Exception { PaiementDAO.create(p);}
    public void Update(Paiement p, String id) throws Exception {PaiementDAO.update(p,id);}
    public void Delete(String id) throws Exception{PaiementDAO.delete(id);}

    public List<Paiement> detecterImpayes() throws Exception {
        return PaiementDAO.findAll().stream().filter(p -> p.getStatut() == StatutPaiement.NON_PAYE || p.getStatut() == StatutPaiement.EN_RETARD).collect(Collectors.toList());
    }

    public double montantTotalImpayesParAbonnement(String abonnementId) throws Exception {

        Abonnement abonnement = abonnementDAO.findById(abonnementId)
                .orElseThrow(() -> new Exception("Abonnement non trouve"));

        List<Paiement> paiementsImpayes = PaiementDAO.findByAbonnement(abonnementId).stream()
                .filter(p -> p.getStatut() == StatutPaiement.NON_PAYE
                        || p.getStatut() == StatutPaiement.EN_RETARD)
                .collect(Collectors.toList());

        return paiementsImpayes.size() * abonnement.getMontantMensuel();
    }
    public double montantTotalpayesParAbonnement(String abonnementId) throws Exception {

        Abonnement abonnement = abonnementDAO.findById(abonnementId)
                .orElseThrow(() -> new Exception("Abonnement non trouve"));

        List<Paiement> paiementsImpayes = PaiementDAO.findByAbonnement(abonnementId).stream()
                .filter(p -> p.getStatut() == StatutPaiement.PAYE)
                .collect(Collectors.toList());

        return paiementsImpayes.size() * abonnement.getMontantMensuel();
    }

    public Paiement findById(String id) throws Exception { return PaiementDAO.findById(id).get();}

    public List<Paiement> GetLastFivePaiement()throws Exception{ return PaiementDAO.findAll().stream().limit(5).collect(Collectors.toList()); }

}
