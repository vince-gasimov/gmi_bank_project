package com.gmibank.pages;

import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.ConfigurationReader;
import com.gmibank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//button[.='Sign in'][@type='submit']")
    public WebElement sigInButton;

    @FindBy(xpath = "//button[.='Cancel'][@type='button']")
    public WebElement cancelButton;

    @FindBy(name = "username")
    public WebElement userNameTextBox;

    @FindBy(name = "password")
    public WebElement passwordTextBox;

    //login formunun kendisi
    @FindBy(xpath = "//form[@method='get']")
    public WebElement loginForm;

    @FindBy(linkText = "Did you forget your password?")
    public WebElement forgotPasswordLink;

    //parolami unuttum linkine tiklama
    public void clickForgotPassword(){
        makeSureLoginFormDisplayed();
        forgotPasswordLink.click();
    }

    //sign in formunun acik oldugundan emin olmak icin kullanilabilir
    public void makeSureLoginFormDisplayed(){
        BrowserUtils.waitForVisibility(loginForm, 10);
    }

    //sign in butonuna tiklama
    public void clickSignInButton(){
        makeSureLoginFormDisplayed();
        sigInButton.click();
    }

    //cancel butonune tiklama
    public void clickCancelButton(){
        makeSureLoginFormDisplayed();
        cancelButton.click();
    }

    /*
    verilen user tipine gore configuration.properties altindan kullanici adini ve sifresini
    ceker ve textboxlara yazar
     */
    public void typeUserNameAndPassword(String userType){
        makeSureLoginFormDisplayed();
        String userName = null;
        String password = null;

        switch (userType.toLowerCase()){
            case "admin":
                userName = ConfigurationReader.getProperty("admin_user_name");
                password = ConfigurationReader.getProperty("admin_password");
                break;
            case "customer":
                userName = ConfigurationReader.getProperty("customer_user_name");
                password = ConfigurationReader.getProperty("customer_password");
                break;
            case "employee":
                userName = ConfigurationReader.getProperty("employee_user_name");
                password = ConfigurationReader.getProperty("employee_password");
                break;
            default:
                System.out.println("undefined user type!!");
                break;
        }

        userNameTextBox.sendKeys(userName);
        passwordTextBox.sendKeys(password);

    }

    //kullanici tarafindan manuel verilen username ve sifre girisi ile sign in yapmaya calisir
    public void typeUserNameAndPassword(String userName, String password){
        makeSureLoginFormDisplayed();
        userNameTextBox.sendKeys(userName);
        passwordTextBox.sendKeys(password);
    }

    //textbox'lara kullanici tipine gore degerleri yazdiktan sonra sign in tiklama
    public void loginWithValidInfo(String userType){
        typeUserNameAndPassword(userType);
        clickSignInButton();
    }







}
