package com.qa.pages;

/**
 * @author Rajeev Ranjan
 * @ Created Date 04/08/18 
 *
 */

import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.test.script.BaseClass;

/**
 * I have created ListingPage class which is used to store all book listing page
 * WebElement.
 *
 */
//
public class ListingPage extends BaseClass {
	WebDriver driver;

	/**
	 * 
	 * We use Page Factory pattern to initialize web elements which are defined in
	 * Page Objects.
	 */
	public ListingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h2[1]")
	public WebElement tittleOfTheBook;

	public WebElement tittleOfTheBook() {
		return tittleOfTheBook;

	}

	@FindBy(xpath = "//span[@class='a-size-small a-color-secondary'][1]")
	public WebElement PublisherDate;

	public String Publisher() {

		return PublisherDate.getText();
	}

	@FindBy(xpath = "//span[@class='a-size-small a-color-secondary'][2]")
	public WebElement creator;

	public String getTextCreator() {

		return creator.getText();
	}

	@FindBy(xpath = "//*[@id='result_0']/div/div/div/div[2]/div[2]/div[1]/div[1]/a/h3")
	public WebElement paperback;

	public String getTextPaperback() {
		return paperback.getText();

	}

	@FindBy(xpath = "//*[@id='result_0']/div/div/div/div[2]/div[2]/div[1]/div[2]/a/span[2]")
	public WebElement offerPricePaperback;

	public String getTextOfferPricePaperback() {
		return offerPricePaperback.getText();

	}

	@FindBy(xpath = "//*[@id='result_0']/div/div/div/div[2]/div[2]/div[1]/div[2]/span[2]")
	public WebElement actualPricePaperback;

	public String getTextActualPricePaperback() {
		return actualPricePaperback.getText();

	}

	@FindBy(xpath = "//*[@id='result_0']/div/div/div/div[2]/div[2]/div[1]/div[3]/span[2]")
	public WebElement youSavePaperback;

	public String getTextYouSavePaperback() {
		return youSavePaperback.getText();

	}

	@FindBy(xpath = "//h3[text()='Hardcover'][1]")
	public WebElement hardcover;

	public String getTextHardcover() {
		return hardcover.getText();

	}

	@FindBy(xpath = "//*[@id='result_0']/div/div/div/div[2]/div[2]/div[1]/div[6]/a/span[2]")
	public WebElement hardcoverPrice;

	public String getTextHardcoverPrice() {
		return hardcoverPrice.getText();

	}

	@FindBy(xpath = "//a[@class='a-link-normal a-text-normal']/img[@src='https://images-eu.ssl-images-amazon.com/images/I/519OWVh4k7L._AC_US218_FMwebp_QL70_.jpg']")
	public WebElement bookImage;

	public WebElement bookImageClick() {

		return bookImage;
	}

}
