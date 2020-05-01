package org.academiadecodigo.ramsters.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private Connection connection;

    public Connection getConnection() {

        try {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/covid", "root", "root");
                System.out.println("Connection Granted");
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect " + e.getMessage());
        }

        return connection;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}