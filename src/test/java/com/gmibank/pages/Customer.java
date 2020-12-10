package com.gmibank.pages;

import com.gmibank.utilities.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Customer extends BasePage{


    @FindBy(id = "jh-create-entity")
    public WebElement createNewCustomerButton;

    public void clickCreateNewCustomerButton(){
        BrowserUtils.waitForClickablility(createNewCustomerButton, 5);
        createNewCustomerButton.click();
    }

}
