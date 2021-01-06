package com.lt.utilities;

import java.net.MalformedURLException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentTest;
import com.lt.base.TestBase;
import com.lt.tests.LoginTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class ApplicationHooks extends TestBase {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationHooks.class.getName());
	public ExtentTest extentLogger;

	@Before
	public void config(io.cucumber.java.Scenario scenarios) {
	try {
			LOGGER.info("Before scenario...");
			setupTest();
			setupScenario(scenarios);
			scenario.log("Executing tests on " + browser.toString() + " browser...");
		} catch (MalformedURLException e) {
			LOGGER.debug("Exception : " + e);
		}
	}

	@After
	public void tearDown(io.cucumber.java.Scenario scenario) {
		LOGGER.info("Scenario Status >>>>>>>>>  " + scenario.getStatus());
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] scourcePath = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(scourcePath, "image/png", screenshotName);
		}		
		LOGGER.info("After scenario executed successfully...");
		getDriver().close();
	}

}
