package service;

import dao.PaiementDAO;
import dao.implement.PaimentDAOImpl;
import entity.Paiement;
import entity.StatutPaiement;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class PaiementService {

    private PaiementDAO dao =  new PaimentDAOImpl();

    public void Enregistrer(Paiement p) throws Exception { dao.create(p);}

    public List<Paiement> detecterImpayes() throws Exception {
        return dao.findAll().stream().filter(p -> p.getStatut() == StatutPaiement.NON_PAYE ).collect(Collectors.toList());
    }

}
