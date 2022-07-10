package com.gmibank.stepDefinitions.uiStepDefs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmibank.api.ApiUtilities.ApiAccountUtilities;
import com.gmibank.api.ApiUtilities.ApiApplicantsUtilities;
import com.gmibank.api.ApiUtilities.ApiCustomerUtilities;
import com.gmibank.pages.*;
import com.gmibank.utilities.*;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CustmerEndToEndStepDefs {
    String path = ConfigurationReader.getProperty("registration_excel_path");
    String registrationSheetName = ConfigurationReader.getProperty("registration_sheet_name");
    String jhiUserSheetInExcel = ConfigurationReader.getProperty("jhi_user_sheet_name");
    ExcelUtilities excel;
    User userFromExcel;
    Map<String, Object> mapFromDatabase;
    String emailFromExcel;
    long idFromDb;
    Response response;
    com.gmibank.api.pojos.User userFromApi;
    ObjectMapper oMapper;
    BasePage basePage = new BasePage();
    LoginPage loginPage = new LoginPage();
    AccountsPageWithTable accountsPageWithTable;
    CreateOrEditAccountPage createOrEditAccountPage;
    Map<String, String> account1MapFromUI;
    Map<String, String> account2MapFromUI;
    JsonPath jsonPath;
    CustomersPageWithTable customersPageWithTable;
    String customerId;
    Map<String, Object> customerMapFromDb = new HashMap<>();
    UsersPageWithTable usersPageWithTable = new UsersPageWithTable();
    int userId;

    @When("a customer candidate register itself with valid information on registration page")
    public void a_customer_candidate_register_itself_with_valid_information_on_registration_page() {

        new RegistrationPage().navigateAndRegisterNewUserWithRandomGeneratedValue();
        //with above method, after registration, it navigates to home page

    }

    @Then("verify the newregistered customer registration information directly from database")
    public void verify_the_newregistered_customer_registration_information_directly_from_database() {

        excel = new ExcelUtilities(path, registrationSheetName);
        userFromExcel = excel.getLastRegistrantAsUser();
        emailFromExcel = userFromExcel.getEmail();
        mapFromDatabase = DatabaseUtility.getUserInformationWithEmailFromGivenTable("tpaccount_registration", emailFromExcel);

        //take id and store it in the user object
        idFromDb = (long) mapFromDatabase.get("id");

        userFromExcel.setId(String.valueOf(idFromDb));
        assertTrue(DatabaseUtility.compareMapFromDbAndUserObjectForNewRegistration(mapFromDatabase,userFromExcel));
    }

    @Then("verify the newregistered customer registration information with the help of API")
    public void verify_the_newregistered_customer_registration_information_with_the_help_of_API() throws IOException {
        int id = Integer.parseInt(userFromExcel.getId());
        response = ApiApplicantsUtilities.getSpecifiedApplicantInfo(id);

        JsonPath jsonPath = response.jsonPath();
        String ssnFromApi = jsonPath.getString("ssn");
        String firstNameFromApi = jsonPath.getString("firstName");
        String lastNameFromApi = jsonPath.getString("lastName");
        String userNameFromApi = jsonPath.getString("userName");
        String emailFromApi = jsonPath.getString("email");

        assertEquals("ssn is wrong", userFromExcel.getSsnNumber(), ssnFromApi);
        assertEquals("firstName is wrong", userFromExcel.getFirstName(), firstNameFromApi);
        assertEquals("lastname is wrong", userFromExcel.getLastName(), lastNameFromApi);
        assertEquals("userName is wrong", userFromExcel.getUserName(), userNameFromApi);
        assertEquals("email is wrong", userFromExcel.getEmail(), emailFromApi);


                /*
                {
    "id": 61613,
    "ssn": "172-83-7736",
    "firstName": "Todd",
    "lastName": "Strosin",
    "address": "86776 Lonnie Drives",
    "mobilePhoneNumber": "511-434-3547",
    "userId": 61574,
    "userName": "nguyet.welch",
    "email": "johnson.reichert@hotmail.com",
    "createDate": "2020-12-24T15:25:09.804Z"
}
                 */
    }

    @When("after successful {string} operation of this customer, an {string} sign in to the system")
    public void after_successful_operation_of_this_customer_an_sign_in_to_the_system(String operationName, String userType) throws Exception {
        if (userType.equals("admin")){
            basePage.singOut();
            BrowserUtils.waitForVisibility(By.linkText("SIGN IN AGAIN"),5);
        }
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon("Sign in");
        loginPage.loginWithValidInfo(userType);
        BrowserUtils.waitForVisibility(new BasePage().myOperationsNavItem, 5);

    }

    @When("employee creates two different accounts for this customer with a description of its username")
    public void employee_creates_two_different_accounts_for_this_customer_with_a_description_of_its_username() {
        basePage.clickGivenNavItemAndSelectGivenDropDownItem("My Operations", "Manage Accounts");
        accountsPageWithTable = new AccountsPageWithTable();
        BrowserUtils.waitForVisibility(accountsPageWithTable.table,15);
        accountsPageWithTable.clickCreateButton();
        createOrEditAccountPage = new CreateOrEditAccountPage();
        String descriptionForAccount1 = userFromExcel.getUserName() + " account 1";
        createOrEditAccountPage.createNewAccount(descriptionForAccount1, 1000, "CHECKING");
        //get created account info from UI
        //accountsPageWithTable.verifyGivenAccountExist(account1Description);
        account1MapFromUI = accountsPageWithTable.getAllInformationFromOneLineUsingGivenCellValueWithHighlight(descriptionForAccount1);
        System.out.println("buradayim");
        accountsPageWithTable.clickCreateButton();
        System.out.println("click yaptim");
        String descriptionForAccount2 = userFromExcel.getUserName() + " account 2";
        createOrEditAccountPage.createNewAccount(descriptionForAccount2, 2000, "CHECKING");
        //account olustugunu dogrula
        account2MapFromUI = accountsPageWithTable.getAllInformationFromOneLineUsingGivenCellValueWithHighlight(descriptionForAccount2);
    }

    @Then("verify the newcreated account information directly from database")
    public void verify_the_newcreated_account_information_directly_from_database() {
        Map<String, Object> account1InfoMapFromDb = DatabaseUtility.getAccountInfoWithGivenDescription(account1MapFromUI.get("Description"));
        Map<String, Object> account2InfoMapFromDb = DatabaseUtility.getAccountInfoWithGivenDescription(account2MapFromUI.get("Description"));


        //to check results, can delete later on
        System.out.println("account1InfoMapFromDb = " + account1InfoMapFromDb);
        System.out.println("account2InfoMapFromDb = " + account2InfoMapFromDb);
        System.out.println("account1MapFromUI = " + account1MapFromUI);
        System.out.println("account2MapFromUI = " + account2MapFromUI);

        assertTrue(DatabaseUtility.compareMapsForAccoutnsFromDbAndUI(account1InfoMapFromDb,account1MapFromUI));
        assertTrue(DatabaseUtility.compareMapsForAccoutnsFromDbAndUI(account2InfoMapFromDb,account2MapFromUI));

/*        assertEquals(account1FromUI.get("ID"), (String) account1InfoMapFromDb.get("id"));
        assertEquals(account1FromUI.get("Balance"),String.valueOf(account1InfoMapFromDb.get("balance"));
        assertEquals(account1FromUI.get("Account Type"), account1InfoMapFromDb.get("account_type"));*/

        /*assertEquals(account1Description,account1InfoMapFromDb.get("description"));
        assertEquals(account2Description,account2InfoMapFromDb.get("description"));*/
    }

    @Then("verify the newcreated account information with the help of API")
    public void verify_the_newcreated_account_information_with_the_help_of_API() {
        response = ApiAccountUtilities.getAccountInfoFromApiWithGivenId(Integer.parseInt(account1MapFromUI.get("ID")));
        jsonPath = response.jsonPath();
        String descriptionFromApi = jsonPath.getString("description");
        int balanceFromApi = jsonPath.getInt("balance");
        String accountTypeFromApi = jsonPath.getString("accountType");
        assertEquals(account1MapFromUI.get("Description"),descriptionFromApi);
        assertEquals(account1MapFromUI.get("Balance"),String.valueOf(balanceFromApi));
        assertEquals(account1MapFromUI.get("Account Type"), accountTypeFromApi);

        response = ApiAccountUtilities.getAccountInfoFromApiWithGivenId(Integer.parseInt(account2MapFromUI.get("ID")));
        jsonPath = response.jsonPath();
        descriptionFromApi = jsonPath.getString("description");
        balanceFromApi = jsonPath.getInt("balance");
        accountTypeFromApi = jsonPath.getString("accountType");
        assertEquals(account2MapFromUI.get("Description"),descriptionFromApi);
        assertEquals(account2MapFromUI.get("Balance"),String.valueOf((balanceFromApi)));
        assertEquals(account2MapFromUI.get("Account Type"), accountTypeFromApi);

        /*
        {
    "id": 2313,
    "description": "Saving",
    "balance": 1377539,
    "accountType": "CHECKING",
    "accountStatusType": "ACTIVE",
    "createDate": "2020-11-05T05:00:00Z",
    "closedDate": "2020-11-05T05:00:00Z",
    "employee": null,
    "accountlogs": null
}
         */

    }

    @When("the employee approves the customer application by filling out additional information and binding created accounts")
    public void the_employee_approves_the_customer_application_by_filling_out_additional_information_and_binding_created_accounts() {
        BrowserUtils.executeJScommand(basePage.myOperationsNavItem, "arguments[0].scrollIntoView();");
        basePage.clickGivenNavItemAndSelectGivenDropDownItem("My Operations","Manage Customers");
        customersPageWithTable = new CustomersPageWithTable();
        String ssn = userFromExcel.getSsnNumber();
        String zipCode = userFromExcel.getZipCode();
        String phoneNumber = "111-111-1111"; //rastgele verebilrisin
        String city = userFromExcel.getCity();
        String country = "Poland"; //kendim vermem lazim
        String state = userFromExcel.getState();
        String account1 = account1MapFromUI.get("Description");
        String account2Value = account2MapFromUI.get("ID");

        customersPageWithTable.createNewCustomerSearchedWithSsn(ssn,zipCode,phoneNumber,city,country,state,account1,account2Value);
        //customerId = (Integer)DatabaseUtility.getAccountOwnerWithAccountId(account1MapFromUI.get("ID")).get("tpcustomer_id");


        excel.removeLastRowAndSave();
        String userIdFromRegistrationAndJhi = String.valueOf((Long) DatabaseUtility.getUserInformationIncludingAuthorityAndActivation(emailFromExcel).get("id"));
        ExcelUtilities.putAdditionalInformationForUser(true,"customer",userIdFromRegistrationAndJhi,userFromExcel);
        excel.writeUserIntoExcel(userFromExcel, ConfigurationReader.getProperty("jhi_user_sheet_name"));
    }

    @Then("verify the approved customer information directly from database")
    public void verify_the_approved_customer_information_directly_from_database() {
        BrowserUtils.waitFor(3);
        customerMapFromDb = DatabaseUtility.getCustomerInfoFromCustomerTable(userFromExcel.getEmail());
        System.out.println("customerMapFromDb = " + customerMapFromDb);
        assertEquals(userFromExcel.getFirstName(), customerMapFromDb.get("first_name"));
        assertEquals(userFromExcel.getLastName(), customerMapFromDb.get("last_name"));
        assertEquals(userFromExcel.getMobilePhoneNumber(), customerMapFromDb.get("mobile_phone_number"));
        assertEquals(userFromExcel.getSsnNumber(), customerMapFromDb.get("ssn"));
    }

    @Then("verify the approved customer information with the help of API")
    public void verify_the_approved_customer_information_with_the_help_of_API() {
        customerId =String.valueOf ((Long)customerMapFromDb.get("id"));
        response = ApiCustomerUtilities.getSpecifiedCustomerInfo(Integer.parseInt(customerId));
        JsonPath jsonPath = response.jsonPath();
        assertEquals(userFromExcel.getFirstName(),jsonPath.getString("firstName"));
        assertEquals(userFromExcel.getLastName(), jsonPath.getString("lastName"));
        assertEquals(userFromExcel.getMobilePhoneNumber(),jsonPath.getString("mobilePhoneNumber"));
        assertEquals(userFromExcel.getSsnNumber(),jsonPath.getString("ssn"));

        assertEquals(account1MapFromUI.get("ID"), String.valueOf(jsonPath.getInt("accounts.id[0]")));
        assertEquals(account2MapFromUI.get("ID"), String.valueOf(jsonPath.getInt("accounts.id[1]")));


    }

    @When("the admin activate the customer as a customer in the system")
    public void the_admin_activate_the_customer_as_a_customer_in_the_system() {
        //1. email'den kisiyi bul
        basePage.clickGivenNavItemAndSelectGivenDropDownItem("Administration", "User management");
        BrowserUtils.waitForVisibility(By.cssSelector(".table.table-striped"), 10);
        WebElement emailElement = usersPageWithTable.locateWantedCellWithGivenColumnAndValue("Email", emailFromExcel);
        System.out.println("emailElement = " + emailElement);

        Map<String, String> usersPageRowMap = usersPageWithTable.getAllInformationFromOneLineUsingGivenCellValueWithHighlight(userFromExcel.getEmail());

        //2. ilk kaydin hemen sonrasinda kullanicinin deactivate olmasi bekleniyor
        Assert.assertEquals("Deactivated",usersPageWithTable.getActivationStatus(emailFromExcel));
        System.out.println("firstActivationStatus = " + usersPageWithTable.getActivationStatus(emailFromExcel));

        //3. sistem tarafindan olusturulan id yi al
/*        userId = Integer.parseInt(usersPageWithTable.getIDElementUsingLoginName(userFromExcel.getUserName()).getText());
        System.out.println("id = " + userId);*/
        //userId'yi nerede kullandim???
        userId = Integer.parseInt(usersPageRowMap.get("ID"));
        System.out.println("userId = " + userId);


        //5. edit altindan profil sec, activate tikla ve de kaydet
        usersPageWithTable.clickGivenButtonForWantedColumnAndValue("Email",emailFromExcel, "edit");
        new CreateOrEditUserPageByClickEdit().selectProfileActivateAndSave("customer");
        BrowserUtils.waitForVisibility(usersPageWithTable.createButton,5);
        basePage.singOut();
    }

    @Then("verify that customer can sign in to the system with its valid credentials")
    public void verify_that_customer_can_sign_in_to_the_system_with_its_valid_credentials() throws Exception {
        BrowserUtils.waitForVisibility(By.linkText("SIGN IN AGAIN"),5);
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon("Sign in");
        loginPage.typeUserNameAndPassword(userFromExcel.getUserName(), userFromExcel.getPassword());
        loginPage.clickSignInButton();
        BrowserUtils.waitForVisibility(new BasePage().myOperationsNavItem, 5);
        String seenUserName = basePage.accountMenuIcon.getText();
        System.out.println("seenUserName = " + seenUserName);
        assertEquals(userFromExcel.getFirstName() + " " + userFromExcel.getLastName(),seenUserName);
    }

    @Then("verify the activated customer information directly from database")
    public void verify_the_activated_customer_information_directly_from_database() {
        Map<String, Object> userInfoWithRoleAndActivationFromDb = DatabaseUtility.getUserInformationIncludingAuthorityAndActivation(emailFromExcel);
        boolean activationStatus = (Boolean) userInfoWithRoleAndActivationFromDb.get("activated");
        assertTrue(activationStatus);
    }

    @Then("verify the activated customer information with the help of API")
    public void verify_the_activated_customer_information_with_the_help_of_API() {
        //en son yapilan sorgudaki response'u kullaniyorum tekrar
        JsonPath jsonPath = response.jsonPath();
        assertTrue(jsonPath.getBoolean("user.activated"));
    }

    @Then("the all customers' information is printed out")
    public void the_all_customers_information_is_printed_out() {
        excel.setWorkSheet(jhiUserSheetInExcel);
        List<String> columnList = Arrays.asList("firstName", "lastName", "profiles");
        List<Map<String, String>> listOfUserRows = excel.getDataListForSpecifiedColumns(columnList);
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        PrintOutAsPdf.createPdf("UserList" + timeStamp + ".pdf", listOfUserRows);
    }
}
