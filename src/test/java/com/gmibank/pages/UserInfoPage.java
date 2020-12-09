package com.gmibank.pages;

import com.gmibank.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

public class UserInfoPage {

    public UserInfoPage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(name = "firstName")
    public WebElement firstNameBox;

    @FindBy(name = "lastName")
    public WebElement lastNameBox;

    @FindBy(name = "email")
    public WebElement emailBox;

    @FindBy(name = "langKey")
    public List<WebElement> languageDropDown;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    public WebElement saveButton;

    public String getUrl1() {

        String currentUrl = "http://gmibank.com/account/settings";
      return currentUrl;

    }
}