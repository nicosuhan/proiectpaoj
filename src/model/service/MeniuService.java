package model.service;

import model.AuditService;
import model.dao.MeniuDAO;
import model.local.Meniu;

import java.sql.SQLException;
import java.util.List;

public class MeniuService {
    private final MeniuDAO meniuDAO;
    private final AuditService auditService;

    public MeniuService() {
        this.meniuDAO = new MeniuDAO();
        this.auditService = AuditService.getInstance();
    }

    public void addMeniu(Meniu meniu) throws SQLException {
        MeniuDAO.insert(meniu);
        auditService.logAction("add_meniu");
    }

    public Meniu getMeniuById(int id) throws SQLException {
        auditService.logAction("get_meniu_by_id");
        return meniuDAO.findById(id);
    }

    public List<Meniu> getAllMeniuri() throws SQLException {
        auditService.logAction("get_all_meniuri");
        return meniuDAO.findAll();
    }

    public void updateMeniu(Meniu meniu) throws SQLException {
        meniuDAO.update(meniu);
        auditService.logAction("update_meniu");
    }

    public void deleteMeniuById(int id) throws SQLException {
        meniuDAO.deleteById(id);
        auditService.logAction("delete_meniu");
    }
}
