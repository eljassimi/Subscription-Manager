package dao.implement;

import dao.AbonnementDAO;
import entity.Abonnement;
import entity.AbonnementAvecEngagement;
import entity.AbonnementSansEngagement;
import entity.StatutAbonnement;
import util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AbonnementDAOImpl implements AbonnementDAO {

    @Override
    public void create(Abonnement a) throws Exception {
        String sql = "INSERT INTO abonnement(id, nom_service, montant_mensuel, date_debut, date_fin, statut, type_abonnement, duree_engagement_mois) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection c = DbConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, a.getId());
            ps.setString(2, a.getNomService());
            ps.setDouble(3, a.getMontantMensuel());
            ps.setDate(4, Date.valueOf(a.getDateDebut()));
            ps.setDate(5, a.getDateFin() == null ? null : Date.valueOf(a.getDateFin()));
            ps.setString(6, a.getStatut().name());
            if (a instanceof AbonnementAvecEngagement) {
                ps.setString(7, "AVEC_ENGAGEMENT");
                ps.setInt(8, ((AbonnementAvecEngagement) a).getDureeEngagementMois());
            } else {
                ps.setString(7, "SANS_ENGAGEMENT");
                ps.setNull(8, Types.INTEGER);
            }
            ps.executeUpdate();
        }
    }

    @Override
    public Optional<Abonnement> findById(String id) throws Exception {
        String sql = "SELECT FROM abonnement Where id = ?";
        try(Connection c = DbConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String type = rs.getString("type_abonnement");
                Abonnement a;
                if("AVEC_ENGAGEMENT".equals(type)) {
                    a = new AbonnementAvecEngagement(rs.getString("nom_service"), rs.getDouble("montant_mensuel"), rs.getDate("date_debut").toLocalDate(), rs.getDate("date_fin") == null ? null : rs.getDate("date_fin").toLocalDate(), StatutAbonnement.valueOf(rs.getString("statut")), rs.getInt("duree_engagement_mois"));
                } else {
                    a = new AbonnementSansEngagement(rs.getString("nom_service"), rs.getDouble("montant_mensuel"), rs.getDate("date_debut").toLocalDate(), rs.getDate("date_fin") == null ? null : rs.getDate("date_fin").toLocalDate(), StatutAbonnement.valueOf(rs.getString("statut")));
                }
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Abonnement> findAll() throws Exception {
        List<Abonnement> res = new ArrayList<>();
        String sql = "SELECT * from abonnement";
        try(Connection c = DbConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Abonnement a = new Abonnement();
                a.setId(rs.getString("id"));
                a.setNomService(rs.getString("nom_service"));
                a.setMontantMensuel(rs.getDouble("montant_mensuel"));
                a.setDateDebut(rs.getDate("date_debut").toLocalDate());
                a.setDateFin(rs.getDate("date_fin") == null ? null : rs.getDate("date_fin").toLocalDate());
                a.setStatut(StatutAbonnement.valueOf(rs.getString("statut")));
                res.add(a);
            }
        }
        return res;
    }

    @Override
    public void update(Abonnement a) throws Exception {

    }

    @Override
    public void delete(String id) throws Exception {

    }
}
