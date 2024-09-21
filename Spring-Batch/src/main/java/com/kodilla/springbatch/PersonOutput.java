package com.kodilla.springbatch;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonOutput {
    private String firstName;
    private String lastName;
    private int age;

    public PersonOutput(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
