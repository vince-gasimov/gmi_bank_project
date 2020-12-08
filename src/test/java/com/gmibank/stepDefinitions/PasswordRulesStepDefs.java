package com.gmibank.stepDefinitions;

import com.gmibank.pages.RegistrationPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class PasswordRulesStepDefs {


    @When("user types a {string} {string} and press TAB")
    public void user_types_a_and_press_TAB(String textBox, String value) {
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.typeValueInsideASpecifiedTextBox(textBox, value);
    }


    @Then("verify that a message is displayed such as {string}")
    public void verify_that_a_message_is_displayed_such_as(String expectedMessage) {
        RegistrationPage registrationPage = new RegistrationPage();
        String actualErrorMessage = registrationPage.getMessage(registrationPage.newPasswordErrorMessage);
        Assert.assertEquals(expectedMessage, actualErrorMessage);
    }

    @Then("verify that no message is displayed")
    public void verify_that_no_message_is_displayed() {
        RegistrationPage registrationPage = new RegistrationPage();
        Assert.assertFalse(registrationPage.isThereAnyError());
    }


    @Then("verify that {int} led is lighting")
    public void verify_that_led_is_lighting(int expectedNumber) {
        RegistrationPage registrationPage = new RegistrationPage();
        int actualNumber = registrationPage.getNumberOfLightingLedsForPasswordStrength();
        Assert.assertEquals(expectedNumber, actualNumber);
    }

}
