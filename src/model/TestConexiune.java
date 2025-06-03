package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestConexiune {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
            System.out.println("Conexiune realizata!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
