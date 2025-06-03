package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    public static void createTables() {
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {

            createUtilizatorTable(stmt);
            createCategorieProdusTable(stmt);
            createMeniuTable(stmt);
            createLocalTable(stmt);
            createOspatarTable(stmt);
            createLivratorTable(stmt);
            createProdusTable(stmt);
            createComandaTable(stmt);
            createComandaProdusTable(stmt);

            System.out.println("Toate tabelele au fost create cu succes!");

        } catch (SQLException e) {
            System.err.println("Eroare la crearea tabelelor: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createUtilizatorTable(Statement stmt) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Utilizator (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nume VARCHAR(100) NOT NULL,
                    email VARCHAR(100) NOT NULL UNIQUE,
                    tip VARCHAR(20) NOT NULL,
                    adresa VARCHAR(255),
                    telefon VARCHAR(20),
                    varsta INT
                )
                """;
        stmt.execute(sql);
    }

    private static void createCategorieProdusTable(Statement stmt) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS CategorieProdus (
                    id INT PRIMARY KEY,
                    numeCategorie VARCHAR(100) NOT NULL,
                    descriere VARCHAR(255),
                    origineCulturala VARCHAR(100),
                    timpMediuPreparare INT,
                    nivelCalorii VARCHAR(50)
                )
                """;
        stmt.execute(sql);
    }

    private static void createMeniuTable(Statement stmt) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Meniu (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nume VARCHAR(100) NOT NULL
                )
                """;
        stmt.execute(sql);
    }

    private static void createLocalTable(Statement stmt) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Local (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    numeLocal VARCHAR(100) NOT NULL,
                    adresa VARCHAR(255) NOT NULL,
                    meniu_id INT NOT NULL,
                    FOREIGN KEY (meniu_id) REFERENCES Meniu(id)
                )
                """;
        stmt.execute(sql);
    }

    private static void createOspatarTable(Statement stmt) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Ospatar (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nume VARCHAR(100) NOT NULL,
                    experientaAni INT NOT NULL,
                    tura VARCHAR(20) NOT NULL
                )
                """;
        stmt.execute(sql);
    }

    private static void createLivratorTable(Statement stmt) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Livrator (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nume VARCHAR(100) NOT NULL,
                    mijlocTransport VARCHAR(50) NOT NULL,
                    zonaLivrare VARCHAR(100) NOT NULL
                )
                """;
        stmt.execute(sql);
    }

    private static void createProdusTable(Statement stmt) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Produs (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nume VARCHAR(100) NOT NULL,
                    pret DECIMAL(10,2) NOT NULL,
                    gramaj INT NOT NULL,
                    disponibil BOOLEAN NOT NULL,
                    categorie_id INT NOT NULL,
                    meniu_id INT,
                    FOREIGN KEY (categorie_id) REFERENCES CategorieProdus(id),
                    FOREIGN KEY (meniu_id) REFERENCES Meniu(id)
                )
                """;
        stmt.execute(sql);
    }

    private static void createComandaTable(Statement stmt) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS Comanda (
                id INT AUTO_INCREMENT PRIMARY KEY,
                utilizator_id INT NOT NULL,
                dataComanda TIMESTAMP NOT NULL,
                total DECIMAL(10,2) NOT NULL,
                status VARCHAR(50) NOT NULL,
                tipComanda VARCHAR(20) NOT NULL,
                local_id INT NOT NULL,
                adresaLivrare VARCHAR(255),
                durataEstimataLivrare INT,
                livrator_id INT,
                numarMasa INT,
                rezervare BOOLEAN,
                ospatar_id INT,
                FOREIGN KEY (utilizator_id) REFERENCES Utilizator(id),
                FOREIGN KEY (local_id) REFERENCES Local(id),
                FOREIGN KEY (livrator_id) REFERENCES Livrator(id),
                FOREIGN KEY (ospatar_id) REFERENCES Ospatar(id)
            )
            """;
        stmt.execute(sql);
    }


    private static void createComandaProdusTable(Statement stmt) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Comanda_Produs (
                    comanda_id INT NOT NULL,
                    produs_id INT NOT NULL,
                    cantitate INT NOT NULL DEFAULT 1,
                    PRIMARY KEY (comanda_id, produs_id),
                    FOREIGN KEY (comanda_id) REFERENCES Comanda(id),
                    FOREIGN KEY (produs_id) REFERENCES Produs(id)
                )
                """;
        stmt.execute(sql);
    }
}
