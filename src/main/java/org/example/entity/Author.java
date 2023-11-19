package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.enumeration.Sex;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private String id, name;
    private Sex sex;
}
