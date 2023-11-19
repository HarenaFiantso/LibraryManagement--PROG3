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

    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                authors.add(mapToAuthor(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public List<Author> saveAll(List<Author> toSave) {
        List<Author> savedAuthors = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false); // Begin transaction
            try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                for (Author author : toSave) {
                    statement.setString(1, author.getAuthorName());
                    statement.setString(2, author.getSex().toString());
                    statement.executeUpdate();
                    ResultSet resultSet = statement.getGeneratedKeys();
                    if (resultSet.next()) {
                        author.setAuthorId(resultSet.getLong(1));
                        savedAuthors.add(author);
                    }
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return savedAuthors;
    }

    public Author save(Author toSave) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, toSave.getAuthorName());
            statement.setString(2, toSave.getSex().toString());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                toSave.setAuthorId(resultSet.getLong(1));
                return toSave;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Author delete(Author toDelete) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setLong(1, toDelete.getAuthorId());
            int deletedRows = statement.executeUpdate();
            if (deletedRows > 0) {
                return toDelete;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Author mapToAuthor(ResultSet resultSet) throws SQLException {
        return new Author(resultSet.getLong("authorId"), resultSet.getString("authorName"), Sex.valueOf(resultSet.getString("sex")));
    }
}
