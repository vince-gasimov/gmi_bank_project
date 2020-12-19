package com.gmibank.stepDefinitions;

import com.gmibank.pages.CustomerInfoPageForOneUser;
import com.gmibank.pages.CustomersPageWithTable;
import com.gmibank.utilities.BrowserUtils;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ManagementOfCustomerByEmployeeStepDefs {
    @Then("verify that a table is displayed in the Customers_page with following columns")
    public void verify_that_a_table_is_displayed_in_the_Customers_page_with_following_columns(List<String> columnNameList) {
        CustomersPageWithTable customersPageWithTable = new CustomersPageWithTable();
        BrowserUtils.waitForVisibility(customersPageWithTable.pageHeader, 10);
        System.out.println("customer.getColumnNameList() = " + customersPageWithTable.getColumnNameList());
        Assert.assertTrue(customersPageWithTable.doesGivenColumnListExist(columnNameList));
    }


    @Then("verify that there is one {string} button for each row in the table")
    public void verify_that_there_is_one_button_for_each_row_in_the_table(String buttonType) {
        CustomersPageWithTable customersPageWithTable = new CustomersPageWithTable();
        int rowNumber = customersPageWithTable.getAllItemsInTheGivenColumn("Email").size();
        int buttonNumber = customersPageWithTable.getButtonCountInTheCurrentPage(buttonType);
        Assert.assertEquals(rowNumber, buttonNumber);
    }

    @Then("verify that after clicking {string} button for a user with a randomly selected email in the page, user information is populated")
    public void verify_that_after_clicking_button_for_a_user_with_a_randomly_selected_email_in_the_page_user_information_is_populated(String buttonType) {
        CustomersPageWithTable customersPageWithTable = new CustomersPageWithTable();
        //uzerinde bulunulan sayfadaki email'lerden rastgele birini secer
        String email = customersPageWithTable.getOneRandomEmailFromCurrentPage();

        //sectigi email uzerinden, emailin oldugu satirdaki bilgileri alir ve map olusuturur
        Map<String, String> infoMapFromTable = customersPageWithTable.generateMapForOneLineKeyValuePairsUsingGivenCellValue(email);
        customersPageWithTable.clickGivenButtonForWantedColumnAndValue("Email", email, buttonType);

        //kisinin sayfasina gecer
        CustomerInfoPageForOneUser customerInfoPageForOneUser = new CustomerInfoPageForOneUser();
        BrowserUtils.waitForVisibility(customerInfoPageForOneUser.firstNameHeader,10);
        String firstName = customerInfoPageForOneUser.getGivenTextValue("firstName");
        String lastName = customerInfoPageForOneUser.getGivenTextValue("lastName");

        //tablo sayfasindan aldigi bilgiler ile kisi sayfasindan aldiklarini check eder.
        Assert.assertEquals(infoMapFromTable.get("First Name"),firstName);
        Assert.assertEquals(infoMapFromTable.get("Last Name"), lastName);
    }

    @Then("verify that in the user page, there is an edit button")
    public void verify_that_in_the_user_page_there_is_an_edit_button() {
        CustomerInfoPageForOneUser customerInfoPageForOneUser = new CustomerInfoPageForOneUser();
        String buttonText = customerInfoPageForOneUser.editButton.getText();
        Assert.assertEquals("Edit", buttonText);
    }
}
