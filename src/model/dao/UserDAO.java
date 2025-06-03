package model.dao;

import model.DatabaseConnection;
import model.user.Admin;
import model.user.Client;
import model.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void create(User user) {
        String sql = "INSERT INTO Utilizator (nume, email, tip, adresa, telefon, varsta) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getNume());
            pstmt.setString(2, user.getEmail());

            if (user instanceof Client client) {
                pstmt.setString(3, "client");
                pstmt.setString(4, client.getAdresa());
                pstmt.setString(5, client.getTelefon());
                pstmt.setInt(6, client.getVarsta());
            } else if (user instanceof Admin) {
                pstmt.setString(3, "admin");
                pstmt.setNull(4, Types.VARCHAR);
                pstmt.setNull(5, Types.VARCHAR);
                pstmt.setNull(6, Types.INTEGER);
            }

            pstmt.executeUpdate();
            System.out.println("✅ Utilizator adăugat cu succes!");

        } catch (SQLException e) {
            System.err.println("❌ Eroare la create: " + e.getMessage());
        }
    }

    public List<User> findAll() {
        List<User> utilizatori = new ArrayList<>();
        String sql = "SELECT * FROM Utilizator";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                utilizatori.add(extractUserFromResultSet(rs));
            }

        } catch (SQLException e) {
            System.err.println("❌ Eroare la findAll: " + e.getMessage());
        }

        return utilizatori;
    }

    public User findById(int id) {
        String sql = "SELECT * FROM Utilizator WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }

        } catch (SQLException e) {
            System.err.println("❌ Eroare la findById: " + e.getMessage());
        }
        return null;
    }

    public void update(User user) {
        String sql = "UPDATE Utilizator SET nume = ?, email = ?, tip = ?, adresa = ?, telefon = ?, varsta = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getNume());
            pstmt.setString(2, user.getEmail());

            if (user instanceof Client client) {
                pstmt.setString(3, "client");
                pstmt.setString(4, client.getAdresa());
                pstmt.setString(5, client.getTelefon());
                pstmt.setInt(6, client.getVarsta());
            } else if (user instanceof Admin) {
                pstmt.setString(3, "admin");
                pstmt.setNull(4, Types.VARCHAR);
                pstmt.setNull(5, Types.VARCHAR);
                pstmt.setNull(6, Types.INTEGER);
            }

            pstmt.setInt(7, user.getId());

            int affected = pstmt.executeUpdate();
            if (affected > 0) {
                System.out.println("✅ Utilizator actualizat.");
            } else {
                System.out.println("⚠️ Nu a fost găsit utilizatorul pentru update.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Eroare la update: " + e.getMessage());
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM Utilizator WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int affected = pstmt.executeUpdate();

            if (affected > 0) {
                System.out.println("🗑️ Utilizator șters.");
            } else {
                System.out.println("⚠️ Nu există utilizator cu ID-ul dat.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Eroare la delete: " + e.getMessage());
        }
    }

    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nume = rs.getString("nume");
        String email = rs.getString("email");
        String tip = rs.getString("tip");

        if ("client".equalsIgnoreCase(tip)) {
            return new Client(
                    id,
                    nume,
                    email,
                    rs.getString("adresa"),
                    rs.getString("telefon"),
                    rs.getInt("varsta")
            );
        } else {
            return new Admin(id, nume, email);
        }
    }
}
