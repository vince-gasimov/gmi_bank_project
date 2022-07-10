package com.gmibank.stepDefinitions.uiStepDefs;

import com.gmibank.api.ApiUtilities.ApiAccountUtilities;
import com.gmibank.api.ApiUtilities.ApiCustomerUtilities;
import com.gmibank.pages.*;
import com.gmibank.utilities.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestClass {

    @BeforeMethod
    public void setup() {
/*        WebDriver driver = Driver.getDriver();
        driver.get(ConfigurationReader.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);*/
/*        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("document.body.style.zoom = '0.8'");*/
        DatabaseUtility.createConnection();
    }


    @AfterMethod
    public void tearDown() {
/*        BrowserUtils.waitFor(1);
        Driver.closeDriver();*/
        DatabaseUtility.closeConnection();
    }

    @Test
    public void test1() throws Exception {


        BasePage basePage = new BasePage();
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon("Sign in");
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithValidInfo("admin");
        BrowserUtils.waitFor(2);
        basePage.clickGivenNavItemAndSelectGivenDropDownItem("Administration", "User management");
        UsersPageWithTable usersPageWithTable = new UsersPageWithTable();
        usersPageWithTable.clickGivenButtonForWantedColumnAndValue("Email", "jenny@gmail.com", "edit");
        System.out.println("basePage.getPageHeader() = " + basePage.getPageHeader());
        Assert.assertTrue(basePage.getPageHeader().contains("User ["));
        BrowserUtils.waitFor(3);

    }

    @Test
    public void test2() throws Exception {
        BasePage basePage = new BasePage();
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon("Sign in");
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithValidInfo("employee");
        BrowserUtils.waitFor(2);
        basePage.clickGivenNavItemAndSelectGivenDropDownItem("My Operations", "Manage Customers");
        CustomersPageWithTable customersPageWithTable = new CustomersPageWithTable();
        customersPageWithTable.clickGivenButtonForWantedColumnAndValue("Email", "gmiprojesi@gmail.com", "view");
        System.out.println("basePage.getPageHeader() = " + basePage.getPageHeader());
        BrowserUtils.waitFor(2);
        CustomerInfoPageForOneUser customerInfoPageForOneUser = new CustomerInfoPageForOneUser();
        System.out.println(customerInfoPageForOneUser.emailHeader.getText());
        System.out.println(customerInfoPageForOneUser.getGivenTextValue("email"));
        //System.out.println(customerInfoPageForOneUser.getGivenTextValue("country"));
    }


    @Test
    public void test3() throws Exception {
        BasePage basePage = new BasePage();
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon("Sign in");
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithValidInfo("employee");
        BrowserUtils.waitFor(2);
        basePage.clickGivenNavItemAndSelectGivenDropDownItem("My Operations", "Manage Accounts");
        AccountsPageWithTable accountsPageWithTable = new AccountsPageWithTable();
        accountsPageWithTable.clickGivenButtonForWantedColumnAndValue("Description", "team13 customer account2", "view");
        System.out.println("basePage.getPageHeader() = " + basePage.getPageHeader());
        Assert.assertTrue(basePage.getPageHeader().equals("Account [30239]"));
        BrowserUtils.waitFor(3);
    }


    @Test
    public void test4() {
        System.out.println("DummyDataGenerator.generateAddress() = " + DummyDataGenerator.generateAddress());
        Map<String, String> map = new HashMap<>();
        map = DummyDataGenerator.generateAllNeededInformationExceptPassword();
        for (String s : map.keySet()) {
            System.out.println(s + " " + map.get(s));
        }
        ExcelUtilities excelUtilities = new ExcelUtilities("src/test/resources/CreatedUserInformation.xlsx", "registration_sheet_name");
        System.out.println("excelUtilities.getColumnsNames() = " + excelUtilities.getColumnsNames());
        System.out.println(excelUtilities.getColumnsNames().indexOf("ssnNumber"));
        excelUtilities.writeUserIntoExcel(map);

    }

    @Test
    public void test5() throws Exception {
        String path = "src/test/resources/CreatedUserInformation.xlsx";

        ExcelUtilities excel = new ExcelUtilities(path, "registered");
/*        int rowNum = excel.rowCount();
        System.out.println("rowNum = " + rowNum);
        System.out.println("excel.getDataList() = " + excel.getDataList());
        excel.removeLastRow();
        System.out.println("excel.getDataList() = " + excel.getDataList());
        rowNum = excel.rowCount();
        excel.saveWorkBook();*/
        User user;

        RegistrationPage registrationPage = new RegistrationPage();
        if (registrationPage.makeSureThereExistRegistrantInExcel()) {
            user = excel.getLastRegistrantAsUser();
            System.out.println("lastRegistrantInfoMap = " + user.getFirstName());
            System.out.println("lastRegistrantInfoMap = " + user.getLastName());
        }

    }

    @Test
    public void test6() throws Exception {
        BasePage basePage = new BasePage();
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon("Sign in");
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithValidInfo("admin");
        BrowserUtils.waitFor(2);
        basePage.clickGivenNavItemAndSelectGivenDropDownItem("Administration", "User management");
        UsersPageWithTable usersPageWithTable = new UsersPageWithTable();
        BrowserUtils.waitForClickablility(usersPageWithTable.createButton, 5);
        String randomEmail = usersPageWithTable.getOneRandomEmailFromCurrentPage();
        System.out.println("randomEmail = " + randomEmail);
        String firstActivationStatus = usersPageWithTable.getActivationStatus(randomEmail);
        System.out.println("firstActivationStatus = " + firstActivationStatus);
        usersPageWithTable.clickAndChangeActivationStatus(randomEmail);
        String expectedResultMessage = "A user is updated with";
        Assert.assertTrue(usersPageWithTable.doesContainSuchAMessageInsideAlert(expectedResultMessage));
        BrowserUtils.waitForInvisibility(By.xpath("//div[@role='alert']"), 10);
        Driver.getDriver().navigate().refresh();
        //BrowserUtils.waitFor(10);
        WebElement element = usersPageWithTable.locateWantedCellWithGivenColumnAndValue("Email", randomEmail);
        BrowserUtils.hover(element);
        String secondActivationStatus = usersPageWithTable.getActivationStatus(randomEmail);

        System.out.println("secondActivationStatus = " + secondActivationStatus);
        Assert.assertFalse(firstActivationStatus.equals(secondActivationStatus));
    }

    @Test
    public void test7() {
        String path = "src/test/resources/CreatedUserInformation.xlsx";
        ExcelUtilities excel = new ExcelUtilities(path, "registration_sheet_name");
        System.out.println("excel.getLastRow() = " + excel.getLastRow());
    }

    @Test
    public void test8() throws Exception {
        String email = "horacio.kutch@gmail.com";
        String query = "select * from jhi_user ju join jhi_user_authority jua on (ju.id = jua.user_id) where ju.email = '" + email + "';";
        if (!DatabaseUtility.doesExistAnyRow(query)) {
            System.out.println("row yok!!!");
        }
        System.out.println("row var");
    }

    @Test
    public void test9() throws Exception {
        BasePage basePage = new BasePage();
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon("Sign in");
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithValidInfo("admin");
        BrowserUtils.waitFor(2);
        basePage.clickGivenNavItemAndSelectGivenDropDownItem("Administration", "User management");
        UsersPageWithTable usersPageWithTable = new UsersPageWithTable();
        BrowserUtils.waitForClickablility(usersPageWithTable.createButton, 5);
        String randomEmail = usersPageWithTable.getOneRandomEmailFromCurrentPage();
        System.out.println("randomEmail = " + randomEmail);
        usersPageWithTable.clickGivenButtonForWantedColumnAndValue("Email", randomEmail, "edit");
        CreateOrEditUserPageByClickEdit createOrEditUserPageByClickEdit = new CreateOrEditUserPageByClickEdit();
        Select select = new Select(createOrEditUserPageByClickEdit.profilesBox);
        System.out.println("butun secenkeleri al *****************''");
        List<String> list = BrowserUtils.getElementsText(select.getOptions());
        for (String s : list) {
            System.out.println("select.getOptions() = " + s);
        }

        System.out.println("secili olanlari getir *************************");
        list = BrowserUtils.getElementsText(select.getAllSelectedOptions());
        for (String s : list) {
            System.out.println("select.getOptions() = " + s);
        }

        System.out.println("yenilerini ekle*********************''");
        select.selectByVisibleText("ROLE_ADMIN");
        select.selectByVisibleText("ROLE_EMPLOYEE");
        System.out.println("butun secili olanlari getir***********************'");

        list = BrowserUtils.getElementsText(select.getAllSelectedOptions());
        for (String s : list) {
            System.out.println("select.getOptions() = " + s);
        }
        System.out.println("hepsini deseltc et************************'");
        select.deselectAll();
        System.out.println("secili olanlari getir**************************''");

        list = BrowserUtils.getElementsText(select.getAllSelectedOptions());
        for (String s : list) {
            System.out.println("select.getOptions() = " + s);
        }
        System.out.println("select.getAllSelectedOptions() = " + select.getAllSelectedOptions());

    }

    @Test
    public void test10() {
        ExcelUtilities excelUtilities = new ExcelUtilities(ConfigurationReader.getProperty("registration_excel_path"), "jhi_user");
        List<String> email = excelUtilities.getGivenColumnValues("email");
        for (String s : email) {
            System.out.println(s);
        }
        System.out.println("excelUtilities.getRowIndexNumForGivenValue(\"email\",\"jake.bayer@hotmail.com\") = " + excelUtilities.getRowIndexNumForGivenValue("email", "jake.bayer@hotmail.com"));
    }

    @Test
    public void test11() {
        ExcelUtilities excelUtilities = new ExcelUtilities(ConfigurationReader.getProperty("registration_excel_path"), "jhi_user");
        System.out.println(excelUtilities.getRandomRowNumWithinSameProfile("ROLE_USER"));
        System.out.println(excelUtilities.getRandomRowNumWithinSameProfile("ROLE_USER"));
        System.out.println(excelUtilities.getRandomRowNumWithinSameProfile("ROLE_ADMIN"));
        System.out.println(excelUtilities.getRandomRowNumWithinSameProfile("ROLE_USER"));
        System.out.println(excelUtilities.getSpecifiedRow(2));
    }

    @Test
    public void test12() throws Exception {
        BasePage basePage = new BasePage();
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon("Sign in");
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithValidInfo("employee");
        BrowserUtils.waitForVisibility(new BasePage().myOperationsNavItem, 5);
        basePage.clickGivenNavItemAndSelectGivenDropDownItem("My Operations", "Manage Accounts");
        AccountsPageWithTable accountsPageWithTable = new AccountsPageWithTable();
        BrowserUtils.waitForVisibility(accountsPageWithTable.table, 15);
        accountsPageWithTable.clickCreateButton();
        CreateOrEditAccountPage createOrEditAccountPage = new CreateOrEditAccountPage();
        createOrEditAccountPage.createNewAccount("ekuzenin hesabi", 100, "CHECKING");
        BrowserUtils.waitForVisibility(accountsPageWithTable.table, 15);


        accountsPageWithTable.clickCreateButton();
        Assert.assertTrue(accountsPageWithTable.verifyGivenAccountExist("ekuzenin hesabi"));
        BrowserUtils.scrollUpWithActionClass();
        createOrEditAccountPage.createNewAccount("dayimin hesabi", 100, "CHECKING");
        BrowserUtils.waitForVisibility(accountsPageWithTable.table, 15);
        Assert.assertTrue(accountsPageWithTable.verifyGivenAccountExist("dayimin hesabi"));

    }

    @Test
    public void test13() {
        ApiAccountUtilities.getAccountInfoFromApiWithGivenId(2313).prettyPrint();
    }

    @Test
    public void test14() {
        System.out.println(DatabaseUtility.getAccountInfoWithGivenDescription("lindsay.morissette account 2"));
    }

    @Test
    public void test15() throws Exception {
        BasePage basePage = new BasePage();
        basePage.clickAndSelectDropDownItemUnderAccountMenuIcon("Sign in");
        LoginPage loginPage = new LoginPage();
        loginPage.loginWithValidInfo("employee");
        BrowserUtils.waitForVisibility(new BasePage().myOperationsNavItem, 5);
        basePage.clickGivenNavItemAndSelectGivenDropDownItem("My Operations", "Manage Customers");
        CustomersPageWithTable customersPageWithTable = new CustomersPageWithTable();
        String ssn = "309-75-3963";
        String zipCode = "94764-5210";
        String phoneNumber = "111-111-1111"; //rastgele verebilrisin
        String city = "South Janview";
        String country = "Poland"; //kendim vermem lazim
        String state = "Georgia";
        String account1 = "esta.connelly account 1";
        String account2 = "esta.connelly account 2";


        customersPageWithTable.createNewCustomerSearchedWithSsn(ssn, zipCode, phoneNumber, city, country, state, account1, account2);
        BrowserUtils.waitFor(3);

    }

    @Test
    public void test16() {
        Map<String, Object> account1IdRowMap = DatabaseUtility.getAccountOwnerWithAccountId("58750");
        org.junit.Assert.assertEquals(String.valueOf((Long) account1IdRowMap.get("tpcustomer_id")), "62170");
    }


    @Test
    public void test17() {
        Response response = ApiCustomerUtilities.getSpecifiedCustomerInfo(Integer.valueOf("55037"));
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> accountIdList = jsonPath.getList("accounts.id");
        for (Integer integer : accountIdList) {
            System.out.println("integer = " + integer);
        }
        org.junit.Assert.assertTrue(accountIdList.containsAll(Arrays.asList(Integer.valueOf("2304"), Integer.valueOf("2307"))));

    }


    @Test
    public void test18() {

        String file_name = "users.pdf";
        Document document = new Document(PageSize.A4);
        System.out.println("document created..");
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.addAuthor("Deneme");
            document.addTitle("Demo for IText");
            System.out.println("write instance create..");
            document.open();
            System.out.println("document open..");
            Paragraph para = new Paragraph("All users");
            document.add(para);
            System.out.println("para added to document..");
            PdfPTable table = new PdfPTable(3);
            PdfPCell c1 = new PdfPCell(new Phrase("Name"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Last Name"));
            table.addCell(c1);
            c1 = new PdfPCell(new Phrase("Role"));
            table.addCell(c1);
            table.setHeaderRows(1);
            table.addCell("ahmet");
            table.addCell("durmaz");
            table.addCell("user");
            document.add(table);
        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();
        System.out.println("document closed..");
    }

    @Test
    public void test19() {
        String path = ConfigurationReader.getProperty("registration_excel_path");
        String registrationSheetName = ConfigurationReader.getProperty("registration_sheet_name");
        ExcelUtilities excel = new ExcelUtilities(path,registrationSheetName);
        excel.setWorkSheet(ConfigurationReader.getProperty("jhi_user_sheet_name"));
        List<String> columnList = Arrays.asList("firstName", "lastName", "profiles");
        List<Map<String, String>> listOfUserRows = excel.getDataListForSpecifiedColumns(columnList);
        PrintOutAsPdf.createPdf("UserList.pdf", listOfUserRows);
    }

}