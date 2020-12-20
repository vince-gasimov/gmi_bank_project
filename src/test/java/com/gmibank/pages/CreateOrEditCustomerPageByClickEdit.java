package com.gmibank.pages;

import com.gmibank.utilities.DummyDataGenerator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/***
customer management sayfasinda edit butonuna tikladiktan sonra bu sayfaya geliyor
 */
public class CreateOrEditCustomerPageByClickEdit extends BasePage{

    @FindBy(id = "tp-customer-id")
    public WebElement id;

    @FindBy(id = "tp-customer-city")
    public WebElement city;

    @FindBy(id = "save-entity")
    public WebElement saveButton;

    public void clickSaveButton(){
        saveButton.click();
    }

    public String getCity(){
        String cityName = city.getAttribute("value");
        System.out.println("cityName = " + cityName);
        return cityName;

    }


    public String generateNewRandomCity(){
        String oldCity = getCity();
        String newCity = null;
        while(true){
            newCity = DummyDataGenerator.faker.address().city();
            if (!newCity.equals(oldCity)){
                break;
            }
        }
        return newCity;
    }

    public void typeCity(String city){
        this.city.click();
        this.city.clear();
        this.city.sendKeys(city);
    }


}
