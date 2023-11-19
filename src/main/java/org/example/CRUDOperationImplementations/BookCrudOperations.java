package org.example.CRUDOperationImplementations;

import org.example.CRUDOperations;
import org.example.DatabaseConnection;
import org.example.entity.Book;
import org.example.entity.enumeration.Topic;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookCrudOperations implements CRUDOperations<Book> {
    private final Connection connection = DatabaseConnection.getConnection();

    public static Book bookInstance(ResultSet resultSet) throws SQLException {
        List<Topic> topics = Arrays.stream((String[]) resultSet.getArray("topics").getArray())
                .map(Topic::valueOf)
                .toList();

        return new Book(
                resultSet.getLong("book_id"),
                resultSet.getString("book_name"),
                resultSet.getInt("page_numbers"),
                topics,
                resultSet.getDate("release_date"),
                resultSet.getString("status"),
                resultSet.getInt("author_id")
        );
    }

    @Override
    public List<Book> findAll() {
        String SELECT_ALL_QUERY = "SELECT * FROM book";
        List<Book> books = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                books.add(bookInstance(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> saveAll(List<Book> toSave) {
        List<Book> books = new ArrayList<>();

        String INSERT_QUERY = "INSERT INTO book (book_name, page_numbers, topics, release_date) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            for (Book book : toSave) {
                statement.setString(1, book.getBookName());
                statement.setInt(2, book.getPageNumbers());
                
                Array topicsArray = connection.createArrayOf("topic", book.getTopics().stream().map(Enum::name).toArray());
                statement.setArray(3, topicsArray);

                statement.setDate(4, Date.valueOf(book.getReleaseDate()));

                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    book.setBookId(resultSet.getLong(1));
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book save(Book toSave) {
        String topics = toSave.getTopics().stream().map(e -> "'" + e.toString() + "'::\"topic\"").collect(Collectors.joining(", "));
        String INSERT_QUERY = "INSERT INTO book (book_name, page_numbers, topics, release_date) VALUES (?, ?, ARRAY[" + topics + "] , ?);";
        Book book = null;

        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, toSave.getBookName());
            statement.setInt(2, toSave.getPageNumbers());
            statement.setDate(4, Date.valueOf(toSave.getReleaseDate()));

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                toSave.setBookId(Long.parseLong(resultSet.getString(1)));
                book = toSave;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public Book delete(Book toDelete) {
        String DELETE_QUERY = "DELETE FROM book WHERE book_id = ?;";
        Book book = null;

        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setLong(1, toDelete.getBookId());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                toDelete.setBookId(Long.parseLong(resultSet.getString(1)));
                book = toDelete;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
}
