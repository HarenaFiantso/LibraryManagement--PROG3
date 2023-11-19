package org.example;

import org.example.test.AuthorTest;
import org.example.test.BookTest;
import org.example.test.SubscriberTest;

public class Main {
    public static void main(String[] args) {
        AuthorTest.run();
        BookTest.run();
        SubscriberTest.run();
        DatabaseConnection.closeConnection();
    }
}