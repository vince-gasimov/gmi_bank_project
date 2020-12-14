package com.gmibank.pages;

import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.ConfigurationReader;
import com.gmibank.utilities.Driver;
import com.gmibank.utilities.RandomStringGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class PasswordForPage extends BasePage{

    @FindBy( xpath="//input[@id='currentPassword']")
    public WebElement currentPasswordTextBox;

    @FindBy(xpath = "//input[@id='newPassword']")
    public WebElement newPasswordTextBox;

    @FindBy(id = "confirmPassword")
    public WebElement confirmPasswordTextBox;

    @FindBy(xpath = "//ul[@id='strengthBar']/li")
    public List<WebElement> strengthBarLeds;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveButton;

    @FindBy(css = ".invalid-feedback")
    public List<WebElement> errorListForTextBoxes;

    @FindBy(xpath = "//*[@role='alert']")
    public WebElement resultAlert;

    public boolean doesExistAnyResultMessageSuchAs(String expectedResultMessage){
        String resultAlertMessage = resultAlert.getText();
        if (expectedResultMessage.contains(resultAlertMessage)){
            return true;
        }else{
            return false;
        }
    }

    public List<WebElement> errorListUnderTextBoxes(){
        return Driver.getDriver().findElements(By.cssSelector(".invalid-feedback"));
    }

    public boolean doesExistSuchAnError(String expectedErrorMessage){
        List<String> errorList = BrowserUtils.getElementsText(errorListForTextBoxes);
        for (String errorText : errorList) {
            if (errorText.contains(expectedErrorMessage)){
                return true;
            }
        }
        return false;
    }

    public void typeCurrentPasswordFromPropertiesFileForDynamicCustomer(){
        typeCurrentPassword(ConfigurationReader.getProperty("dynamic_customer_password"));
    }

    public void typeCurrentPassword(String currentPassword){
        currentPasswordTextBox.click();
        currentPasswordTextBox.sendKeys(currentPassword);
        currentPasswordTextBox.sendKeys(Keys.TAB);
    }

    public void typeNewPassword(String newPassword){
        newPasswordTextBox.click();
        newPasswordTextBox.sendKeys(newPassword);
        newPasswordTextBox.sendKeys(Keys.TAB);
    }

    public void typeNewPasswordConfirmation(String newPassword){
        confirmPasswordTextBox.click();
        confirmPasswordTextBox.sendKeys(newPassword);
        confirmPasswordTextBox.sendKeys(Keys.TAB);
    }

    public void clickSaveButton(){
        BrowserUtils.waitForVisibility(saveButton, 5);
        saveButton.click();
    }

    public void typeAllInformationAndClickSaveButton(String newPassword){
        typeCurrentPasswordFromPropertiesFileForDynamicCustomer();
        typeNewPassword(newPassword);
        typeNewPasswordConfirmation(newPassword);
        clickSaveButton();
    }

    public String getAlertMessage(){
        BrowserUtils.waitForVisibility(resultAlert, 5);
        return resultAlert.getText();
    }

    public boolean isPasswordChanged(){
        String message = getAlertMessage();
        if (message.contains("Password changed!")){
            return true;
        }else{
            return false;
        }
    }

    public boolean doesMessageContains(String expectedMessage){
        String actualMessage = getAlertMessage();
        if (actualMessage.contains(expectedMessage)){
            return true;
        }else{
            return false;
        }
    }

    public void typeValueInsideASpecifiedTextBox(String value, String textBox){
        switch (textBox.toLowerCase()){
            case "current_password":
                typeCurrentPassword(value);
                break;
            case "new_password":
                typeNewPassword(value);
                break;
            case "new_password_confirmation":
                typeNewPasswordConfirmation(value);
                break;
            default:
                System.out.println("specified textbox does not exist!!!");
                break;
        }

    }

    public void changePasswordWithRandomGenerated(){
        String oldPassword = ConfigurationReader.getProperty("dynamic_customer_password");
        String newPassword = RandomStringGenerator.compareAndGenerateAgain(oldPassword,7,1,1,1,1);
        System.out.println("newPassword = " + newPassword);
        typeAllInformationAndClickSaveButton(newPassword);
        if (isPasswordChanged()){
            ConfigurationReader.changePropertyValue("dynamic_customer_password", newPassword);
        }else {
            System.out.println("there is a problem, new password cannot be restored!!!");
        }
    }

    public void changePasswordWithGivenOne(String newPassword){
        typeAllInformationAndClickSaveButton(newPassword);
        if (isPasswordChanged()){
            ConfigurationReader.changePropertyValue("dynamic_customer_password", newPassword);
        }else {
            System.out.println("there is a problem, new password cannot be restored!!!");
        }
    }

    public void clearCurrentPasswordTextBox(){
        currentPasswordTextBox.click();
        currentPasswordTextBox.clear();
    }

    public void clearNewPasswordTextBox(){
        newPasswordTextBox.click();
        newPasswordTextBox.clear();
    }

    public void clearNewPasswordConfirmationTextBox(){
        confirmPasswordTextBox.click();
        confirmPasswordTextBox.clear();
    }

    public void clearAllTextBoxes(){
        clearCurrentPasswordTextBox();
        clearNewPasswordConfirmationTextBox();
        clearNewPasswordTextBox();
    }


    public List<String> getRgbAttributesOfLeds(){
        List<String> styleValues = new ArrayList<>();
        for (WebElement element : strengthBarLeds) {
            styleValues.add(element.getAttribute("style"));
        }
        List<String> rgbValues = new ArrayList<>();

        for (String styleValue : styleValues) {
            rgbValues.add(styleValue.substring(18));
        }
        return rgbValues;
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
        }else if(numberOfLightingLed == 5 && countRgbValue(rgbValues,noLightingColor) == 0){
            return true;
        }else{
            return false;
        }
    }

    //ilgili pattern ile ayni pattern'a sahip olan isik sayisi doner
    public int countRgbValue(List<String> rgbValues, String pattern){
        int count = 0;
        for (String rgbValue : rgbValues) {
            count = rgbValue.equals(pattern) ? ++count : count;
        }
        return count;
    }


}
