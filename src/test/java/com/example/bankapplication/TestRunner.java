package com.example.bankapplication;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="classpath:features",
    //tags =  "@economy or @vip",
    plugin = { "pretty",
        "json:target/cucumber_reports/Cucumber.json",
        "junit:target/cucumber_reports/Cucumber.xml",
        "html:target/cucumber_reports/Cucumber.html" },
    monochrome = true,
snippets = CucumberOptions.SnippetType.CAMELCASE)
public class TestRunner {

}
