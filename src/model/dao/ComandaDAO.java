package model.dao;

import model.comanda.*;
import model.personal.Livrator;
import model.personal.Ospatar;
import model.produs.Produs;
import model.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComandaDAO {

    public void create(Comanda comanda) {
        String insertComandaSQL = "INSERT INTO Comanda (dataComanda, total, status, tipComanda, local_id, adresaLivrare, durataEstimataLivrare, livrator_id, numarMasa, rezervare, ospatar_id, utilizator_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String insertProdusSQL = "INSERT INTO Comanda_Produs (comanda_id, produs_id, cantitate) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement psComanda = conn.prepareStatement(insertComandaSQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psProdus = conn.prepareStatement(insertProdusSQL)) {

            psComanda.setTimestamp(1, Timestamp.valueOf(comanda.getDataComanda()));
            psComanda.setDouble(2, comanda.getTotal());
            psComanda.setString(3, comanda.getStatus());
            psComanda.setString(4, comanda instanceof ComandaLivrare ? "livrare" : "restaurant");
            psComanda.setInt(5, 1); // local_id dummy

            if (comanda instanceof ComandaLivrare cl) {
                psComanda.setString(6, cl.getAdresaLivrare());
                psComanda.setInt(7, cl.getDurataEstimataLivrare());
                psComanda.setInt(8, cl.getLivrator().getId());
                psComanda.setNull(9, Types.INTEGER);
                psComanda.setNull(10, Types.BOOLEAN);
                psComanda.setNull(11, Types.INTEGER);
            } else if (comanda instanceof ComandaRestaurant cr) {
                psComanda.setNull(6, Types.VARCHAR);
                psComanda.setNull(7, Types.INTEGER);
                psComanda.setNull(8, Types.INTEGER);
                psComanda.setInt(9, cr.getNumarMasa());
                psComanda.setBoolean(10, cr.isRezervare());
                psComanda.setInt(11, cr.getOspatar().getId());
            } else {
                psComanda.setNull(6, Types.VARCHAR);
                psComanda.setNull(7, Types.INTEGER);
                psComanda.setNull(8, Types.INTEGER);
                psComanda.setNull(9, Types.INTEGER);
                psComanda.setNull(10, Types.BOOLEAN);
                psComanda.setNull(11, Types.INTEGER);
            }

            psComanda.setInt(12, comanda.getUtilizatorId());

            psComanda.executeUpdate();

            ResultSet rs = psComanda.getGeneratedKeys();
            if (rs.next()) {
                int comandaId = rs.getInt(1);
                for (Produs p : comanda.getProduse()) {
                    psProdus.setInt(1, comandaId);
                    psProdus.setInt(2, p.getId());
                    psProdus.setInt(3, 1);
                    psProdus.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Comanda read(int id) {
        return null; // Placeholder
    }

    // 🔧 AICI e singura modificare
    public List<Comanda> findAll() {
        List<Comanda> comenzi = new ArrayList<>();
        String sql = "SELECT * FROM Comanda";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int utilizatorId = rs.getInt("utilizator_id");
                String tip = rs.getString("tipComanda");
                double total = rs.getDouble("total");
                String status = rs.getString("status");

                if ("livrare".equalsIgnoreCase(tip)) {
                    Livrator livrator = new Livrator(rs.getInt("livrator_id"), "NumeLivrator", "Scuter", "Zona");
                    ComandaLivrare cl = new ComandaLivrare(id, utilizatorId,
                            rs.getString("adresaLivrare"),
                            rs.getInt("durataEstimataLivrare"),
                            livrator);
                    cl.setStatus(status);
                    comenzi.add(cl);
                } else if ("restaurant".equalsIgnoreCase(tip)) {
                    Ospatar ospatar = new Ospatar(rs.getInt("ospatar_id"), "NumeOspatar", 5, "zi");
                    ComandaRestaurant cr = new ComandaRestaurant(id, utilizatorId,
                            rs.getInt("numarMasa"),
                            rs.getBoolean("rezervare"),
                            ospatar);
                    cr.setStatus(status);
                    comenzi.add(cr);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comenzi;
    }

    public void updateStatus(int id, String newStatus) {
        String sql = "UPDATE Comanda SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newStatus);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sqlComandaProdus = "DELETE FROM Comanda_Produs WHERE comanda_id = ?";
        String sqlComanda = "DELETE FROM Comanda WHERE id = ?";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps1 = conn.prepareStatement(sqlComandaProdus);
             PreparedStatement ps2 = conn.prepareStatement(sqlComanda)) {

            ps1.setInt(1, id);
            ps1.executeUpdate();

            ps2.setInt(1, id);
            ps2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
