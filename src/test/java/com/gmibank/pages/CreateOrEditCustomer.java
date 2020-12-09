package com.gmibank.pages;

import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CreateOrEditCustomer extends BasePage{


    @FindBy(id = "search-ssn")
    public WebElement ssnTextBox;

    @FindBy(xpath = "//button[@type='button'][text()='Search']")
    public WebElement searchButton;

    @FindBy(xpath = "//div[@role='alert']")
    public WebElement alertMessage;

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
    }

    public void typeSsnAndClickSearchButton(String ssn){
        typeSsnNumber(ssn);
        clickSearchButton();
    }


}
