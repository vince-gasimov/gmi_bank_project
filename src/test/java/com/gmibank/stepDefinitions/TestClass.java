package com.gmibank.stepDefinitions;

import com.gmibank.utilities.RandomStringGenerator;

public class TestClass {
    public static void main(String[] args) {
        String generatedString;
        for (int i = 0; i < 10; i++) {
            generatedString = RandomStringGenerator.generateStrongPassword(7, 2, 1, 1, 1);
            System.out.println(generatedString);
        }
    }
}
