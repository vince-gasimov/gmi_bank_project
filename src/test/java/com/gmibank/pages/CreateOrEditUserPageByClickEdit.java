package com.gmibank.pages;

import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CreateOrEditUserPageByClickEdit extends BasePage {

    @FindBy(id = "login")
    public WebElement loginTextBox;

    @FindBy(id = "firstName")
    public WebElement firstNameTextBox;

    @FindBy(id = "lastName")
    public WebElement lastNameTextBox;

    @FindBy(id = "email")
    public WebElement emailTextBox;


    @FindBy(id = "activated")
    public WebElement activatedCheckBox;

    @FindBy(id = "authorities")
    public WebElement profilesBox;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveButton;


    public void selectProfileActivateAndSave(String profile) {
        BrowserUtils.waitForVisibility(loginTextBox, 5);
        deselectAllOptions();
        selectAuthorityProfileOption(profile);
        activate();
        clickSaveButton();
    }

    public void deselectAllOptions() {
        Select select = new Select(profilesBox);
        select.deselectAll();
    }

    public void clickSaveButton() {
        BrowserUtils.waitForClickablility(saveButton, 5);
        saveButton.click();
    }

    public void selectAuthorityProfileOption(String profile) {
        Select select = new Select(profilesBox);
        String originalText = null;
        switch (profile.toLowerCase()) {
            case "user":
                originalText = "ROLE_USER";
                break;
            case "admin":
                originalText = "ROLE_ADMIN";
                break;
            case "employee":
                originalText = "ROLE_EMPLOYEE";
                break;
            case "manager":
                originalText = "ROLE_MANAGER";
                break;
            case "customer":
                originalText = "ROLE_CUSTOMER";
                break;
            default:
                System.out.println("there is no such option!!");
                break;
        }
        select.selectByVisibleText(originalText);

    }


    public boolean isActivated() {
        return activatedCheckBox.getAttribute("value").equals("true");
    }


    public void activate() {
        if (!isActivated()) {
            BrowserUtils.executeJScommand(activatedCheckBox, "arguments[0].click();");
        }
    }

    public void deactivate() {
        if (isActivated()) {
            activatedCheckBox.click();
        }
    }
}
