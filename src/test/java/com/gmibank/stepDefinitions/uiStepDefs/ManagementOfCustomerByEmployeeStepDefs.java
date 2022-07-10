package com.gmibank.stepDefinitions.uiStepDefs;

import com.gmibank.pages.CreateOrEditCustomerPageByClickEdit;
import com.gmibank.pages.CustomerInfoPageForOneUser;
import com.gmibank.pages.CustomersPageWithTable;
import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.DummyDataGenerator;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Map;

public class ManagementOfCustomerByEmployeeStepDefs {

    public String newCity = "yok";
    public String email = "yok";

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
        BrowserUtils.waitFor(2);
        //BrowserUtils.waitForTextAppearInElement(customerInfoPageForOneUser.pageHeader, "Customer", 5);
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


    @Then("verify that after clicking {string} button for a user with a randomly selected email in the page, user information is populated on create_or_edit_customer_page")
    public void verify_that_after_clicking_button_for_a_user_with_a_randomly_selected_email_in_the_page_user_information_is_populated_on_create_or_edit_customer_page(String buttonType) {
        CustomersPageWithTable customersPageWithTable = new CustomersPageWithTable();
        //uzerinde bulunulan sayfadaki email'lerden rastgele birini secer
        String email = customersPageWithTable.getOneRandomEmailFromCurrentPage();

        //sonraki methodlarda kullanmak uzere kaydediyorum
        this.email = email;

        //sectigi email uzerinden, emailin oldugu satirdaki bilgileri alir ve map olusuturur
        Map<String, String> infoMapFromTable = customersPageWithTable.generateMapForOneLineKeyValuePairsUsingGivenCellValue(email);
        customersPageWithTable.clickGivenButtonForWantedColumnAndValue("Email", email, buttonType);

        CreateOrEditCustomerPageByClickEdit createOrEditCustomerPageByClickEdit = new CreateOrEditCustomerPageByClickEdit();
        BrowserUtils.waitFor(1);
        BrowserUtils.waitForTextAppearInElement(createOrEditCustomerPageByClickEdit.pageHeader,"Create or edit a Customer", 5);

        String id = createOrEditCustomerPageByClickEdit.id.getAttribute("value");
        Assert.assertEquals(infoMapFromTable.get("ID"), id);
    }


    @When("user edit {string} by generating a new one in create_or_edit_customer_page")
    public void user_edit_by_generating_a_new_one_in_create_or_edit_customer_page(String string) {
        CreateOrEditCustomerPageByClickEdit createOrEditCustomerPageByClickEdit = new CreateOrEditCustomerPageByClickEdit();
        String newCity = createOrEditCustomerPageByClickEdit.generateNewRandomCity();
        createOrEditCustomerPageByClickEdit.typeCity(newCity);

        //bu methodun devamindaki methodda verification yapmak icin kullanmak uzere kaydediyorum global degisken icine
        this.newCity = newCity;

        createOrEditCustomerPageByClickEdit.clickSaveButton();
    }

    @Then("verify that a message is displayed such as {string} on the customers_page")
    public void verify_that_a_message_is_displayed_such_as_on_the_customers_page(String messageContent) {
        CustomersPageWithTable customersPageWithTable = new CustomersPageWithTable();
        BrowserUtils.waitForVisibility(customersPageWithTable.toastAlert,5);
        Assert.assertTrue(customersPageWithTable.doesContainSuchAMessageInsideAlert(messageContent));
    }

    @Then("verify that a message is displayed such as {string} on the customers_page and new value is seen on user_info_page")
    public void verify_that_a_message_is_displayed_such_as_on_the_customers_page_and_new_value_is_seen_on_user_info_page(String messageContent) {
        CustomersPageWithTable customersPageWithTable = new CustomersPageWithTable();
        BrowserUtils.waitForVisibility(customersPageWithTable.toastAlert,5);
        Assert.assertTrue(customersPageWithTable.doesContainSuchAMessageInsideAlert(messageContent));
        String alertLocator = "//div[@role='alert']";
        BrowserUtils.waitForInvisibility(By.xpath(alertLocator), 10);
        String expectedCity = newCity;
        String email = this.email;

        customersPageWithTable.clickGivenButtonForWantedColumnAndValue("Email", email, "edit");
        this.email = "yok";
        this.newCity = "yok";
        CreateOrEditCustomerPageByClickEdit createOrEditCustomerPageByClickEdit = new CreateOrEditCustomerPageByClickEdit();
        BrowserUtils.waitForVisibility(createOrEditCustomerPageByClickEdit.id,5);
        BrowserUtils.waitFor(5);
        Assert.assertEquals(expectedCity, createOrEditCustomerPageByClickEdit.getCity());
    }

    @When("user clicks {string} button for a user with a randomly selected email in the page")
    public void user_clicks_button_for_a_user_with_a_randomly_selected_email_in_the_page(String buttonType) {
        CustomersPageWithTable customersPageWithTable = new CustomersPageWithTable();
        String email = customersPageWithTable.getOneRandomEmailFromCurrentPage();
        customersPageWithTable.clickGivenButtonForWantedColumnAndValue("Email", email, buttonType);
    }

    @Then("verify that such an information message {string} is appeared after delete operation")
    public void verify_that_such_an_information_message_is_appeared_after_delete_operation(String messageContent) {
        CustomersPageWithTable customersPageWithTable = new CustomersPageWithTable();
        BrowserUtils.waitForVisibility(customersPageWithTable.deleteButtonOnConfirmationBox,5);
        System.out.println("customersPageWithTable.getTextOfWarning() = " + customersPageWithTable.getTextOfWarning());
        customersPageWithTable.clickDeleteOnConfirmationBox();
        BrowserUtils.waitForVisibility(customersPageWithTable.toastAlert,5);
        Assert.assertTrue(customersPageWithTable.doesContainSuchAMessageInsideAlert(messageContent));
    }
}
