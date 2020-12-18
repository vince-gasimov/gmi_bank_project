package com.gmibank.stepDefinitions;

import com.gmibank.pages.CreateOrEditCustomer;
import com.gmibank.pages.CustomersPageWithTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CreateOrEditCustomerStepDefs {

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
        CreateOrEditCustomer createOrEditCustomer = new CreateOrEditCustomer();
        String actualMessage = createOrEditCustomer.getAlertMessage();
        String expectedMessage = "gmiBankBackendApp.tPAccountRegistration.updated";
        System.out.println("actualMessage = " + actualMessage);
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }


    @When("user click and leaves blank {string} textBox and press TAB")
    public void user_click_and_leaves_blank_textBox_and_press_TAB(String textBox) {
        CreateOrEditCustomer createOrEditCustomer = new CreateOrEditCustomer();
        createOrEditCustomer.leaveBlankTextBox(textBox);

    }

    @Then("verify that an error message such as {string} is appeared under textBox")
    public void verify_that_an_error_message_such_as_is_appeared_under_textBox(String errorMessage) {
        CreateOrEditCustomer createOrEditCustomer = new CreateOrEditCustomer();
        Assert.assertTrue(createOrEditCustomer.isThereAnySuchErrorUnderTextBoxes(errorMessage));
    }

}
