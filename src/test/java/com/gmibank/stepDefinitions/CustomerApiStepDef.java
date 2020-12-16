package com.gmibank.stepDefinitions;

import com.gmibank.utilities.ConfigurationReader;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import org.junit.Assert;
import java.util.List;

public class CustomerApiStepDef {

    Response response;

    @Given("user read all customer and sets response using to api end point {string}")
    public void user_read_all_customer_and_sets_response_using_to_api_end_point(String api_endpoint) {
        Response response=given().
                                auth().
                                oauth2(ConfigurationReader.getProperty("token")).
                                contentType(ContentType.JSON)
                .when()
                                    .get(api_endpoint)
                .then()
                                    .contentType(ContentType.JSON)
                                    .statusCode(200)
                                    .extract()
                                    .response();
        response.prettyPrint();
        /*
        .headers(
                 "Authorization",
                 "Bearer " + ConfigurationReader.getProperty("token"),
                 "Content-Type",
                  ContentType.JSON,
                  "Accept",
                  ContentType.JSON)
         */
    }

    @Given("user deserialization customer data json to java pojo")
    public void user_deserialization_customer_data_json_to_java_pojo() {

    }

    @Then("user validates all data")
    public void user_validates_all_data() {

    }

}
