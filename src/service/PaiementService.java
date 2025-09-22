package service;

import dao.PaiementDAO;
import dao.implement.PaimentDAOImpl;
import entity.Paiement;

public class PaiementService {

    private PaiementDAO dao =  new PaimentDAOImpl();
    public void Enregistrer(Paiement p) throws Exception { dao.create(p);}

}
