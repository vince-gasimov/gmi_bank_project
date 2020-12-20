package com.gmibank.stepDefinitions;

import com.gmibank.pages.CreateOrEditCustomer;
import com.gmibank.pages.CustomersPageWithTable;
import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.DateUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

public class CreateOrEditCustomerStepDefs {

    CreateOrEditCustomer createOrEditCustomer = new CreateOrEditCustomer();

    @When("user click Create_New_Customer button under customer_page")
    public void user_click_Create_New_Customer_button_under_customer_page() {
        new CustomersPageWithTable().clickCreateButton();
    }

    @When("user types ssn number and click search_button")
    public void user_types_ssn_number_and_click_search_button() {
        //ssn numarasini nereden bulmam lazim?
        new CreateOrEditCustomer().typeSsnAndClickSearchButton("450-37-8358");
    }

    @Then("the create_or_edit_customer page is populated and getting successful message")
    public void the_create_or_edit_customer_page_is_populated_and_getting_successful_message() {
        String actualMessage = createOrEditCustomer.getAlertMessage();
        String expectedMessage = "gmiBankBackendApp.tPAccountRegistration.updated";
        System.out.println("actualMessage = " + actualMessage);
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }


    @When("user click and leaves blank {string} textBox and press TAB")
    public void user_click_and_leaves_blank_textBox_and_press_TAB(String textBox) {
        createOrEditCustomer.leaveBlankTextBox(textBox);

    }

    @Then("verify that an error message such as {string} is appeared under textBox")
    public void verify_that_an_error_message_such_as_is_appeared_under_textBox(String errorMessage) {
        Assert.assertTrue(createOrEditCustomer.isThereAnySuchErrorUnderTextBoxes(errorMessage));
    }

    //US_011

    @When("user click date&time box")
    public void user_click_date_time_box() {
            createOrEditCustomer.DateTimeBox.click();
    }

    @When("user types invalid date {string} and press tab key")
    public void user_types_invalid_date_and_press_tab_key(String invalidDate) {
        createOrEditCustomer.DateTimeBox.sendKeys(invalidDate);
        //BrowserUtils.waitFor(5);
        createOrEditCustomer.DateTimeBox.sendKeys(Keys.TAB);
    }

    @Then("verify that invalid date is not accepted")
    public void verify_that_invalid_date_is_not_accepted() {
        Assert.assertFalse(createOrEditCustomer.DateTimeBox.getAttribute("value").isEmpty());
    }

    @When("user types valid date and press tab key")
    public void user_types_valid_date_and_press_tab_key() {
        createOrEditCustomer.DateTimeBox.sendKeys(DateUtil.todaysDate5());
        createOrEditCustomer.DateTimeBox.sendKeys(Keys.TAB);
    }

    @Then("verify that valid date is accepted")
    public void verify_that_valid_date_is_accepted() {
        Assert.assertFalse(createOrEditCustomer.DateTimeBox.getAttribute("value").isEmpty());
    }

    @When("user types valid date form {string} and press tab key")
    public void user_types_valid_date_form_and_press_tab_key(String validDateForm) {
        createOrEditCustomer.DateTimeBox.sendKeys(validDateForm);
        createOrEditCustomer.DateTimeBox.sendKeys(Keys.TAB);
    }

    @Then("verify that valid date form is accepted")
    public void verify_that_valid_date_form_is_accepted() {
        Assert.assertFalse(createOrEditCustomer.DateTimeBox.getAttribute("value").isEmpty());
    }

    @When("user types invalid date form {string} and press tab key")
    public void user_types_invalid_date_form_and_press_tab_key(String invalidDateForm) {
        createOrEditCustomer.DateTimeBox.sendKeys(invalidDateForm);
        createOrEditCustomer.DateTimeBox.sendKeys(Keys.TAB);
    }

    @Then("verify that invalid date form is not accepted")
    public void verify_that_invalid_date_form_is_not_accepted() {
        Assert.assertFalse(createOrEditCustomer.DateTimeBox.getAttribute("value").isEmpty());
    }

    @When("user click User box")
    public void user_click_User_box() {
        createOrEditCustomer.UserBox.click();
    }

    @When("user select a User from the drop down menu")
    public void user_select_a_User_from_the_drop_down_menu() {
        Select select = new Select(createOrEditCustomer.UserBox);
        select.selectByIndex(3);
    }

    @Then("verify that selected a User from the drop down menu")
    public void verify_that_selected_a_User_from_the_drop_down_menu() {
        Assert.assertFalse(createOrEditCustomer.UserBox.getText().isEmpty());
    }

    @When("user select blank option from the drop-down menu")
    public void user_select_blank_option_from_the_drop_down_menu() {
        Select select = new Select(createOrEditCustomer.UserBox);
        select.selectByIndex(0);
    }

    @Then("verify that not selected blank option from the drop-down menu")
    public void verify_that_not_selected_blank_option_from_the_drop_down_menu() {
        Assert.assertFalse(createOrEditCustomer.UserBox.getText().isEmpty());
    }

    @When("user click Account box")
    public void user_click_Account_box() {
        createOrEditCustomer.AccountBox.click();
    }

    @When("user select an account")
    public void user_select_an_account() {
        Select select = new Select(createOrEditCustomer.AccountBox);
        select.selectByValue("2313");
    }

    @Then("verify that selected an account")
    public void verify_that_selected_an_account() {
        Assert.assertFalse(createOrEditCustomer.AccountBox.getText().isEmpty());
    }

    @When("user click Zelle Enrolled check box")
    public void user_click_Zelle_Enrolled_check_box() {
        createOrEditCustomer.ZelleCheckBox.click();
    }

    @Then("verify that clicked Zelle Enrolled check box")
    public void verify_that_clicked_Zelle_Enrolled_check_box() {
        Assert.assertTrue(createOrEditCustomer.ZelleCheckBox.isSelected());
    }


}
