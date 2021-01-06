package com.lt.base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.google.inject.PrivateBinder;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBaseLt {

	public static Properties prop = new Properties();
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	private static WebDriver driver;
	private static Logger LOGGER = LoggerFactory.getLogger(TestBaseLt.class.getName());

	public void initDriver() {
		String browserName = System.getenv("BROWSER");
		LOGGER.info("Browser NAme :" + browserName);

		if ("Chrome".equalsIgnoreCase(browserName)) {
			setDriver(createChromeDriver());

		} else if ("IE".equalsIgnoreCase(browserName)) {
			try {
				setDriver(createIEDriver());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("ChromeEmulator".equalsIgnoreCase(browserName)) {
			setDriver(ChromeEmulator());

		} else if ("Edge".equalsIgnoreCase(browserName)) {
			setDriver(crateEdgeDriver());

		} else {
			LOGGER.info("Opening Default Chrome Browser - User Passed " + browserName);
			setDriver(createChromeDriver());
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public ChromeDriver createChromeDriver() {
		try {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\jars\\chromedriver.exe");

		} catch (Exception e) {
			LOGGER.error(e.toString());
		}
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");

		return new ChromeDriver(options);
	}

	public RemoteWebDriver createIEDriver() throws Exception {

		WebDriverManager.iedriver().setup();
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

		capabilities.setCapability("ignoreProtectedModeSettings", true);

		return new RemoteWebDriver(new URL("http://localhost:32835/wd/hub"), capabilities);

	}

	public ChromeDriver ChromeEmulator() {
		WebDriverManager.chromedriver().driverVersion("84").setup();
		Map<String, String> mobileEmulation = new HashMap();
		mobileEmulation.put("deviceName", "Nexus 5");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

		return new ChromeDriver(chromeOptions);
	}

	public EdgeDriver crateEdgeDriver() {
		WebDriverManager.edgedriver().setup();
		return new EdgeDriver();
	}

	public String getUrl() throws Exception {
		prop.load(new FileReader(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\config.properties"));
		return prop.getProperty("application.url");
	}

	public String getPropertyValue(String value) {
		try {
			prop.load(new FileReader(
					System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\config.properties"));

		} catch (IOException e) {
			System.out.println("Exception - getPropertyValue : " + e);
		}
		return prop.getProperty(value);
	}

	public Properties getPropertyValue() {
		try {
			prop.load(new FileReader(
					System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\config.properties"));

		} catch (IOException e) {
			System.out.println("Exception - getPropertyValue : " + e);
		}
		return prop;
	}

	public void extentTestConfig() {

		/*
		 * ExtentSparkReporter reporter = new ExtentSparkReporter(
		 * System.getProperty("user.dir") +
		 * "\\test-output\\ExtentReports\\extent-report.html");
		 */
		extent = new ExtentReports();
		// extent.attachReporter(reporter);
	}

	public void createTest(String testName) {
		extentTest = extent.createTest(testName);
	}

	public String takeScreenshot() {
		File file = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String fileName = System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshots\\KotakTest_"
				+ new Date().getDate() + "_" + new Date().getTime() + "_" + new Date().getSeconds();
		try {
			FileUtils.copyFile(file, new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileName;
	}

	public void reportInfo(String msg) throws IOException {
		extentTest.info(msg);
		System.out.println("Info : " + msg);
	}

	public void reportError(String msg) throws IOException {
		// extentTest.error(msg);
		System.out.println("Error : " + msg);
	}

	public void reportInfo(String msg, String screenshots) throws IOException {
		extentTest.info(msg, MediaEntityBuilder.createScreenCaptureFromPath(screenshots).build());
		System.out.println(msg);

	}

	public static void setDriver(WebDriver driver) {
		TestBaseLt.driver = driver;
	}

}
