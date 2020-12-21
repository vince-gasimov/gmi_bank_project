package com.gmibank.stepDefinitions.DatabaseStepDefs;

import com.gmibank.pages.UsersPageWithTable;
import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.ConfigurationReader;
import com.gmibank.utilities.Driver;
import com.gmibank.utilities.ExcelUtilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Map;

public class AdminActivation {
    UsersPageWithTable usersPageWithTable = new UsersPageWithTable();
    String path = ConfigurationReader.getProperty("registration_excel_path");
    String registrationSheet = ConfigurationReader.getProperty("registration_sheet_name");
    Map<String, String> registrantInfoMapFromExcel;

    @Given("find the user taken from excel and activate it as {string}")
    public void find_the_user_taken_from_excel_and_activate_it_as(String string) {
        ExcelUtilities excel = new ExcelUtilities(path, registrationSheet);
        registrantInfoMapFromExcel = excel.getLastRow();
        String emailFromExcel = registrantInfoMapFromExcel.get("email");
        BrowserUtils.waitForClickablility(usersPageWithTable.createButton,5);
        WebElement emailElement = usersPageWithTable.locateWantedCellWithGivenColumnAndValue("Email", emailFromExcel);
        String oldActivationStatus = usersPageWithTable.getActivationStatus(emailFromExcel);


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

    @Then("verify that UI information matches database response")
    public void verify_that_UI_information_matches_database_response() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
