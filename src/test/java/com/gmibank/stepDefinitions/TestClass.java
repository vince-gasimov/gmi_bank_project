package com.gmibank.stepDefinitions;

import com.gmibank.pages.BasePage;
import com.gmibank.pages.LoginPage;
import com.gmibank.pages.TablePage;
import com.gmibank.utilities.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

public class TestClass {

@BeforeTest
public void setup(){
    WebDriver driver = Driver.getDriver();
    driver.get(ConfigurationReader.getProperty("url"));
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
}


@AfterTest
public void tearDown(){
    BrowserUtils.waitFor(1);
    Driver.closeDriver();
}

    @Test
    public void test1() throws Exception {


        BasePage basePage = new BasePage();
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon("Sign in");
        LoginPage loginPage= new LoginPage();
        loginPage.loginWithValidInfo("employee");

        basePage.clickAndSelectDropDownItemUnderMyOperationsNavItem("Manage Customers");

        TablePage tablePage = new TablePage();
        BrowserUtils.waitFor(2);
        //System.out.println(customer.getWebElementWithGivenButtonType("07-12-20-13-54@US012.com", "view").getAttribute("outerHTML"));
        //System.out.println(Driver.getDriver().findElement(By.xpath("//td[text()='team-19@gmail.com']/following-sibling::td//a[@class='btn btn-info btn-sm']")).getAttribute("outerHTML"));

        System.out.println("customer.getTotalNumberOfPages() = " + tablePage.getTotalNumberOfPages());
        //BrowserUtils.scrollToElement(tablePage.pageNumberInfo);



/*        for (int i = 0; i < 10; i++) {
            customer.moveToNextPage();
            BrowserUtils.waitFor(2);
        }*/

        tablePage.clickGivenButtonForWantedEmail("aline.hilpert@example.com", "view");

    }

}
