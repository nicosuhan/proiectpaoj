package model.service;

import model.AuditService;
import model.DatabaseConnection;
import model.comanda.Comanda;
import model.dao.ComandaDAO;
import model.produs.FastFood;
import model.produs.Produs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ComandaService {
    private final ComandaDAO comandaDAO;
    private final AuditService auditService;

    public ComandaService() {
        this.comandaDAO = new ComandaDAO();
        this.auditService = AuditService.getInstance();
    }

    public void addComanda(Comanda c) {
        comandaDAO.create(c);
        auditService.logAction("add_comanda");
    }

    public List<Comanda> getAllComenzi() {
        auditService.logAction("get_all_comenzi");
        return comandaDAO.findAll();
    }

    public void updateStatus(int id, String statusNou) {
        comandaDAO.updateStatus(id, statusNou);
        auditService.logAction("update_status_comanda");
    }

    public void deleteComandaById(int id) {
        comandaDAO.delete(id);
        auditService.logAction("delete_comanda");
    }

    public void loadProdusePentruComanda(Comanda comanda) {
        String sql = "SELECT p.id, p.nume, p.pret, p.gramaj, p.disponibil, p.categorie_id " +
                "FROM Comanda_Produs cp " +
                "JOIN Produs p ON cp.produs_id = p.id " +
                "WHERE cp.comanda_id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, comanda.getId());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Produs produs = new Produs(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getDouble("pret"),
                        rs.getInt("gramaj"),
                        rs.getBoolean("disponibil"),
                        new FastFood() // poți înlocui cu logica reală dacă ai categorii multiple
                );
                comanda.adaugaProdus(produs);
            }
        } catch (SQLException e) {
            System.err.println("Eroare la încărcarea produselor pentru comanda " + comanda.getId() + ": " + e.getMessage());
        }
    }



}
