package com.gmibank.pages;

import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.Driver;
import com.gmibank.utilities.StringUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class TablePage extends BasePage{

    /******************************************************************* DUZENLENDI'
        bu sayfaya ozel olabilir, fakat locator ayni. Eger genel yapilirsa createButton gibi
    genel bir isim verilebilir.

     */
    @FindBy(id = "jh-create-entity")
    public WebElement createButton;

    @FindBy(xpath = "//table//th")
    public List<WebElement> columnList;

    @FindBy(css = ".info.jhi-item-count")
    public WebElement pageNumberInfo;

    @FindBy(xpath = "//ul[@class='pagination']//li")
    public List<WebElement> pageLinkButtons;

    /*******************************************************************'
     email demek yerine kolon ismi denilip genelleme yapilabilir. Dinamik bir yapi kazandirilmali

     */

    public void clickGivenButtonForWantedEmail(String email, String buttonName){
        WebElement emailElement = locateWantedElementWithEmail(email);
        WebElement button = getWebElementWithGivenButtonType(email, buttonName);
        BrowserUtils.executeJScommand("window.scrollBy(0,-document.body.scrollHeight)");
        BrowserUtils.waitForVisibility(button,5);
        BrowserUtils.hover(button);
        button.click();
        BrowserUtils.executeJScommand("window.scrollBy(0,-document.body.scrollHeight)");
    }

    /******************************************************************* DUZENLENDI'
     email demek yerine kolon ismi denilip genelleme yapilabilir. Dinamik bir yapi kazandirilmali

     */
    public List<WebElement> getAllItemsInTheGivenColumn(String column){
        String locator = "//tr//td[" + getIndexNumberOfGivenColumnName(column) + "]";
        return Driver.getDriver().findElements(By.xpath(locator));
    }


    /*****
    kolon ismini dinamik yapmak icin verilen kolon index numarasini bulacak bir method yazacaim DUZENLENDI

     */
    public int getIndexNumberOfGivenColumnName(String columnName){
        if (columnName.equals("activation_button")){
            return 4;
        }else if(columnName.equals("view_edit_delete_button")){
            return columnList.size();
        }else{
            int index = calculateColumnIndex(columnName, BrowserUtils.getElementsText(columnList));
            if (index != -1){
                System.out.println("found index number in the columns = " + index);
                return index;
            }
            System.out.println("There should be a problem related with column index number!!!");
        }
        return 0;
    }

    public int calculateColumnIndex(String wantedColumnName, List<String> columnNameList){
        for (int i = 0; i < columnNameList.size(); i++) {
             if (columnNameList.get(i).equals(wantedColumnName)){
                 return i + 1;
             }
        }
        return -1;
    }
    /*******************************************************************DUZENLENDI'
     email demek yerine kolon ismi denilip genelleme yapilabilir. Dinamik bir yapi kazandirilmali

     */
    public WebElement locateWantedElementWithEmail(String email){
        boolean devam = true;
        while (devam){
            List<WebElement> columnElementsOnCurrentPage = getAllItemsInTheGivenColumn("Email");
            System.out.println(BrowserUtils.getElementsText(columnElementsOnCurrentPage));
            for (WebElement emailElement : columnElementsOnCurrentPage) {
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

    /****************************************************************** DUZENLENDI'
    burada customer yerine item kullanilabilir genel olarak tanimlayabilmek icin, sonucta
    sayfa saysi bulmak icin kullanilacak
     */
    //cok gerek kalmadi
    public int getTotalNumberOfItems(){
        String pageInfoText = getElementText(pageNumberInfo);
        String totalNumberOfItems = StringUtilities.getInnerStringBetweenTwoText(pageInfoText, "of", "items.");
        return Integer.parseInt(totalNumberOfItems);
    }

    /******************************************************************* DUZENLENDI'
     icindeki degisken isimleri ile oynanabilir

     */
    //cok gerek kalmadi, baska bir yerden daha kolay aliniyor
    public int getTotalNumberOfPages(){
        int totalItems = getTotalNumberOfItems();
        int result = totalItems / 20;
        if(totalItems % 20 != 0){
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

    /******************************************************************* DUZENLENDI'
     yukaridaki create butonu locator in ismi degistirildiginde burasi da degismeli
     o zaman hata kalmaz
     */
    public void clickCreateNewCustomerButton() {
        BrowserUtils.waitForClickablility(createButton, 5);
        createButton.click();
    }


    /******************************************************************* DUZENLENDI KULLNAIRKEN LOCATOR DUZENLEMESI GEREKIYOR'
     burada dikkatli olmak lazim. Birlestirilmesi gereken locator parcalari degisiyor. Sayfaya has locator
     degiskeni olusuturup method icine pass edebilrisin genelleme yapmak icin. Boylece implementation ayni kalir
     parametre olarak email yerine direkt  locator koyabilirsin.
     */
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
