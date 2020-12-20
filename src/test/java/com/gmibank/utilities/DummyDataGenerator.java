package com.gmibank.utilities;

import com.github.javafaker.Faker;
import com.github.javafaker.IdNumber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyDataGenerator {

    public static Faker faker = new Faker();

    public static String generateSsnNumber() {
        return faker.idNumber().ssnValid();
    }


    // 111-11-1111 pattern
    public static String ssnPatternManipulation(String numbersWithoutPattern) {
        return numbersWithoutPattern.substring(0, 3) + "-" + numbersWithoutPattern.substring(3, 5) + "-" + numbersWithoutPattern.substring(5);
    }



    public static String generateFirstName() {
        return faker.name().firstName();
    }

    public static String generateLastname() {
        return faker.name().lastName();
    }

    public static String generateAddress() {
        return faker.address().fullAddress();
    }

    public static String generateMobilePhoneNumber() {
        return "111-111-1111";
    }

    public static String generateUserName() {
        return faker.name().username();
    }

    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

    public static String generatePassword(int passwordLength, int lowerCaseNumber, int upperCaseNumber, int digitNumber, int specialCharNumber) {
        return RandomStringGenerator.generateStrongPassword(passwordLength, lowerCaseNumber, upperCaseNumber, digitNumber, specialCharNumber);
    }

    public static Map<String, String> generateAllNeededInformationExceptPassword(List<String> textBoxList) {
        Map<String, String> keyValuePairMap = new HashMap<>();

        for (String textBox : textBoxList) {
            keyValuePairMap.put(textBox, generateAStringForSpecifiedTextBox(textBox));
        }
        return keyValuePairMap;
    }

    public static String generateAStringForSpecifiedTextBox(String textBox) {
        switch (textBox) {
            case "ssnNumber":
                return generateSsnNumber();
            case "firstName":
                return generateFirstName();
            case "lastName":
                return generateLastname();
            case "address":
                return generateAddress();
            case "mobilePhoneNumber":
                return generateMobilePhoneNumber();
            case "userName":
                return generateUserName();
            case "email":
                return generateEmail();
            default:
                System.out.println("specified textbox does not exist");
                break;
        }
        return null;
    }
}