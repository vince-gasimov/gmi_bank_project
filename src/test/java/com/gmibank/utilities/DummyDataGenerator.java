package com.gmibank.utilities;

import com.github.javafaker.Faker;
import com.github.javafaker.IdNumber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyDataGenerator {

    private static Faker faker = new Faker();

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
        return faker.phoneNumber().cellPhone();
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

    public static List<Map<String, Object>> generateAllNeededInformationExceptPassword(List<String> textBoxList) {
        Map<String, Object> keyValuePairMap = new HashMap<>();
        List<Map<String, Object>> listOfMap = new ArrayList<>();
        for (String textBox : textBoxList) {
            keyValuePairMap.put(textBox, generateAStringForSpecifiedTextBox(textBox));
            listOfMap.add(keyValuePairMap);
        }
        return listOfMap;
    }

    public static String generateAStringForSpecifiedTextBox(String textBox) {
        switch (textBox.toLowerCase()) {
            case "ssn":
                return generateSsnNumber();
            case "first_name":
                return generateFirstName();
            case "last_name":
                return generateLastname();
            case "address":
                return generateAddress();
            case "mobile_phone_number":
                return generateMobilePhoneNumber();
            case "username":
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
