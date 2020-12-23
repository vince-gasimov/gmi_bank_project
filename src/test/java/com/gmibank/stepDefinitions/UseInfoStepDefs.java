package com.gmibank.stepDefinitions;

import com.gmibank.pages.UserInfoPage;
import com.gmibank.utilities.ConfigurationReader;
import com.gmibank.utilities.Driver;
import com.gmibank.utilities.DummyDataGenerator;
import groovyjarjarantlr4.v4.analysis.LeftRecursiveRuleAnalyzer;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.hu.Ha;
import org.apache.poi.ss.formula.functions.T;
import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.openqa.selenium.Alert;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.HashMap;
import java.util.List;

public class UseInfoStepDefs {
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
        Select select = new Select(page.languageDropDown);
        select.selectByIndex(0);
        String s = page.languageDropDown.getText();

        Assert.assertTrue(s.contains("Türkçe"));
        Assert.assertTrue(s.contains("English"));

        page.saveButtonUserinfo.click();
    }

    //US_06TC_02
    @When("user should click on firstname button and can update")
    public void user_should_click_on_firstname_button_and_can_update() {

        String s = DummyDataGenerator.generateFirstName();
        System.out.println(s);
        page.firstNameBox.clear();
        if (s.equals(page.firstNameBox.getAttribute("Value"))) {
            page.firstNameBox.sendKeys(s + "a");
        } else {
            page.firstNameBox.sendKeys(s);
        }

    }

    @When("user should click on lastname button and can update")
    public void user_should_click_on_lastname_button_and_can_update() {

        String s = DummyDataGenerator.generateLastname();
        System.out.println(s);
        page.lastNameBox.clear();
        if (s.equals(page.lastNameBox.getAttribute("value"))) {
            page.lastNameBox.sendKeys(s + "b");
        } else {
            page.lastNameBox.sendKeys(s);
        }


    }

    @Then("user should updates  e-mail  and checks to update all user information")
    public void user_should_updates_e_mail_and_checks_to_update_all_user_information() throws InterruptedException {


        String s = DummyDataGenerator.generateEmail();
        page.emailBox.clear();
        if (s.equals(page.emailBox.getAttribute("value"))) {
            page.emailBox.sendKeys(1 + s);
        } else {
            page.emailBox.sendKeys(s);
        }

        page.saveButtonUserinfo.click();
        Thread.sleep(5000);
        Assert.assertTrue(page.invalidDataToAlert.size() == 0);


    }


    //US_06_TC_02nEGATIVE
    @Then("if user should to be update  {string}  then get alert")
    public void if_user_should_to_be_update_then_get_alert(String string) {
        page.emailBox.clear();
        page.emailBox.sendKeys(string);
        List<String> list = page.getAlert();

        Assert.assertTrue(list.size() > 0);


    }


}
