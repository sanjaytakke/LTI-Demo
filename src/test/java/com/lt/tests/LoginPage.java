package com.lt.tests;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lt.autotest.handlers.ActionHandler;
import com.lt.autotest.handlers.AssertHandler;
import com.lt.autotest.utils.Config;
import com.lt.base.TestBase;
import com.lt.base.TestBaseLt;
import com.lt.constants.Constants;
import com.lt.containers.LoginPageContainer;
import com.lt.utilities.CommonFunctions;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPage extends TestBase {

	public WebDriver driver = getDriver();
	private static String title;
	private LoginPageContainer loginPageContainer = PageFactory.initElements(driver, LoginPageContainer.class);

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginPage.class.getName());
	public CommonFunctions commonFunctions = new CommonFunctions();

	@Given("^User is on home page$")
	public void user_is_on_Home_Page() {
		String url = "";
		try {
			 url = Config.getPropertyValue("application.url");
			if (null != url) {
				LOGGER.info("URL : "+ url);
				driver.get(url);
			} else {
				LOGGER.error("application url not found. Reverting to default url: " + Constants.DEFAULTURL);
			}
		} catch (Exception e) {
			// Reporter.addScenarioLog("IOException while fetching application url - " + e);
			LOGGER.error("IOException while fetching application url.", e);
		}
	}

	@When("user gets the title of the page")
	public void user_gets_the_title_of_the_page() {
		title = driver.getTitle();
		LOGGER.info("Page title is: " + title);
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String expectedTitleName) {
		Assert.assertTrue(title.contains(expectedTitleName));
		
	}

	@Then("forgot your password link should be displayed")
	public void forgot_your_password_link_should_be_displayed() throws Exception {
		ActionHandler.wait(2);
		//Assert.assertTrue(loginPageContainer.forgotPwdLink.isDisplayed());
		AssertHandler.assertElementPresent(loginPageContainer.forgotPwdLink);
	}

	@When("user enters username {string}")
	public void user_enters_username(String username) {
		ActionHandler.setText(loginPageContainer.emailId, username);
	}

	@When("user enters password {string}")
	public void user_enters_password(String password) {
		ActionHandler.setText(loginPageContainer.password, password);
	}

	@When("user clicks on Login button")
	public void user_clicks_on_login_button() {
		ActionHandler.click(loginPageContainer.signInButton);
	}

	public void doLogin(String userName, String password) {
		LOGGER.info("login with: " + userName + " and " + password);
		user_enters_username(userName);
		user_enters_password(password);
		user_clicks_on_login_button();

	}
}
