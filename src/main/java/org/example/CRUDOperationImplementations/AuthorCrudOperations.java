package org.example.CRUDOperationImplementations;

import org.example.CRUDOperations;
import org.example.DatabaseConnection;
import org.example.entity.Author;
import org.example.entity.enumeration.Sex;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorCrudOperations implements CRUDOperations<Author> {
    private static final Connection connection = DatabaseConnection.getConnection();

    public static Author authorInstance(ResultSet resultSet) throws SQLException {
        return new Author(
                resultSet.getLong("author_id"),
                resultSet.getString("author_name"),
                Sex.valueOf(resultSet.getString("sex"))
        );
    }

    @Override
    public List<Author> findAll() {
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
        return authors;
    }

    @Override
    public List<Author> saveAll(List<Author> toSave) {
        List<Author> authors = new ArrayList<>();
        toSave.forEach(e -> authors.add(save(e)));
        return authors;
    }

    @Override
    public Author save(Author toSave) {
        String INSERT_QUERY = "INSERT INTO author (author_name, sex) VALUES (?, ?);";
        Author author = null;

        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, toSave.getAuthorName());
            statement.setString(2, toSave.getSex().toString());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                toSave.setAuthorId(resultSet.getInt(1));
                author = toSave;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public Author delete(Author toDelete) {
        String DELETE_QUERY = "DELETE FROM author WHERE author_id = ?;";
        Author author = null;

        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setLong(1, toDelete.getAuthorId());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                toDelete.setAuthorId(Long.parseLong(resultSet.getString(1)));
                author = toDelete;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }
}
