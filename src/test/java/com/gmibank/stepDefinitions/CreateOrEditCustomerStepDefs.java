package com.gmibank.stepDefinitions;

import com.gmibank.pages.BasePage;
import com.gmibank.pages.CreateOrEditCustomer;
import com.gmibank.pages.Customer;
import com.gmibank.pages.LoginPage;
import com.gmibank.utilities.BrowserUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CreateOrEditCustomerStepDefs {

    @When("user click Create_New_Customer button under customer_page")
    public void user_click_Create_New_Customer_button_under_customer_page() {
        new Customer().clickCreateNewCustomerButton();
    }

    @When("user types ssn number and click search_button")
    public void user_types_ssn_number_and_click_search_button() {
        //ssn numarasini nereden bulmam lazim?
        new CreateOrEditCustomer().typeSsnAndClickSearchButton("111-11-1111");
    }

    @Then("the create_or_edit_customer page is populated and getting successful message")
    public void the_create_or_edit_customer_page_is_populated_and_getting_successful_message() {
        CreateOrEditCustomer createOrEditCustomer = new CreateOrEditCustomer();
        String actualMessage = createOrEditCustomer.getAlertMessage();
        String expectedMessage = "gmiBankBackendApp.tPAccountRegistration.updated";
        System.out.println("actualMessage = " + actualMessage);
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }
}
