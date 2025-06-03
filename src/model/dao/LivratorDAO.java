package model.dao;

import model.DatabaseConnection;
import model.personal.Livrator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivratorDAO {

    public void insert(Livrator l) throws SQLException {
        String sql = "INSERT INTO Livrator (nume, mijlocTransport, zonaLivrare) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, l.getNume());
            stmt.setString(2, l.getMijlocTransport());
            stmt.setString(3, l.getZonaLivrare());
            stmt.executeUpdate();
        }
    }

    public Livrator findById(int id) throws SQLException {
        String sql = "SELECT * FROM Livrator WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Livrator(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("mijlocTransport"),
                        rs.getString("zonaLivrare")
                );
            }
        }
        return null;
    }

    public List<Livrator> findAll() throws SQLException {
        String sql = "SELECT * FROM Livrator";
        List<Livrator> lista = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Livrator(
                        rs.getInt("id"),
                        rs.getString("nume"),
                        rs.getString("mijlocTransport"),
                        rs.getString("zonaLivrare")
                ));
            }
        }
        return lista;
    }

    public void update(Livrator l) throws SQLException {
        String sql = "UPDATE Livrator SET nume=?, mijlocTransport=?, zonaLivrare=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, l.getNume());
            stmt.setString(2, l.getMijlocTransport());
            stmt.setString(3, l.getZonaLivrare());
            stmt.setInt(4, l.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM Livrator WHERE id=?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
