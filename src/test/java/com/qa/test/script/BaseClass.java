package com.qa.test.script;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {

	public static WebDriver driver;

	public String url = "https://www.amazon.in/";
	public String key = "webdriver.chrome.driver";
	public String value = "C:\\Users\\Rajeev Ranjan\\Desktop\\chromedriver_win32\\chromedriver.exe";

	public WebDriver baseSetUp() {

		System.setProperty(key, value);
		WebDriver driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
	}

}
