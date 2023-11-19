package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.enumeration.Topic;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Book {
    private long bookId;
    private String bookName;
    private int pageNumbers;
    private List<Topic> topics;
    private String releaseDate;
    private String status;
    private Author author;

    public Book(long bookId, String bookName, int pageNumbers, List<Topic> topics, Date releaseDate, int authorId) {
    }
}
