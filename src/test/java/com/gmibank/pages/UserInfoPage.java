package com.gmibank.pages;

import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.Driver;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import javax.xml.bind.SchemaOutputResolver;
import java.util.List;

public class UserInfoPage extends BasePage {

    public UserInfoPage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//label[@for='firstName']")
    public WebElement forFirstname;

    @FindBy(xpath = "//label[@for='lastName']")
    public WebElement forLastname;


    @FindBy(xpath = "//label[@for='email']")
    public WebElement forEmail;


    @FindBy(xpath = "//label[@for='langKey']")
    public WebElement forLangkey;

    @FindBy(name = "firstName")
    public WebElement firstNameBox;

    @FindBy(name = "lastName")
    public WebElement lastNameBox;

    @FindBy(name = "email")
    public WebElement emailBox;

    @FindBy(name = "langKey")
    public WebElement languageDropDown;


    @FindBy(xpath = "//button[@class='btn btn-primary']")
    public WebElement saveButtonUserinfo;


    @FindBy(xpath = "//div[@class='invalid-feedback']")
    public List<WebElement> invalidDataToAlert;

    @FindBy(xpath = "//div[@role='alert']")
    public  WebElement toastAlert;

    public List<String> getAlert() {


        List<String> invalidDataToalertList = BrowserUtils.getElementsText(invalidDataToAlert);
        if (invalidDataToalertList.size() != 0) {
            System.out.println(invalidDataToalertList.get(0));
        } else {
            System.out.println("invalidEmailListSize:" + invalidDataToalertList.size());
        }
        return invalidDataToalertList;

    }





}