package com.gmibank.stepDefinitions;

import com.gmibank.pages.*;
import com.gmibank.utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.UnknownServiceException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestClass {

    @BeforeMethod
    public void setup() {
    WebDriver driver = Driver.getDriver();
    driver.get(ConfigurationReader.getProperty("url"));
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }


    @AfterMethod
    public void tearDown() {
    BrowserUtils.waitFor(1);
    Driver.closeDriver();
    }

    @Test
    public void test1() throws Exception {


        BasePage basePage = new BasePage();
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon("Sign in");
        LoginPage loginPage = new LoginPage();
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
        LoginPage loginPage = new LoginPage();
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
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithValidInfo("employee");
        BrowserUtils.waitFor(2);
        basePage.clickGivenNavItemAndSelectGivenDropDownItem("My Operations", "Manage Accounts");
        AccountsPageWithTable accountsPageWithTable = new AccountsPageWithTable();
        accountsPageWithTable.clickGivenButtonForWantedColumnAndValue("Description", "team13 customer account2", "view");
        System.out.println("basePage.getPageHeader() = " + basePage.getPageHeader());
        Assert.assertTrue(basePage.getPageHeader().equals("Account [30239]"));
        BrowserUtils.waitFor(3);
    }


    @Test
    public void test4() {
        System.out.println("DummyDataGenerator.generateAddress() = " + DummyDataGenerator.generateAddress());
        Map<String, String> map = new HashMap<>();
        map = DummyDataGenerator.generateAllNeededInformationExceptPassword();
        for (String s : map.keySet()) {
            System.out.println(s + " " + map.get(s));
        }
        ExcelUtilities excelUtilities = new ExcelUtilities("src/test/resources/CreatedUserInformation.xlsx", "registration_sheet_name");
        System.out.println("excelUtilities.getColumnsNames() = " + excelUtilities.getColumnsNames());
        System.out.println(excelUtilities.getColumnsNames().indexOf("ssnNumber"));
        excelUtilities.writeUserIntoExcel(map);

    }

    @Test
    public void test5() throws Exception {
        String path = "src/test/resources/CreatedUserInformation.xlsx";

        ExcelUtilities excel = new ExcelUtilities(path, "registered");
/*        int rowNum = excel.rowCount();
        System.out.println("rowNum = " + rowNum);
        System.out.println("excel.getDataList() = " + excel.getDataList());
        excel.removeLastRow();
        System.out.println("excel.getDataList() = " + excel.getDataList());
        rowNum = excel.rowCount();
        excel.saveWorkBook();*/
        User user;

        RegistrationPage registrationPage = new RegistrationPage();
        if (registrationPage.makeSureThereExistRegistrantInExcel()){
            user = excel.getLastRegistrantAsUser();
            System.out.println("lastRegistrantInfoMap = " + user.getFirstName());
            System.out.println("lastRegistrantInfoMap = " + user.getLastName());
        }

    }

    @Test
    public void test6() throws Exception {
        BasePage basePage = new BasePage();
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon("Sign in");
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithValidInfo("admin");
        BrowserUtils.waitFor(2);
        basePage.clickGivenNavItemAndSelectGivenDropDownItem("Administration", "User management");
        UsersPageWithTable usersPageWithTable = new UsersPageWithTable();
        BrowserUtils.waitForClickablility(usersPageWithTable.createButton,5);
        String randomEmail = usersPageWithTable.getOneRandomEmailFromCurrentPage();
        System.out.println("randomEmail = " + randomEmail);
        String firstActivationStatus = usersPageWithTable.getActivationStatus(randomEmail);
        System.out.println("firstActivationStatus = " + firstActivationStatus);
        usersPageWithTable.clickAndChangeActivationStatus(randomEmail);
        String expectedResultMessage = "A user is updated with";
        Assert.assertTrue(usersPageWithTable.doesContainSuchAMessageInsideAlert(expectedResultMessage));
        BrowserUtils.waitForInvisibility(By.xpath("//div[@role='alert']"),10);
        Driver.getDriver().navigate().refresh();
        //BrowserUtils.waitFor(10);
        WebElement element = usersPageWithTable.locateWantedCellWithGivenColumnAndValue("Email", randomEmail);
        BrowserUtils.hover(element);
        String secondActivationStatus = usersPageWithTable.getActivationStatus(randomEmail);

        System.out.println("secondActivationStatus = " + secondActivationStatus);
        Assert.assertFalse(firstActivationStatus.equals(secondActivationStatus));
    }

    @Test
    public void test7(){
        String path = "src/test/resources/CreatedUserInformation.xlsx";
        ExcelUtilities excel = new ExcelUtilities(path, "registration_sheet_name");
        System.out.println("excel.getLastRow() = " + excel.getLastRow());
    }

}