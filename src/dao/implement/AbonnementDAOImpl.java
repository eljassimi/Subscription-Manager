package dao.implement;

import dao.AbonnementDAO;
import entity.Abonnement;
import entity.AbonnementAvecEngagement;
import util.DbConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Types;
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
        return Optional.empty();
    }

    @Override
    public List<Abonnement> findAll() throws Exception {
        return Collections.emptyList();
    }

    @Override
    public void update(Abonnement a) throws Exception {

    }

    @Override
    public void delete(String id) throws Exception {

    }
}
