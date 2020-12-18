package com.gmibank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateAndAccountMngPage extends UsersPageWithTable {


    @FindBy(id = "tp-account-createDate")
    public WebElement createDateItem;

    //hem yeni hesap olusturdaktan sonra hem de manage customer'dan giris yaildigi gelen liste icin gecerli
    @FindBy(xpath = "//a[@class='btn btn-success btn-sm']")
    public List<WebElement> accountsId;

    @FindBy(xpath = "//a[@class='btn btn-primary']")
    public WebElement editButton;

    @FindBy(xpath = "//input[@id='tp-customer-zelleEnrolled']")
    public WebElement zelleEnrolledRadioButton;


    @FindBy(id = "save-entity")
    public WebElement saveButton;

    @FindBy(xpath = "//div[@class='invalid-feedback']")
    public List<WebElement> invalidDataToAlert;

    @FindBy(id="tp-account-employee")
    public WebElement employeeDropDownitem;




    //üzerine calis
    public String localDate(LocalDate localDate) {




        String time = new SimpleDateFormat("dd.MM.yyyy").format(new Date());

        return time;
    }




}