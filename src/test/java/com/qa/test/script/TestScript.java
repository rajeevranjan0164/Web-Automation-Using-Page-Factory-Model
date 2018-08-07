package com.qa.test.script;

/**
 * @author Rajeev Ranjan
 * @ Created Date 04/08/18 
 *
 */

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.pages.DetailsPage;
import com.qa.pages.HomePage;
import com.qa.pages.ListingPage;
import com.qa.utility.DataStore;

//TestScript class is the main scripting class here I can write code all test cases combination  
public class TestScript {

	// Here I have created WebDriver globally I can use this driver any other method
	public WebDriver driver;

	// Select is the class which is used to select the drop down in the web Page.
	// Here I have declared globally
	Select selectt;

	// JavaScriptExecutor is an Interface that helps to execute JavaScript through
	// Selenium Webdriver
	// Here I have created WebDriver globally I can use this driver any other method
	// Using JavascriptExecutor I can perform many operation eg:- Scroll up , Scrol
	// Down , Clicking web page
	JavascriptExecutor jse;

	// ArrayList is the part of collection frame work which is used to store element
	// , add element , remove element , get size of element , get() index of element
	// So I can perform Many operation

	// Here I have used to perform tab operation in browser
	ArrayList<String> tabs2;

	// This is Amazon url
	public String url = "https://www.amazon.in/";

	// This is chrome driver key
	public String key = "webdriver.chrome.driver";

	// This is the path of chrome driver , which is used to open my chrome driver
	public String value = "C://Users//Rajeev Ranjan//Desktop//chromedriver_win32//chromedriver.exe";

	// HomePage is the class, in side the class, I have created Amazon all home page
	// web element
	// Here I have declared globally
	HomePage homePage;

	// I have created DataStore class, in side the class, I have put all data and
	// String
	// Here I have declared globally
	DataStore dataStore;

	// ListingPage is the class, in side the class, I have created Amazon all
	// listing page web element
	// Here I have declared globally
	ListingPage listingPage;

	// DetailsPage is the class, in side the class, I have created Amazon all
	// DetailsPage web element
	// Here I have declared globally
	DetailsPage detailsPage;

	// setUp method I have done all initial setup
	@BeforeTest()
	public void setUp() {
		// here I have pass the chrome driver "key" and "value"
		System.setProperty(key, value);
		// Here It will lunch of my chrome driver
		driver = new ChromeDriver();

		// using get method it will open my amazon website
		driver.get(url);

		// here It will delete cache data and cookies data
		driver.manage().deleteAllCookies();

		// Here it will maximize my chrome browser
		driver.manage().window().maximize();

		// Using pageLoadTimeout it will wait for page loging
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);

		// implicitlyWait is the part of the the selenium which is used to wait for all
		// element , mostly I can implicitlyWait globally
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// here I have created object of the Homepage class , using this class i can
		// call all HomePage Webelement
		homePage = new HomePage(driver);
		// here I have created object of the the dataStore class , using this class i
		// can call all DataStore Webelement
		dataStore = new DataStore(driver);
		// here I have created object of listingPage class , using this class i can call
		// all ListingPage Webelement
		listingPage = new ListingPage(driver);
		// here I have created object of the detailsPage class , using this class i can
		// call all DetailsPage Webelement
		detailsPage = new DetailsPage(driver);

		// Here I have creted object of the JavascriptExecutor
		jse = (JavascriptExecutor) driver;

		// Here I have created object of ArrayList<String>(driver.getWindowHandles());
		tabs2 = new ArrayList<String>(driver.getWindowHandles());

	}

	@Test(priority = 1, description = "verify title Of WebSite")
	public void verifyTittleOfWebSite() {

		// getTitle() is a method which is used to capture the WebSite title
		String webTittleExpcted = driver.getTitle();

		/**
		 * Assert is the class of the TestNg. Asserts that two Strings are equal. If
		 * they are not, assertEquals is the method in side the TestNg It is will take
		 * two parameter "actual value" and "expected value" it will match "actual
		 * value" and "expected value" it will return boolean.
		 **/
		Assert.assertEquals(dataStore.WebtittleActual, webTittleExpcted, "It is not matching web site title");
	}

	@Test(priority = 2, description = "Verify Book Tittle ")
	public void verifyBookTittle() {

		/**
		 * Select is the class in selenium WebDriver Here it will take one parameter as
		 * a WebElement
		 */
		selectt = new Select(homePage.selectIteam);
		// selectByIndex() is the method it will take parameter as a index value
		// it will select Books.
		selectt.selectByIndex(10);
		/**
		 * here It will pass sendKeys "data catalog" in side the Amazon search box.
		 */
		homePage.searchKeyWords().sendKeys(dataStore.searchWord);
		// it will click the amazon search button
		homePage.searchIteamButton();

		/**
		 * Using JavascriptExecutor object I have class executeScript (method) It will
		 * scroll the Web page down
		 */
		jse.executeScript("window.scrollBy(0,100)", "");
		// capturing the title of the book in listing page as String
		String bookTittleNameExpectedResult = listingPage.tittleOfTheBook.getText();
		// clicking the book image
		listingPage.bookImageClick().click();

		// I have created object ArrayList<String>(driver.getWindowHandles());
		tabs2 = new ArrayList<String>(driver.getWindowHandles());

		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));

		// capturing the title of the book in book details page as String
		String bookNameActualResult = detailsPage.bookName();
		/**
		 * Assert is the class of the TestNg. Asserts that two Strings are equal. If
		 * they are not, assertEquals is the method in side the TestNg It is will take
		 * two parameter "actual value" and "expected value" it will match "actual
		 * value" and "expected value" it will return boolean.
		 **/
		Assert.assertEquals(bookNameActualResult, bookTittleNameExpectedResult, "Book Name must be Same");
		// Here it will close the second tab
		driver.close();

	}

	@Test(priority = 3, description = "Verify the book paper type")
	public void paperType() throws Exception {

		/**
		 * Here it will switch the first tab
		 */
		driver.switchTo().window(tabs2.get(0));
		// capturing the book paper type in the listing page as String
		String papperbackExpectedResult = listingPage.getTextPaperback();
		// clicking the book image
		listingPage.bookImageClick().click();
		// I have created object ArrayList<String>(driver.getWindowHandles());, it will
		// use to take getWindowHandles()
		tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));

		// capturing the book paper type in the details page as String
		String paperbacktextActualResult = detailsPage.paperbackDetaisPage();

		/**
		 * Assert is the class of the TestNg. Asserts that two Strings are equal. If
		 * they are not, assertEquals is the method in side the TestNg It is will take
		 * two parameter "actual value" and "expected value" it will match "actual
		 * value" and "expected value" it will return boolean.
		 **/
		Assert.assertEquals(papperbackExpectedResult, paperbacktextActualResult, "paperType should be paperback");
		// Here it will close the second tab
		driver.close();
	}

	@Test(priority = 4, description = "Verify the offer price type of the paper- Paperback")
	public void OfferPricePaperback() throws Exception {

		/**
		 * Here it will switch the first tab
		 */
		driver.switchTo().window(tabs2.get(0));
		// capturing book offer price in the listing page as String
		String OfferPricePaperbackExpectedResult = listingPage.getTextOfferPricePaperback();
		// clicking the book image
		listingPage.bookImageClick().click();
		// I have created object ArrayList<String>(driver.getWindowHandles()); , it will
		// use to take getWindowHandles()
		tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));
		// capturing book offer price in the details page as String
		String OfferPriceActualResult = detailsPage.geTextOfferPrice();
		/**
		 * This variant of split method takes a regular expression as parameter, and
		 * breaks the given string around matches of this regular expression regex it
		 * will split the value ".000" and store as String Array
		 */
		String[] offerSplit = OfferPriceActualResult.split(".00");
		for (String offerPriceActual : offerSplit) {

			/**
			 * Asserts that a condition is true. If it isn't, an AssertionError, with the
			 * given message, is thrown.
			 * 
			 * @param condition
			 *            the condition to evaluate
			 * @param message
			 *            the assertion error message
			 */
			Assert.assertTrue(offerPriceActual.contains(OfferPricePaperbackExpectedResult),
					"Offer price is not matching");
			// Here it will close the second tab
			driver.close();
		}
	}

	@Test(priority = 5, description = "Verify the Actual price type of the paper- Paperback")
	public void ActualPricePaperback() throws Exception {

		/**
		 * Here it will switch the first tab
		 */
		driver.switchTo().window(tabs2.get(0));
		// capturing Actual price type in the listing page as String
		String ActualPricePaperbackExpectedResult = listingPage.getTextActualPricePaperback();
		// clicking the book image
		listingPage.bookImageClick().click();
		// I have created object ArrayList<String>(driver.getWindowHandles());
		tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));

		// capturing Actual price type in the details page as String
		String ActualPriceActualResult = detailsPage.geTextActualPrice();
		/**
		 * This variant of split method takes a regular expression as parameter, and
		 * breaks the given string around matches of this regular expression regex it
		 * will split the value ".000" and store as String Array
		 */
		String[] ActalSplit = ActualPriceActualResult.split(".00");
		for (String ActualPriceActual : ActalSplit) {

			/**
			 * Asserts that a condition is true. If it isn't, an AssertionError, with the
			 * given message, is thrown.
			 * 
			 * @param condition
			 *            the condition to evaluate
			 * @param message
			 *            the assertion error message
			 */
			Assert.assertTrue(ActualPriceActual.contains(ActualPricePaperbackExpectedResult), "not");
			// Here it will close the second tab
			driver.close();
		}
	}

	@Test(priority = 6, description = "Verify the book paper type - Hardcover")
	public void hardcoverPaper() throws Exception {

		/**
		 * Here it will switch the first tab
		 */
		driver.switchTo().window(tabs2.get(0));
		// capturing book paper type - Hardcover in the listing page as String
		String hardcoverPaperTextExpectedResult = listingPage.getTextHardcover();
		// clicking the book image
		listingPage.bookImageClick().click();
		// I have created object ArrayList<String>(driver.getWindowHandles());
		tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));

		// capturing book paper type - Hardcover in the details page as String
		String hardcoverPaperTextActualResult = detailsPage.hardcoverPaper().getText();

		/**
		 * Assert is the class of the TestNg. Asserts that two Strings are equal. If
		 * they are not, assertEquals is the method in side the TestNg It is will take
		 * two parameter "actual value" and "expected value" it will match "actual
		 * value" and "expected value" it will return boolean.
		 **/

		Assert.assertEquals(hardcoverPaperTextExpectedResult, hardcoverPaperTextActualResult,
				"Hardcover Paper should be match");
		// Here it will close the second tab
		driver.close();
	}

	@Test(priority = 7, description = " Verify the publisher date book listing and book details page.")
	public void publisher() throws Exception {

		/**
		 * Here it will switch the first tab
		 */
		driver.switchTo().window(tabs2.get(0));
		// capturing publisher date and name in the listing page as String
		String hardcoverPaperTextExpectedResult = listingPage.Publisher();
		// clicking the book image
		listingPage.bookImageClick().click();
		// I have created object ArrayList<String>(driver.getWindowHandles());
		tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));

		// capturing publisher date and name in the details page as String
		String hardcoverPaperTextActualResult = detailsPage.publisherDetailsPage();

		/**
		 * Assert is the class of the TestNg. Asserts that two Strings are equal. If
		 * they are not, assertEquals is the method in side the TestNg It is will take
		 * two parameter "actual value" and "expected value" it will match "actual
		 * value" and "expected value" it will return boolean.
		 **/

		Assert.assertTrue(hardcoverPaperTextExpectedResult.startsWith(hardcoverPaperTextExpectedResult),
				"publisher is not maching");
		// Here it will close the second tab
		driver.close();
	}

	@Test(priority = 8, description = " Verify the Hardcover price  book listing and book details page.")
	public void hardcoverPaperPrice() throws Exception {

		/**
		 * Here it will switch the first tab
		 */
		driver.switchTo().window(tabs2.get(0));
		// capturing Hardcover price book in the listing page as String
		String hardcoverPaperTextExpectedResult = listingPage.getTextHardcoverPrice();
		// here I have replace the blank space using replaceAll method
		String Expected = hardcoverPaperTextExpectedResult.replaceAll("\\s+", "");
		// clicking the hardcover link
		listingPage.hardcover.click();
		// I have created object ArrayList<String>(driver.getWindowHandles());
		tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));

		// capturing Hardcover price book in the details page as String
		String hardcoverPaperTextActualResult = detailsPage.hardcoverPrice.getText();

		// here I have replace the blank space
		String Actual = hardcoverPaperTextActualResult.replaceAll("\\s+", "");

		/**
		 * Assert is the class of the TestNg. Asserts that two Strings are equal. If
		 * they are not, assertEquals is the method in side the TestNg It is will take
		 * two parameter "actual value" and "expected value" it will match "actual
		 * value" and "expected value" it will return boolean.
		 **/
		Assert.assertEquals(Actual, Expected, "Hardcover Paper should be match");
		// Here it will close the second tab
		driver.close();
	}

	@Test(priority = 9, description = " Verify the Author Name book details page.")
	public void FirstAuthorName() throws Exception {

		/**
		 * Here it will switch the first tab
		 */
		driver.switchTo().window(tabs2.get(0));
		// capturing the Author Name in the listing page as String
		String authorExceptedResult = dataStore.firstAuthorName;
		// clicking the hardcover link
		listingPage.hardcover.click();
		// I have created object ArrayList<String>(driver.getWindowHandles());
		tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));

		// capturing the Author Name in the details page as String
		String authorActual = detailsPage.getfirstAuthorName();

		/**
		 * Assert is the class of the TestNg. Asserts that two Strings are equal. If
		 * they are not, assertEquals is the method in side the TestNg It is will take
		 * two parameter "actual value" and "expected value" it will match "actual
		 * value" and "expected value" it will return boolean.
		 **/
		Assert.assertEquals(authorActual, authorExceptedResult, "Author name is not matching");
		// Here it will close the second tab
		driver.close();
	}

	@Test(priority = 10, description = "Product details Paperback :- Pages")
	public void PaperbackPages() throws Exception {

		/**
		 * Here it will switch the first tab
		 */
		driver.switchTo().window(tabs2.get(0));
		// capturing the Product Pages in the listing page as String
		String paperbackPagesExceptedResult = dataStore.paperbackPages;
		// clicking the book image link
		listingPage.bookImageClick().click();
		// I have created object ArrayList<String>(driver.getWindowHandles());
		tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));

		/**
		 * using JavascriptExecutor , I am scrolling down web page
		 */
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,700)", "");

		// capturing the Product Pages in the book details page as String
		String paperbackPagesActualResult = detailsPage.paperbackPages();
		/**
		 * replaceFirst is the method in java , it will replace the first word
		 * 
		 */
		String s = paperbackPagesActualResult.replaceFirst("Paperback: ", "");

		/**
		 * Assert is the class of the TestNg. Asserts that two Strings are equal. If
		 * they are not, assertEquals is the method in side the TestNg It is will take
		 * two parameter "actual value" and "expected value" it will match "actual
		 * value" and "expected value" it will return boolean.
		 **/

		Assert.assertEquals(s, paperbackPagesExceptedResult, "Paperback Pages is not matching");
		// Here it will close the second tab
		driver.close();

	}

	@Test(priority = 11, description = "Product details Paperback :- publisher Name And Date")
	public void publisherNameAndDate() throws Exception {

		/**
		 * Here it will switch the first tab
		 */
		driver.switchTo().window(tabs2.get(0));
		// capturing publisher Name And Date in the listing page as String
		String publisherNameAndDateExceptedResult = dataStore.publisherNameAndDate;
		// clicking the book image link
		listingPage.bookImageClick().click();
		// I have created object ArrayList<String>(driver.getWindowHandles());
		tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));

		/**
		 * using JavascriptExecutor , I am scrolling down web page
		 */
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,700)", "");

		// capturing publisher Name And Date in the details page as String
		String publisherNameAndDateActualResult = detailsPage.publisherName();

		/**
		 * replaceFirst is the method in java , it will replace the first word
		 * 
		 */
		String actual = publisherNameAndDateActualResult.replaceFirst("Publisher: ", "");

		/**
		 * Assert is the class of the TestNg. Asserts that two Strings are equal. If
		 * they are not, assertEquals is the method in side the TestNg It is will take
		 * two parameter "actual value" and "expected value" it will match "actual
		 * value" and "expected value" it will return boolean.
		 **/
		Assert.assertEquals(actual, publisherNameAndDateExceptedResult, "publisher Name And Date is not matching");
		// Here it will close the second tab
		driver.close();

	}

	@Test(priority = 12, description = "Product details Paperback :- Language")
	public void language() throws Exception {

		/**
		 * Here it will switch the first tab
		 */
		driver.switchTo().window(tabs2.get(0));
		// capturing Language in the listing page as String
		String languageExceptedResult = dataStore.language;
		// clicking the book image link
		listingPage.bookImageClick().click();
		// I have created object ArrayList<String>(driver.getWindowHandles());
		tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));

		/**
		 * using JavascriptExecutor , I am scrolling down web page
		 */
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,700)", "");

		// capturing Language in the details page as String
		String languageActualResult = detailsPage.language();
		/**
		 * replaceFirst is the method in java , it will replace the first word
		 * 
		 */
		String actual = languageActualResult.replaceFirst("Language: ", "");
		/**
		 * Assert is the class of the TestNg. Asserts that two Strings are equal. If
		 * they are not, assertEquals is the method in side the TestNg It is will take
		 * two parameter "actual value" and "expected value" it will match "actual
		 * value" and "expected value" it will return boolean.
		 **/
		Assert.assertEquals(actual, languageExceptedResult, "Language is not matching");
		// Here it will close the second tab
		driver.close();

	}

	@Test(priority = 13, description = "Product details Paperback :- ISBN-10:")
	public void iSBNTen() throws Exception {
		/**
		 * Here it will switch the first tab
		 */

		driver.switchTo().window(tabs2.get(0));
		// capturing ISBN-10: in the listing page as String
		String iSBNTenExceptedResult = dataStore.iSBNTen;
		// clicking the book image link
		listingPage.bookImageClick().click();

		// I have created object ArrayList<String>(driver.getWindowHandles());
		tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));

		/**
		 * using JavascriptExecutor , I am scrolling down web page
		 */
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,700)", "");

		// capturing ISBN-10: in the book details page as String
		String iSBNTenActualResult = detailsPage.ISBN10();
		/**
		 * replaceFirst is the method in java , it will replace the first word
		 * 
		 */
		String actual = iSBNTenActualResult.replaceFirst("ISBN-10: ", "");

		/**
		 * Assert is the class of the TestNg. Asserts that two Strings are equal. If
		 * they are not, assertEquals is the method in side the TestNg It is will take
		 * two parameter "actual value" and "expected value" it will match "actual
		 * value" and "expected value" it will return boolean.
		 **/
		Assert.assertEquals(actual, iSBNTenExceptedResult, "ISBNTen is not matching");
		// Here it will close the second tab
		driver.close();

	}

	@Test(priority = 14, description = "Product details Paperback :- ISBN-13:")
	public void ISBN13() throws Exception {

		/**
		 * Here it will switch the first tab
		 */
		driver.switchTo().window(tabs2.get(0));
		// capturing ISBN-13: in the listing page as String
		String ISBN13ExceptedResult = dataStore.iSBNThrtheen;
		// clicking the boook image link
		listingPage.bookImageClick().click();
		// I have created object ArrayList<String>(driver.getWindowHandles());
		tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));

		/**
		 * using JavascriptExecutor , I am scrolling down web page
		 */
		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,700)", "");

		// capturing ISBN-13: in the book details page as String
		String ISBN13ActualResult = detailsPage.ISBN13();
		/**
		 * replaceFirst is the method in java , it will replace the first word
		 * 
		 */
		String actual = ISBN13ActualResult.replaceFirst("ISBN-13: ", "");

		/**
		 * Assert is the class of the TestNg. Asserts that two Strings are equal. If
		 * they are not, assertEquals is the method in side the TestNg It is will take
		 * two parameter "actual value" and "expected value" it will match "actual
		 * value" and "expected value" it will return boolean.
		 **/
		Assert.assertEquals(actual, ISBN13ExceptedResult, "ISBN13 is not matching");
		// Here it will close the second tab
		driver.close();

	}

	@Test(priority = 15, description = "Product details Paperback :- productDimensions")
	public void productDimensions() throws Exception {
		/**
		 * Here it will switch the first tab
		 */
		driver.switchTo().window(tabs2.get(0));
		// capturing productDimensions in the listing page as String
		String productDimensionsExceptedResult = dataStore.productDimensions;
		// clicking the book image link
		listingPage.bookImageClick().click();
		// I have created object ArrayList<String>(driver.getWindowHandles());
		tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));

		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,700)", "");

		// capturing productDimensions in the book details page as String
		String productDimensionsActualResult = detailsPage.productDimensions();
		/**
		 * replaceFirst is the method in java , it will replace the first word
		 * 
		 */
		String actual = productDimensionsActualResult.replaceFirst("Product Dimensions: ", "");

		/**
		 * Assert is the class of the TestNg. Asserts that two Strings are equal. If
		 * they are not, assertEquals is the method in side the TestNg It is will take
		 * two parameter "actual value" and "expected value" it will match "actual
		 * value" and "expected value" it will return boolean.
		 **/
		Assert.assertEquals(actual, productDimensionsExceptedResult, "Produc Dimensions is not matching");
		// Here it will close the second tab
		driver.close();

	}

	@Test(priority = 16, description = "Product details HardCover :- Pages")
	public void PaperbackHardCoverPages() throws Exception {

		/**
		 * Here it will switch the first tab
		 */
		driver.switchTo().window(tabs2.get(0));
		// capturing Pages in the listing page as String
		String PaperbackHardExceptedResult = dataStore.pagesHard;
		// clicking the hardcover link

		listingPage.hardcover.click();
		// I have created object ArrayList<String>(driver.getWindowHandles());
		tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));

		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,700)", "");

		// capturing Pages in the book details page as String
		String HardCoverPagesActualResult = detailsPage.paperbackPages();

		/**
		 * replaceFirst is the method in java , it will replace the first word
		 * 
		 */
		String Actual = HardCoverPagesActualResult.replaceFirst("Hardcover: ", "");

		/**
		 * Assert is the class of the TestNg. Asserts that two Strings are equal. If
		 * they are not, assertEquals is the method in side the TestNg It is will take
		 * two parameter "actual value" and "expected value" it will match "actual
		 * value" and "expected value" it will return boolean.
		 **/
		Assert.assertEquals(Actual, PaperbackHardExceptedResult, "Hardcover Pages is not matching");
		// Here it will close the second tab
		driver.close();

	}

	@Test(priority = 17, description = "Product details HardCover :- publisher Name And Date")
	public void publisherNameAndDateHardcover() throws Exception {

		/**
		 * Here it will switch the first tab
		 */
		driver.switchTo().window(tabs2.get(0));
		// capturing publisher Name And Date in the listing page as String
		String publisherNameAndDateExceptedResult = dataStore.publisherNameAndDateHard;
		// clicking the hardcover link
		listingPage.hardcover.click();
		// I have created object ArrayList<String>(driver.getWindowHandles());
		tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));

		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,750)", "");

		// capturing publisher Name And Date in the book details page as String
		String publisherNameAndDateActualResult = detailsPage.publisherName();
		/**
		 * replaceFirst is the method in java , it will replace the first word
		 * 
		 */
		String actual = publisherNameAndDateActualResult.replaceFirst("Publisher: ", "");

		/**
		 * Assert is the class of the TestNg. Asserts that two Strings are equal. If
		 * they are not, assertEquals is the method in side the TestNg It is will take
		 * two parameter "actual value" and "expected value" it will match "actual
		 * value" and "expected value" it will return boolean.
		 **/
		Assert.assertEquals(actual, publisherNameAndDateExceptedResult, "publisher Name And Date is not matching");
		// Here it will close the second tab
		driver.close();

	}

	@Test(priority = 18, description = "Product details HardCover :- Language")
	public void languageHardCover() throws Exception {

		/**
		 * Here it will switch the first tab
		 */
		driver.switchTo().window(tabs2.get(0));
		// capturing Language in the page as String
		String languageExceptedResult = dataStore.languageHard;
		// clicking the hardcover link
		listingPage.hardcover.click();
		// I have created object ArrayList<String>(driver.getWindowHandles());
		tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));

		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,700)", "");
		// capturing Language in the details page as String
		String languageActualResult = detailsPage.language();
		/**
		 * replaceFirst is the method in java , it will replace the first word
		 * 
		 */
		String actual = languageActualResult.replaceFirst("Language: ", "");
		/**
		 * Assert is the class of the TestNg. Asserts that two Strings are equal. If
		 * they are not, assertEquals is the method in side the TestNg It is will take
		 * two parameter "actual value" and "expected value" it will match "actual
		 * value" and "expected value" it will return boolean.
		 **/
		Assert.assertEquals(actual, languageExceptedResult, "Language is not matching");
		// Here it will close the second tab
		driver.close();

	}

	@Test(priority = 19, description = "Product details HardCover :- ISBN-10:")
	public void iSBNTenHardCover() throws Exception {

		/**
		 * Here it will switch the first tab
		 */
		driver.switchTo().window(tabs2.get(0));
		// capturing ISBN-10: in the listing page as String
		String iSBNTenExceptedResult = dataStore.iSBNTenHard;
		// clicking the hardcover link
		listingPage.hardcover.click();
		// I have created object ArrayList<String>(driver.getWindowHandles());
		tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));

		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,700)", "");
		// capturing ISBN-10: in the details page as String
		String iSBNTenActualResult = detailsPage.ISBN10();
		/**
		 * replaceFirst is the method in java , it will replace the first word
		 * 
		 */
		String actual = iSBNTenActualResult.replaceFirst("ISBN-10: ", "");
		/**
		 * Assert is the class of the TestNg. Asserts that two Strings are equal. If
		 * they are not, assertEquals is the method in side the TestNg It is will take
		 * two parameter "actual value" and "expected value" it will match "actual
		 * value" and "expected value" it will return boolean.
		 **/

		Assert.assertEquals(actual, iSBNTenExceptedResult, "ISBNTen is not matching");
		// Here it will close the second tab
		driver.close();

	}

	@Test(priority = 20, description = "Product details HardCover :- ISBN-13:")
	public void ISBN13HardCover() throws Exception {

		/**
		 * Here it will switch the first tab
		 */
		driver.switchTo().window(tabs2.get(0));
		// capturing ISBN-13: in the listing page as String
		String ISBN13ExceptedResult = dataStore.iSBNThrtheenHard;

		// clicking the hardcover link
		listingPage.hardcover.click();
		// I have created object ArrayList<String>(driver.getWindowHandles());
		tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));

		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,700)", "");

		// capturing ISBN-13: in the details page as String
		String ISBN13ActualResult = detailsPage.ISBN13();
		/**
		 * replaceFirst is the method in java , it will replace the first word
		 * 
		 */
		String actual = ISBN13ActualResult.replaceFirst("ISBN-13: ", "");
		/**
		 * Assert is the class of the TestNg. Asserts that two Strings are equal. If
		 * they are not, assertEquals is the method in side the TestNg It is will take
		 * two parameter "actual value" and "expected value" it will match "actual
		 * value" and "expected value" it will return boolean.
		 **/
		Assert.assertEquals(actual, ISBN13ExceptedResult, "ISBN13 is not matching");
		// Here it will close the second tab
		driver.close();

	}

	@Test(priority = 21, description = "Product details HardCover :- productDimensions")
	public void productDimensionsHardCover() throws Exception {

		/**
		 * Here it will switch the first tab
		 */
		driver.switchTo().window(tabs2.get(0));
		// capturing productDimensions in the listing page as String
		String productDimensionsExceptedResult = dataStore.productDimensionsHard;

		// clicking the hardcover link
		listingPage.hardcover.click();
		// I have created object ArrayList<String>(driver.getWindowHandles());
		tabs2 = new ArrayList<String>(driver.getWindowHandles());
		// Here It will switch the second tab
		driver.switchTo().window(tabs2.get(1));

		jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,700)", "");

		// capturing productDimensions in the details page as String
		String productDimensionsActualResult = detailsPage.productDimensions();
		/**
		 * replaceFirst is the method in java , it will replace the first word
		 * 
		 */
		String actual = productDimensionsActualResult.replaceFirst("Product Dimensions: ", "");

		/**
		 * Assert is the class of the TestNg. Asserts that two Strings are equal. If
		 * they are not, assertEquals is the method in side the TestNg It is will take
		 * two parameter "actual value" and "expected value" it will match "actual
		 * value" and "expected value" it will return boolean.
		 **/
		Assert.assertEquals(actual, productDimensionsExceptedResult, "Produc Dimensions is not matching");
		// Here it will close the second tab
		driver.close();

	}

	@AfterTest()
	public void closeBroswer() {
		 driver.quit();
	}
}
