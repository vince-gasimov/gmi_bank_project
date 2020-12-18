package com.gmibank.stepDefinitions;

import com.gmibank.pages.DateAndAccountMngPage;
import com.gmibank.utilities.DateUtil;
import com.gmibank.utilities.Driver;
import io.cucumber.java.en.Then;
import org.apache.poi.ss.formula.functions.T;
import org.jsoup.helper.DataUtil;
import org.testng.Assert;
import sun.security.krb5.internal.PAData;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;

public class DateAndAccountMngStepDesf {
    DateAndAccountMngPage page = new DateAndAccountMngPage();

    @Then("user must be alerted if a date {string} in the past is entered")
    public void user_must_be_alerted_if_a_date_in_the_past_is_entered(String string) throws InterruptedException {

        page.createDateItem.sendKeys(DateUtil.todaysDate2());


        page.employeeDropDownitem.click();
        Assert.assertTrue(page.invalidDataToAlert.size() >= 1);


    }


    //invalid Date type
    @Then("if user enters invalid datetype {string}  then get alert")
    public void if_user_enters_invalid_datetype_then_get_alert(String string) {

        page.createDateItem.sendKeys(string);
        page.employeeDropDownitem.click();

        Assert.assertTrue(page.invalidDataToAlert.size() >= 1);
    }


    //TC_03
    @Then("user can chooses a user from the registirition and can not be blank")
    public void user_can_chooses_a_user_from_the_registirition_and_can_not_be_blank() throws InterruptedException {
        page.saveButton.click();
        Thread.sleep(5000);

        page.accountsId.get(3).click();
        Thread.sleep(5000);
        Assert.assertTrue(page.editButton.isDisplayed());


    }

    //TC_04
    @Then("user can chooses one of the Accounts")
    public void user_can_chooses_one_of_the_Accounts() throws InterruptedException {

        page.accountsId.get(5).click();
        Thread.sleep(5000);

        Assert.assertTrue(page.editButton.isDisplayed());


    }

    @Then("User can select Zelle Enrolled Optionally")
    public void user_can_select_Zelle_Enrolled_Optionally() throws InterruptedException {

        page.accountsId.get(5).click();
        Thread.sleep(5000);
        page.editButton.click();
        page.zelleEnrolledRadioButton.click();

        page.saveButton.click();


        Assert.assertEquals(Driver.getDriver().getCurrentUrl(),"https://gmibank.com/tp-customer?page=1&sort=id,asc");
    }


}
