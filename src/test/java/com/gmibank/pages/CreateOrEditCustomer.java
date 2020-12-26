package com.gmibank.pages;

import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.Driver;
import io.cucumber.java.eo.Se;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class CreateOrEditCustomer extends BasePage {


    @FindBy(id = "search-ssn")
    public WebElement ssnTextBox;

    @FindBy(xpath = "//button[@type='button'][text()='Search']")
    public WebElement searchButton;

    @FindBy(id = "tp-customer-middleInitial")
    public WebElement middleInitialTextbox;

    //US_011
    @FindBy(id = "tp-customer-createDate")
    public WebElement DateTimeBox;

    @FindBy(id = "tp-customer-user")
    public WebElement UserBox;

    @FindBy(id = "tp-customer-account")
    public WebElement AccountBox;

    @FindBy(id = "tp-customer-zelleEnrolled")
    public WebElement ZelleCheckBox;

    @FindBy(id = "save-entity")
    public WebElement SaveButton;

    @FindBy(id = "tp-customer-address")
    public WebElement addressTextBox;

    @FindBy(id = "tp-customer-city")
    public WebElement cityTextBox;

    @FindBy(id = "tp-customer-country")
    public WebElement countryDropDown;

    @FindBy(id = "tp-customer-account")
    public WebElement accountSelectionDropDown;

    @FindBy(id = "tp-customer-state")
    public WebElement stateTextbox;

    @FindBy(id = "tp-customer-zipCode")
    public WebElement zipCodeTextBox;

    @FindBy(xpath = "//div[@role='alert']")
    public WebElement toastAlert;

    @FindBy(id = "tp-customer-phoneNumber")
    public WebElement phoneTextBox;

    public boolean doesContainSuchAMessageInsideAlert(String message) {
        BrowserUtils.waitForVisibility(toastAlert, 5);
        return toastAlert.getText().contains(message);
    }

    public void typeAddress(String address) {
        addressTextBox.click();
        addressTextBox.sendKeys(address);
        addressTextBox.sendKeys(Keys.TAB);
    }


    public void leaveBlankTextBox(String textBox) {
        WebElement element = null;

        switch (textBox) {
            case "address":
                element = addressTextBox;
                break;
            case "city":
                element = cityTextBox;
                break;
            case "state":
                element = stateTextbox;
                break;
            case "zipCode":
                element = zipCodeTextBox;
                break;
            default:
                System.out.println("textbox cannot be found!!!!");
                break;
        }


        element.click();
        String writtenAddress = element.getAttribute("value");
        int size = writtenAddress.length();
        element.click();
        for (int i = 0; i < size; i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }
        element.sendKeys(Keys.TAB);

/*        addressTextBox.click();
        String writtenAddress = addressTextBox.getAttribute("value");
        int size = writtenAddress.length();
        addressTextBox.click();
        for (int i = 0; i < size; i++) {
            addressTextBox.sendKeys(Keys.BACK_SPACE);
        }
        addressTextBox.sendKeys(Keys.TAB);*/
    }

    public List<WebElement> getErrorElementsUnderTextBoxes() {
        BrowserUtils.waitForVisibility(By.cssSelector(".invalid-feedback"), 5);
        return Driver.getDriver().findElements(By.cssSelector(".invalid-feedback"));
    }

    public boolean isThereAnySuchErrorUnderTextBoxes(String expectedErrorMessage) {
        List<String> errorListTexts = BrowserUtils.getElementsText(getErrorElementsUnderTextBoxes());
        for (String error : errorListTexts) {
            if (error.contains(expectedErrorMessage)) {
                return true;
            }
        }
        return false;
    }

    public WebElement getAlert() {
        BrowserUtils.waitForVisibility(By.xpath("//div[@role='alert']"), 5);
        List<WebElement> elements = Driver.getDriver().findElements(By.xpath("//div[@role='alert']"));
        if (elements.size() == 0) {
            return null;
        } else {
            return elements.get(0);
        }
    }

    public String getAlertMessage() {
        WebElement element = getAlert();
        return element.getText();
    }


    public void typeSsnNumber(String ssn) {
        ssnTextBox.click();
        ssnTextBox.sendKeys(ssn);
    }

    public void clickSearchButton() {
        BrowserUtils.waitForClickablility(searchButton, 5);
        searchButton.click();
        BrowserUtils.waitFor(3);
    }

    public void typeSsnAndClickSearchButton(String ssn) {
        typeSsnNumber(ssn);
        clickSearchButton();
    }

    public void typeMiddleInitial() {
        middleInitialTextbox.click();
        middleInitialTextbox.sendKeys("-");
    }

    public void typeCity(String city) {
        cityTextBox.click();
        cityTextBox.sendKeys(city);
    }

    public void typeZipCode(String zipCode) {
        zipCodeTextBox.click();
        zipCodeTextBox.sendKeys(zipCode);
    }

    public void selectCountry(String country) {
        Select select = new Select(countryDropDown);
        select.selectByVisibleText(country);
    }

    public void typeState(String state) {
        stateTextbox.click();
        stateTextbox.sendKeys(state);
    }

    public void selectTwoAccounts(String account1, String account2Value) {
        Select select = new Select(accountSelectionDropDown);
        System.out.println("select.isMultiple() = " + select.isMultiple());
/*        List<WebElement> optionList = select.getOptions();
        Actions actions = new Actions(Driver.getDriver());
        Actions clickAction;
        clickAction = actions.keyDown(Keys.CONTROL);
        for (WebElement option : optionList) {
            if (option.getText().equals(account1) || option.getText().equals(account2)){
                clickAction.click(option);
            }
        }
        clickAction.keyUp(Keys.CONTROL)
                .build().perform();*/
        select.selectByVisibleText(account1);
        select.selectByValue(account2Value);
    }

    public void checkZelleCheckBox() {
        if (!ZelleCheckBox.isSelected()) {
            ZelleCheckBox.click();
        }
    }

    public void clickSaveButton() {
        BrowserUtils.waitForClickablility(SaveButton, 5);
        SaveButton.click();
    }

    public void typePhoneNumber(String phoneNumber) {
        phoneTextBox.click();
        phoneTextBox.sendKeys(phoneNumber);
    }

    public void fillUpAllInformationToCreateCustomerAfterSearch(String zipCode, String phoneNumber, String city, String country, String state, String account1, String account2Value) {
        typeMiddleInitial();
        typeZipCode(zipCode);
        typePhoneNumber(phoneNumber);
        typeCity(city);
        selectCountry(country);
        typeState(state);
        selectTwoAccounts(account1, account2Value);
        BrowserUtils.waitFor(2);
        checkZelleCheckBox();
    }

}
