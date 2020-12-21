package com.gmibank.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiUtilities {

        public static String getCustomerApi(String customer){
            Response response=given().headers(
                    "Authorization",
                    "Bearer " + ConfigurationReader.getProperty("token"),
                    "Content-Type",
                    ContentType.JSON,
                    "Accept",
                    ContentType.JSON).
                    and().
                    queryParam("size", 1500) //Customer'i alirken size'i belirtir
                    .when()
                    .get(ConfigurationReader.getProperty("customer_Api_endpoint")) //countries, states, account-reg,
                    .then()
                    .contentType(ContentType.JSON)
                    .statusCode(200)
                    .extract()
                    .response();
            return customer;
        }

    public static String postCountriesApi(String countries){
        Response response = given().headers(
                "Authorization",
                "Bearer " + ConfigurationReader.getProperty("token"),
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON)
                .when()
                .body("{\"" + type + "\":\"" + country + "\"}")
                .post(ConfigurationReader.getProperty("countries_Api_endpoint"))
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        response.prettyPrint();
        return countries;
    }

    public static String deleteStatesApi(String states){
        Response response = given().headers(
                "Authorization",
                "Bearer " + ConfigurationReader.getProperty("token"),
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON)
                .when().
                        delete(ConfigurationReader.getProperty("states_Api_endpoint")).
                        then().
                        extract().
                        response();
        return states;
    }







}
