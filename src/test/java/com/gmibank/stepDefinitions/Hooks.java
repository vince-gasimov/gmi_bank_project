package com.gmibank.stepDefinitions;

import com.gmibank.utilities.BrowserUtils;
import com.gmibank.utilities.ConfigurationReader;
import com.gmibank.utilities.Driver;
import com.gmibank.utilities.ExcelUtilities;
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
        Driver.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
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

}
