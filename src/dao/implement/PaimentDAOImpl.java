package dao.implement;

import dao.PaiementDAO;
import entity.Paiement;
import entity.StatutPaiement;
import util.DbConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
            ps.setDate(4,Date.valueOf(p.getDatePaiement()));
            ps.setString(5,p.getTypePaiement());
            ps.setString(6,p.getStatut().name());
            ps.executeUpdate();
        }

    }

    @Override
    public Optional<Paiement> findById(String id) throws Exception {
        String sql = "SELECT * FROM paiement WHERE id_paiement = ?";
        try(Connection c = DbConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                Paiement p = new Paiement();
                p.setIdPaiement(rs.getString("id_paiement"));
                p.setIdAbonnement(rs.getString("id_abonnement"));
                p.setDateEcheance(rs.getDate("date_echeance").toLocalDate());
                p.setDatePaiement(rs.getDate("date_paiement").toLocalDate());
                p.setTypePaiement(rs.getString("type_paiement"));
                p.setStatut(StatutPaiement.valueOf(rs.getString("statut")));

                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Paiement> findByAbonnement(String abonnementId) throws Exception {
        ArrayList<Paiement> pm = new ArrayList<>();
        String sql = "SELECT FROM paiement WHERE id_abonnement = ?";
        try(Connection c = DbConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,abonnementId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Paiement p = new Paiement();
                p.setIdPaiement(rs.getString("id_paiement"));
                p.setIdAbonnement(rs.getString("id_abonnement"));
                p.setDateEcheance(rs.getDate("date_echeance").toLocalDate());
                p.setDatePaiement(rs.getDate("date_paiement").toLocalDate());
                p.setTypePaiement(rs.getString("type_paiement"));
                p.setStatut(StatutPaiement.valueOf(rs.getString("status")));
                pm.add(p);
            }
            return pm;
        }
    }

    @Override
    public List<Paiement> findAll() throws Exception {
        ArrayList<Paiement> pm = new ArrayList<>();
        String sql = "SELECT * FROM paiement";
        try( Connection c = DbConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Paiement p = new Paiement();
                p.setIdPaiement(rs.getString("id_paiement"));
                p.setIdAbonnement(rs.getString("id_abonnement"));
                p.setDateEcheance(rs.getDate("date_echeance").toLocalDate());
                p.setDatePaiement(rs.getDate("date_paiement").toLocalDate());
                p.setTypePaiement(rs.getString("type_paiement"));
                p.setStatut(StatutPaiement.valueOf(rs.getString("status")));
                pm.add(p);
            }
        }
        return pm;
    }

    @Override
    public void update(Paiement p,String id) throws Exception {
        String sql = "UPDATE paiement SET id_paiement = ?, id_abonnement = ?, date_echeance = ?, date_paiement = ?, type_paiement = ?, statut = ? WHERE id_paiement = ?";
        try(Connection c = DbConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,p.getIdPaiement());
            ps.setString(2,p.getIdAbonnement());
            ps.setDate(3, Date.valueOf(p.getDateEcheance()));
            ps.setDate(4, Date.valueOf(p.getDatePaiement()));
            ps.setString(5,p.getTypePaiement());
            ps.setString(6,p.getStatut().name());
            ps.setString(7,id);
            ps.executeUpdate();
        }

    }

    @Override
    public void delete(String id) throws Exception {
        String sql = "DELETE from paiement where id = ?";
        try(Connection c =  DbConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,id);
            ps.executeUpdate();
        }

    }
}
