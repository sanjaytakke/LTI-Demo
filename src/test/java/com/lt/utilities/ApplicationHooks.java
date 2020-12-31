package com.lt.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lt.base.TestBaseLt;
import com.lt.tests.LoginTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class ApplicationHooks extends TestBaseLt {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginTest.class.getName());
	private WebDriver driver;

	@Before
	public void config() {
		LOGGER.info("Before scenario executed successfully...");
		// extentTestConfig();
		// createTest("Test");
		getPropertyValue();
		initDriver();
		driver=getDriver();
	}

	/*
	 * @After(order = 1) public void quitBrowser() {
	 * LOGGER.info("After scenario executed successfully..."); getDriver().close();
	 * }
	 */

	@After
	public void tearDown(io.cucumber.java.Scenario scenario) {
		WebDriver driver1 = getDriver();
		LOGGER.info("Scenario Status >>>>>>>>>  " + scenario.getStatus());
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] scourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(scourcePath, "image/png", screenshotName);
		}
		LOGGER.info("After scenario executed successfully...");
		getDriver().close();
	}

}
