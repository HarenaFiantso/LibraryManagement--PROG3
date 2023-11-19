package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String jdbcUrl = System.getenv("JDBC_URL");
    private static final String dbUser = System.getenv("DB_USER");
    private static final String dbPassword = System.getenv("DB_PASSWORD");
    private static Connection connection;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        try {
            connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            System.out.println("Database connection connected successfully ! ");
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
