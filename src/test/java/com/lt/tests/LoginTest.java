package com.lt.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lt.base.TestBaseLt;
import com.lt.constants.Constants;
import com.lt.containers.HomePageContainer;
import com.lt.containers.LoginContainer;
import com.lt.utilities.CommonFunctions;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.messages.Messages.StepDefinition;

public class LoginTest extends TestBaseLt {

	public WebDriver driver = getDriver();
	private static LoginContainer loginContainer = null;
	HomePageContainer homePageContainer = PageFactory.initElements(driver, HomePageContainer.class);

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginTest.class.getName());
	public CommonFunctions commonFunctions = new CommonFunctions();

	@Given("^User is on home pages$")
	public void user_is_on_Home_Page() {
		String url = getPropertyValue().getProperty("application.url");
		try {
			if (null != url) {
				// Reporter.addStepLog("URL : " + url);
				driver.get(url);
			} else {
				LOGGER.error("application url not found. Reverting to default url: " + Constants.DEFAULTURL);
			}
		} catch (Exception e) {
			// Reporter.addScenarioLog("IOException while fetching application url - " + e);
			LOGGER.error("IOException while fetching application url.", e);
		}
	}

	@Given("^User navigate to train info page$")
	public void User_Navigate_to_train_info_Pages() throws Exception {
		Thread.sleep(2000);
		// Reporter.addStepLog("Click on Train Link");

		// Reporter.addScreenCaptureFromPath(takeScreenshot());
		homePageContainer.trainTab.click();
		System.out.println("Train Info Page");
		Thread.sleep(2000);
		// Reporter.addScreenCaptureFromPath(takeScreenshot());
		// Reporter.addStepLog("Validate Page title");
		System.out.println("Page Title : >>>> " + driver.getTitle());
		assertTrue("User not navigated on train info page",
				driver.getTitle().contains("IRCTC Train Tickets Reservation"));
		Thread.sleep(2000);

	}

	@Given("^Verify default stations name$")
	public void Verify_default_stations_name() throws Exception {
		// Reporter.addStepLog("Verify default stations name");
		// Reporter.addScreenCaptureFromPath(takeScreenshot());

		Assert.assertTrue(false);
		/*
		 * assertTrue(homePageContainer.trainFrom.getAttribute("value").
		 * contains("New Delhi"));
		 * 
		 * assertTrue(homePageContainer.trainTo.getAttribute("value").contains("Kanpur")
		 * );
		 */
	}

	@Given("^User Navigate to hotels page$")
	public void User_Navigate_to_hotels_Page() throws Exception {

		// Reporter.addStepLog("Click on Hotels Link");
		// Reporter.addScreenCaptureFromPath(takeScreenshot());
		// homePageContainer.hotelsTab.click();
		Thread.sleep(2000);
		// Reporter.addScreenCaptureFromPath(takeScreenshot());
		// Reporter.addStepLog("Validate Page title");
		assertTrue("User not navigated on train info page", driver.getTitle().contains("Hotel Booking"));
		Thread.sleep(2000);

	}

	@Given("^Verify default selected city$")
	public void Verify_default_selected_city() throws Exception {
		// Reporter.addStepLog("Verify default selected city");
		// Reporter.addScreenCaptureFromPath(takeScreenshot());

		// assertEquals(homePageContainer.hotelCity.getAttribute("value"), "Goa,
		// India");

	}

}
