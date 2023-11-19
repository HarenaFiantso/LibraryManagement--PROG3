package org.example.test;

import org.example.crudOperationImplementations.AuthorCrudOperations;
import org.example.entity.Author;
import org.example.entity.enumeration.Sex;

import java.util.List;

public class AuthorTest {
    private final static AuthorCrudOperations authorCrudOperations = new AuthorCrudOperations();

    public static void run() {
        Utils.printList(authorCrudOperations.findAll());

        System.out.println(authorCrudOperations.save(
                new Author("", "Author 4", Sex.F))
        );

        Utils.printList(authorCrudOperations.saveAll(List.of(
                new Author("", "Author 5", Sex.M),
                new Author("", "Author 6", Sex.F)
        )));

        System.out.println(authorCrudOperations.delete(new Author("4", "authorName_4", Sex.F)));
    }
}
