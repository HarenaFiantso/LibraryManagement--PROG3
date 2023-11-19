package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Subscriber {
    private Long subscriberId;
    private String subscriberName;
    private String sex;

    public Subscriber(String subscriberName, String sex) {
    }
}
