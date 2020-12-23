package com.gmibank.stepDefinitions.ApiStepDefs;

import com.gmibank.Api.ApiUtilities.ApiStatesUtilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class ApiStatesDeleteStepDefs {


    @Given("user deletes the state of a given id")
    public void user_deletes_the_state_of_a_given_id() {

        ApiStatesUtilities.deleteOneState("/31099");
    }

    @Then("verifies the state he deleted")
    public void verifies_the_state_he_deleted() {
        Assert.assertEquals(ApiStatesUtilities.getSpecifiedStateInfo(31099).getStatusCode(), 404 + "not verify");
    }

}
