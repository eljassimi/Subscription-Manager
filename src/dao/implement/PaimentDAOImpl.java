package dao.implement;

import dao.PaiementDAO;
import entity.Paiement;
import util.DbConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PaimentDAOImpl implements PaiementDAO {

    @Override
    public void create(Paiement p) throws Exception {
        String sql = "INSERT INTO paiement(id_paiement,id_abonnement,date_echeance,date_paiement,type_paiement,statut) values (?,?,?,?,?,?)";
        try(Connection c = DbConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,p.getIdPaiement());
            ps.setString(2,p.getIdAbonnement());
            ps.setDate(3, Date.valueOf(p.getDateEcheance()));
            ps.setDate(4,Date.valueOf(p.getIdPaiement()));
            ps.setString(5,p.getTypePaiement());
            ps.setString(6,p.getStatut().name());
            ps.executeUpdate();

        }

    }

    @Override
    public Optional<Paiement> findById(String id) throws Exception {
        return Optional.empty();
    }

    @Override
    public List<Paiement> findByAbonnement(String abonnementId) throws Exception {
        return Collections.emptyList();
    }

    @Override
    public List<Paiement> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public void update(Paiement p) throws Exception {

    }

    @Override
    public void delete(String id) throws Exception {

    }
}
