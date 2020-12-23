package com.gmibank.pages;

import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.Driver;
import com.gmibank.utilities.StringUtilities;
import io.cucumber.java.it.Ma;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TablePage extends BasePage {
/***
 * Bu class; tablo iceren sayfalarda ortak olan locator ve methodlari iceriyor. Eger tablo iceren bir sayfada calisiyorsan
 * bu class i extend ederek burada olan her seyi kullanabilirsin. Bu class ayni zmanda BAsPage'den extend
 * edildiginden dolayi BasePage elemanlarina da bu syfa uzerinden erisebilirisn. Iki tane extend etme hakkin
 * yok zaten class icin. Bu class'a tablo iceren sayfalarda ortak olmayan bir sye ekleme.
 * Halihazrida customers, users ve accounts sayfalari bundan extend edilerek olusturuldu zaten. eger isin onlarla
 * ise direkt onlar uzerinde calisabilirsn.
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

    @FindBy(css = ".btn.btn-info.btn-sm")
    public List<WebElement> viewButtonList;

    @FindBy(css = ".btn.btn-primary.btn-sm")
    public List<WebElement> editButtonList;

    @FindBy(css = ".btn.btn-danger.btn-sm")
    public List<WebElement> deleteButtonList;

    @FindBy(css = ".modal-title")
    public WebElement deleteConfirmationBox;

    @FindBy(css = ".modal-body")
    public WebElement textElementInDeleteConfirmationBox;

    @FindBy(xpath = "//*[@class='modal-footer']/button[2]")
    public WebElement deleteButtonInConfirmationBox;


    public boolean isDeleteConfirmationBoxDisplayed() {
        BrowserUtils.waitForVisibility(deleteConfirmationBox, 5);
        if (textElementInDeleteConfirmationBox.getText().contains("Are you sure you want to delete")) {
            return true;
        }
        return false;
    }


    public void deleteUser() {
        if (isDeleteConfirmationBoxDisplayed()) {
            deleteButtonInConfirmationBox.click();
        } else {
            System.out.println("confirmation box is not appeared!!!");
        }
    }


    /**
     * sayfada yapilan islmein basarili olup olmaidigni donen toasty alert mesaji
     */
    @FindBy(xpath = "//div[@role='alert']")
    public WebElement toastAlert;

    public int getButtonCountInTheCurrentPage(String buttonType) {
        switch (buttonType) {
            case "view":
                return viewButtonList.size();
            case "edit":
                return editButtonList.size();
            case "delete":
                return deleteButtonList.size();
            default:
                System.out.println("there is no such a button with this name!!!");
                break;
        }
        return -1;
    }

    /**
     * bir islem sonrasinda islemin basarili olup olmaidigni donen toasty mesaji, belirtilen content
     * iceriyor mu.
     *
     * @param message
     * @return
     */
    public boolean doesContainSuchAMessageInsideAlert(String message) {
        BrowserUtils.waitForVisibility(toastAlert, 5);
        return toastAlert.getText().contains(message);
    }


    /*******************************************************************'
     Tablo icinde eger bir degere gore bir satirdaki bilgiyi bulmak istiyorsan asagidaki method icine
     bilgiyi aradigin kolon ismini, aradigin degeri ve de satiri buldugunda view- edit-delete butonlarindan
     hangisine tiklamak istedigini vermen gerekiyor.
     buton ismini kucuk harflerle "view" orneginde oldugu gibi verebilirisn.
     Kolon ismini sitedeki sayfadaki yazildigi sekilde vermelisin
     Degeri de sayfada gorundugu sekilde vermelisin.

     */
    public void clickGivenButtonForWantedColumnAndValue(String column, String value, String buttonType) {
        WebElement element = locateWantedCellWithGivenColumnAndValue(column, value);
        WebElement button = getOneOfTheTripleButtonWithGivenType(value, buttonType);
        BrowserUtils.executeJScommand("window.scrollBy(0,-document.body.scrollHeight)");
        BrowserUtils.waitForVisibility(button, 5);
        BrowserUtils.hover(button);
        button.click();
        BrowserUtils.executeJScommand("window.scrollBy(0,-document.body.scrollHeight)");
    }

    /******************************************************************* '
     Methoda verdigin kolondaki butun degerleri alir.

     */
    public List<WebElement> getAllItemsInTheGivenColumn(String column) {
        String locator = "//tr//td[" + getIndexNumberOfGivenColumnName(column) + "]";
        return Driver.getDriver().findElements(By.xpath(locator));
    }


    /*****
     Sayfalardaki kolon siralamalari farkli. Bu method ile verilen kolon isminin tablo icindeki index numar-
     rasi alinir. Kolon ismi sayfadaki gorundugu gibi olmali
     */
    public int getIndexNumberOfGivenColumnName(String columnName) {
        if (columnName.equals("activation_button")) {
            return 4;
        } else if (columnName.equals("view_edit_delete_button")) {
            return columnList.size();
        } else {
            int index = calculateColumnIndex(columnName, BrowserUtils.getElementsText(columnList));
            if (index != -1) {
                System.out.println("found index number in the columns = " + index);
                return index;
            }
            System.out.println("There should be a problem related with column index number!!!");
        }
        return 0;
    }

    /****
     * Yukaridaki methoda yaridmci method
     * @param wantedColumnName
     * @param columnNameList
     * @return
     */
    public int calculateColumnIndex(String wantedColumnName, List<String> columnNameList) {
        for (int i = 0; i < columnNameList.size(); i++) {
            if (columnNameList.get(i).equals(wantedColumnName)) {
                return i + 1;
            }
        }
        return -1;
    }

    /*******************************************************************DUZENLENDI'
     Verilen kolondaki verilen degerin locate edilmesi icin kullanilabilir.
     Mesela "Email" kolonundaki "mrecihanbey@gmail.com" degerini bul diyebilirsin.
     */
    public WebElement locateWantedCellWithGivenColumnAndValue(String column, String value) {
        List<WebElement> columnElementsOnCurrentPage = getAllItemsInTheGivenColumn(column);
        //System.out.println(BrowserUtils.getElementsText(columnElementsOnCurrentPage));
        for (WebElement element : columnElementsOnCurrentPage) {
            if (element.getText().equals(value)) {
                System.out.println("element.getText() = " + element.getText());
                return element;
            }
        }
        return null;
    }

    /**
     * Tablodaki kolon isimlerini alir.
     *
     * @return
     */
    public List<String> getColumnNameList() {
        return BrowserUtils.getElementsText(columnList);
    }

    /**
     * Verilen kolon isimleri listesinde verilen kolon isminin olup olmadigini doner.
     *
     * @param columnName
     * @param columnNameList
     * @return
     */
    public boolean doesGivenColumnExist(String columnName, List<String> columnNameList) {
        if (columnNameList.contains(columnName)) {
            return true;
        }
        return false;
    }

    /***
     * Verilen kolon ismi listesinin tabloda olup olmaidigini doner
     * @param givenColumnNameList
     * @return
     */
    public boolean doesGivenColumnListExist(List<String> givenColumnNameList) {
        List<String> locatedColumnNameList = getColumnNameList();
        for (String columnName : givenColumnNameList) {
            if (!doesGivenColumnExist(columnName, locatedColumnNameList)) {
                return false;
            }
        }
        return true;
    }

    /**
     * VErilen web elementin textini doner.
     *
     * @param element
     * @return
     */
    public String getElementText(WebElement element) {
        return element.getText();
    }

    /******************************************************************* DUZENLENDI'

     sayfalardaki create butonuna tiklar.

     */
    public void clickCreateButton() {
        BrowserUtils.waitForClickablility(createButton, 5);
        createButton.click();
    }


    /*******************************************************************
     Bu method her bir satridaki view-edit-delete butonlarini locate etmek icin dinamik
     bir locator ureterek butonu bulur. Ilgili butona gitmek icin o satirdaki bir hcure degerini
     ve de locate etmek istedigin buton ismini asagidaki gibi kucuk harf olacak sekilde ver.
     Bu method sana webelement doner. Yukarida direkt olarak buradan aldigi webelemente
     tiklama methodu var. Eger isine yararsa ona bakabilriisn.

     edit-view-delete
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

    /**
     * bu methodu; bir satirdaki herhangi bir huceredeki degeri bilip (degerin sayfada unique olmasi gerekiyor)
     * satiraki butun bilgileri cekmek istediginde kullanabilirsin. Daha sonra bu satirdaki bilgilerle
     * kolon isimlerini kullanrak map uretebilirsin baska bir method ile.
     *
     * @param cellValue
     * @return
     */
    public List<String> getAllInformationFromOneLineUsingGivenCellValue(String cellValue) {
        //    //*[contains(text(),'Tako')]/ancestor::tr  >>>> one lcoator sample

        String locator = "//*[contains(text(),'" + cellValue + "')]/ancestor::tr/td";
        List<WebElement> cellElementList = Driver.getDriver().findElements(By.xpath(locator));
        return BrowserUtils.getElementsText(cellElementList);
    }

    public Map<String, String> generateMapForOneLineKeyValuePairsUsingGivenCellValue(String cellValue) {
        Map<String, String> columnInfoMap = new HashMap<>();
        List<String> values = getAllInformationFromOneLineUsingGivenCellValue(cellValue);
        List<String> columns = getColumnNameList();
        for (int i = 0; i < columns.size(); i++) {
            columnInfoMap.put(columns.get(i), values.get(i));
        }
        return columnInfoMap;
    }


}
