package com.kodilla.bytecode.reflection;

import java.util.Random;

public class Student {
    private String indexNumber;

    public Student(int z) {
        this.indexNumber = generateRandomString(z);
    }

    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    // Getter method for indexNumber for validation if needed, can be removed.
    public String getIndexNumber() {
        return indexNumber;
    }
}

