package com.gmibank.pages;

import com.gmibank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * manage customer altindaki user'larin view sayfasini temsil ediyor.
 */
public class CustomerInfoPageForOneUser extends BasePage {

    @FindBy(id = "firstName")
    public WebElement firstNameHeader;

    @FindBy(id = "email")
    public WebElement emailHeader;

    @FindBy(css = ".btn.btn-primary")
    public WebElement editButton;


    /**
     * sayfadaki textler altindaki degerleri okumak icin kullanilir. Mesela firstName altindaki degeri.
     * method icine verilecek olan parametre, istedigimiz degerin hemen ustindeki basligin id'si olmali
     * sayfa icinden bulunup o sekilde verilmeli.
     * country, state gibi bazi key'ler icn id eklenmemis. Onlar icin bu methodu kullanma
     * @param idOfAboveKey
     * @return
     */
    public String getGivenTextValue(String idOfAboveKey) {
        //   //*[@id='ssn']/ancestor::dt/following-sibling::dd
        String locator = "//*[@id='" + idOfAboveKey + "']/ancestor::dt/following-sibling::dd";
        return Driver.getDriver().findElement(By.xpath(locator)).getText();
    }


}
