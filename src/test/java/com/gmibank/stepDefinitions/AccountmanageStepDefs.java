package com.gmibank.stepDefinitions;

import com.gmibank.pages.AccountmanagePage;
import com.gmibank.pages.BasePage;
import com.gmibank.pages.LoginPage;
import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.ConfigurationReader;
import com.gmibank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class AccountmanageStepDefs {
    //TC01
    @Given("user sign in to the system as new Customer")
    public void user_sign_in_to_the_system_as_new_Customer() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        LoginPage loginPage=new LoginPage();
        AccountmanagePage accountmanagePage=new AccountmanagePage();
        accountmanagePage.Login.click();
        accountmanagePage.SignIn.click();
        loginPage.userNameTextBox.sendKeys(ConfigurationReader.getProperty("new_customer"));
        loginPage.passwordTextBox.sendKeys(ConfigurationReader.getProperty("new_customer_password"));
        loginPage.sigInButton.click();
    }
    //TC01
    @When("user navigates to {string} under the My Operations menu")
    public void user_navigates_to_under_the_My_Operations_menu(String pageName) throws Exception  {
        new BasePage().clickAndSelectDropDownItemUnderMyOperationsNavItem(pageName);
    }
    //TC01
    @When("verify that visible titles such as Balance and AccountType")
    public void verify_that_visible_titles_such_as_Balance_and_AccountType() {
        AccountmanagePage accountmanagePage=new AccountmanagePage();
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(accountmanagePage.AccountType.isDisplayed());
        softAssert.assertTrue(accountmanagePage.AccountBalance.isDisplayed());
        softAssert.assertAll();
    }
    @When("verify that a view Transaction and clickable and visible")
    public void verify_that_a_view_Transaction_and_clickable_and_visible() {
        AccountmanagePage accountmanagePage=new AccountmanagePage();
        //SoftAssert softAssert=new SoftAssert();
        if(accountmanagePage.viewTransaction.isDisplayed()==true){
            accountmanagePage.viewTransaction.click();
        }
        BrowserUtils.waitFor(5);
        //Assert.assertFalse(accountmanagePage.TransactionNotFound.isDisplayed());
      Assert.assertTrue(accountmanagePage.transacAccountId.isDisplayed());
       Assert.assertTrue(accountmanagePage.transacDescriptions.isDisplayed());
        //softAssert.assertAll();
    }

}

