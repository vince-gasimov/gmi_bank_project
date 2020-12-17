package com.gmibank.pages;

import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.Driver;
import com.gmibank.utilities.StringUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class TablePage extends BasePage{
/***
 * Bu class; icinde user bilgileri giib tablo iceren sayfalarin hepsi icin kullanilabilir. Eger kendi sayfanda
 * burada olmayan bir method veya element varsa ve de butun tablolu sayfalarda ayni sey varsa buraya ekleyebilirsin
 * Fakat butun tablolu sayfalar icinde olmayan bir seyi buraya ekleme.
 *
 * Tablo iceren sayfalarda
 */


    /******************************************************************* '
    Asagidaki webeleemntleri tablo olan sayfalar icin ortak. Ayni locator ile bulunuyor.
     Eger burada olmayan bir webelement varsa onu kendi olustrudugun class icinde ayrica tanimlayabilirsin.
     Buraya; butun tablo iceren sayfalarda ortak olmayan bir sey ekleme. Sadece ortak olanlar olmali

     */
    @FindBy(css = ".jh-create-entity")
    public WebElement createButton;

    @FindBy(xpath = "//table//th")
    public List<WebElement> columnList;

    /*******************************************************************'
     email demek yerine kolon ismi denilip genelleme yapilabilir. Dinamik bir yapi kazandirilmali

     */
    public void clickGivenButtonForWantedColumnAndValue(String column, String value, String buttonType){
        WebElement element = locateWantedCellWithGivenColumnAndValue(column, value);
        WebElement button = getOneOfTheTripleButtonWithGivenType(value, buttonType);
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
    public WebElement locateWantedCellWithGivenColumnAndValue(String column, String value){
            List<WebElement> columnElementsOnCurrentPage = getAllItemsInTheGivenColumn(column);
            //System.out.println(BrowserUtils.getElementsText(columnElementsOnCurrentPage));
            for (WebElement element : columnElementsOnCurrentPage) {
                if (element.getText().equals(value)){
                    System.out.println("element.getText() = " + element.getText());
                    return element;
                }
            }
        return null;
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

    public String getElementText(WebElement element){
        return element.getText();
    }

    /******************************************************************* DUZENLENDI'
     yukaridaki create butonu locator in ismi degistirildiginde burasi da degismeli
     o zaman hata kalmaz
     */
    public void clickCreateButton() {
        BrowserUtils.waitForClickablility(createButton, 5);
        createButton.click();
    }


    /******************************************************************* DUZENLENDI KULLNAIRKEN LOCATOR DUZENLEMESI GEREKIYOR'
     burada dikkatli olmak lazim. Birlestirilmesi gereken locator parcalari degisiyor. Sayfaya has locator
     degiskeni olusuturup method icine pass edebilrisin genelleme yapmak icin. Boylece implementation ayni kalir
     parametre olarak email yerine direkt  locator koyabilirsin. EDIT-VIEW-DELETE
     */
    public WebElement getOneOfTheTripleButtonWithGivenType(String searchValue, String buttonName) {
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
        String locator = "//td[text()='" + searchValue + "']/following-sibling::td//a[@class='" + buttonClassAttribute + "']";
        return Driver.getDriver().findElement(By.xpath(locator));
    }

}
