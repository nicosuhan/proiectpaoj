package model;

import java.sql.Connection;

public class TestSingleton {
    public static void main(String[] args) {
        try {
            Connection conn1 = DatabaseConnection.getInstance().getConnection();
            Connection conn2 = DatabaseConnection.getInstance().getConnection();

            System.out.println("Conn1 == Conn2? " + (conn1 == conn2));

            conn1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
