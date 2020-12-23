package com.gmibank.stepDefinitions;

import com.gmibank.pages.CreateOrEditCustomerPageByClickEdit;
import com.gmibank.pages.CustomersPageWithTable;
import com.gmibank.utilities.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class DatabaseStepDefs {
    ExcelUtilities excel;
    String excelPath = ConfigurationReader.getProperty("registration_excel_path");
    Map<String, Object> mapFromDb;
    Map<String, String> mapFromExcel;
    List<String> countryListFromUI;
    List<String> countryNamesFromDb;

    @Given("take randomly a user with role {string} from excel sheet {string}")
    public void take_randomly_a_user_with_role_from_excel_sheet(String userType, String sheetName) {
        excel = new ExcelUtilities(excelPath, sheetName);
        String profile = StringUtilities.userTypeRoleConversion(userType);
        int rowIndex = excel.getRandomRowNumWithinSameProfile(profile);
        mapFromExcel = excel.getSpecifiedRow(rowIndex);
        System.out.println(mapFromExcel);
    }

    @When("retrieve same user from database")
    public void retrieve_same_user_from_database() {
        mapFromDb = DatabaseUtility.getUserInformationIncludingAuthorityAndActivation(mapFromExcel.get("email"));
    }

    @Then("verify that information retrieved from database and excel match")
    public void verify_that_information_retrieved_from_database_and_excel_match() {
        Assert.assertTrue(DatabaseUtility.compareMapFromDbAndMapFromExcel(mapFromDb, mapFromExcel));
        mapFromExcel = null;
        mapFromDb = null;
        excel = null;
    }


    @Given("user navigates to new customer creation page and gets all countries")
    public void user_navigates_to_new_customer_creation_page_and_gets_all_countries() {
        CustomersPageWithTable customersPageWithTable = new CustomersPageWithTable();
        BrowserUtils.waitForClickablility(customersPageWithTable.createButton,5);
        customersPageWithTable.clickCreateButton();
        CreateOrEditCustomerPageByClickEdit createOrEditCustomerPageByClickEdit = new CreateOrEditCustomerPageByClickEdit();
        countryListFromUI = createOrEditCustomerPageByClickEdit.getCountryList();
    }

    @When("retrieve all countries from database")
    public void retrieve_all_countries_from_database() {
        countryNamesFromDb = DatabaseUtility.getCountryNames();
    }

    @Then("verify that UI countries match database countries")
    public void verify_that_UI_countries_match_database_countries() {
        Assert.assertTrue(countryNamesFromDb.containsAll(countryListFromUI));
        System.out.println("countryListFromUI = " + countryListFromUI);
        System.out.println("countryNamesFromDb = " + countryNamesFromDb);
        System.out.println("countryListFromUI.size() = " + countryListFromUI.size());
        System.out.println("countryNamesFromDb.size() = " + countryNamesFromDb.size());
        List<String> countryListFromUI = null;
        List<String> countryNamesFromDb = null;
    }

}
