package com.gmibank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.xml.bind.annotation.W3CDomHandler;

public class AdminNewAccountCreatePage extends BasePage{


   @FindBy(xpath = "//div[@class='alert alert-danger']")
    public WebElement adminAccountNotCreatedAlert;





}
