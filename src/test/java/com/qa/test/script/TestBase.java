package com.qa.test.script;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase   {

	public static WebDriver driver;
	public static Properties prop;
	public static int TIMEOUT = 5;
	
	// This is chrome driver key
		public static String key = "webdriver.chrome.driver";

	/*
	 * TestBase class constructor : used to initialize the Properties object to
	 * fetch config (env) variables from config.properties file
	 */
	public TestBase() {

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/qa/ui/config/config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Function name: initialize This is used to initialize all the pre requisites:
	 * >>WebDriver reference initialization on the basis of browser property
	 * >>Maximizing the window >>Initializing Global/dynamic implicit wait and
	 * PageLoadTimeOut >>is used to get the application URL from property file
	 * 
	 */
	public static void initialize() {

		if (prop.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.setProperty(key,System.getProperty("user.dir")+"/src/main/resource/drivers/chromedriver.exe")); // path of
																									// chromedriver
																									// executable file
			driver = new ChromeDriver();

		} else if (prop.getProperty("browser").equals("FF")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/src/main/resources/drivers/geckodriver"); // path of geckodriver
																									// executable file
			driver = new FirefoxDriver();

			/*
			 * Not using the following code as its part of IE browser. Commented this code
			 * as running this code on MAC... Mac doesn't support IE browsers If you want to
			 * run this code on IE, please add Internet explorer driver executable file in
			 * main/resources/drivers directory Please change the appropriate browser
			 * settings for IE automation execution
			 * 
			 * else if (prop.getProperty("browser").equals("IE")) {
			 * System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+
			 * "/main/resources/drivers/internetexplorerdriver"); //path of
			 * internetexplorerdriver executable file driver = new InternetExplorerDriver();
			 */
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));

	}

	/*
	 * custom clickOn method: click on element on the basis of some
	 * ExpectedConditions to avoid StaleElementReferenceException
	 */
	public static void clickOn(WebDriver driver, WebElement locator, int timeout) {
		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(locator));
		locator.click();
	}

	/*
	 * custom sendKeyValue method: enter value in element on the basis of some
	 * ExpectedConditions to avoid StaleElementReferenceException
	 */
	public static void sendKeyValue(WebDriver driver, WebElement locator, int timeout, String value) {
		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(locator));
		locator.sendKeys(value);

	}

	/*
	 * custom getWebElement method: get the WebElement on the basis of some
	 * ExpectedConditions to avoid StaleElementReferenceException
	 */
	public static WebElement getWebElement(WebDriver driver, WebElement locator, int timeout) {
		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(locator));
		return locator;
	}

	/*
	 * custom getWebElements method: get similar WebElements on the basis of some
	 * ExpectedConditions to avoid StaleElementReferenceException
	 */
	public static List<WebElement> getWebElements(WebDriver driver, WebElement locator, int timeout) {
		new WebDriverWait(driver, timeout).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(locator));
		return driver.findElements((By) locator);
	}

}
