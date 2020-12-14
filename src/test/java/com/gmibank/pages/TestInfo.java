package com.gmibank.pages;

import com.gmibank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TestInfo {
    public TestInfo(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy( xpath = "//input[@name='firstName']")
    public WebElement firstName;

    @FindBy( xpath = "//input[@name='lastName']")
    public WebElement lastName;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement emailKutusu;

    @FindBy(xpath = "//div[@class='invalid-feedback']")
    List<WebElement> invalidMessages;

}
