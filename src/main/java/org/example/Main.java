package org.example;

import org.example.CRUDOperationImplementations.AuthorCrudOperations;
import org.example.entity.Author;
import org.example.entity.enumeration.Sex;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AuthorCrudOperations authorCrudOperations = new AuthorCrudOperations();

        List<Author> allAuthors = authorCrudOperations.findAll();
        System.out.println("List of all authors :");
        allAuthors.forEach(author -> System.out.println(author.getAuthorId() + " - " + author.getAuthorName()));

        Author newAuthor = new Author("New author", Sex.M);
        Author savedAuthor = authorCrudOperations.save(newAuthor);
        System.out.println("New author added : " + savedAuthor.getAuthorId() + " - " + savedAuthor.getAuthorName());

        Author authorToDelete = new Author(5, "Author to delete", Sex.F);
        Author deletedAuthor = authorCrudOperations.delete(authorToDelete);
        if (deletedAuthor != null) {
            System.out.println("Author deleted : " + deletedAuthor.getAuthorId() + " - " + deletedAuthor.getAuthorName());
        } else {
            System.out.println("Can't delete or find the author");
        }
    }
}