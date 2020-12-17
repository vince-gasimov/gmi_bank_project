package com.gmibank.pages;


import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.StringUtilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CustomersPageWithTable extends TablePage{

    @FindBy(css = ".info.jhi-item-count")
    public WebElement pageNumberInfo;

    @FindBy(xpath = "//ul[@class='pagination']//li")
    public List<WebElement> pageLinkButtons;

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

    public int getTotalNumberOfPages(){
        int totalItems = getTotalNumberOfItems();
        int result = totalItems / 20;
        if(totalItems % 20 != 0){
            result++;
        }
        return result;
    }

}
