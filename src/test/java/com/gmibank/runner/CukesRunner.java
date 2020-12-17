package com.gmibank.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "json:target/cucumber.json",
                "html:target/default-html-reports",
                "junit:target/xml-report/cucumber.xml"
        },
        features = "src/test/resources/features",
        glue = "com/gmibank/stepDefinitions",
        tags = "@us1401",
        dryRun = true
)
public class CukesRunner {
}
