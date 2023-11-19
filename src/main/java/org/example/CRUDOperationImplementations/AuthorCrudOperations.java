package org.example.CRUDOperationImplementations;

import org.example.CRUDOperations;
import org.example.DatabaseConnection;
import org.example.entity.Author;
import org.example.entity.enumeration.Sex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorCrudOperations implements CRUDOperations {
    private static final Connection connection = DatabaseConnection.getConnection();

    public static Author authorInstance(ResultSet resultSet) throws SQLException {
        return new Author(
                resultSet.getLong("author_id"),
                resultSet.getString("author_name"),
                Sex.valueOf(resultSet.getString("sex"))
        );
    }

    @Override
    public List findAll() throws SQLException {
        String SELECT_ALL_QUERY = "SELECT * FROM author";
        List<Author> authors = new ArrayList<>();

        try {
            ResultSet resultSet = connection.prepareStatement(SELECT_ALL_QUERY).executeQuery();
            while (resultSet.next()) {
                authors.add(authorInstance(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List saveAll(List toSave) {
        return null;
    }

    @Override
    public Object save(Object toSave) {
        return null;
    }

    @Override
    public Object delete(Object toDelete) {
        return null;
    }
}
