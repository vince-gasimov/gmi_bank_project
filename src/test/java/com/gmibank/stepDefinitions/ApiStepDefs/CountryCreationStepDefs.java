package com.gmibank.stepDefinitions.ApiStepDefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gmibank.Api.ApiUtilities.ApiCountriesUtilities;
import com.gmibank.utilities.DummyDataGenerator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

public class CountryCreationStepDefs {


    int id;
    String name;
    @Given("send a new country with generated name")
    public void send_a_new_country_with_generated_name() throws JsonProcessingException {
        //butun name'leri cek, farkli name uret.
        name = DummyDataGenerator.faker.country().name();
        Response creationResponse = ApiCountriesUtilities.postOneCountry(name);
        creationResponse.prettyPrint();
        Assert.assertEquals(creationResponse.statusCode(),201);
        JsonPath jsonPath = creationResponse.jsonPath();
        id = jsonPath.getInt("id");
    }

    @Then("verify that new country is created")
    public void verify_that_new_country_is_created() {
        Response createdCountryResponse = ApiCountriesUtilities.getSpecifiedCountryInfo(id);
        JsonPath jsonPath = createdCountryResponse.jsonPath();
        int actualId = jsonPath.getInt("id");
        String actualName = jsonPath.getString("name");
        Assert.assertEquals(id, actualId);
        Assert.assertEquals(name,actualName);
    }


}
