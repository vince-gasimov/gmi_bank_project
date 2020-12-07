package com.gmibank.stepDefinitions;

import com.gmibank.pages.BasePage;
import com.gmibank.pages.RegistrationPage;
import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.ConfigurationReader;
import com.gmibank.utilities.Driver;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestClass {

    @Test
    public void test() throws Exception {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        Driver.getDriver().manage().window().maximize();
        BasePage basePage = new BasePage();
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon("Register");
        BrowserUtils.waitFor(2);
        //System.out.println("basePage.getPageHeader() = " + basePage.getPageHeader());
        RegistrationPage registrationPage = new RegistrationPage();
        BrowserUtils.waitFor(1);

        Map<String, String> values = new HashMap<>();
        values.put("ssnNumber", "000-10-1111");
        values.put("firstName", "emre");
        values.put("lastName", "cihan");
        values.put("address", "svdfd");
        values.put("mobilePhoneNumber", "111-111-1111");
        values.put("userName", "11111111");
        values.put("email", "mr@gmail.com");
        values.put("newPassword", "11111111");
        values.put("passwordConfirmation", "11111111");


        registrationPage.registerNewUser(values);

        System.out.println("registrationPage.getRgbAttributesOfLeds() = " + registrationPage.getRgbAttributesOfLeds());
        System.out.println("registrationPage.checkConsistency() = " + registrationPage.checkConsistency());
/*

        typeSsnNumber(fieldsValuesMap.get("ssnNumber"));
        typeFirstName(fieldsValuesMap.get("firstName"));
        typeLastName(fieldsValuesMap.get("lastName"));
        typeAddress(fieldsValuesMap.get("address"));
        typeMobilePhoneNumber(fieldsValuesMap.get("mobilePhoneNumber"));
        typeUserName(fieldsValuesMap.get("userName"));
        typeEmail(fieldsValuesMap.get("email"));
        typeNewPassword(fieldsValuesMap.get("newPassword"));
        typeNewPasswordConfirmation(fieldsValuesMap.get("passwordConfirmation"));
*/

        Driver.closeDriver();


    }

}
