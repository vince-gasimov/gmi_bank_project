package com.gmibank.stepDefinitions;

import com.gmibank.pages.BasePage;
import io.cucumber.java.en.When;

public class NavigateStepDefs {

    @When("user navigates to {string} page under account menu icon")
    public void user_navigates_to_page_under_account_menu_icon(String pageName) throws Exception {
        BasePage basePage = new BasePage();
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon(pageName);
        //BrowserUtils.waitFor(1);
    }


    @When("user navigates to {string} page under My Operations menu")
    public void user_navigates_to_page_under_My_Operations_menu(String pageName) throws Exception {
        new BasePage().clickAndSelectDropDownItemUnderMyOperationsNavItem(pageName);
    }

}
