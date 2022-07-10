package com.gmibank.stepDefinitions.uiStepDefs;

import com.gmibank.pages.TestInfo;
import io.cucumber.java.en.When;

public class TestInfo2 {

    @When("user types a email without {string}")
    public void user_types_a_email_without(String emailwithoutat) {

        TestInfo testInfo=new TestInfo();
        testInfo.emailKutusu.sendKeys(emailwithoutat);

    }
















}
