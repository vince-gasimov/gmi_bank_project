package com.gmibank.stepDefinitions.uiStepDefs;

import com.gmibank.pages.RegistrationPage;
import com.gmibank.utilities.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Hooks {
    @Before
    public void setup(){
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Driver.getDriver().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

    }

    @After
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }

        BrowserUtils.waitFor(1);
        Driver.closeDriver();
    }

    /**
     * bu before methodu sadece @activation olan senaryolarda calisir. Bu senaryolar icin
     * excel'den veri cekilmesi gerekir. Bu methodun fonksiyonu excel icinde cekilecek veriyi hazirlamaktir
     * eger veri yoksa yenilerini ekler, varsa degisiklik yapmadan devam eder
     */
    @Before ("@activation")
    public void setupActivation(){
        new RegistrationPage().makeSureThereExistRegistrantInExcel();
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }

    @Before ("@db")
    public void setupDatabase(){
        DatabaseUtility.createConnection();
    }

    @After ("@db")
    public void closeDatabase(){
        DatabaseUtility.closeConnection();
    }

}
