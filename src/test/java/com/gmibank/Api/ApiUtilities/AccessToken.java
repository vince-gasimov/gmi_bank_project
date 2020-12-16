package com.gmibank.Api.ApiUtilities;

import com.gmibank.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.baseURI;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class AccessToken {

    /*
sadece bu methodu kullanmak yeterli olacak access token almak icin
Diger methodlar buna yardimci olmak icin kullanildi.
 */
    public static String getAccessTokenWithBearer(String userType){
        String userKeyInConfigFile = getKeyFromConfigFileBasedOnUserType(userType);
        String accessTokenWithBearer = "Bearer ";
        if(!isApiValid(userType)){
            String newToken = getTokenFromAPI(userType);
            if(!getAccessTokenFromConfigFile(userType).equals(newToken)){
                System.out.println("accessToken is updated!");
            }else{
                System.out.println("access token is the same, there can be a problem");
            }
            updateTakenAccessTokenInConfigFile(userType, newToken);
        }

        accessTokenWithBearer += getAccessTokenFromConfigFile(userType);
        return accessTokenWithBearer;
    }


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
        String userKeyInConfigFile = getKeyFromConfigFileBasedOnUserType(userType);
        ConfigurationReader.changePropertyValue(userKeyInConfigFile, accessToken);
    }

    public static void updateTakenAccessTokenInConfigFile(String userType, String accessToken){
        setAccessTokenInConfigFile(userType,accessToken);
    }

    public static String getAccessTokenFromConfigFile(String userType){
        String userKeyInConfigFile = getKeyFromConfigFileBasedOnUserType(userType);
        return ConfigurationReader.getProperty(userKeyInConfigFile);
    }

    public static String getKeyFromConfigFileBasedOnUserType(String userType){
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
        return userKeyInConfigFile;
    }

    public static boolean isApiValid(String userType){
        String userKeyInConfigFile = getKeyFromConfigFileBasedOnUserType(userType);
        if(testAccessTokenWith(userType)){
            return true;
        }
         return false;
    }

    public static boolean testAccessTokenWith(String userType){
        String userKeyInConfigFile = getKeyFromConfigFileBasedOnUserType(userType);
        String currentAccessToken = getAccessTokenFromConfigFile(userType);
        return getRequestAccountInfoBasedOnGivenAccessToken(userType, currentAccessToken);
    }

    public static boolean getRequestAccountInfoBasedOnGivenAccessToken(String userType, String accessToken){
        String accessTokenWithHeader = "Bearer" + " " + accessToken;

        Response response = RestAssured.given()
                                .accept(ContentType.JSON)
                            .and()
                                .header("Authorization", accessTokenWithHeader)
                            .get("https://gmibank.com/api/account");

        if (response.getStatusCode()==200){
            return true;
        }
        return false;
    }
}
