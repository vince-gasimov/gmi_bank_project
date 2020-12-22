package com.gmibank.stepDefinitions.ApiStepDefs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmibank.Api.ApiUtilities.ApiCountriesUtilities;
import com.gmibank.Api.pojos.Country;
import com.gmibank.Api.pojos.Customer;
import com.gmibank.utilities.DummyDataGenerator;
import com.gmibank.utilities.ReadTxt;
import com.gmibank.utilities.WriteToTxt;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CountryStepDefs {

    //Response response;
    //Country[] countries;

    @Given("get all countries")
    public void get_all_countries() {
        Response getAllCountries = ApiCountriesUtilities.getAllCountries();
        getAllCountries.prettyPrint();
    }

    //List<String> allCountries = new ArrayList<>();
    @Given("verify that all countries data")
    public void verify_that_all_countries_data() throws IOException {
    /*    //Response allCountriesResponse = ApiCountriesUtilities.getAllCountries();
        ObjectMapper objectMapper = new ObjectMapper();
        countries = objectMapper.readValue(response.asString(), Country[].class);

        for (int i =0; i<countries.length; i++){
            allCountries.add(countries[i].getName());
        }
        System.out.println(allCountries);

        WriteToTxt.saveAllCountries("allCountriesName.txt", countries);
        List<String > allCountriesNameList = ReadTxt.returnCustomerSNNList("allCountriesName.txt");
        Assert.assertEquals("not verify", allCountries, allCountriesNameList);
        */
    }

    @Then("verify that data of country id {int}")
    public void verify_that_data_of_country_id(Integer int1) {
        Response aCountryResponse = ApiCountriesUtilities.getSpecifiedCountryInfo(id);
        JsonPath jsonPath = aCountryResponse.jsonPath();
        int actualId = jsonPath.getInt("id");
        String actualName = jsonPath.getString("name");
        Assert.assertEquals(id, actualId);
        Assert.assertEquals(name,actualName);
    }


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

    @Given("update a random selected country with a new generated name")
    public void update_a_random_selected_country_with_a_new_generated_name() {
        Response updateCountryResponse = ApiCountriesUtilities.updateCountry(60779, "Belize_island");
        JsonPath jsonPath = updateCountryResponse.jsonPath();
        updateCountryResponse.prettyPrint();
        Assert.assertEquals(updateCountryResponse.statusCode(),200);
    }

    @Then("verify that country information is update")
    public void verify_that_country_information_is_update() {
        Response updatedCountryResponse = ApiCountriesUtilities.getSpecifiedCountryInfo(60779);
        JsonPath jsonPath = updatedCountryResponse.jsonPath();
        int actualId = jsonPath.getInt("id");
        String actualName = jsonPath.getString("name");
        Assert.assertEquals(60779, actualId);
        Assert.assertEquals("Belize_island",actualName);
    }


}
