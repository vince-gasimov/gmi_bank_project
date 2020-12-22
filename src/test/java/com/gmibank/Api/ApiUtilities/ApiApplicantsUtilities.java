package com.gmibank.Api.ApiUtilities;

import com.gmibank.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiApplicantsUtilities {

    public static String token;
    static{
        token = AccessToken.getAccessTokenWithBearer("admin");
    }

    public static Response getAllApplicants(){


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
                .get(ConfigurationReader.getProperty("applicants_Api_endpoint"))
                .then()
                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .extract()
                .response();
        return response;
    }

    //id'si verilen applicant'in bilgilerini return eder, verify etmek icin kullanilabilir
    public static Response getSpecifiedApplicantInfo(int id){
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
                .get(ConfigurationReader.getProperty("applicants_Api_endpoint") + "/{id}")
                .then().log().all()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        return response;
    }

}
