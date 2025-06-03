package model.service;

import model.dao.LocalDAO;
import model.local.Local;
import model.AuditService;

import java.util.List;

public class LocalService {
    private final LocalDAO localDAO;
    private final AuditService auditService;

    public LocalService() {
        this.localDAO = new LocalDAO();
        this.auditService = AuditService.getInstance();
    }

    public void addLocal(Local local) {
        localDAO.insert(local);
        auditService.logAction("add_local");
    }

    public Local getLocalById(int id) {
        auditService.logAction("get_local_by_id");
        return localDAO.findById(id);
    }

    public List<Local> getAllLocaluri() {
        auditService.logAction("get_all_localuri");
        return localDAO.findAll();
    }

    public void updateLocal(Local local) {
        localDAO.update(local);
        auditService.logAction("update_local");
    }

    public void deleteLocalById(int id) {
        localDAO.deleteById(id);
        auditService.logAction("delete_local");
    }
}
