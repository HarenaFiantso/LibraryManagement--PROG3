package org.example.CRUDOperationImplementations;

import org.example.CRUDOperations;
import org.example.DatabaseConnection;
import org.example.entity.Book;
import org.example.entity.enumeration.Topic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookCrudOperations implements CRUDOperations<Book> {
    private final Connection connection = DatabaseConnection.getConnection();

    public static Book bookInstance(ResultSet resultSet) throws SQLException {
        List<Topic> topics = Arrays.stream((String[]) resultSet.getArray("topics").getArray()).map(Topic::valueOf).toList();
        return new Book(
                resultSet.getLong("book_id"),
                resultSet.getString("book_name"),
                resultSet.getInt("page_numbers"),
                topics,
                resultSet.getDate("release_date"),
                resultSet.getInt("author_id")
        );
    }

    @Override
    public List<Book> findAll() {
        String SELECT_ALL_QUERY = "SELECT * FROM book";
        List<Book> books = new ArrayList<>();

        try {
            ResultSet resultSet = connection.prepareStatement(SELECT_ALL_QUERY).executeQuery();
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
        return null;
    }

    @Override
    public Book save(Book toSave) {
        return null;
    }

    @Override
    public Book delete(Book toDelete) {
        return null;
    }
}
