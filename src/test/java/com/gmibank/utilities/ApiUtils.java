package com.gmibank.utilities;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.baseURI;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ApiUtils {


    public static String getTokenFromAPI(String userType){
        Map<String, Object> authenticationMap = createAuthenticationCredentialsMap(userType);
        String accessToken = null;

        Response response = RestAssured
                .given()
                        .contentType(ContentType.JSON)
                .and()
                        .accept(ContentType.JSON)
                .and()
                        .body(authenticationMap).log().all()
                .post("https://gmibank.com/api/authenticate");

        JsonPath jsonPath = response.jsonPath();

        accessToken = jsonPath.getString("id_token");

        return accessToken;

    }


    public static Map<String, Object> createAuthenticationCredentialsMap(String userType){
        Map<String, Object> authenticationMap = new HashMap<>();
        String username = null;
        String password = null;

        switch (userType){
            case "admin":
                username = ConfigurationReader.getProperty("admin_user_name");
                password = ConfigurationReader.getProperty("admin_password");
                break;
            case "employee":
                username = ConfigurationReader.getProperty("employee_user_name");
                password = ConfigurationReader.getProperty("employee_password");
                break;
            case "dynamic_customer":
                username = ConfigurationReader.getProperty("dynamic_customer_user_name");
                password = ConfigurationReader.getProperty("dynamic_customer_password");
                break;
            case "customer":
                username = ConfigurationReader.getProperty("customer_user_name");
                password = ConfigurationReader.getProperty("customer_password");
                break;
            default:
                System.out.println("non-defined user type!");
                break;
        }

        authenticationMap.put("username", username);
        authenticationMap.put("password", password);
        authenticationMap.put("rememberMe",false);

        return authenticationMap;
    }

    public static void setAccessTokenInConfigFile(String userType, String accessToken){
        String userKeyInConfigFile = null;
        switch (userType){
            case "admin":
                userKeyInConfigFile = "admin_access_token";
                break;
            case "employee":
                userKeyInConfigFile = "employee_access_token";
                break;
            case "dynamic_customer":
                userKeyInConfigFile = "dynamic_customer_token";
                 break;
            case "customer":
                userKeyInConfigFile = "customer_access_token";
                break;
            default:
                System.out.println("non-defined user type!");
                break;
        }
        ConfigurationReader.changePropertyValue(userKeyInConfigFile, accessToken);
    }

    public static void getAndSetAccessToken(String userType){
        String accessToken = getTokenFromAPI(userType);
        setAccessTokenInConfigFile(userType,accessToken);
    }

}
