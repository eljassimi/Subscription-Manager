package service;

import dao.AbonnementDAO;
import dao.implement.AbonnementDAOImpl;
import entity.Abonnement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AbonnementService {

    private AbonnementDAO dao = new AbonnementDAOImpl();

    public void Enregistrer(Abonnement a) throws Exception { dao.create(a); }
    public void update(Abonnement a) throws Exception {dao.update(a);}
    public void delete(String id) throws Exception {dao.delete(id);}
    public Optional<Abonnement> findById(String id) throws Exception { return dao.findById(id); }
    public List<Abonnement> findAll() throws Exception { return dao.findAll(); }
    public List<Abonnement> findActive() throws Exception {
        return dao.findAll().stream().filter(ab -> ab.getStatut().name().equals("ACTIVE")).collect(Collectors.toList());
    }
}
