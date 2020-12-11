package com.gmibank.pages;

import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.Driver;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import javax.xml.bind.SchemaOutputResolver;
import java.util.List;

public class UserInfoPage extends BasePage{

    public UserInfoPage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }


    //sag ust kosedeki kullanici ikonu
    @FindBy(id = "account-menu")
    public WebElement accountMenuIcon;

    @FindBy(css = "[id='account-menu'] a[class='dropdown-item']")
    public List<WebElement> accountIconDropDownItems;

    @FindBy(name = "firstName")
    public WebElement firstNameBox;

    @FindBy(name = "lastName")
    public WebElement lastNameBox;

    @FindBy(name = "email")
    public WebElement emailBox;

    @FindBy(name = "langKey")
    public List<WebElement> languageDropDown;


    @FindBy(xpath = "//button[@class='btn btn-primary']")
    public WebElement saveButton;


    @FindBy(xpath = "//div[@class='invalid-feedback']")
    public List<WebElement> invalidEmailToAlert;

    public void getAlert() {



        List<String> invalidEmailToalertList= BrowserUtils.getElementsText(invalidEmailToAlert);
        if(invalidEmailToalertList.size()!=0){
            System.out.println(invalidEmailToalertList.get(0));
        }else{
            System.out.println("invalidEmailListSize:"+invalidEmailToalertList.size());
        }


}

    public void singOut() throws Exception {



        clickAndSelectDropDownItemUnderAccountMenuIcon("Sign Out");
    }

    public  void  languageChooses(){
        List<String> lanhuageText=BrowserUtils.getElementsText(languageDropDown);
        System.out.println("language Chooses:"+lanhuageText);

    }

}