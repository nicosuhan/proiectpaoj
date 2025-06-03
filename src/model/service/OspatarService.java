package model.service;

import model.AuditService;
import model.dao.OspatarDAO;
import model.personal.Ospatar;

import java.sql.SQLException;
import java.util.List;

public class OspatarService {
    private final OspatarDAO ospatarDAO;
    private final AuditService auditService;

    public OspatarService() {
        this.ospatarDAO = new OspatarDAO();
        this.auditService = AuditService.getInstance();
    }

    public void addOspatar(Ospatar o) throws SQLException {
        ospatarDAO.insert(o);
        auditService.logAction("add_ospatar");
    }

    public Ospatar getOspatarById(int id) throws SQLException {
        auditService.logAction("get_ospatar_by_id");
        return ospatarDAO.findById(id);
    }

    public List<Ospatar> getAllOspatari() throws SQLException {
        auditService.logAction("get_all_ospatari");
        return ospatarDAO.findAll();
    }

    public void updateOspatar(Ospatar o) throws SQLException {
        ospatarDAO.update(o);
        auditService.logAction("update_ospatar");
    }

    public void deleteOspatarById(int id) throws SQLException {
        ospatarDAO.deleteById(id);
        auditService.logAction("delete_ospatar");
    }
}
