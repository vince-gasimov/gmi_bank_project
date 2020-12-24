package com.gmibank.stepDefinitions.ApiStepDefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmibank.Api.ApiUtilities.ApiStatesUtilities;
import com.gmibank.Api.pojos.States;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.IOException;

public class ApiStateStepDef {

    Response response;
    States[] states;

    @Given("get all states")
    public void get_all_states() {
        response = ApiStatesUtilities.getAllStates();
        //response.prettyPrint();
    }

    @Given("verify that all states data")
    public void verify_that_all_states_data() throws IOException {
        Response allStatesResponse = ApiStatesUtilities.getAllStates();

        ObjectMapper objectMapper = new ObjectMapper();

        states = objectMapper.readValue(allStatesResponse.asString(), States[].class);

        //System.out.println(states[0].getName());

       for (int i =0; i<states.length; i++){
            if(states[i].getName() !=null);
            System.out.println(states[i].getId());
        }

    }

    @Then("verify that data of state id")
    public void verify_that_data_of_state_id() {
        Response aStateResponse = ApiStatesUtilities.getAllStates();
        JsonPath jsonPath = aStateResponse.jsonPath();

        String actualId = jsonPath.getString("id");
        String actualName = jsonPath.getString("name");

        Assert.assertTrue("not verify", actualId.contains("58975"));
        Assert.assertTrue("not verify", actualName.contains("texas"));
    }



}
