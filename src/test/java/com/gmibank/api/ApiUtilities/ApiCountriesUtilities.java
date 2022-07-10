package com.gmibank.Api.ApiUtilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gmibank.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiCountriesUtilities {

    public static String token;
    static{
        token = AccessToken.getAccessTokenWithBearer("admin");
    }

    public static Response getAllCountries(){

        Response response = given().headers(
                                        "Authorization",
                                        token,
                                        "Content-Type",
                                         ContentType.JSON,
                                            "Accept",
                                            ContentType.JSON).
                when().
                get(ConfigurationReader.getProperty("countries_Api_endpoint")).
                then().
                contentType(ContentType.JSON).
                extract().
                response();

        return response;
    }

    //Sadece Country ismi girin. Json formatini icerde kendisi olusturacak
    public static Response postOneCountry(String countryName) throws JsonProcessingException {
        String jsonBody =  "{\n" +
                "    \"name\": \""+ countryName +"\"\n" +
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
                            .post(ConfigurationReader.getProperty("countries_Api_endpoint"))
                .then().log().all()
                            .contentType(ContentType.JSON)
                            .extract()
                            .response();

        return response;
    }

    //Update edilecek country'nin id'si girilecek ve yeni isim verilecek
    public static Response updateCountry(int idNumber, String newName){
        String jsonBody = "{\n" +
                "    \"id\": \""+ idNumber +"\"\n," +
                "    \"name\": \""+ newName +"\"\n" +"}";

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
                .put(ConfigurationReader.getProperty("countries_Api_endpoint"))
                .then().log().all()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        return response;
    }

    //id'si verilen country'nin bilgilerini return eder, verify etmek icin kullanilabilir
    public static Response getSpecifiedCountryInfo(int id){
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
                .get(ConfigurationReader.getProperty("countries_Api_endpoint") + "/{id}")
                .then().log().all()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        return response;
    }



}
