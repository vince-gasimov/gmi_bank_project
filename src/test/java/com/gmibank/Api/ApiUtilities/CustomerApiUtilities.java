package com.gmibank.Api.ApiUtilities;

import com.gmibank.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CustomerApiUtilities {


    public static Response getAllCustomers(){
        String token = AccessToken.getAccessTokenWithBearer("admin");
        Response response=given().log().all().headers(
                    "Authorization",
                    token,
                    "Content-Type",
                    ContentType.JSON,
                    "Accept",
                    ContentType.JSON).
                and().
                     queryParam("size", 1500) //Customer'i alirken size'i belirtir
                .when()
                    .get(ConfigurationReader.getProperty("customer_Api_endpoint")) //countries, states, account-reg,
                .then()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .statusCode(200)
                    .extract()
                    .response();
        return response;
    }

}
