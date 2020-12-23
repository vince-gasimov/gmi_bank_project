package com.gmibank.stepDefinitions;

import com.gmibank.pages.UsersPageWithTable;
import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ManagementOfUsersByAdminStepDefs {
    UsersPageWithTable usersPageWithTable = new UsersPageWithTable();
    String firstActivationStatus = "yok";
    String randomEmail = "yok";

    @When("admin changes activation status of a randomly selected user")
    public void admin_changes_activation_status_of_a_randomly_selected_user() {

        BrowserUtils.waitForClickablility(usersPageWithTable.createButton,5);
        randomEmail = usersPageWithTable.getOneRandomEmailFromCurrentPage();
        System.out.println("randomEmail = " + randomEmail);
        firstActivationStatus = usersPageWithTable.getActivationStatus(randomEmail);
        System.out.println("firstActivationStatus = " + firstActivationStatus);
        usersPageWithTable.clickAndChangeActivationStatus(randomEmail);
    }
    @Then("verify that such an information message {string} is appeared after change of status operation")
    public void verify_that_such_an_information_message_is_appeared_after_change_of_status_operation(String message) {
        Assert.assertTrue(usersPageWithTable.doesContainSuchAMessageInsideAlert(message));
    }
    @Then("verify that activation status has changed")
    public void verify_that_activation_status_has_changed() {
        Driver.getDriver().navigate().refresh();
        WebElement element = usersPageWithTable.locateWantedCellWithGivenColumnAndValue("Email", randomEmail);
        BrowserUtils.hover(element);
        String secondActivationStatus = usersPageWithTable.getActivationStatus(randomEmail);
        System.out.println("secondActivationStatus = " + secondActivationStatus);
        Assert.assertFalse(firstActivationStatus.equals(secondActivationStatus));
    }

}
