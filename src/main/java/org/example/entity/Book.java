package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.enumeration.Topic;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String id, bookName;
    private long pageNumbers;
    private Date releaseDate;
    private List<Topic> topics;
    private Author author;
}
