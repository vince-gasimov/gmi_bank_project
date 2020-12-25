package com.gmibank.pages;

import com.gmibank.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CreateOrEditAccountPage extends BasePage{

    @FindBy(xpath="//input[@name='description']")
    public WebElement descriptionBox;

    @FindBy(name = "balance")
    public WebElement balanceBox;

    @FindBy(id="tp-account-accountType")
    public WebElement accountTypeDropDownItem;


    @FindBy(id = "tp-account-accountStatusType")
    public WebElement accountStatusTypeDropDown;


    @FindBy(id="tp-account-employee")
    public WebElement employeeDropDownitem;

    @FindBy(id="save-entity")
    public WebElement saveButton;


    //description deger gir
    public void typeDescription(String description){
        descriptionBox.click();
        descriptionBox.sendKeys(description);
    }
    //balance deger gir
    public void typeBalance(int balance){
        balanceBox.click();
        balanceBox.sendKeys(String.valueOf(balance));
    }
    //account type sec
    public void selectAccountType(String accountType){
        Select  select = new Select(accountTypeDropDownItem);
        //select.deselectAll();
        select.selectByVisibleText(accountType);
    }

    //save tikla

    public void clickSaveButton(){
        saveButton.click();
    }


    //acount olusturma
    public void createNewAccount(String description, int balance, String accountType ){
        BrowserUtils.waitForVisibility(descriptionBox,5);
        typeDescription(description);
        typeBalance(balance);
        selectAccountType(accountType);
        clickSaveButton();
    }

}
