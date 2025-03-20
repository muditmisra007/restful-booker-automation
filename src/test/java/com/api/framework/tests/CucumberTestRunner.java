package com.api.framework.tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",   // Path to feature files
        glue = {"com.api.framework.stepdefinitions", "com.api.framework.hooks"}, // Path to step definitions
        plugin = {
//                "pretty",
                "html:target/cucumber-reports.html",
                "json:target/cucumber.json"
        },
        monochrome = true
//        , dryRun = true
)
public class CucumberTestRunner extends AbstractTestNGCucumberTests {
}
