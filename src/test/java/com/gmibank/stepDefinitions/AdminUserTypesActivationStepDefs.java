package com.gmibank.stepDefinitions;

import com.gmibank.pages.CreateOrEditUserPageByClickEdit;
import com.gmibank.pages.UserInfoPage;
import com.gmibank.pages.UsersPageWithTable;
import com.gmibank.utilities.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AdminUserTypesActivationStepDefs {

    UsersPageWithTable usersPageWithTable = new UsersPageWithTable();
    String path = ConfigurationReader.getProperty("registration_excel_path");
    String registrationSheet = ConfigurationReader.getProperty("registration_sheet_name");
    User userFromExcel;
    String jhiUserSheet = ConfigurationReader.getProperty("jhi_user_sheet_name");
    ExcelUtilities excel;
    String userType;
    String id;
    String newFirstName;

    @Given("find the user taken from excel and activate it as {string}")
    public void find_the_user_taken_from_excel_and_activate_it_as(String userRole) {
        //1. excel'den son satirdaki yeni kaydi al ve email'ini cek
        excel = new ExcelUtilities(path, registrationSheet);
        userFromExcel = excel.getLastRegistrantAsUser();
        BrowserUtils.waitForClickablility(usersPageWithTable.createButton,5);
        userType = userRole;
        //2. email'den kisiyi bul ve sistem tarafindan olusturulmus id'yi al
        WebElement emailElement = usersPageWithTable.locateWantedCellWithGivenColumnAndValue("Email", userFromExcel.getEmail());
        System.out.println("emailElement = " + emailElement);

        //3. ilk kaydin hemen sonrasinda kullanicinin deactivate olmasi bekleniyor
        Assert.assertEquals("Deactivated",usersPageWithTable.getActivationStatus(userFromExcel.getEmail()));
        System.out.println("firstActivationStatus = " + usersPageWithTable.getActivationStatus(userFromExcel.getEmail()));

        //4. sistem tarafindan olusturulan id yi al
        id = usersPageWithTable.getIDElementUsingLoginName(userFromExcel.getUserName()).getText();
        System.out.println("id = " + id);

        //5. edit altindan profil sec, activate tikla ve de kaydet
        usersPageWithTable.clickGivenButtonForWantedColumnAndValue("Email",userFromExcel.getEmail(), "edit");
        new CreateOrEditUserPageByClickEdit().selectProfileActivateAndSave(userType);
        BrowserUtils.waitForVisibility(usersPageWithTable.createButton,5);

    }


    @Then("update excel file by moving user from registered page to jhi page")
    public void update_excel_file_by_moving_user_from_registered_page_to_jhi_page() {
        //7. aktive edilen kullaniciyi yeni kayit sayfasindan sil ve onaylanmis kullaniclar sayfasina tasi
        excel.removeLastRowAndSave();
        ExcelUtilities.putAdditionalInformationForUser(true,userType,id,userFromExcel);
        excel.writeUserIntoExcel(userFromExcel, jhiUserSheet);
        userFromExcel = null;
        excel = null;
    }


    @Given("take a user from excel sheet name {string} and delete it on users_page")
    public void take_a_user_from_excel_sheet_name_and_delete_it_on_users_page(String sheetName) {
        excel = new ExcelUtilities(path, sheetName);
        userFromExcel = excel.getLastUserFromGivenSheetInExcel();
        BrowserUtils.waitForClickablility(usersPageWithTable.createButton,5);
        usersPageWithTable.clickGivenButtonForWantedColumnAndValue("Email",userFromExcel.getEmail(), "delete");
        usersPageWithTable.deleteUser();
        BrowserUtils.waitForVisibility(usersPageWithTable.toastAlert,5);
    }
/*

    @Then("verify that such an information message {string} is appeared after delete operation on users_page")
    public void verify_that_such_an_information_message_is_appeared_after_delete_operation_on_users_page(String messageContent) {
        Assert.assertTrue(usersPageWithTable.doesContainSuchAMessageInsideAlert(messageContent));
    }*/

    @Then("update excel file by removing user from {string} sheet")
    public void update_excel_file_by_removing_user_from_sheet(String sheetName) {
        excel.setWorkSheet(sheetName);
        excel.removeLastRowAndSave();
        excel = null;
        userFromExcel = null;
    }

    @Given("take a user from excel sheet name {string} and edit its on users_page")
    public void take_a_user_from_excel_sheet_name_and_edit_its_on_users_page(String sheetName) {
        excel = new ExcelUtilities(path, sheetName);
        userFromExcel = excel.getLastUserFromGivenSheetInExcel();
        BrowserUtils.waitForClickablility(usersPageWithTable.createButton,5);
        usersPageWithTable.clickGivenButtonForWantedColumnAndValue("Email",userFromExcel.getEmail(), "edit");
        String firstName = userFromExcel.getFirstName();
        newFirstName = StringUtilities.reverseWord(firstName);
        UserInfoPage userInfoPage = new UserInfoPage();
        BrowserUtils.waitForVisibility(userInfoPage.emailBox,5);
        userInfoPage.editAndSaveFirstName(newFirstName);
        BrowserUtils.waitForVisibility(usersPageWithTable.toastAlert,5);
    }

    @Then("update excel file by editing user information inside {string} sheet")
    public void update_excel_file_by_editing_user_information_inside_sheet(String sheetName) {
        excel.setWorkSheet(sheetName);
        excel.editInformationInRow("email", userFromExcel.getEmail(),"firstName", newFirstName);
        excel = null;
        userFromExcel = null;

    }

    @Then("verify that such an information message {string} is appeared after operation on users_page")
    public void verify_that_such_an_information_message_is_appeared_after_operation_on_users_page(String messageContent) {
        Assert.assertTrue(usersPageWithTable.doesContainSuchAMessageInsideAlert(messageContent));
    }


}
