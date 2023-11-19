package org.example.test;

import org.example.crudOperationImplementations.SubscriberCrudOperations;
import org.example.entity.Subscriber;

import java.util.List;

public class SubscriberTest {
    private final static SubscriberCrudOperations subscriberCrudOperations = new SubscriberCrudOperations();

    public static void run() {
        Utils.printList(subscriberCrudOperations.findAll());

        System.out.println(subscriberCrudOperations.save(
                new Subscriber("", "Subscriber 4", "subscriberRef_4"))
        );

        Utils.printList(subscriberCrudOperations.saveAll(List.of(
                new Subscriber("", "Subscriber 5", "subscriberRef_5"),
                new Subscriber("", "Subscriber 6", "subscriberRef_6")
        )));

        System.out.println(subscriberCrudOperations.delete(new Subscriber("1", "", "subscriberRef_6")));
    }
}
