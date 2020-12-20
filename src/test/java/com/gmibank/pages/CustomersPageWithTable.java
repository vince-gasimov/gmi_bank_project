package com.gmibank.pages;


import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.RandomStringGenerator;
import com.gmibank.utilities.StringUtilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class CustomersPageWithTable extends TablePage{
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
     * bir user silinmek istediginde onay isteniyor. Bu onay kutucugunu temsil eder.
     */
    @FindBy(id = "gmibankfrontendApp.tPCustomer.delete.question")
    public WebElement deleteConfirmationBox;

    @FindBy(id = "jhi-confirm-delete-tPCustomer")
    public WebElement deleteButtonOnConfirmationBox;



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
        boolean devam = true;
        while (devam){
            List<WebElement> columnElementsOnCurrentPage = getAllItemsInTheGivenColumn(column);
            System.out.println(BrowserUtils.getElementsText(columnElementsOnCurrentPage));
            for (WebElement emailElement : columnElementsOnCurrentPage) {
                if (emailElement.getText().equals(value)){
                    return emailElement;
                }
            }
            devam = moveToNextPage();
            BrowserUtils.waitFor(2);
        }
        return null;
    }

    public String getTextOfWarning(){
        BrowserUtils.waitForVisibility(deleteConfirmationBox,5);
        System.out.println(deleteConfirmationBox.getText());
        return deleteConfirmationBox.getText();
    }

    public void clickDeleteOnConfirmationBox(){
        BrowserUtils.waitForVisibility(deleteButtonOnConfirmationBox,5);
        System.out.println(deleteButtonOnConfirmationBox.getText());
        deleteButtonOnConfirmationBox.click();
    }



    //sonraki sayfaya gecmek icin kullanilacak butonu doner. Asagida direk sayfa degistirme methodu var.
    public WebElement getNextPageButton(){
        int totalNumberOfButtons = pageLinkButtons.size();
        return pageLinkButtons.get(totalNumberOfButtons - 2);
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

    public String getOneRandomEmailFromCurrentPage(){
        Random random = new Random();
        int maxNumber = getAllItemsInTheGivenColumn(columnList.get(1).getText()).size() - 2;
        int randomNumber = random.nextInt(maxNumber);
        return getAllItemsInTheGivenColumn("Email").get(randomNumber).getText();
    }




}
