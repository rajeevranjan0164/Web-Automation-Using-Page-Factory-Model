package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.test.script.BaseClass;

/**
 * @author Rajeev Ranjan
 * @ Created Date 04/08/18 
 *
 */

/**
 * 
 * I have created book HomePage class which is used store all WebElemnt in side
 * the HomePage
 *
 */
public class HomePage extends BaseClass {

	public Select select;
	public WebDriver driver;

	//
	/**
	 * 
	 * We use Page Factory pattern to initialize web elements which are defined in Page Objects.
	 */
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		select = new Select(selectIteam);// initialize here

	}

	@FindBy(id = "searchDropdownBox")
	public WebElement selectIteam;

	@FindBy(id = "twotabsearchtextbox")
	public WebElement searchKeyWords;

	public WebElement searchKeyWords() {
		return searchKeyWords;

	}

	@FindBy(xpath = "//input[@type='submit' and @value='Go']")
	public WebElement searchIteamButton;

	public void searchIteamButton() {

		searchIteamButton.click();
	}

}
