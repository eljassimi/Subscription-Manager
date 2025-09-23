package service;

import dao.AbonnementDAO;
import dao.PaiementDAO;
import dao.implement.AbonnementDAOImpl;
import dao.implement.PaimentDAOImpl;
import entity.Abonnement;
import entity.Paiement;
import entity.StatutPaiement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaiementService {

    private PaiementDAO dao =  new PaimentDAOImpl();
    private AbonnementDAO abonnementDAO = new AbonnementDAOImpl();

    public void Enregistrer(Paiement p) throws Exception { dao.create(p);}

    public List<Paiement> detecterImpayes() throws Exception {
        return dao.findAll().stream().filter(p -> p.getStatut() == StatutPaiement.NON_PAYE || p.getStatut() == StatutPaiement.EN_RETARD).collect(Collectors.toList());
    }

    public double montantTotalImpayesParAbonnement(String abonnementId) throws Exception {

        Abonnement abonnement = abonnementDAO.findById(abonnementId)
                .orElseThrow(() -> new Exception("Abonnement non trouve"));

        List<Paiement> paiementsImpayes = dao.findByAbonnement(abonnementId).stream()
                .filter(p -> p.getStatut() == StatutPaiement.NON_PAYE
                        || p.getStatut() == StatutPaiement.EN_RETARD)
                .collect(Collectors.toList());

        return paiementsImpayes.size() * abonnement.getMontantMensuel();
    }

}
