package org.example.test;

import org.example.crudOperationImplementations.BookCrudOperations;
import org.example.entity.Book;
import org.example.entity.enumeration.Topic;

import java.sql.Date;
import java.util.List;

public class BookTest {
    private final static BookCrudOperations bookCrudOperations = new BookCrudOperations();

    public static void run() {
        Utils.printList(bookCrudOperations.findAll());

        System.out.println(bookCrudOperations.save(new Book("", "Book 4", 200, Date.valueOf("2023-01-01"), List.of(Topic.COMEDY), null)));

        Utils.printList(bookCrudOperations.saveAll(List.of(new Book("", "Book 5", 200, Date.valueOf("2023-02-01"), List.of(Topic.ROMANCE, Topic.OTHER), null), new Book("", "bookName_6", 200, Date.valueOf("2023-03-01"), List.of(Topic.OTHER), null))));

        Utils.printList(bookCrudOperations.findAll());
    }
}
