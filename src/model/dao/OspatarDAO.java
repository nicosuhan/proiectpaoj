package model.dao;

import model.DatabaseConnection;
import model.personal.Ospatar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OspatarDAO {

    public void insert(Ospatar o) throws SQLException {
        String sql = "INSERT INTO Ospatar (nume, experientaAni, tura) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, o.getNume());
            stmt.setInt(2, o.getExperientaAni());
            stmt.setString(3, o.getTura());
            stmt.executeUpdate();
        }
    }

    public Ospatar findById(int id) throws SQLException {
        String sql = "SELECT * FROM Ospatar WHERE id=?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Ospatar(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getInt("experientaAni"),
                        rs.getString("tura")
                );
            }
        }
        return null;
    }

    public List<Ospatar> findAll() throws SQLException {
        String sql = "SELECT * FROM Ospatar";
        List<Ospatar> lista = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Ospatar(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getInt("experientaAni"),
                        rs.getString("tura")
                ));
            }
        }
        return lista;
    }

    public void update(Ospatar o) throws SQLException {
        String sql = "UPDATE Ospatar SET nume=?, experientaAni=?, tura=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, o.getNume());
            stmt.setInt(2, o.getExperientaAni());
            stmt.setString(3, o.getTura());
            stmt.setInt(4, o.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM Ospatar WHERE id=?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
