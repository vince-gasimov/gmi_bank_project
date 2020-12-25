package com.gmibank.pages;

import com.gmibank.utilities.BrowserUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AccountsPageWithTable extends TablePage{

    /***
     * bu sayfa TablePage sayfasindan extedn edilmisitir. TablePage sayfasi da BasePage'den extend edilmsitir.
     * Dolayisiyla BasePage'de ve TablePage'de ne varsa hepsi buarda da vardir. Bu class ile
     * urettigin nesne ile butun weblementlerine ve de methodlara ulasabilirisn. Eger aradigin
     * tablePage class'inda yoksa direkt buraya (AccountsPageWithTable class'ina) kendin ekleyeebilirsin.
     */

    public boolean verifyGivenAccountExist(String description){
        //BrowserUtils.waitForVisibility(table,15);
        BrowserUtils.waitForPresenceOfElement(By.xpath("//table"), 15);
        BrowserUtils.scrollDownWithActionClass();
        WebElement createdAccount = locateWantedCellWithGivenColumnAndValue("Description", description);
        if (createdAccount.getText().equals(description)){
            System.out.println("createdAccount.getText() = " + createdAccount.getText());
            return true;
        }
        System.out.println("account ile ilgili problem var!!!");
        return false;
    }

}
