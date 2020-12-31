package com.lt.containers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePageContainer {

	@FindBy(how = How.XPATH, using = "//span[contains(text(),' Home ')]")
	public static WebElement homeButton;

	@FindBy(how = How.XPATH, using = "//li[@class='menu_Trains']")
	public static WebElement trainTab;
	
	@FindBy(how = How.ID, using = "fromCity")
	public static WebElement trainFrom;
	
	@FindBy(how = How.ID, using = "toCity")
	public static WebElement trainTo;
	
	@FindBy(how = How.XPATH, using = "//li[@class='menu_Hotels']")
	public static WebElement hotelsTab;
	
	@FindBy(how = How.ID, using = "city")
	public static WebElement hotelCity;

}
