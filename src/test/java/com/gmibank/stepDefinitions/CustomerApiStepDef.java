package com.gmibank.stepDefinitions;

import com.gmibank.utilities.ConfigurationReader;
import io.cucumber.java.en.*;
//import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CustomerApiStepDef {

    @Given("user read all customer and sets response using to api end point {string}")
    public void user_read_all_customer_and_sets_response_using_to_api_end_point(String api_endponit) {
        Response response=given().headers(
                                        "Authorization",
                                        "Bearer " + ConfigurationReader.getProperty("token"),
                                        "Content-Type",
                                        ContentType.JSON,
                                        "Accept",
                                        ContentType.JSON)
                .when()
                        .get(api_endponit)
                        .then()
                        .contentType(ContentType.JSON)
                        .statusCode(200)
                        .extract()
                        .response();
        response.prettyPrint();


    }

    @Given("user deserialization customer data json to java pojo")
    public void user_deserialization_customer_data_json_to_java_pojo() {

    }

    @Then("user validates all data")
    public void user_validates_all_data() {

    }

}
