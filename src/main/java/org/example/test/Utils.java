package org.example.test;

import java.util.List;

public class Utils {
    public static <T> void printList(List<T> lists) {
        System.out.println("[");
        lists.forEach(el -> System.out.println("\t" + el));
        System.out.println("]");
    }
}
