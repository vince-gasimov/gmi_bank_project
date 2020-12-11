package com.gmibank.pages;

import com.gmibank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;

public class UserInfoPage {

    public UserInfoPage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }


    //sag ust kosedeki kullanici ikonu
    @FindBy(id = "account-menu")
    public WebElement accountMenuIcon;

    @FindBy(css = "[id='account-menu'] a[class='dropdown-item']")
    public List<WebElement> accountIconDropDownItems;

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


    @FindBy(xpath = "//div[@class='invalid-feedback']")
    public List<WebElement> invalidEmailToAlert;

    public void getAlert() {
        List<WebElement> elements = Driver.getDriver().findElements(By.xpath("invalid-feedback"));

        System.out.println(elements);


        if(elements.size()!=0){
            System.out.println(elements.get(0));
        }



}}