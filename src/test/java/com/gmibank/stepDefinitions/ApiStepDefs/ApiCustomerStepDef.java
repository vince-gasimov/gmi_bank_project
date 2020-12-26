package com.gmibank.stepDefinitions.ApiStepDefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmibank.Api.ApiUtilities.ApiCustomerUtilities;
import com.gmibank.Api.pojos.Customer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.IOException;

public class ApiCustomerStepDef {

    Response response;
    Customer[] customers;

    @Given("get all customers")
    public void get_all_customers()  {
        response = ApiCustomerUtilities.getAllCustomers();
        //response.prettyPrint();
    }

    @Given("verify that all customers data")
    public void verify_that_all_customers_data() throws IOException {
        Response allCustomersResponse = ApiCustomerUtilities.getAllCustomers();

        ObjectMapper objectMapper = new ObjectMapper();

        customers = objectMapper.readValue(allCustomersResponse.asString(), Customer[].class);

        for (int i =0; i<customers.length; i++){
            System.out.println(customers[i].getFirstName());
        }
    }

    @Then("verify that data of customer id")
    public void verify_that_data_of_customer_id() {
        Response aCustomerResponse = ApiCustomerUtilities.getAllCustomers();
        JsonPath jsonPath = aCustomerResponse.jsonPath();

        String actualId = jsonPath.getString("id");
        String actualName = jsonPath.getString("firstName");

        Assert.assertTrue("not verify", actualId.contains("42687"));
        Assert.assertTrue("not verify", actualName.contains("Frederick"));

    }

}
