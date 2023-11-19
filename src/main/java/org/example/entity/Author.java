package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.enumeration.Sex;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Author {
    private long authorId;
    private String authorName;
    private Sex sex;

    public Author(String authorName, Sex sex) {
    }
}
