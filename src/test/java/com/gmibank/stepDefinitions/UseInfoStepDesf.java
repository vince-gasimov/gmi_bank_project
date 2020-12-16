package com.gmibank.stepDefinitions;

import com.gmibank.pages.UserInfoPage;
import com.gmibank.utilities.ConfigurationReader;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.HashMap;

public class UseInfoStepDesf {
    UserInfoPage page = new UserInfoPage();

    @When("user navigates user  account dropdown menu icon")
    public void user_navigates_user_account_dropdown_menu_icon() throws Exception {
        page.clickAndSelectDropDownItemUnderAccountMenuIcon("User Info");


    }

    @Then("user checks to information")
    public void user_checks_to_information() {

        HashMap<String, String> userInfoMap = new HashMap<String, String>();
        String s1 = page.forFirstname.getText();
        String s2 = page.forEmail.getText();
        String s3 = page.forLastname.getText();

        userInfoMap.put(s1, page.firstNameBox.getAttribute("value"));
        userInfoMap.put(s3, page.lastNameBox.getAttribute("value"));
        userInfoMap.put(s2, page.emailBox.getAttribute("value"));
        System.out.println(userInfoMap);

        HashMap<String, String> userEnterInfoMap = new HashMap<>();
        userEnterInfoMap.put(s1, ConfigurationReader.getProperty("employee_firstname"));
        userEnterInfoMap.put(s3, ConfigurationReader.getProperty("employee_lastname"));
        userEnterInfoMap.put(s2, ConfigurationReader.getProperty("employee_email"));
        System.out.println(userEnterInfoMap);

        Assert.assertEquals(userInfoMap, userEnterInfoMap);


    }

    @Then("user  clicks on language  drop-down and chooses one of the English and Turkish language")
    public void user_clicks_on_language_drop_down_and_chooses_one_of_the_English_and_Turkish_language() throws InterruptedException {
        Select select=new Select(page.languageDropDown);
        select.deselectByIndex(1);



    }


}
