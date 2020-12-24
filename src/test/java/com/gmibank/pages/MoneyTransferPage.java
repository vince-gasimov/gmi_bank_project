package com.gmibank.pages;

import com.gmibank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MoneyTransferPage extends BasePage{

    @FindBy(id = "fromAccountId")
    public WebElement FromDropdown;

    @FindBy(id = "fromAccountId")
    List <WebElement> FromDropdownlist;

    @FindBy(xpath = "/html/body/div/div/div/div[3]/div[1]/div/div/div/form/div[2]")
    public WebElement warningAlert;

    @FindBy(xpath ="/html/body/div/div/div/div[3]/div[1]/div/div/div/form/div[4]/div/div")
    public WebElement RequiredMessageDescription;

    @FindBy(xpath = "/html/body/div/div/div/div[3]/div[1]/div/div/div/form/div[3]/div/div[1]/div/div")
    public WebElement requiredMessage;

    @FindBy(id = "toAccountId")
    public WebElement ToDropDown;

    @FindBy(id = "balance")
    public WebElement balance;

    @FindBy(id = "balancecent")
    public WebElement balancecent;

    @FindBy(id = "description")
    public WebElement description;

    @FindBy(id = "make-transfer")
    public WebElement makeTransfer;

    @FindBy(xpath = "/html/body/div/div/div/div[3]/div[1]/div/div/div/form/div[3]/div/div[1]/div/div")
    public WebElement Max5Digit;

    @FindBy(xpath = "/html/body/div/div/div/div[3]/div[1]/div/div/div/form/div[3]/div/div[2]/div/div")
    public WebElement Max2Digit;

    @FindBy(xpath = "//div[@role='alert']/span/strong")
            //html/body/div/div/div/div[1]/div/div[5]")
    public WebElement successtransfer;

    public List<WebElement> MakeTransferResult;
    public String getTextOfMoneyTransferResult() {
        MakeTransferResult = Driver.getDriver().findElements(By.xpath("//div[@role='alert']/span/strong"));
        if (MakeTransferResult.size() == 0){
            return null;
        }else{
            return MakeTransferResult.get(0).getAttribute("outerHTML");
        }
    }

}
