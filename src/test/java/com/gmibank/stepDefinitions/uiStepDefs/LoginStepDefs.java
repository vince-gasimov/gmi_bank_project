package com.gmibank.stepDefinitions.uiStepDefs;

import com.gmibank.pages.BasePage;
import com.gmibank.pages.LoginPage;
import com.gmibank.pages.RegistrationPage;
import com.gmibank.pages.ResetYourPasswordPage;
import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.ConfigurationReader;
import com.gmibank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class LoginStepDefs {

    LoginPage loginPage = new LoginPage();
    BasePage basePage = new BasePage();

    @Given("user sign in to the system as {string}")
    public void user_sign_in_to_the_system_as(String userType) throws Exception {
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon("Sign in");
        loginPage.loginWithValidInfo(userType);
        BrowserUtils.waitForVisibility(new BasePage().myOperationsNavItem, 5);
        //BrowserUtils.waitForTextAppearInElement(basePage.accountMenuIcon, "group16", 10);
    }

    @When("user types and submit the valid {string} credentials")
    public void user_types_and_submit_the_valid_credentials(String userType) {
        loginPage.loginWithValidInfo(userType);
        //BrowserUtils.waitFor(2);
    }

    @Then("{string} page should be displayed with user information beside icon")
    public void page_should_be_displayed_with_user_information_beside_icon(String pageName) {
        BrowserUtils.waitForTextAppearInElement(basePage.accountMenuIcon, "group16", 10);

        String userName = basePage.getUserNameBesideAccountMenuIcon();
        String actualURL = Driver.getDriver().getCurrentUrl();
        String expectedURL = basePage.getPageUrl(pageName);

        Assert.assertEquals(expectedURL, actualURL);

        //eger user name degeri null degilse sisteme giris yapilmis demektir
        Assert.assertNotEquals(null, userName);

        //System.out.println("expectedURL = " + expectedURL);
        //System.out.println("userName = " + userName);


    }


    @Given("user click cancel button")
    public void user_click_cancel_button() {
        loginPage.clickCancelButton();
    }

    @Then("verify that user is on the home page without sign in to system")
    public void verify_that_user_is_on_the_home_page_without_sign_in_to_system() {
        String expectedUrl = basePage.getPageUrl("home");
        String actualUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
        String userName = basePage.getUserNameBesideAccountMenuIcon();
        Assert.assertEquals("", userName);
    }

    @When("user types invalid username {string}")
    public void user_types_invalid_username(String invalidUserName) {
        loginPage.typeUserName(invalidUserName);
    }

    @When("user types valid password")
    public void user_types_valid_password() {
        loginPage.typePassword(ConfigurationReader.getProperty("employee_password"));
    }

    @When("user click to sign in button")
    public void user_click_to_sign_in_button() {
        loginPage.clickSignInButton();
    }

    @Then("verify that login operation failed")
    public void verify_that_login_operation_failed() {
        String actualResultMessage = loginPage.getResultMessage();
        System.out.println("actualResultMessage = " + actualResultMessage);
        String expectedResultMessage = "Failed to sign in!";
        BrowserUtils.waitFor(2);
        Assert.assertTrue(actualResultMessage.contains(expectedResultMessage));
    }

    @When("user types valid username")
    public void user_types_valid_username() {
        loginPage.typeUserName(ConfigurationReader.getProperty("employee_user_name"));
    }

    @When("user types invalid password {string}")
    public void user_types_invalid_password(String invalidPassword) {
        loginPage.typePassword(invalidPassword);
    }

    @When("user click on the forget password link")
    public void user_click_on_the_forget_password_link() {
        loginPage.clickForgotPassword();
    }

    @Then("verify that user navigate to forgot password page")
    public void verify_that_user_navigate_to_forgot_password_page() {
        ResetYourPasswordPage resetYourPasswordPage = new ResetYourPasswordPage();
        BrowserUtils.waitForVisibility(resetYourPasswordPage.pageHeader, 5);
        String actualPageHeader = resetYourPasswordPage.getPageHeader();
        String expectedPageHeader = "Reset your password";
        Assert.assertEquals(expectedPageHeader, actualPageHeader);
    }

    @Given("user click on the register a new account link")
    public void user_click_on_the_register_a_new_account_link() {
        loginPage.clickRegisterLink();
    }

    @Then("verify that user navigate to registration page")
    public void verify_that_user_navigate_to_registration_page() {
        RegistrationPage registrationPage = new RegistrationPage();
        BrowserUtils.waitForVisibility(registrationPage.ssnTextBox, 5);
        String expectedUrl = "https://gmibank.com/account/register";
        String actualUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }


}
