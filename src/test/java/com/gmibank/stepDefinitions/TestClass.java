package com.gmibank.stepDefinitions;

import com.gmibank.utilities.*;

public class TestClass {
    public static void main(String[] args) {
/*        String generatedString;
        for (int i = 0; i < 10; i++) {
            generatedString = RandomStringGenerator.generateStrongPassword(7, 2, 1, 1, 1);
            System.out.println(generatedString);
        }*/

        ApiUtils.getAndSetAccessToken("admin");
        String accessToken = ConfigurationReader.getProperty("admin_access_token");
        System.out.println("accessToken = " + accessToken);
        ApiUtils.getAndSetAccessToken("employee");
        ApiUtils.getAndSetAccessToken("customer");
        ApiUtils.getAndSetAccessToken("dynamic_customer");
    }
}
