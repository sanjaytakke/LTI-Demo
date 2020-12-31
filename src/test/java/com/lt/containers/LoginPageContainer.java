package com.lt.containers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPageContainer {

	@FindBy(how = How.LINK_TEXT, using = "Forgot your password?")
	public WebElement forgotPwdLink;
	
	@FindBy(how = How.ID, using = "email")
	public WebElement emailId;

	@FindBy(how = How.ID, using = "passwd")
	public WebElement password;

	@FindBy(how = How.ID, using = "SubmitLogin")
	public WebElement signInButton;

	@FindBy(how = How.XPATH, using = "//*[@data-name='closeclose']")
	public WebElement popUpClose;
}
