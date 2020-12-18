package com.gmibank.stepDefinitions;

import com.gmibank.pages.CustomersPageWithTable;
import com.gmibank.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class ManagementOfCustomerByEmployeeStepDefs {
    @Then("verify that a table is displayed in the Customers_page with following columns")
    public void verify_that_a_table_is_displayed_in_the_Customers_page_with_following_columns(List<String> columnNameList) {
        CustomersPageWithTable customersPageWithTable = new CustomersPageWithTable();
        BrowserUtils.waitForVisibility(customersPageWithTable.pageHeader, 10);
        System.out.println("customer.getColumnNameList() = " + customersPageWithTable.getColumnNameList());
        Assert.assertTrue(customersPageWithTable.doesGivenColumnListExist(columnNameList));
    }

}
