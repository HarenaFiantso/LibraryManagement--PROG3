package org.example.CRUDOperationImplementations;

import org.example.CRUDOperations;
import org.example.DatabaseConnection;
import org.example.entity.Author;
import org.example.entity.enumeration.Sex;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorCrudOperations implements CRUDOperations<Author> {
    private static final String SELECT_ALL_QUERY = "SELECT * FROM author";
    private static final String INSERT_QUERY = "INSERT INTO author (authorName, sex) VALUES (?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM author WHERE authorId = ?";

    private static final Connection connection = DatabaseConnection.getConnection();

    public static Author authorInstance(ResultSet resultSet) throws SQLException {
        return new Author(resultSet.getLong("authorId"), resultSet.getString("authorName"), Sex.valueOf(resultSet.getString("sex")));
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY); ResultSet resultSet = statement.executeQuery()) {
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
        Author author = null;

        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, toSave.getAuthorName());
            statement.setString(2, toSave.getSex().toString());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                toSave.setAuthorId(resultSet.getLong(1));
                author = toSave;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    @Override
    public Author delete(Author toDelete) {
        Author author = null;

        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setLong(1, toDelete.getAuthorId());
            statement.executeUpdate();
            author = toDelete;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }
}
