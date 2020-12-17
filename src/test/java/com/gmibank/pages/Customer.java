package com.gmibank.pages;

import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.Driver;
import com.gmibank.utilities.StringUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Customer extends BasePage {


    @FindBy(id = "jh-create-entity")
    public WebElement createNewCustomerButton;

    @FindBy(xpath = "//table//th")
    public List<WebElement> columnList;

    @FindBy(css = ".info.jhi-item-count")
    public WebElement pageNumberInfo;

    @FindBy(xpath = "//ul[@class='pagination']//li")
    public List<WebElement> pageLinkButtons;


    public void clickGivenButtonForWantedEmail(String email, String buttonName){
        WebElement emailElement = locateWantedElementWithEmail(email);
        WebElement button = getWebElementWithGivenButtonType(email, buttonName);
        BrowserUtils.executeJScommand("window.scrollBy(0,-document.body.scrollHeight)");
        BrowserUtils.waitForVisibility(button,5);
        BrowserUtils.hover(button);
        button.click();
        BrowserUtils.executeJScommand("window.scrollBy(0,-document.body.scrollHeight)");
    }

    public List<WebElement> getAllEmailsInThePage(){
        return Driver.getDriver().findElements(By.xpath("//tr//td[5]"));
    }

    public WebElement locateWantedElementWithEmail(String email){
        List<WebElement> emailElementsOnCurrentPage = getAllEmailsInThePage();
        boolean devam = true;
        while (devam){
            for (WebElement emailElement : emailElementsOnCurrentPage) {
                if (emailElement.getText().equals(email)){
                    return emailElement;
                }
            }
            devam = moveToNextPage();
            BrowserUtils.waitFor(2);
        }
        return null;
    }



    //sonraki sayfaya gecmek icin kullanilacak buton
    public WebElement getNextPageButton(){
        int totalNumberOfButtons = pageLinkButtons.size();
        return pageLinkButtons.get(totalNumberOfButtons - 2);
    }


    //sonraki sayfaya gecmek icin kullanilacak olan method
    public boolean moveToNextPage(){
        WebElement nextPageButton = getNextPageButton();
        if (!nextPageButton.getAttribute("class").equals("page-item disabled")){
            nextPageButton.click();
            return true;
        }
        return false; //gidecek yer kalmadi
    }

    //cok gerek kalmadi
    public int getTotalNumberOfCustomers(){
        String pageInfoText = getElementText(pageNumberInfo);
        String totalNumberOfCustomer = StringUtilities.getInnerStringBetweenTwoText(pageInfoText, "of", "items.");
        return Integer.parseInt(totalNumberOfCustomer);
    }


    //cok gerek kalmadi, baska bir yerden daha kolay aliniyor
    public int getTotalNumberOfPages(){
        int totalCustomer = getTotalNumberOfCustomers();
        int result = totalCustomer / 20;
        if(totalCustomer % 20 != 0){
            result++;
        }
        return result;
    }

    public String getElementText(WebElement element){
        return pageNumberInfo.getText();
    }

    public List<String> getColumnNameList() {
        return BrowserUtils.getElementsText(columnList);
    }

    public boolean doesGivenColumnExist(String columnName, List<String> columnNameList) {
        if (columnNameList.contains(columnName)) {
            return true;
        }
        return false;
    }

    public boolean doesGivenColumnListExist(List<String> givenColumnNameList) {
        List<String> locatedColumnNameList = getColumnNameList();
        for (String columnName : givenColumnNameList) {
            if (!doesGivenColumnExist(columnName, locatedColumnNameList)) {
                return false;
            }
        }
        return true;
    }


    public void clickCreateNewCustomerButton() {
        BrowserUtils.waitForClickablility(createNewCustomerButton, 5);
        createNewCustomerButton.click();
    }

    public WebElement getWebElementWithGivenButtonType(String email, String buttonName) {
        String buttonClassAttribute = null;
        switch (buttonName.toLowerCase()) {
            case "view":
                buttonClassAttribute = "btn btn-info btn-sm";
                break;
            case "edit":
                buttonClassAttribute = "btn btn-primary btn-sm";
                break;
            case "delete":
                buttonClassAttribute = "btn btn-danger btn-sm";
                break;
        }
        /*
        locator based on email and button type //td[text()='07-12-20-13-54@US012.com']/following-sibling::td//a[@class='btn btn-info btn-sm']
         */
        String locator = "//td[text()='" + email + "']/following-sibling::td//a[@class='" + buttonClassAttribute + "']";
        return Driver.getDriver().findElement(By.xpath(locator));
    }


}
