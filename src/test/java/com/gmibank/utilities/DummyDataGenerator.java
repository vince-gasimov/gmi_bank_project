package com.gmibank.utilities;

import com.github.javafaker.Faker;
import com.github.javafaker.IdNumber;

import java.util.*;

public class DummyDataGenerator {

    public static Faker faker = new Faker();

    private static List<String> phoneNumberPool = Arrays.asList("432-131-5405", "206-381-6032", "189-843-0512", "511-434-3547", "588-697-5116", "319-959-3517", "118-675-2045", "715-457-6770", "107-834-0930", "967-954-5421");

    public static int generateRandomNumberFromZeroToTen() {
        Random random = new Random();
        return (random.nextInt(10) + 1);
    }

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
        String address = faker.address().streetAddress();
        return address;
    }

    public static String generateZipCode() {
        return faker.address().zipCode();
    }

    public static String generateCity() {
        return faker.address().city();
    }

    public static String generateState() {
        return faker.address().state();
    }

    public static String generatePhoneNumber() {
        return phoneNumberPool.get(generateRandomNumberFromZeroToTen() - 1);
    }

    public static String generateMobilePhoneNumber() {
        return phoneNumberPool.get(generateRandomNumberFromZeroToTen() - 1);
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

    /**
     * textBox listesi verilirken verilecek key'ler excel'deki ile birebir olmali. Aksi halde
     * uretemez ve yazamaz.
     * @param textBoxList
     * @return
     */
    public static Map<String, String> generateAllNeededInformationExceptPassword(List<String> textBoxList) {
        Map<String, String> keyValuePairMap = new HashMap<>();

        for (String textBox : textBoxList) {
            keyValuePairMap.put(textBox, generateAStringForSpecifiedTextBox(textBox));
        }
        return keyValuePairMap;
    }

    /**
     * yukaridaki methodu overload ettim. Parametre vermiyorum. Password haricindeki butun alanlarin
     * uretilmesini istiyorum. Olusturulacak data setini icerde kendim veriyorum.
     * @return
     */
    public static Map<String, String> generateAllNeededInformationExceptPassword() {
        List<String> keyList = new ArrayList<>();
        keyList.add("ssnNumber");
        keyList.add("firstName");
        keyList.add("lastName");
        keyList.add("address");
        keyList.add("mobilePhoneNumber");
        keyList.add("userName");
        keyList.add("email");
        keyList.add("zipCode");
        keyList.add("city");
        keyList.add("state");
        keyList.add("phoneNumber");
        Map<String, String> keyValuePairMap = new HashMap<>();

        for (String textBox : keyList) {
            keyValuePairMap.put(textBox, generateAStringForSpecifiedTextBox(textBox));
        }
        return keyValuePairMap;
    }

    /***
     * asagidaki formatta excel'deki ayni format kullanilmali. Aksi halde excelde yazacagi kolanlari
     * bulamaz. Bunun yaninda feature dosyasinda da bu formatta olmali.
     * @param textBox
     * @return
     */
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
            case "zipCode":
                return generateZipCode();
            case "city":
                return generateCity();
            case "state":
                return generateState();
            case "phoneNumber":
                return generatePhoneNumber();
            default:
                System.out.println("specified textbox does not exist");
                break;
        }
        return null;
    }

    /**
     * fake data uretir (password dahil) register yapmadan direkt excl dosyasina yazar.
     */

    public void generateRandomlyUserInfoAndWriteIntoExcelWithoutRegistering(){
        Map<String, String> map = new HashMap<>();
        map = DummyDataGenerator.generateAllNeededInformationExceptPassword();
        ExcelUtilities excelUtilities = new ExcelUtilities("src/test/resources/CreatedUserInformation.xlsx", "registered");
        String password = RandomStringGenerator.generateStrongPassword(7,1,1,1,1);
        map.put("password", password);
        excelUtilities.writeUserIntoExcel(map);
    }
}