package com.lt.containers;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountPageContainer {

	@FindBy(how = How.CSS, using = "div#center_column span")
	public List<WebElement> accountSections;
	

	
}
