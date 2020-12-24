package com.gmibank.stepDefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmibank.pojos.States1;
import com.gmibank.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class CreationStateStepDefs {
    Response response;
    @Given("user get data with valid token and {string}")
    public void user_get_data_with_valid_token_and(String string) {
        response = given().
                auth().
                oauth2(ConfigurationReader.getProperty("token")).
                when().get(ConfigurationReader.getProperty("state_api_endpoint"));
         response.prettyPrint();
    }
    @Then("user verify status code {int} and content type JSon")
    public void user_verify_status_code_and_content_type_JSon(Integer int1) {
        response.
                then().
                assertThat().
                statusCode(int1).
                contentType(ContentType.JSON);

    }
    @Then("user create a new {string} one by one if it is not created already")
    public void user_create_a_new_one_by_one_if_it_is_not_created_already(String state) {
        States1 states = new States1(state,null);
        response = given().
                contentType(ContentType.JSON).
                auth().
                oauth2(ConfigurationReader.getProperty("token")).
                body(states).
                when().
                post(ConfigurationReader.getProperty("state_api_endpoint"));
           response.prettyPrint();

    }
    @When("validate {string} is created")
    public void validate_is_created(String state) throws IOException {
            ObjectMapper objectMapper = new ObjectMapper();
            States1 states = objectMapper.readValue(response.asString(), States1.class);
            String actual = states.getName();
            Assert.assertEquals(actual,state);

    }
}
