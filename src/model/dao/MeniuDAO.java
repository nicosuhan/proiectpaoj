// MeniuDAO.java
package model.dao;

import model.DatabaseConnection;
import model.local.Meniu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MeniuDAO {
    public static void insert(Meniu meniu) throws SQLException {
        String sql = "INSERT INTO Meniu (id, nume) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, meniu.getId());
            pstmt.setString(2, meniu.getNume());
            pstmt.executeUpdate();
        }
    }

    public Meniu findById(int id) throws SQLException {
        String sql = "SELECT * FROM Meniu WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Meniu(id, rs.getString("nume"));
            }
        }
        return null;
    }

    public List<Meniu> findAll() throws SQLException {
        List<Meniu> meniuri = new ArrayList<>();
        String sql = "SELECT * FROM Meniu";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                meniuri.add(new Meniu(rs.getInt("id"), rs.getString("nume")));
            }
        }
        return meniuri;
    }

    public void update(Meniu meniu) throws SQLException {
        String sql = "UPDATE Meniu SET nume = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, meniu.getNume());
            pstmt.setInt(2, meniu.getId());
            pstmt.executeUpdate();
        }
    }

    public void deleteById(int id) throws SQLException {
        String sql = "DELETE FROM Meniu WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
