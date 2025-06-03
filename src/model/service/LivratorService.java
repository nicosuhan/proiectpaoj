package model.service;

import model.dao.LivratorDAO;
import model.personal.Livrator;
import model.AuditService;

import java.sql.SQLException;
import java.util.List;

public class LivratorService {
    private final LivratorDAO livratorDAO;
    private final AuditService auditService;

    public LivratorService() {
        this.livratorDAO = new LivratorDAO();
        this.auditService = AuditService.getInstance();
    }

    public void addLivrator(Livrator l) throws SQLException {
        livratorDAO.insert(l);
        auditService.logAction("add_livrator");
    }

    public Livrator getLivratorById(int id) throws SQLException {
        auditService.logAction("get_livrator_by_id");
        return livratorDAO.findById(id);
    }

    public List<Livrator> getAllLivratori() throws SQLException {
        auditService.logAction("get_all_livratori");
        return livratorDAO.findAll();
    }

    public void updateLivrator(Livrator l) throws SQLException {
        livratorDAO.update(l);
        auditService.logAction("update_livrator");
    }

    public void deleteLivratorById(int id) throws SQLException {
        livratorDAO.deleteById(id);
        auditService.logAction("delete_livrator");
    }
}
