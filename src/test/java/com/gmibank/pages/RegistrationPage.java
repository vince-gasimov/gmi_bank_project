package com.gmibank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{

/*
    Abla bu kismi BasePage sayfasinda yaptik. RegistrationPage sayfasini da
    BasePage'den extend ettik. Dolayisiyla tekrardan locate etmeye gerek yok.
    Burada ihtiyac oldugunda direkt BasePage'den geldigi icin kullanilabilir.

    @FindBy(xpath = "//a[@href='#']")
    public WebElement loginAndRegisterButton;
*/

/*
    Abla bu kismi BasePage sayfasinda yaptik. RegistrationPage sayfasini da
    BasePage'den extend ettik. Dolayisiyla tekrardan locate etmeye gerek yok.
    Burada ihtiyac oldugunda direkt BasePage'den geldigi icin kullanilabilir.

    @FindBy(xpath = "//span[.='Register']")
    public WebElement registerMenuLink;*/

/*
    bu locator'a beraber bakabiliriz. Benim anladigim kadariyla bu uyari mesaji icin kullanilacak. Uyari mesajlarinin hepsi icin bir tane
    List<WebElement> tanimlayabiliriz. Hepsini bunun icinden kontrol edebiliriz. Bakalim berbaer ins.

    @FindBy(xpath = "//div[.='Your SSN is invalid']")
    public WebElement ssnHataMesaji;*/


/*
    Uyari mesajlari Yukaridaki ile ayni kategoride

    @FindBy(xpath = "//div[.='Your First Name is required']")
    public WebElement firstnameHataMesaji;*/


/*
    Uyari mesajlari

    @FindBy(xpath = "//div[.='Your Last Name is required']")
    public WebElement lastnameHataMesaji;*/

/*
    uyari mesajlari

    @FindBy(xpath = "//div[.='Your mobile phone number is invalid']")
    public WebElement mobilePhoneHataMesaji;*/

    @FindBy(id = "ssn")
    public WebElement ssnTextBox;

    @FindBy(id = "firstname")
    public WebElement firstNameTextBox;

    @FindBy(id = "lastname")
    public WebElement lastNameTextBox;

    @FindBy(id = "address")
    public WebElement addressTextBox;

    @FindBy(id = "mobilephone")
    public WebElement mobilePhoneTextBox;

    @FindBy(id = "username")
    public WebElement userNameTextBox;

    @FindBy(id = "email")
    public WebElement emailTextBox;

    @FindBy(id = "firstPassword")
    public WebElement newPasswordTextBox;

    @FindBy(id = "secondPassword")
    public WebElement passwordConfirmationTextBox;

    @FindBy(id = "register-submit")
    public WebElement registerButton;




}
