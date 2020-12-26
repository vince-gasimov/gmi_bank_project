package com.gmibank.stepDefinitions.ApiStepDefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmibank.Api.ApiUtilities.ApiApplicantsUtilities;
import com.gmibank.Api.pojos.Applicant;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.IOException;

public class ApiApplicantStepDef {

    Response response;
    Applicant[] applicants;

    @Given("get all applicants")
    public void get_all_applicants() {
        response = ApiApplicantsUtilities.getAllApplicants();
        response.prettyPrint();
    }

    @Given("verify that all applicants data")
    public void verify_that_all_applicants_data() throws IOException {
        Response allApplicantsResponse = ApiApplicantsUtilities.getAllApplicants();

        ObjectMapper objectMapper = new ObjectMapper();

        applicants = objectMapper.readValue(allApplicantsResponse.asString(), Applicant[].class);

        //System.out.println(states[0].getName());

        for (int i =0; i<applicants.length; i++){
            //if(applicants[i].getFirstName() !=null);
            System.out.println(applicants[i].getSsn());
        }
    }

    @Then("verify that data of applicant")
    public void verify_that_data_of_applicant() {
        Response aApplicantResponse = ApiApplicantsUtilities.getAllApplicants();
        JsonPath jsonPath = aApplicantResponse.jsonPath();

        String actualId = jsonPath.getString("id");
        String actualName = jsonPath.getString("firstName");

        Assert.assertTrue("not verify", actualId.contains("10914"));
        Assert.assertTrue("not verify", actualName.contains("John"));
    }

}
