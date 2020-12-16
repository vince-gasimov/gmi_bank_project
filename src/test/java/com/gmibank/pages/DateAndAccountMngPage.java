package com.gmibank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import javax.xml.bind.annotation.W3CDomHandler;
import java.util.List;

public class DateAndAccountMngPage extends BasePage {


@FindBy(id="tp-account-createDate")
    public WebElement createDateItem;

//hem yeni hesap olusturdaktan sonra hem de manage customer'dan giris yaildigi gelen liste icin gecerli
@FindBy(xpath = "//a[@class='btn btn-success btn-sm']")
    public List<WebElement> accountsId;

@FindBy(xpath = "//a[@class='btn btn-primary']")
    public WebElement editButton;

@FindBy(xpath = "//input[@id='tp-customer-zelleEnrolled']")
    public WebElement zelleEnrolledRadioButton;


@FindBy(id="save-entity")
    public  WebElement saveButton;



}
