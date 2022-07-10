package com.gmibank.stepDefinitions.uiStepDefs;

import com.gmibank.pages.PasswordForPage;
import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.ConfigurationReader;
import com.gmibank.utilities.RandomStringGenerator;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PasswordRenewStepDefs {

    @When("user types current password and new generated password and then click to save button inside password_renew_page")
    public void user_types_current_password_and_new_generated_password_and_then_click_to_save_button_inside_password_renew_page() {
        PasswordForPage passwordForPage = new PasswordForPage();
        passwordForPage.changePasswordWithRandomGenerated();
    }

    @Then("verify that a message is displayed such as {string} inside password_renew_page")
    public void verify_that_a_message_is_displayed_such_as_inside_password_renew_page(String message) {
        PasswordForPage passwordForPage = new PasswordForPage();
        Assert.assertTrue(passwordForPage.doesMessageContains(message));
    }

    @When("user types {string} inside textBox {string} on password_renew_page")
    public void user_types_inside_textBox_on_password_renew_page(String value, String textBox) {
        PasswordForPage passwordForPage = new PasswordForPage();
        passwordForPage.typeValueInsideASpecifiedTextBox(value, textBox);
    }

    @Then("verify that a message is displayed such as {string} on password_renew_page")
    public void verify_that_a_message_is_displayed_such_as_on_password_renew_page(String expectedMessage) {
        PasswordForPage passwordForPage = new PasswordForPage();
        Assert.assertTrue(passwordForPage.doesExistSuchAnError(expectedMessage));
    }


    @When("user change password with a new one {string} and change again with old one")
    public void user_change_password_with_a_new_one_and_change_again_with_old_one(String newPassword) {
        PasswordForPage passwordForPage = new PasswordForPage();
        String oldPassword = ConfigurationReader.getProperty("dynamic_customer_password");
        System.out.println("Degisim oncesi = " + ConfigurationReader.getProperty("dynamic_customer_password"));
        passwordForPage.changePasswordWithGivenOne(newPassword);
        System.out.println("1. Degisim sonrasi = " + ConfigurationReader.getProperty("dynamic_customer_password"));
        BrowserUtils.waitFor(10);
        passwordForPage.clearAllTextBoxes();
        passwordForPage.changePasswordWithGivenOne(oldPassword);
        System.out.println("2. Degisim sonrasi = " + ConfigurationReader.getProperty("dynamic_customer_password"));
    }


    @Then("verify that no error message under password textbox is displayed and {int} led lighting")
    public void verify_that_no_error_message_under_password_textbox_is_displayed_and_led_lighting(int expectedNumberOfLed) {
        PasswordForPage passwordForPage = new PasswordForPage();
        List<WebElement> errorListUnderTextBoxes = passwordForPage.errorListUnderTextBoxes();
        Assert.assertEquals(0, errorListUnderTextBoxes.size());
        int actualNumberOfLightingLed = passwordForPage.getNumberOfLightingLedsForPasswordStrength();
        Assert.assertEquals(expectedNumberOfLed, actualNumberOfLightingLed);
    }


    @When("user types a different password as current password and new generated password into new password textbox and then click to save button inside password_renew_page")
    public void user_types_a_different_password_as_current_password_and_new_generated_password_into_new_password_textbox_and_then_click_to_save_button_inside_password_renew_page() {
        //guncel sifremle yani olmayan bir sifre uret
        String oldPassword = ConfigurationReader.getProperty("dynamic_customer_password");
        String randomCurrentPassword = RandomStringGenerator.compareAndGenerateAgain(oldPassword,7, 1,1,1,1);
        //bu sifreyi guncel sifre textbox'a yaz
        PasswordForPage passwordForPage = new PasswordForPage();
        passwordForPage.typeCurrentPassword(randomCurrentPassword);
        //yeni sifre uret
        String newRandomPassword = RandomStringGenerator.compareAndGenerateAgain(oldPassword,7, 1,1,1,1);
        //yeni sifreleri yeni sifre ve confirmatin texboxlarina yaz
        passwordForPage.typeNewPassword(newRandomPassword);
        passwordForPage.typeNewPasswordConfirmation(newRandomPassword);
        passwordForPage.clickSaveButton();
    }

    @When("user types {string} into new password textBox and {string} into new password confirmation textbox")
    public void user_types_into_new_password_textBox_and_into_new_password_confirmation_textbox(String newPassword, String differentNewPassword) {
       PasswordForPage passwordForPage = new PasswordForPage();
       passwordForPage.typeNewPassword(newPassword);
       passwordForPage.typeNewPasswordConfirmation(differentNewPassword);
    }
}
