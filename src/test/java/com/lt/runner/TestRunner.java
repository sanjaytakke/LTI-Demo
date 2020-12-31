package com.lt.runner;

import org.junit.runner.RunWith;
import com.lt.base.TestBaseLt;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features  = "src/test/resources/Features", glue = { "com.lt.tests", "com.lt.utilities" }, 
		plugin = {"pretty","html:target/report.json","json:target/cucumber.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }, 
		tags = "@ETE")
// )
public class TestRunner extends TestBaseLt {

}
