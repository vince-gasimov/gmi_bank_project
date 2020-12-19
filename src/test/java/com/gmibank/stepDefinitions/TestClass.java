package com.gmibank.stepDefinitions;

import com.gmibank.pages.*;
import com.gmibank.utilities.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;


import java.util.concurrent.TimeUnit;

public class TestClass {

@BeforeMethod
public void setup(){
    WebDriver driver = Driver.getDriver();
    driver.get(ConfigurationReader.getProperty("url"));
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
}


@AfterMethod
public void tearDown(){
    BrowserUtils.waitFor(1);
    Driver.closeDriver();
}

    @Test
    public void test1() throws Exception {


        BasePage basePage = new BasePage();
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon("Sign in");
        LoginPage loginPage= new LoginPage();
        loginPage.loginWithValidInfo("admin");
        BrowserUtils.waitFor(2);
        basePage.clickGivenNavItemAndSelectGivenDropDownItem("Administration", "User management");
        UsersPageWithTable usersPageWithTable = new UsersPageWithTable();
        usersPageWithTable.clickGivenButtonForWantedColumnAndValue("Email", "jenny@gmail.com", "edit");
        System.out.println("basePage.getPageHeader() = " + basePage.getPageHeader());
        Assert.assertTrue(basePage.getPageHeader().contains("User ["));
        BrowserUtils.waitFor(3);

    }

    @Test
    public void test2() throws Exception {
        BasePage basePage = new BasePage();
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon("Sign in");
        LoginPage loginPage= new LoginPage();
        loginPage.loginWithValidInfo("employee");
        BrowserUtils.waitFor(2);
        basePage.clickGivenNavItemAndSelectGivenDropDownItem("My Operations", "Manage Customers");
        CustomersPageWithTable customersPageWithTable = new CustomersPageWithTable();
        customersPageWithTable.clickGivenButtonForWantedColumnAndValue("Email", "gmiprojesi@gmail.com", "view");
        System.out.println("basePage.getPageHeader() = " + basePage.getPageHeader());
        BrowserUtils.waitFor(2);
        CustomerInfoPageForOneUser customerInfoPageForOneUser = new CustomerInfoPageForOneUser();
        System.out.println(customerInfoPageForOneUser.emailHeader.getText());
        System.out.println(customerInfoPageForOneUser.getGivenTextValue("email"));
        //System.out.println(customerInfoPageForOneUser.getGivenTextValue("country"));
    }


    @Test
    public void test3() throws Exception {
        BasePage basePage = new BasePage();
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon("Sign in");
        LoginPage loginPage= new LoginPage();
        loginPage.loginWithValidInfo("employee");
        BrowserUtils.waitFor(2);
        basePage.clickGivenNavItemAndSelectGivenDropDownItem("My Operations", "Manage Accounts");
        AccountsPageWithTable accountsPageWithTable = new AccountsPageWithTable();
        accountsPageWithTable.clickGivenButtonForWantedColumnAndValue("Description", "team13 customer account2", "view");
        System.out.println("basePage.getPageHeader() = " + basePage.getPageHeader());
        Assert.assertTrue(basePage.getPageHeader().equals("Account [30239]"));
        BrowserUtils.waitFor(3);
    }

}
