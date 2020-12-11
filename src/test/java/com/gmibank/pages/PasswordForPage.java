package com.gmibank.pages;

import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.ConfigurationReader;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PasswordForPage extends BasePage{

    @FindBy( xpath="//input[@id='currentPassword']")
    public WebElement currentPasswordTextBox;

    @FindBy(xpath = "//input[@id='newPassword']")
    public WebElement newPasswordTextBox;

    @FindBy(id = "confirmPassword")
    public WebElement confirmPasswordTextBox;

    @FindBy(id = "strengthBar")
    List<WebElement> StrengthBar;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveButton;

    @FindBy(css = ".invalid-feedback")
    public List<WebElement> errorListForTextBoxes;


    public void typeCurrentPasswordFromPropertiesFile(){
        typeCurrentPassword(ConfigurationReader.getProperty("customer_password"));
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
        typeCurrentPasswordFromPropertiesFile();
        typeNewPassword(newPassword);
        typeNewPasswordConfirmation(newPassword);
        clickSaveButton();
    }

}
