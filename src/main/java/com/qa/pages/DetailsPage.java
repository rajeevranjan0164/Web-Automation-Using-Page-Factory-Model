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

/**
 * 
 * I have created book DetailsPage class which is used store all WebElemnt in
 * side the DetailsPage
 *
 */
public class DetailsPage {

	/**
	 * 
	 * We use Page Factory pattern to initialize web elements which are defined in
	 * Page Objects.
	 */

	public DetailsPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "productTitle")
	public WebElement bookName;

	public String bookName() {
		return bookName.getText();
	}

	@FindBy(xpath = "//*[@id='title']/span[3]")
	public WebElement publisherDetailsPage;

	public String publisherDetailsPage() {
		return publisherDetailsPage.getText();
	}

	@FindBy(xpath = "//a[@id='a-autoid-3-announce']/span[text()='Paperback']")
	public WebElement paperbackDetaisPage;

	public String paperbackDetaisPage() {

		return paperbackDetaisPage.getText();
	}

	@FindBy(xpath = "//span[@class='a-size-medium a-color-price inlineBlock-display offer-price a-text-normal price3P']")
	public WebElement offePrice;

	public String geTextOfferPrice() {

		return offePrice.getText();
	}

	@FindBy(xpath = "//span[@class='a-color-secondary a-text-strike']")
	public WebElement ActualPrice;

	public String geTextActualPrice() {

		return ActualPrice.getText();
	}

	@FindBy(xpath = "//span[text()='Hardcover']")
	public WebElement hardcoverPaper;

	public WebElement hardcoverPaper() {
		return hardcoverPaper;
	}

	@FindBy(xpath = "//*[@id='soldByThirdParty']/span")
	public WebElement hardcoverPrice;

	public void hardcoverPrice() {

		hardcoverPrice.getText();
	}

	@FindBy(xpath = "//a[text()='Francis M 1890- Ed Turner']")
	public WebElement firstAuthor;

	public String getfirstAuthorName() {
		return firstAuthor.getText();
	}

	@FindBy(css = "#detail_bullets_id > table > tbody > tr > td > div > ul > li:nth-child(1)")
	public WebElement paperbackPages;

	public String paperbackPages() {
		return paperbackPages.getText();

	}

	@FindBy(xpath = "//*[@id='detail_bullets_id']/table/tbody/tr/td/div/ul/li[2]")
	public WebElement publisherName;

	public String publisherName() {
		return publisherName.getText();

	}

	@FindBy(xpath = "//*[@id='detail_bullets_id']/table/tbody/tr/td/div/ul/li[3]")
	public WebElement language;

	public String language() {
		return language.getText();

	}

	@FindBy(xpath = "//*[@id='detail_bullets_id']/table/tbody/tr/td/div/ul/li[4]")
	public WebElement ISBN10;

	public String ISBN10() {
		return ISBN10.getText();

	}

	@FindBy(xpath = "//*[@id='detail_bullets_id']/table/tbody/tr/td/div/ul/li[5]")
	public WebElement ISBN13;

	public String ISBN13() {
		return ISBN13.getText();

	}

	@FindBy(xpath = "//*[@id='detail_bullets_id']/table/tbody/tr/td/div/ul/li[6]")
	public WebElement productDimensions;

	public String productDimensions() {
		return productDimensions.getText();

	}

	@FindBy(xpath = "//*[@id='detail_bullets_id']/table/tbody/tr/td/div/ul")
	public List<WebElement> productDetails;

	public List<WebElement> productDetails() {
		return productDetails;
	}

}
