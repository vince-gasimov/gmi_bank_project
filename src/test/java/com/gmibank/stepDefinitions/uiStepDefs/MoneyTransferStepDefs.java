package com.gmibank.stepDefinitions;

import com.gmibank.pages.AccountmanagePage;
import com.gmibank.pages.LoginPage;
import com.gmibank.pages.MoneyTransferPage;
import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.ConfigurationReader;
import com.gmibank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class MoneyTransferStepDefs {

    @Given("user sign in to the system as new Customer1")
    public void user_sign_in_to_the_system_as_new_Customer1() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        LoginPage loginPage=new LoginPage();
        AccountmanagePage accountmanagePage=new AccountmanagePage();
        accountmanagePage.Login.click();
        accountmanagePage.SignIn.click();
        loginPage.userNameTextBox.sendKeys(ConfigurationReader.getProperty("new_customer1"));
        loginPage.passwordTextBox.sendKeys(ConfigurationReader.getProperty("new_customer_password"));
        loginPage.sigInButton.click();
    }
    //tc01
    @When("verify that user can not transfer money with one account")
    public void verify_that_user_can_not_transfer_money_with_one_account() {
        MoneyTransferPage moneyTransferPage=new MoneyTransferPage();
        Select select = new Select(moneyTransferPage.FromDropdown);
        BrowserUtils.waitFor(5);
        List<WebElement> allOptions = select.getOptions();
        for (WebElement option : allOptions) {
            System.out.println(option.getText());
        }
        System.out.println(allOptions.size());
        //size 2 veya daha kücük ise 2 hesap olmadigi anlamina gelir,
        Assert.assertFalse(allOptions.size()>2);

    }
    //tc02
    @When("verify that user can transfer money least two accounts")
    public void verify_that_user_can_transfer_money_least_two_accounts() {
        MoneyTransferPage moneyTransferPage=new MoneyTransferPage();
        Select select1 = new Select(moneyTransferPage.FromDropdown);
        BrowserUtils.waitFor(5);
        List<WebElement> allaccounts=select1.getOptions();
        for (WebElement account : allaccounts ) {
            System.out.println(account.getText());
        }
        System.out.println(allaccounts.size());
        Assert.assertTrue(allaccounts.size()>2);
    }
    //tc03
    @When("verify that user can not see any account on the TO Dropdown")
    public void verify_that_user_can_not_see_any_account_on_the_TO_Dropdown() {
        MoneyTransferPage moneyTransferPage=new MoneyTransferPage();
        BrowserUtils.waitFor(8);
        Select select = new Select(moneyTransferPage.FromDropdown);
        select.selectByIndex(1);
        Select select1=new Select(moneyTransferPage.ToDropDown);
        select1.selectByIndex(0);
        BrowserUtils.waitFor(4);
        Assert.assertTrue(moneyTransferPage.warningAlert.isDisplayed());

    }
    //tc04
    @When("verify that user should see two accounts on the FROM Dropdown")
    public void verify_that_user_should_see_two_accounts_on_the_FROM_Dropdown() {
        MoneyTransferPage moneyTransferPage=new MoneyTransferPage();
        Select select=new Select(moneyTransferPage.FromDropdown);
        BrowserUtils.waitFor(5);
        List<WebElement> accountsfrom=select.getOptions();
        for (WebElement w : accountsfrom) {
            System.out.println(w.getText());
        }
        System.out.println(accountsfrom.size());
        System.out.println(accountsfrom.get(1));
        System.out.println(accountsfrom.get(2));
      Assert.assertTrue(accountsfrom.size()>2);

    }
    //tc05
    @When("verify that user should get warning message under the balance textbox")
    public void verify_that_user_should_get_warning_message_under_the_balance_textbox() {
     MoneyTransferPage moneyTransferPage=new MoneyTransferPage();
     moneyTransferPage.balance.click();
        BrowserUtils.waitFor(4);
        moneyTransferPage.balance.sendKeys(Keys.TAB);
     Assert.assertTrue(moneyTransferPage.requiredMessage.isDisplayed());
    }

    //tc06
    @When("user types {string} inside balance textbox")
    public void user_types_inside_balance_textbox(String invalid) {
        MoneyTransferPage moneyTransferPage=new MoneyTransferPage();
        moneyTransferPage.balance.sendKeys(invalid+Keys.TAB);
        BrowserUtils.waitFor(4);
        Assert.assertTrue(moneyTransferPage.Max5Digit.isDisplayed());

    }
    //tc07
    @When("user types {string} inside balancecent textbox")
    public void user_types_inside_balancecent_textbox(String invalidbalancecent) {
        MoneyTransferPage moneyTransferPage=new MoneyTransferPage();
        moneyTransferPage.balancecent.sendKeys(invalidbalancecent+Keys.TAB);

    }

    @When("verify that user should get warning message under the balancecent textbox")
    public void verify_that_user_should_get_warning_message_under_the_balancecent_textbox() {
        MoneyTransferPage moneyTransferPage=new MoneyTransferPage();
        BrowserUtils.waitFor(4);
        Assert.assertTrue(moneyTransferPage.Max2Digit.isDisplayed());

    }
    //tc08
    @When("verify that user should get warning message under the description textbox")
    public void verify_that_user_should_get_warning_message_under_the_description_textbox() {
        MoneyTransferPage moneyTransferPage=new MoneyTransferPage();
        moneyTransferPage.description.sendKeys(Keys.TAB);
        BrowserUtils.waitFor(4);
        Assert.assertTrue(moneyTransferPage.RequiredMessageDescription.isDisplayed());
    }
    //TC09
    @When("verify that user should get success message left side")
    public void verify_that_user_should_get_success_message_left_side() {
        MoneyTransferPage moneyTransferPage=new MoneyTransferPage();
        Select select=new Select(moneyTransferPage.FromDropdown);
        BrowserUtils.waitFor(5);
        select.selectByIndex(1);
        Select select1=new Select(moneyTransferPage.ToDropDown);
        BrowserUtils.waitFor(5);
        select1.selectByIndex(1);
        BrowserUtils.waitFor(2);
        moneyTransferPage.balance.sendKeys("500"+Keys.TAB+"50"+Keys.TAB+"monthly bill");
        moneyTransferPage.makeTransfer.click();
        String succesmesaj=moneyTransferPage.getTextOfMoneyTransferResult();
        System.out.println(succesmesaj);
       //Assert.assertTrue(succesmesaj.
    }

}
