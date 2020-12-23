package com.gmibank.pages;

import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.Driver;
import com.gmibank.utilities.StringUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class UsersPageWithTable extends TablePage {
    /***
     * bu sayfa TablePage sayfasindan extedn edilmisitir. TablePage sayfasi da BasePage'den extend edilmsitir.
     * Dolayisiyla BasePage'de ve TablePage'de ne varsa hepsi buarda da vardir. Bu class ile
     * urettigin nesne ile butun weblementlerine ve de methodlara ulasabilirisn. Eger aradigin
     * tablePage class'inda yoksa direkt buraya (AccountsPageWithTable class'ina) kendin ekleyeebilirsin.
     * Asagidaki tanimlanmis methodlar ise TablePage icinde yer almayan, bu class'a has methodlardir.
     * Ayni sekilde bu class'tan urettigin nesne ile hepsine ulasabilrisin.
     */

    /**
     * Sayfanin en altindaki toplam item sayisini belirten yazi
     */
    @FindBy(css = ".info.jhi-item-count")
    public WebElement pageNumberInfo;

    /**
     * Sayfanin en altindaki diger sayfalara gecmek icin kullanabilecegin sayfa degistirme butnlari
     */
    @FindBy(xpath = "//ul[@class='pagination']//li")
    public List<WebElement> pageLinkButtons;

    /**
     * activation button list, both activated and deactivated
     */
    @FindBy(xpath = "//span[contains(text(),'ctivated')]/..")
    public List<WebElement> activationButtonList;




    /**
     * TablePage icinde tanimlanmis olan ayni isimli method override edilmistir. Bu sayfada arama yapmak istiyorsan
     * En iyi cozum email adresi gorunuor. Gitmet istedigin satirdaki email adresini asagidaki methoda ver.
     * Asagidaki method ile eger uzerinde bulundugun sayfada bulamazsa diger sayfaya gecebilirisn.
     * Yani eger verdigin bilgi 50. sayfda ise tek tek 50 ye kadar butun sayfalarda arama yapar.
     * @param column
     * @param value
     * @return
     */
    @Override
    public WebElement locateWantedCellWithGivenColumnAndValue(String column, String value){
        List<WebElement> columnElementsOnCurrentPage = null;
        if (isWantedElementOnTheLastPage(column, value)){
            columnElementsOnCurrentPage = getAllItemsInTheGivenColumn(column);
            System.out.println(BrowserUtils.getElementsText(columnElementsOnCurrentPage));
            for (WebElement element : columnElementsOnCurrentPage) {
                if (element.getText().equals(value)){
                    BrowserUtils.executeJScommand(element,"arguments[0].scrollIntoView(true);");
                    BrowserUtils.highlight(element);
                    BrowserUtils.waitFor(2);
                    return element;
                }
            }
        }
        moveToFirstPage();
        boolean devam = true;
        while (devam){
            columnElementsOnCurrentPage = getAllItemsInTheGivenColumn(column);
            System.out.println(BrowserUtils.getElementsText(columnElementsOnCurrentPage));
            for (WebElement element : columnElementsOnCurrentPage) {
                if (element.getText().equals(value)){
                    BrowserUtils.executeJScommand(element,"arguments[0].scrollIntoView(true);");
                    BrowserUtils.highlight(element);
                    BrowserUtils.waitFor(2);
                    return element;
                }
            }
            devam = moveToNextPage();
            BrowserUtils.waitFor(2);
        }
        return null;
    }

    public boolean isWantedElementOnTheLastPage(String column, String value){
        moveToLastPage();
        BrowserUtils.waitFor(1);
        List<WebElement> columnElementsOnCurrentPage = getAllItemsInTheGivenColumn(column);
        //System.out.println(BrowserUtils.getElementsText(columnElementsOnCurrentPage));
        for (WebElement emailElement : columnElementsOnCurrentPage) {
            if (emailElement.getText().equals(value)){
                return true;
            }
        }
        return false;
    }


    //sonraki sayfaya gecmek icin kullanilacak butonu doner. Asagida direk sayfa degistirme methodu var.
    public WebElement getNextPageButton(){
        int totalNumberOfButtons = pageLinkButtons.size();
        return pageLinkButtons.get(totalNumberOfButtons - 2);
    }

    //en son sayfaya gecmek icin kullanilacak butonu doner. Asagida direk sayfa degistirme methodu var.
    public WebElement getGoToLastPageButton(){
        int totalNumberOfButtons = pageLinkButtons.size();
        return pageLinkButtons.get(totalNumberOfButtons - 1);
    }

    //ilk sayfaya gecmek icin kullanilacak butonu doner. Asagida direk sayfa degistirme methodu var.
    public WebElement getGoToFirstPageButton(){
        return pageLinkButtons.get(0);
    }


    /**
     sonraki sayfaya gecmek icin kullanilacak olan method
     Bu method ayni zamanda son sayfaya gelip gelmedigini de kontrol eder.
     Eger son sayfaya gelmissen artik calismaz.
     */
    public boolean moveToNextPage(){
        WebElement nextPageButton = getNextPageButton();
        if (!nextPageButton.getAttribute("class").equals("page-item disabled")){
            nextPageButton.click();
            return true;
        }
        return false; //gidecek yer kalmadi
    }

    /**
     ilk sayfaya gecmek icin kullanilacak olan method
     Bu method ayni zamanda son sayfaya gelip gelmedigini de kontrol eder.
     Eger ilk sayfaya gelmissen artik calismaz.
     */

    public boolean moveToFirstPage(){
        WebElement firstPageButton = getGoToFirstPageButton();
        if (!firstPageButton.getAttribute("class").equals("page-item disabled")){
            firstPageButton.click();
            return true;
        }
        return false;
    }

    /**
     Son sayfaya gecmek icin kullanilacak olan method
     Bu method ayni zamanda son sayfaya gelip gelmedigini de kontrol eder.
     Eger son sayfaya gelmissen artik calismaz.
     */
    public boolean moveToLastPage(){
        WebElement lastPageButton = getGoToLastPageButton();
        if (!lastPageButton.getAttribute("class").equals("page-item disabled")){
            lastPageButton.click();
            return true;
        }
        return false;
    }

    /****************************************************************** DUZENLENDI'
     Butun sayfalardaki toplam item sayisini sayfanin altindaki text icinden alir.
     */
    //cok gerek kalmadi
    public int getTotalNumberOfItems(){
        String pageInfoText = getElementText(pageNumberInfo);
        String totalNumberOfItems = StringUtilities.getInnerStringBetweenTwoText(pageInfoText, "of", "items.");
        return Integer.parseInt(totalNumberOfItems);
    }

    //toplamda kac tane sayfa (tablo iceren) oldgunu sayfanin en altindaki yazidan alir.
    public int getTotalNumberOfPages(){
        int totalItems = getTotalNumberOfItems();
        int result = totalItems / 20;
        if(totalItems % 20 != 0){
            result++;
        }
        return result;
    }

    /**
     * Activation-Deactivation butonunu locate eder verilen email'e gore.
     * @param email
     * @return
     */
    public WebElement getActivationButton(String email){
        //     //td[text()='globalcostumer1@gmail.com']/following-sibling::td/button
        String locator = "//td[text()='" + email + "']/following-sibling::td/button";
        return Driver.getDriver().findElement(By.xpath(locator));
    }

    /**
     * verilen email degerine gore locate edilen activation-deactivation butonunun status'unu doner.
     * @param email
     * @return
     */
    public String getActivationStatus(String email){
        WebElement activationButton = getActivationButton(email);
        return activationButton.getText();
    }

    /**
     * activation deactivation butonuna tiklar.
     * @param email
     */
    public void clickAndChangeActivationStatus(String email){
        WebElement activationButton = getActivationButton(email);
        BrowserUtils.waitForClickablility(activationButton,5);
        BrowserUtils.waitFor(1);
        BrowserUtils.clickWithJS(activationButton);
    }

    public String getOneRandomEmailFromCurrentPage(){
        Random random = new Random();
        int maxNumber = getAllItemsInTheGivenColumn(columnList.get(2).getText()).size() - 2;
        int randomNumber = random.nextInt(maxNumber);
        return getAllItemsInTheGivenColumn("Email").get(randomNumber).getText();
    }

    public WebElement getIDElementUsingLoginName(String loginName){
        //    //tr[@id='example2ek']//a[@class='btn btn-link btn-sm']
        String locator = "//tr[@id='" + loginName  + "']//a[@class='btn btn-link btn-sm']";
        return Driver.getDriver().findElement(By.xpath(locator));
    }

}
