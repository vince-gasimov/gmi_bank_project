package com.gmibank.stepDefinitions;

import com.gmibank.pages.EmpNewAccountPage;
import com.sun.deploy.net.proxy.pac.PACFunctions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

public class EmpNewAccountStepDefs {

    EmpNewAccountPage page = new EmpNewAccountPage();

    @When("user clicks on the create a new Account item")
    public void user_clicks_on_the_create_a_new_Account_item() {
        page.createNewAccountItem.click();


    }

    @When("user enters  description {string}")
    public void user_enters_description(String string) {

        page.descriptionBox.click();
        page.descriptionBox.sendKeys(string);


    }

    @Then("If user leaves the description blank, he should receive an alert")
    public void if_user_leaves_the_description_blank_he_should_receive_an_alert() {

        page.descriptionBox.sendKeys(Keys.ENTER);


    }


//US_13_TC02

    @When("user enters {string} on balanceBox")
    public void user_enters_on_balanceBox(String string) {
        page.balanceBox.click();
        page.balanceBox.sendKeys(string);

    }

    @When("If the user just clicks on the balance box and no value is entered, the alert will be")
    public void if_the_user_just_clicks_on_the_balance_box_and_no_value_is_entered_the_alert_will_be() {
page.balanceBox.sendKeys(Keys.ENTER);


    }

    @Then("the user receives the alert if they click the save button without clicking the balance box")
    public void the_user_receives_the_alert_if_they_click_the_save_button_without_clicking_the_balance_box() {
page.saveButton.click();


    }

//US_13_TC02NEGATIVE
    @Then("if user enters {string} ,the alert will be")
    public void if_user_enters_the_alert_will_be(String string) {

        page.balanceBox.sendKeys(string);


    }


//US_13_TC03
    @When("User chooses Account Type {string} dropDown")
    public void user_chooses_Account_Type_dropDown(String string) {

        Select select=new Select(page.accountTypeDropDownItem);
        select.selectByVisibleText(string);



    }





}
