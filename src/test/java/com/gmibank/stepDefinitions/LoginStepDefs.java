package com.gmibank.stepDefinitions;

import com.gmibank.pages.BasePage;
import com.gmibank.pages.LoginPage;
import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class LoginStepDefs {

    @When("user types and submit the valid {string} credentials")
    public void user_types_and_submit_the_valid_credentials(String userType) {
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithValidInfo(userType);
        //BrowserUtils.waitFor(2);
    }

    @Then("{string} page should be displayed with user information beside icon")
    public void page_should_be_displayed_with_user_information_beside_icon(String pageName) {
        BasePage basePage = new BasePage();
        BrowserUtils.waitForTextAppearInElement(basePage.accountMenuIcon, "group16", 10);

        String userName = basePage.getUserNameBesideAccountMenuIcon();
        String actualURL = Driver.getDriver().getCurrentUrl();
        String expectedURL = basePage.getPageUrl(pageName);

        Assert.assertEquals(expectedURL, actualURL);

        //eger user name degeri null degilse sisteme giris yapilmis demektir
        Assert.assertNotEquals(null, userName);

        //System.out.println("expectedURL = " + expectedURL);
        //System.out.println("userName = " + userName);


    }

}
