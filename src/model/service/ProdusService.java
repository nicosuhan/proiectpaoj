package model.service;

import model.dao.ProdusDAO;
import model.produs.Produs;
import model.AuditService;

import java.sql.SQLException;
import java.util.List;

public class ProdusService {
    private final ProdusDAO produsDAO = new ProdusDAO();
    private final AuditService audit = AuditService.getInstance();

    public void addProdus(Produs produs) throws SQLException {
        produsDAO.create(produs);
        audit.logAction("add_produs");
    }

    public List<Produs> getAllProduse() throws SQLException {
        audit.logAction("get_all_produse");
        return produsDAO.findAll();
    }

    public Produs getProdusById(int id) {
        audit.logAction("get_produs_by_id");
        return produsDAO.findById(id);
    }

    public void updateProdus(Produs produs) throws SQLException {
        produsDAO.update(produs);
        audit.logAction("update_produs");
    }

    public void deleteProdusById(int id) throws SQLException {
        produsDAO.deleteById(id);
        audit.logAction("delete_produs_by_id");
    }
}
