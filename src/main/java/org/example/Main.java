package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/library_management";
        String user = "prog_admin";
        String password = "123456";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, user, password)) {
            if (connection != null) {
                System.out.println("Database connected ! ");
            }
        } catch (SQLException e) {
            System.out.println("Database connection error : " + e.getMessage());
        }
    }
}