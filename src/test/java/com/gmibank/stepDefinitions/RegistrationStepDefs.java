package com.gmibank.stepDefinitions;

import com.gmibank.pages.RegistrationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class RegistrationStepDefs {
    @Then("Verify that this error message {string} is displayed")
    public void verify_that_this_error_message_is_displayed(String expectedMessage) {
        RegistrationPage registrationPage = new RegistrationPage();
        Assert.assertTrue(registrationPage.doesExistSuchErrorMessageInsidePage(expectedMessage));
        Assert.assertTrue(registrationPage.isThereAnyError());
    }



    @Then("Verify that error message {string} is displayed under {string} textbox")
    public void verify_that_error_message_is_displayed_under_textbox(String expectedMessage, String textBox ) {
        RegistrationPage registrationPage = new RegistrationPage();
        String actualMessage = registrationPage.getErrorMessageFromSpecifiedTextBox(textBox);
        System.out.println("actualMessage = " + actualMessage);
        registrationPage.getMessage(registrationPage.mobilePhoneErrorMessage);
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Given("user types click and leaves blank {string} textbox")
    public void user_types_click_and_leaves_blank_textbox(String textBox) {
        new RegistrationPage().typeValueInsideASpecifiedTextBox(textBox, "");
    }
}
