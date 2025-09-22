package dao;

import entity.Abonnement;
import java.util.Optional;
import java.util.List;

public interface AbonnementDAO {

    void create(Abonnement a) throws  Exception;
    Optional<Abonnement> findById(String id) throws Exception;
    List<Abonnement> findAll() throws Exception;
    void update(Abonnement a) throws Exception;
    void delete(String id) throws Exception;
}
