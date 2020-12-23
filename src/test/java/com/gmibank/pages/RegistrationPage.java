package com.gmibank.pages;


import com.gmibank.utilities.*;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationPage extends BasePage {



    @FindBy(id = "ssn")
    public WebElement ssnTextBox;

    @FindBy(xpath = "//input[@id='ssn']/following-sibling::div[@class='invalid-feedback']")
    public WebElement ssnErrorMessage;


    @FindBy(id = "firstname")
    public WebElement firstNameTextBox;

    @FindBy(xpath = "//input[@id='firstname']/following-sibling::div[@class='invalid-feedback']")
    public WebElement firstNameErrorMessage;


    @FindBy(id = "lastname")
    public WebElement lastNameTextBox;

    @FindBy(xpath = "//input[@id='lastname']/following-sibling::div[@class='invalid-feedback']")
    public WebElement lastNameErrorMessage;


    @FindBy(id = "address")
    public WebElement addressTextBox;


    @FindBy(id = "mobilephone")
    public WebElement mobilePhoneTextBox;

    @FindBy(xpath = "//input[@id='mobilephone']/following-sibling::div[@class='invalid-feedback']")
    public WebElement mobilePhoneErrorMessage;


    @FindBy(id = "username")
    public WebElement userNameTextBox;

    @FindBy(xpath = "//input[@id='username']/following-sibling::div[@class='invalid-feedback']")
    public WebElement userNameErrorMessage;


    @FindBy(id = "email")
    public WebElement emailTextBox;

    @FindBy(xpath = "//input[@id='email']/following-sibling::div[@class='invalid-feedback']")
    public WebElement emailErrorMessage;


    @FindBy(id = "firstPassword")
    public WebElement newPasswordTextBox;

    @FindBy(xpath = "//input[@id='firstPassword']/following-sibling::div[@class='invalid-feedback']")
    public WebElement newPasswordErrorMessage;


    @FindBy(id = "secondPassword")
    public WebElement passwordConfirmationTextBox;

    @FindBy(xpath = "//input[@id='secondPassword']/following-sibling::div[@class='invalid-feedback']")
    public WebElement passwordConfirmationErrorMessage;


    @FindBy(id = "register-submit")
    public WebElement registerButton;

    @FindBy(css = "ul[id='strengthBar']>li")
    public List<WebElement> passwordStrengthBarLeds;

    public List<WebElement> registrationResult;

    public String getTextOfRegistrationResult() {
            registrationResult = Driver.getDriver().findElements(By.xpath("//div[@role='alert']/span/strong"));
            if (registrationResult.size() == 0){
                return null;
            }else{
                return registrationResult.get(0).getAttribute("outerHTML");
            }
    }

    public String getMessage(WebElement errorWebElement){
        return errorWebElement.getText();
    }

    public void clickRegisterButton() {
        registerButton.click();
    }

    public void typeSsnNumber(String ssnNumber) {
        ssnTextBox.click();
        ssnTextBox.sendKeys(ssnNumber);
        ssnTextBox.sendKeys(Keys.TAB);
    }

    public void typeFirstName(String firstName) {
        firstNameTextBox.click();
        firstNameTextBox.sendKeys(firstName);
        firstNameTextBox.sendKeys(Keys.TAB);
    }

    public void typeLastName(String lastName) {
        lastNameTextBox.click();
        lastNameTextBox.sendKeys(lastName);
        lastNameTextBox.sendKeys(Keys.TAB);
    }

    public void typeAddress(String address) {
        addressTextBox.click();
        addressTextBox.sendKeys(address);
        addressTextBox.sendKeys(Keys.TAB);
    }

    public void typeMobilePhoneNumber(String phoneNumber) {
        mobilePhoneTextBox.click();
        mobilePhoneTextBox.sendKeys(phoneNumber);
        mobilePhoneTextBox.sendKeys(Keys.TAB);
    }

    public void typeUserName(String userName) {
        userNameTextBox.click();
        userNameTextBox.sendKeys(userName);
        userNameTextBox.sendKeys(Keys.TAB);
    }

    public void typeEmail(String email) {
        emailTextBox.click();
        emailTextBox.sendKeys(email);
        emailTextBox.sendKeys(Keys.TAB);
    }

    public void typeNewPassword(String newPassword) {
        newPasswordTextBox.click();
        newPasswordTextBox.sendKeys(newPassword);
        newPasswordTextBox.sendKeys(Keys.TAB);
    }

    public void typeNewPasswordConfirmation(String newPasswordConfirmation) {
        passwordConfirmationTextBox.click();
        passwordConfirmationTextBox.sendKeys(newPasswordConfirmation);
        passwordConfirmationTextBox.sendKeys(Keys.TAB);
    }


    public void typeValueInsideASpecifiedTextBox(String textBox, String value){

        switch (textBox.toLowerCase()){
            case "ssn":
                typeSsnNumber(value);
                break;
            case "firstname":
                typeFirstName(value);
                break;
            case "lastname":
                typeLastName(value);
                break;
            case "address":
                typeAddress(value);
                break;
            case "mobilephonenumber":
                typeMobilePhoneNumber(value);
                break;
            case "username":
                typeUserName(value);
                break;
            case "email":
                typeEmail(value);
                break;
            case "password":
                typeNewPassword(value);
                break;
            case "newpasswordconfirmation":
                typeNewPasswordConfirmation(value);
                break;
            default:
                System.out.println("specified textbox does not exist!!!");
                break;
        }

    }

    public void typeAllFieldInformation(Map<String, String> fieldsValuesMap) {
        typeSsnNumber((String) fieldsValuesMap.get("ssnNumber"));
        typeFirstName((String) fieldsValuesMap.get("firstName"));
        typeLastName((String) fieldsValuesMap.get("lastName"));
        typeAddress((String) fieldsValuesMap.get("address"));
        typeMobilePhoneNumber((String) fieldsValuesMap.get("mobilePhoneNumber"));
        typeUserName((String) fieldsValuesMap.get("userName"));
        typeEmail((String) fieldsValuesMap.get("email"));
        typeNewPassword((String) fieldsValuesMap.get("password"));
        typeNewPasswordConfirmation((String) fieldsValuesMap.get("passwordConfirmation"));
    }

    public boolean makeSureThereExistRegistrantInExcel() {

        //if there does not exist any row except for headers
        String path = ConfigurationReader.getProperty("registration_excel_path");
        String sheetName = ConfigurationReader.getProperty("registration_sheet_name");
        ExcelUtilities excel = new ExcelUtilities(path, sheetName);
        if (!excel.doesExistAnyRowExceptForHeader()) {
            try {
                navigateAndRegisterNewUserWithRandomGeneratedValue();
            } catch (Exception e) {
                return false;
            }
            System.out.println("kosul icine girdi");
        }
        //System.out.println("yeni olusturma ve son satiri alma methodu icindeyim" + excel.getLastRow());
        //ExcelUtilities excelWithRegistrant = new ExcelUtilities(path, sheetName);
        return true;
    }


    public void registerNewUser(Map<String, String> fieldsValuesMap){
        typeAllFieldInformation(fieldsValuesMap);
        if (!isThereAnyError()){
            clickRegisterButton();
        }else{
            System.out.println("There is error inside some textboxes");
        }

    }

    public boolean isThereAnyError(){
        List<WebElement> errorWebElements = getAllErrors();
        int size = getAllErrors().size();
        return size != 0;
    }

    public List<WebElement> getAllErrors(){
        return Driver.getDriver().findElements(By.cssSelector(".invalid-feedback"));
    }

    public boolean doesExistSuchErrorMessageInsidePage(String message){
        List<String> messageTextList = BrowserUtils.getElementsText(getAllErrors());
        for (String messageText : messageTextList) {
            if (messageText.contains(message)){
                return true;
            }
        }
        return false;
    }

    public List<String> getRgbAttributesOfLeds(){
        List<String> styleValues = new ArrayList<>();
        for (WebElement element : passwordStrengthBarLeds) {
            styleValues.add(element.getAttribute("style"));
        }
        List<String> rgbValues = new ArrayList<>();

        for (String styleValue : styleValues) {
            rgbValues.add(styleValue.substring(18));
        }
        return rgbValues;
    }

    public String getErrorMessageFromSpecifiedTextBox(String textBox){
        BrowserUtils.waitForVisibility(By.cssSelector(".invalid-feedback"), 5);
        //BrowserUtils.waitFor(2);
        switch (textBox.toLowerCase()){
            case "ssn":
                return getMessage(ssnErrorMessage);
            case "firstname":
                return getMessage(firstNameErrorMessage);
            case "lastname":
                return getMessage(lastNameErrorMessage);
            case "mobilephonenumber":
                return getMessage(mobilePhoneErrorMessage);
            case "username":
                return getMessage(userNameErrorMessage);
            case "email":
                return getMessage(emailErrorMessage);
            case "password":
                return getMessage(newPasswordErrorMessage);
            case "newpasswordconfirmation":
                return getMessage(passwordConfirmationErrorMessage);
            default:
                System.out.println("specified textbox does not exist!!!");
                break;
        }
        return null;
    }


    //sifrenin gucluluk derecesini veriyor
    public int getNumberOfLightingLedsForPasswordStrength(){
       List<String> rgbValues = getRgbAttributesOfLeds();
       return countRgbValue(rgbValues, rgbValues.get(0));
    }

    //renk uyumunu check ediyor
    public boolean checkConsistency(){
        int numberOfLightingLed = getNumberOfLightingLedsForPasswordStrength();
        List<String> rgbValues = getRgbAttributesOfLeds();

        String noLightingColor = "rgb(221, 221, 221);";
        String oneLightingColor = "rgb(255, 0, 0);";
        String twoLightingColor = "rgb(255, 153, 0);";
        String fourLightingColor = "rgb(153, 255, 0);";
        String fiveLightingColor = "rgb(0, 255, 0);";

        if (numberOfLightingLed == 1 && countRgbValue(rgbValues,noLightingColor) == 4){
            return true;
        }else if(numberOfLightingLed == 2 && countRgbValue(rgbValues,noLightingColor) == 3){
            return true;
        }else if(numberOfLightingLed == 4 && countRgbValue(rgbValues,noLightingColor) == 1){
            return true;
        }else return numberOfLightingLed == 5 && countRgbValue(rgbValues, noLightingColor) == 0;
    }

    //ilgili pattern ile ayni pattern'a sahip olan isik sayisi doner
    public int countRgbValue(List<String> rgbValues, String pattern){
        int count = 0;
        for (String rgbValue : rgbValues) {
            count = rgbValue.equals(pattern) ? ++count : count;
        }
        return count;
    }


    public void navigateAndRegisterNewUserWithRandomGeneratedValue() throws Exception {
        BasePage basePage = new BasePage();
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon("Register");
        BrowserUtils.waitForVisibility(ssnTextBox,5);
        registerNewUserWithRandomGeneratedValue();
        BrowserUtils.waitFor(1);
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    /**
     * gerekli nilgiler uretilerek yeni bir kayit olusuturulr, sonrainda excel dosyaina yazdirilir.
     * kayit olsutururken ssn veya username tekrari ortaya cikarsa yenilerii olustrr ve dener.
     *
     */
    public void registerNewUserWithRandomGeneratedValue(){
        String path = ConfigurationReader.getProperty("registration_excel_path");
        Map<String, String> keyValuePairs = DummyDataGenerator.generateAllNeededInformationExceptPassword();

        //password liste icinde yok ekle.   password
        String password = RandomStringGenerator.generateStrongPassword(7,1,1,1,1);
        keyValuePairs.put("password", password);
        keyValuePairs.put("passwordConfirmation", password);

        //textboxlari doldur
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.registerNewUser(keyValuePairs);

        //hata var mi diye kontrol et

        //userName double olup olmadigini check et. userName
        while (true) {
            String resultMessage = registrationPage.getTextOfRegistrationResult();
            if (resultMessage.contains("Login name already used!")) {
                String newUserName = DummyDataGenerator.generateUserName();
                keyValuePairs.replace("userName", newUserName);
                registrationPage.typeUserName(newUserName);
                System.out.println(keyValuePairs);
                System.out.println("bir deneme basarisiz");
            } else if (resultMessage.contains("error.ssnexists")) {
                String newSsn = DummyDataGenerator.generateSsnNumber();
                keyValuePairs.replace("ssnNumber", newSsn);
                registrationPage.typeSsnNumber(newSsn);
                System.out.println(keyValuePairs);
                System.out.println("bir deneme basarisiz");
            } else if(resultMessage.contains("Registration saved!")) {
                System.out.println("no problem it should be passed");
                System.out.println(keyValuePairs);
                ExcelUtilities excelUtilities = new ExcelUtilities(path, "registered");
                excelUtilities.writeUserIntoExcel(keyValuePairs);
                break;
            }else{
                System.out.println("unknown error");
                System.out.println("resultMessage = " + resultMessage);
            }
        }
    }

}
