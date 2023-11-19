package org.example;

import java.util.List;

public interface CRUDOperations<T> {
    List<T> findAll();

    List<T> saveAll(List<T> toSave);

    T save(T toSave);

    T delete(T toDelete);
}
