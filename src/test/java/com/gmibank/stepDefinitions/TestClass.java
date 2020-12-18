package com.gmibank.stepDefinitions;

import com.gmibank.Api.ApiUtilities.AccessToken;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;


public class TestClass {

    @Test
    public void test1(){
        String accessToken = AccessToken.getAccessTokenWithBearer("admin");

        Response response = RestAssured.given().log().all()
                            .accept(ContentType.JSON)
                            .and()
                            .header("Authorization", accessToken)
                            .get("https://gmibank.com/api/account");

        response.prettyPrint();
    }

}
