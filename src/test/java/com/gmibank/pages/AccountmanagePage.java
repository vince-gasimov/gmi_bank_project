package com.gmibank.pages;

import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountmanagePage extends BasePage {



    @FindBy(xpath ="/html/body/div/div/div/div[2]/nav/div/ul/li[8]/a")
    public WebElement Login;

    @FindBy(id = "login-item")
    public WebElement SignIn;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/nav/div/ul/li[8]/a/span")
    public WebElement MyOperationsMenu;

    @FindBy(xpath = "/html/body/div/div/div/div[3]/div[1]/div/div/div/h2")
    public WebElement CustomerAccounts;

    @FindBy(xpath ="/html/body/div/div/div/div[3]/div[1]/div/div/div/div[1]/div/table/thead/tr/th[1]")
     public WebElement AccountType;

    @FindBy(xpath = "/html/body/div/div/div/div[3]/div[1]/div/div/div/div[1]/div/table/thead/tr/th[2]")
    public WebElement AccountBalance;

    @FindBy(xpath = "/html/body/div/div/div/div[3]/div[1]/div/div/div/div[2]/div")
    public WebElement TransactionNotFound;

    @FindBy (xpath="/html/body/div/div/div/div[3]/div[1]/div/div/div/div[1]/div/table/tbody/tr[1]/td[4]/button")
    public WebElement viewTransaction;
    // @FindBy( xpath="button[@class='btn btn-success btn-sm']")
   // public WebElement viewTransaction;

    @FindBy(xpath = "/html/body/div/div/div/div[3]/div[1]/div/div/div/div[2]/div/table/thead/tr/th[3]")
    public WebElement transacDescriptions;

    @FindBy(xpath = "/html/body/div/div/div/div[3]/div[1]/div/div/div/div[2]/div/table/thead/tr/th[2]")
    public WebElement transacAccountId;

    @FindBy(xpath = "/html/body/div/div/div/div[3]/div[1]/div/div/div/div[2]/div")
    public WebElement transactions;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/nav/div/ul/li[8]/a")
    public WebElement MyOperations;

    @FindBy(linkText = "My Accounts")
    public WebElement MyAccounts;





}
