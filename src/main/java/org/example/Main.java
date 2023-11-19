package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String jdbcUrl = System.getenv("JDBC_URL");
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        System.out.println("JDBC_URL: " + jdbcUrl);
        System.out.println("DB_USER: " + user);
        System.out.println("DB_PASSWORD: " + password);

        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password)) {
            if (connection != null) {
                System.out.println("Database connected ! ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}