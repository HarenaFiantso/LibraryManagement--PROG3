package org.example;

import org.example.CRUDOperationImplementations.AuthorCrudOperations;
import org.example.CRUDOperationImplementations.BookCrudOperations;
import org.example.CRUDOperationImplementations.SubscriberCrudOperations;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.entity.Subscriber;
import org.example.entity.enumeration.Sex;
import org.example.entity.enumeration.Topic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Test des opérations CRUD pour Author
        testAuthorOperations();

        // Test des opérations CRUD pour Book
        testBookOperations();

        // Test des opérations CRUD pour Subscriber
        testSubscriberOperations();
    }

    private static void testAuthorOperations() {
        AuthorCrudOperations authorCrud = new AuthorCrudOperations();

        Author author1 = new Author("John Doe", Sex.M);
        Author savedAuthor1 = authorCrud.save(author1);
        System.out.println("Saved Author: " + savedAuthor1);

        List<Author> allAuthors = authorCrud.findAll();
        System.out.println("All Authors:");
        for (Author author : allAuthors) {
            System.out.println(author);
        }
    }

    private static void testBookOperations() {
        BookCrudOperations bookCrud = new BookCrudOperations();

        List<Topic> topics = new ArrayList<>();
        topics.add(Topic.ROMANCE);
        topics.add(Topic.COMEDY);

        Book book1 = new Book("Sample Book", 200, topics, LocalDate.now(), "Available", 1);
        Book savedBook1 = bookCrud.save(book1);
        System.out.println("Saved Book: " + savedBook1);

        List<Book> allBooks = bookCrud.findAll();
        System.out.println("All Books:");
        for (Book book : allBooks) {
            System.out.println(book);
        }
    }

    private static void testSubscriberOperations() {
        SubscriberCrudOperations subscriberCrud = new SubscriberCrudOperations();

        Subscriber subscriber1 = new Subscriber("Alice", Sex.F);
        Subscriber savedSubscriber1 = subscriberCrud.save(subscriber1);
        System.out.println("Saved Subscriber: " + savedSubscriber1);

        List<Subscriber> allSubscribers = subscriberCrud.findAll();
        System.out.println("All Subscribers:");
        for (Subscriber subscriber : allSubscribers) {
            System.out.println(subscriber);
        }
    }
}