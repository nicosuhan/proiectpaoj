package model.dao;

import model.DatabaseConnection;
import model.local.Local;
import model.local.Meniu;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocalDAO {

    // INSERT
    public static void insert(Local local) {
        String sql = "INSERT INTO Local (id, numeLocal, adresa, meniu_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, local.getId());
            pstmt.setString(2, local.getNumeLocal());
            pstmt.setString(3, local.getAdresa());
            pstmt.setInt(4, local.getMeniu().getId());

            pstmt.executeUpdate();
            System.out.println("✔️ Local inserat: " + local.getNumeLocal());

        } catch (SQLException e) {
            System.err.println("❌ Eroare la inserare local: " + e.getMessage());
        }
    }

    // FIND BY ID
    public Local findById(int id) {
        String sql = "SELECT * FROM Local WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String nume = rs.getString("numeLocal");
                String adresa = rs.getString("adresa");
                int meniuId = rs.getInt("meniu_id");

                Meniu meniu = new Meniu(meniuId, "Meniu asociat"); // Simplificat
                return new Local(id, nume, adresa, meniu);
            }

        } catch (SQLException e) {
            System.err.println("❌ Eroare la căutare local: " + e.getMessage());
        }
        return null;
    }

    // FIND ALL
    public List<Local> findAll() {
        String sql = "SELECT * FROM Local";
        List<Local> localuri = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nume = rs.getString("numeLocal");
                String adresa = rs.getString("adresa");
                int meniuId = rs.getInt("meniu_id");
                Meniu meniu = new Meniu(meniuId, "Meniu asociat"); // Simulat
                localuri.add(new Local(id, nume, adresa, meniu));
            }

        } catch (SQLException e) {
            System.err.println("❌ Eroare la listare localuri: " + e.getMessage());
        }
        return localuri;
    }

    // UPDATE
    public void update(Local local) {
        String sql = "UPDATE Local SET numeLocal = ?, adresa = ?, meniu_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, local.getNumeLocal());
            pstmt.setString(2, local.getAdresa());
            pstmt.setInt(3, local.getMeniu().getId());
            pstmt.setInt(4, local.getId());

            pstmt.executeUpdate();
            System.out.println("✔️ Local actualizat: " + local.getNumeLocal());

        } catch (SQLException e) {
            System.err.println("❌ Eroare la actualizare local: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteById(int id) {
        String sql = "DELETE FROM Local WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("✔️ Local șters cu ID: " + id);

        } catch (SQLException e) {
            System.err.println("❌ Eroare la ștergere local: " + e.getMessage());
        }
    }


}
