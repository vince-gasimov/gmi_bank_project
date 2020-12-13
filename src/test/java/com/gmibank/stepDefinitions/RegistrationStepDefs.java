package com.gmibank.stepDefinitions;

import com.gmibank.pages.RegistrationPage;
import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.DatabaseUtility;
import com.gmibank.utilities.DummyDataGenerator;
import com.gmibank.utilities.RandomStringGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

public class RegistrationStepDefs {
    @Then("Verify that this error message {string} is displayed")
    public void verify_that_this_error_message_is_displayed(String expectedMessage) {
        RegistrationPage registrationPage = new RegistrationPage();
        Assert.assertTrue(registrationPage.doesExistSuchErrorMessageInsidePage(expectedMessage));
        Assert.assertTrue(registrationPage.isThereAnyError());
    }


    @Then("Verify that error message {string} is displayed under {string} textbox")
    public void verify_that_error_message_is_displayed_under_textbox(String expectedMessage, String textBox) {
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

    @When("user type valid dummy information into following textBoxes")
    public void user_type_valid_dummy_information_into_following_textBoxes(List<String> textBoxList) {

       Map<String, String> keyValuePairs = DummyDataGenerator.generateAllNeededInformationExceptPassword(textBoxList);

       //password liste icinde yok ekle.   newPassword
        String password = RandomStringGenerator.generateStrongPassword(7,1,1,1,1);
        keyValuePairs.put("newPassword", password);
        keyValuePairs.put("passwordConfirmation", password);

        //textboxlari doldur
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.registerNewUser(keyValuePairs);

        //hata var mi diye kontrol et

        //userName double olup olmadigini check et. userName
        while (true) {
            String resultMessage = registrationPage.getTextOfRegistrationResult();
            if (resultMessage.contains("Login name already used!")) {
                String newUserName = DummyDataGenerator.generateUserName();
                keyValuePairs.replace("userName", newUserName);
                registrationPage.typeUserName(newUserName);
                System.out.println(keyValuePairs);
                System.out.println("bir deneme basarisiz");
            } else if (resultMessage.contains("error.ssnexists")) {
                String newSsn = DummyDataGenerator.generateSsnNumber();
                keyValuePairs.replace("ssnNumber", newSsn);
                registrationPage.typeSsnNumber(newSsn);
                System.out.println(keyValuePairs);
                System.out.println("bir deneme basarisiz");
            } else if(resultMessage.contains("Registration saved!")) {
                System.out.println("no problem it should be passed");
                System.out.println(keyValuePairs);
                break;
            }else{
                System.out.println("unknown error");
                System.out.println("resultMessage = " + resultMessage);
            }
        }

    }

    @Then("verify that new user is registered by getting such a message {string}")
    public void verify_that_new_user_is_registered_by_getting_such_a_message(String expectedMessage) {
        RegistrationPage registrationPage = new RegistrationPage();
        String actualResultMessage = registrationPage.getTextOfRegistrationResult();
        Assert.assertTrue(actualResultMessage.contains(expectedMessage));
    }

    @Then("verify that new user is appeared in database")
    public void verify_that_new_user_is_appeared_in_database() {

    }

}
