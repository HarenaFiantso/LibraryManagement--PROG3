package org.example.crudOperationImplementations.utilities;

import org.example.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CRUDBasics {
    static private final Connection connection = DatabaseConnection.getConnection();

    public static boolean delete(String tableName, String id) {
        String query = SQLQuery.delete(tableName);
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return false;
    }
}
