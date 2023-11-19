package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.enumeration.Topic;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Book {
    private long bookId;
    private String bookName;
    private long authorId;
    private int pageNumbers;
    private Topic topic;
    private String releaseDate;
    private String status;
}
