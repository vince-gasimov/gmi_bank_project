package com.gmibank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class EmpNewAccountPage extends BasePage {


    @FindBy(xpath = "//jh-create-entity")
    public WebElement createNewAccountItem;
    @FindBy(name = "description")
    public WebElement descriptionBox;

    @FindBy(name = "balance")
    public WebElement balanceBox;
    @FindBy(id="tp-account-accountType")
    public List<WebElement> accountTypeDropDownItem;


    @FindBy(id = "tp-account-accountStatusType")
    public List<WebElement> accountStatusTypeDropDown;


    @FindBy(id="tp-account-employee")
    public WebElement employeeDropDownitem;

    @FindBy(id="save-entity")
    public WebElement saveButton;






}
