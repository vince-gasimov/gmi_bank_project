package com.gmibank.stepDefinitions;

import com.gmibank.pages.AdminNewAccountCreatePage;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class AdminNewAccountStepDefs {

    @Then("user take {string} as alert message")
    public void user_take_as_alert_message(String string) {

        AdminNewAccountCreatePage page=new AdminNewAccountCreatePage();
       String alertMessage= page.adminAccountNotCreatedAlert.getText();
        System.out.println(alertMessage);

        Assert.assertFalse(alertMessage.equals(alertMessage));

    }




}
