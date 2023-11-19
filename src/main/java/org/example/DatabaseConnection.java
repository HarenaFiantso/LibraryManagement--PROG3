package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection != null)
            return connection;

        try {
            connection = DriverManager.getConnection(
                    System.getenv("JDBC_URL"),
                    System.getenv("DB_USER"),
                    System.getenv("DB_PASSWORD")
            );
            return connection;
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            throw new RuntimeException("Database connection failed ! ");
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
