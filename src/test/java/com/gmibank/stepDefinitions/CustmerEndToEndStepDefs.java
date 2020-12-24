package com.gmibank.stepDefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmibank.Api.ApiUtilities.ApiApplicantsUtilities;
import com.gmibank.pages.BasePage;
import com.gmibank.pages.LoginPage;
import com.gmibank.pages.RegistrationPage;
import com.gmibank.utilities.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

public class CustmerEndToEndStepDefs {
    String path = ConfigurationReader.getProperty("registration_excel_path");
    String registrationSheetName = ConfigurationReader.getProperty("registration_sheet_name");
    ExcelUtilities excel;
    User userFromExcel;
    Map<String, Object> mapFromDatabase;
    String emailFromExcel;
    long idFromDb;
    Response response;
    com.gmibank.Api.pojos.User userFromApi;
    ObjectMapper oMapper;
    BasePage basePage;
    LoginPage loginPage;


    @When("a customer candidate register itself with valid information on registration page")
    public void a_customer_candidate_register_itself_with_valid_information_on_registration_page() {

        new RegistrationPage().navigateAndRegisterNewUserWithRandomGeneratedValue();
        //with above method, after registration, it navigates to home page

    }

    @Then("verify the newregistered customer registration information directly from database")
    public void verify_the_newregistered_customer_registration_information_directly_from_database() {

        excel = new ExcelUtilities(path, registrationSheetName);
        userFromExcel = excel.getLastRegistrantAsUser();
        emailFromExcel = userFromExcel.getEmail();
        mapFromDatabase = DatabaseUtility.getUserInformationWithEmailFromGivenTable("tpaccount_registration", emailFromExcel);

        //take id and store it in the user object
        idFromDb = (long) mapFromDatabase.get("id");

        userFromExcel.setId(String.valueOf(idFromDb));
        assertTrue(DatabaseUtility.compareMapFromDbAndUserObjectForNewRegistration(mapFromDatabase,userFromExcel));
    }

    @Then("verify the newregistered customer registration information with the help of API")
    public void verify_the_newregistered_customer_registration_information_with_the_help_of_API() throws IOException {
        int id = Integer.parseInt(userFromExcel.getId());
        response = ApiApplicantsUtilities.getSpecifiedApplicantInfo(id);

        JsonPath jsonPath = response.jsonPath();
        String ssnFromApi = jsonPath.getString("ssn");
        String firstNameFromApi = jsonPath.getString("firstName");
        String lastNameFromApi = jsonPath.getString("lastName");
        String userNameFromApi = jsonPath.getString("userName");
        String emailFromApi = jsonPath.getString("email");

        assertEquals("ssn is wrong", userFromExcel.getSsnNumber(), ssnFromApi);
        assertEquals("firstName is wrong", userFromExcel.getFirstName(), firstNameFromApi);
        assertEquals("lastname is wrong", userFromExcel.getLastName(), lastNameFromApi);
        assertEquals("userName is wrong", userFromExcel.getUserName(), userNameFromApi);
        assertEquals("email is wrong", userFromExcel.getEmail(), emailFromApi);


                /*
                {
    "id": 61613,
    "ssn": "172-83-7736",
    "firstName": "Todd",
    "lastName": "Strosin",
    "address": "86776 Lonnie Drives",
    "mobilePhoneNumber": "511-434-3547",
    "userId": 61574,
    "userName": "nguyet.welch",
    "email": "johnson.reichert@hotmail.com",
    "createDate": "2020-12-24T15:25:09.804Z"
}
                 */
    }

    @When("after successful {string} operation of this customer, an {string} sign in to the system")
    public void after_successful_operation_of_this_customer_an_sign_in_to_the_system(String operationName, String userType) throws Exception {
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon("Sign in");
        loginPage.loginWithValidInfo(userType);
        BrowserUtils.waitForVisibility(new BasePage().myOperationsNavItem, 5);
    }

    @When("employee creates two different accounts for this customer with a description of its username")
    public void employee_creates_two_different_accounts_for_this_customer_with_a_description_of_its_username() {

    }

    @Then("verify the newcreated account information directly from database")
    public void verify_the_newcreated_account_information_directly_from_database() {

    }

    @Then("verify the newcreated account information with the help of API")
    public void verify_the_newcreated_account_information_with_the_help_of_API() {

    }

    @When("the employee approves the customer application by filling out additional information and binding created accounts")
    public void the_employee_approves_the_customer_application_by_filling_out_additional_information_and_binding_created_accounts() {

    }

    @Then("verify the approved customer information directly from database")
    public void verify_the_approved_customer_information_directly_from_database() {

    }

    @Then("verify the approved customer information with the help of API")
    public void verify_the_approved_customer_information_with_the_help_of_API() {

    }

    @When("the admin activate the customer as a customer in the system")
    public void the_admin_activate_the_customer_as_a_customer_in_the_system() {

    }

    @Then("verify that customer can sign in to the system with its valid credentials")
    public void verify_that_customer_can_sign_in_to_the_system_with_its_valid_credentials() {

    }

    @Then("verify that the customer can see its own accounts")
    public void verify_that_the_customer_can_see_its_own_accounts() {

    }

    @Then("verify the activated customer information directly from database")
    public void verify_the_activated_customer_information_directly_from_database() {

    }

    @Then("verify the activated customer information with the help of API")
    public void verify_the_activated_customer_information_with_the_help_of_API() {

    }

    @Then("the all customers' information is printed out")
    public void the_all_customers_information_is_printed_out() {

    }
}
