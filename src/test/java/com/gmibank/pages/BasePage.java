package com.gmibank.pages;

import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    //ust menu cubugundaki butun sayfa isimleri (Home, Loan, Services vs)
    @FindBy(css = "nav-item")
    public List<WebElement> navigateItems;


    //sag ust kosedeki kullanici ikonu
    @FindBy(id = "account-menu")
    public WebElement accountMenuIcon;

    //sag ust kosedeki kullanici ikonu tiklaninca acilan drop down
    @FindBy(css = "[id='account-menu'] a[class='dropdown-item']")
    public List<WebElement> accountIconDropDownItems;


    //sayfabasligi(hersayfadayok.registration,accountgibibirkacsayfadavar
    @FindBy(xpath = "//div[@class='view-routes']//h2")
    public WebElement pageHeader;

    //eger sayfa basligi varsa, sayfa basligini doner
    public String getPageHeader() {
        return pageHeader.getText();
    }

    @FindBy(css = "li[id='entity-menu']>a")
    public WebElement myOperationsNavItem;

    @FindBy(css = "[id='entity-menu'] a[class='dropdown-item']")
    public List<WebElement> myOperationsDropDownItems;


    /*
    BasePage icindeki Home, Loans, About Us, Blog, About ve Contact sayfalarinin expected URL adreslerini
    doner. Fakat sig in yaptiktan sonra gelen My Operations ve User sayfalari icin baska method kullanilmali.
     */
    public String getPageUrl(String pageName) {
        String pageUrl = null;

        switch (pageName.toLowerCase()) {
            case "home":
                pageUrl = "https://gmibank.com/";
                break;
            case "loans":
                pageUrl = "https://gmibank.com/loan";
                break;
            case "about us":
                pageUrl = "https://gmibank.com/about";
                break;
            case "services":
                pageUrl = "https://gmibank.com/services";
                break;
            case "blog":
                pageUrl = "https://gmibank.com/blog";
                break;
            case "about":
                pageUrl = "https://gmibank.com/about";
                break;
            case "contact":
                pageUrl = "https://gmibank.com/contact";
                break;
            default:
                break;
        }
        return pageUrl;
    }


    //sag ustteki kullanici ikonuna tiklama
    public void clickOnAccountMenuIcon() {
        BrowserUtils.waitForClickablility(accountMenuIcon, 10);
        accountMenuIcon.click();
    }

    //sag ustteki kullanici ikonuna tiklama ve icinden bir option secme
    public void clickAndSelectDropDownItemUnderAccountMenuIcon(String dropDownItemName) throws Exception {
        clickOnAccountMenuIcon();
        BrowserUtils.waitForVisibility(accountIconDropDownItems.get(0), 5);
        List<String> accountIconDropDownItemsTextList = BrowserUtils.getElementsText(accountIconDropDownItems);
        for (int i = 0; i < accountIconDropDownItems.size(); i++) {
            if (accountIconDropDownItemsTextList.get(i).equalsIgnoreCase(dropDownItemName)) {
                accountIconDropDownItems.get(i).click();
                return;
            }
        }
        throw new Exception("cannot find dropdown item");
    }


    public void clickMyOperationsNavItem() {
        BrowserUtils.waitForClickablility(myOperationsNavItem, 10);
        myOperationsNavItem.click();
    }

    //sag ustteki kullanici ikonuna tiklama ve icinden bir option secme
    public void clickAndSelectDropDownItemUnderMyOperationsNavItem(String dropDownItemName) throws Exception {
        clickMyOperationsNavItem();
        BrowserUtils.waitForVisibility(myOperationsDropDownItems.get(0), 5);
        List<String> myOperationsDropDownItemsTextList = BrowserUtils.getElementsText(myOperationsDropDownItems);
        for (int i = 0; i < myOperationsDropDownItems.size(); i++) {
            if (myOperationsDropDownItemsTextList.get(i).equalsIgnoreCase(dropDownItemName)) {
                myOperationsDropDownItems.get(i).click();
                return;
            }
        }
        throw new Exception("cannot find dropdown item");
    }

    //sisteme giris yapilmissa user name'i doner. Eger giris yapilmamissa null doner
    public String getUserNameBesideAccountMenuIcon() {
        return accountMenuIcon.getText();
    }


    public void singOut() throws Exception {
        clickAndSelectDropDownItemUnderAccountMenuIcon("Sign Out");
    }


}
