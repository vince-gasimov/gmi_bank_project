package com.gmibank.api.ApiUtilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gmibank.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiStatesUtilities {

    public static String token;
    static{
        token = AccessToken.getAccessTokenWithBearer("admin");
    }

    public static Response getAllStates(){

        Response response = given().headers(
                "Authorization",
                token,
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON).
                when().
                get(ConfigurationReader.getProperty("states_Api_endpoint")).
                then().
                contentType(ContentType.JSON).
                extract().
                response();

        return response;
    }

    public static Response postOneState(String stateName) throws JsonProcessingException {
        String jsonBody =  "{\n" +
                "    \"name\": \""+ stateName +"\"\n" +
                "}";
        Response response = given().log().all()
                .headers(
                        "Authorization",
                        token,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when()
                .body(jsonBody)
                .post(ConfigurationReader.getProperty("states_Api_endpoint"))
                .then().log().all()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        return response;
    }

    public static String deleteOneState(String id){
        Response response = given().headers(
                "Authorization",
                token,
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON)
                .when().
                        delete(ConfigurationReader.getProperty("states_Api_endpoint") +id).
                        then().
                        extract().
                        response();
        return id;
    }

    //id'si verilen state'in bilgilerini return eder, verify etmek icin kullanilabilir
    public static Response getSpecifiedStateInfo(int id){
        Response response = given().log().all()
                .headers(
                        "Authorization",
                        token,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .and()
                .pathParam("id", id)
                .when()
                .get(ConfigurationReader.getProperty("states_Api_endpoint") + "/{id}")
                .then().log().all()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        return response;
    }

}
