package com.lt.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lt.base.TestBaseLt;
import com.lt.containers.AccountPageContainer;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class AccountsPageSteps extends TestBaseLt {

	private LoginPage loginPage = new LoginPage();
	public WebDriver driver = getDriver();
	private AccountPageContainer accountPageContainer = PageFactory.initElements(driver, AccountPageContainer.class);
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountsPageSteps.class.getName());
	
	@Given("user has already logged in to application")
	public void user_has_already_logged_in_to_application(DataTable credTable) {

		List<Map<String, String>> credList = credTable.asMaps();
		String userName = credList.get(0).get("username");
		String password = credList.get(0).get("password");

		driver.get(getPropertyValue().getProperty("application.url"));
		loginPage.doLogin(userName, password);

	}

	@Given("user is on Accounts page")
	public void user_is_on_accounts_page() {
		String title = driver.getTitle();
		LOGGER.info("Accounts Page title is: " + title);
	}

	@Then("user gets accounts section")
	public void user_gets_accounts_section(DataTable sectionsTable) {

		List<String> expAccountSectionsList = sectionsTable.asList();

		LOGGER.info("Expected accounts section list: " + expAccountSectionsList);

		List<String> actualAccountSectionsList = new ArrayList<>();
		List<WebElement> accountsHeaderList = accountPageContainer.accountSections;

		for (WebElement e : accountsHeaderList) {
			String text = e.getText();
			LOGGER.info(text);
			actualAccountSectionsList.add(text);
		}

		LOGGER.info("Actual accounts section list: " + actualAccountSectionsList);

		Assert.assertTrue(expAccountSectionsList.containsAll(actualAccountSectionsList));

	}

	@Then("accounts section count should be {int}")
	public void accounts_section_count_should_be(Integer expectedSectionCount) {
		Assert.assertTrue(accountPageContainer.accountSections.size() == expectedSectionCount);
	}

}
