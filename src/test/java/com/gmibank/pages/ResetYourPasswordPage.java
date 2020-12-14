package com.gmibank.pages;

import com.gmibank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResetYourPasswordPage extends BasePage{

    @FindBy(xpath = "//h1")
    public WebElement pageHeader;

    public String getPageHeader(){
        return pageHeader.getText();
    }


}
