package com.lt.containers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginContainer {

	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "QA Testing")
	public WebElement qaLink;

	@FindBy(how = How.XPATH, using = "(//a[text()='Software Testing'])[1]")
	public WebElement softwareTestLink;

	@FindBy(how = How.XPATH, using = "(//a[text()='Test Cases'])[1]")
	public WebElement testCasesLink;

	@FindBy(how = How.XPATH, using = "//time[@class='updated']")
	public WebElement updatedValueLabel;

	@FindBy(how = How.XPATH, using = "//*[@data-name='closeclose']")
	public WebElement popUpClose;
}
