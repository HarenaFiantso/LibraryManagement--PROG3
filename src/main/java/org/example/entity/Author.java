package org.example.entity;

import lombok.*;
import org.example.entity.enumeration.Sex;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Author {
    private long authorId;
    private String authorName;
    private Sex sex;
}
