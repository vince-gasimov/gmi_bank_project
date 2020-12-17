package com.gmibank.stepDefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmibank.pojos.Customer;
import com.gmibank.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.Response.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class CustomerApiStepDef {

    Response response;
    Customer[] customers;

    @Given("user read all customer and sets response using to api end point {string}")
    public void user_read_all_customer_and_sets_response_using_to_api_end_point(String api_endpoint) {
         response=given().headers(
                "Authorization",
                "Bearer " + ConfigurationReader.getProperty("token"),
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON)
                .when()
                                    .get(api_endpoint)
                .then()
                                    .contentType(ContentType.JSON)
                                    .statusCode(200)
                                    .extract()
                                    .response();
        //response.prettyPrint();

        /*
                                        auth().
                                oauth2(ConfigurationReader.getProperty("token")).
                                contentType(ContentType.JSON)
         */
    }

    @Given("user deserialization customer data json to java pojo")
    public void user_deserialization_customer_data_json_to_java_pojo() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        customers=objectMapper.readValue(response.asString(),Customer[].class);

        // System.out.println(customers[0].getFirstName());

        for (int i=0; i< customers.length;i++) {
            System.out.println("FirstName: " + customers[i].getFirstName());
        }

        System.out.println("************************");

        for (int i=0;i<customers.length;i++) {
            if (customers[i].getUser()!=null) {
                System.out.println("LastName: " + customers[i].getUser().getLastName());
            }
        }

        System.out.println("************************");

        for(int i=0; i< customers.length; i++){
            System.out.println("Customer SSN: " + customers[i].getSsn());
            System.out.println("Customer mobilePhoneNumber: " + customers[i].getMobilePhoneNumber());
            if (customers[i].getCountry()!=null)
            System.out.println("Country Name: " + customers[i].getCountry().getName());
        }

    }

    @Then("user validates all data")
    public void user_validates_all_data() {

    }

}
