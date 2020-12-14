package com.gmibank.pages;

import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.security.Key;
import java.util.List;

public class CreateOrEditCustomer extends BasePage{


    @FindBy(id = "search-ssn")
    public WebElement ssnTextBox;

    @FindBy(xpath = "//button[@type='button'][text()='Search']")
    public WebElement searchButton;

    @FindBy(xpath = "//div[@role='alert']")
    public WebElement alertMessage;


    //US_011
    @FindBy (id = "tp-customer-createDate")
    public WebElement DateTimeBox;

    @FindBy (id="tp-customer-user")
    public WebElement UserBox;

    @FindBy (id="tp-customer-account")
    public WebElement AccountBox;

    @FindBy (id="tp-customer-zelleEnrolled")
    public WebElement ZelleCheckBox;

    @FindBy (id="save-entity")
    public WebElement SaveButton;

    @FindBy(id = "tp-customer-address")
    public WebElement addressTextBox;

    @FindBy(id = "tp-customer-city")
    public WebElement cityTextBox;

    @FindBy(id = "tp-customer-state")
    public WebElement stateTextbox;

    @FindBy(id = "tp-customer-zipCode")
    public WebElement zipCodeTextBox;

    public void typeAddress(String address){
        addressTextBox.click();
        addressTextBox.sendKeys(address);
        addressTextBox.sendKeys(Keys.TAB);
    }

    public void leaveBlankTextBox(String textBox){
        WebElement element = null;

       switch (textBox){
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

    public List<WebElement> getErrorElementsUnderTextBoxes(){
        BrowserUtils.waitForVisibility(By.cssSelector(".invalid-feedback"),5);
        return Driver.getDriver().findElements(By.cssSelector(".invalid-feedback"));
    }

    public boolean isThereAnySuchErrorUnderTextBoxes(String expectedErrorMessage){
        List<String> errorListTexts = BrowserUtils.getElementsText(getErrorElementsUnderTextBoxes());
        for (String error : errorListTexts) {
            if (error.contains(expectedErrorMessage)){
                return true;
            }
        }
        return false;
    }

    public WebElement getAlert(){
        BrowserUtils.waitForVisibility(By.xpath("//div[@role='alert']"), 5);
        List<WebElement> elements = Driver.getDriver().findElements(By.xpath("//div[@role='alert']"));
        if (elements.size()==0){
            return null;
        }else{
            return elements.get(0);
        }
    }

    public String getAlertMessage(){
        WebElement element = getAlert();
        return element.getText();
    }


    public void typeSsnNumber(String ssn){
        ssnTextBox.click();
        ssnTextBox.sendKeys(ssn);
    }

    public void clickSearchButton(){
        BrowserUtils.waitForClickablility(searchButton,5);
        searchButton.click();
        BrowserUtils.waitFor(3);
    }

    public void typeSsnAndClickSearchButton(String ssn){
        typeSsnNumber(ssn);
        clickSearchButton();
    }


}
