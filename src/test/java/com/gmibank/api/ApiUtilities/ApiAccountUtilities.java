package com.gmibank.api.ApiUtilities;

import com.gmibank.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiAccountUtilities {
    public static String token;
    public static String accountEndPoint = ConfigurationReader.getProperty("account_end_point");

    static{
        token = AccessToken.getAccessTokenWithBearer("admin");
    }

    public static Response getAccountInfoFromApiWithGivenId(int id){
        //  https://gmibank.com/api/tp-accounts

        Response response=given().log().all().headers(
                "Authorization",
                token,
                "Content-Type",
                ContentType.JSON,
                "Accept",
                ContentType.JSON).
                and()
                .pathParam("id", id)
                .when()
                .get(accountEndPoint + "/{id}")
                .then()
                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .extract()
                .response();
        return response;
    }

}
