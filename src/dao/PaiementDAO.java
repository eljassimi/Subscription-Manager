package dao;

import entity.Paiement;

import java.util.List;
import java.util.Optional;

public interface PaiementDAO {
    void create(Paiement p) throws Exception;
    Optional<Paiement> findById(String id) throws Exception;
    List<Paiement> findByAbonnement(String abonnementId) throws Exception;
    List<Paiement> findAll() throws Exception;
    void update(Paiement p) throws Exception;
    void delete(String id) throws Exception;
}
