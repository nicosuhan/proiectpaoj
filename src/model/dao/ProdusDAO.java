package model.dao;

import model.DatabaseConnection;
import model.produs.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdusDAO {

    // CREATE
    public void create(Produs produs) throws SQLException {
        String sql = "INSERT INTO Produs (nume, pret, gramaj, disponibil, categorie_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, produs.getNume());
            pstmt.setDouble(2, produs.getPret());
            pstmt.setInt(3, produs.getGramaj());
            pstmt.setBoolean(4, produs.isDisponibil());
            pstmt.setInt(5, produs.getCategorie().getId());
            pstmt.executeUpdate();
        }
    }

    // READ
    public Produs findById(int id) {
        String sql = "SELECT * FROM Produs WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nume = rs.getString("nume");
                double pret = rs.getDouble("pret");
                int gramaj = rs.getInt("gramaj");
                boolean disponibil = rs.getBoolean("disponibil");
                int categorieId = rs.getInt("categorie_id");

                CategorieProdus categorie = getCategorieById(categorieId);
                return new Produs(nume, pret, gramaj, disponibil, categorie);
            }
        } catch (SQLException e) {
            System.err.println("Eroare la findById produs: " + e.getMessage());
        }
        return null;
    }

    public List<Produs> findAll() throws SQLException {
        String sql = "SELECT * FROM Produs";
        List<Produs> produse = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nume = rs.getString("nume");
                double pret = rs.getDouble("pret");
                int gramaj = rs.getInt("gramaj");
                boolean disponibil = rs.getBoolean("disponibil");
                int categorieId = rs.getInt("categorie_id");

                CategorieProdus categorie = getCategorieById(categorieId);
                produse.add(new Produs(nume, pret, gramaj, disponibil, categorie));
            }
        }
        return produse;
    }

    // UPDATE
    public void update(Produs produs) throws SQLException {
        String sql = "UPDATE Produs SET pret=?, gramaj=?, disponibil=?, categorie_id=? WHERE nume=?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, produs.getPret());
            pstmt.setInt(2, produs.getGramaj());
            pstmt.setBoolean(3, produs.isDisponibil());
            pstmt.setInt(4, produs.getCategorie().getId());
            pstmt.setString(5, produs.getNume());
            pstmt.executeUpdate();
        }
    }

    // DELETE BY NAME
    public void deleteByName(String nume) throws SQLException {
        String sql = "DELETE FROM Produs WHERE nume = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nume);
            pstmt.executeUpdate();
        }
    }

    // DELETE BY ID (METODA LIPSĂ PE CARE AI FOLOSIT-O ÎN MAIN)
    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM Produs WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // UTILITAR - conversie id categorie în obiect categorie
    private CategorieProdus getCategorieById(int id) {
        return switch (id) {
            case 1 -> new FastFood();
            case 2 -> new Japonez();
            case 3 -> new Vegetarian();
            case 4 -> new Traditional();
            default -> throw new IllegalArgumentException("Categorie necunoscută: " + id);
        };
    }
    public void insert(Produs produs) {
        String sql = "INSERT INTO Produs (nume, pret, gramaj, disponibil, categorie_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, produs.getNume());
            pstmt.setDouble(2, produs.getPret());
            pstmt.setInt(3, produs.getGramaj());
            pstmt.setBoolean(4, produs.isDisponibil());
            pstmt.setInt(5, produs.getCategorie().getId());

            pstmt.executeUpdate();
            System.out.println("✔️ Produs inserat cu succes: " + produs.getNume());

        } catch (SQLException e) {
            System.err.println("❌ Eroare la inserarea produsului: " + e.getMessage());
        }
    }


}
