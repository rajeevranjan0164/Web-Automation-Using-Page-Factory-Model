package com.qa.utility;

/**
 * @author Rajeev Ranjan
 * @Created Date 04/08/18 
 *
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 * I have created DataStore class which is used to store all my text or String
 * data
 *
 */
public class DataStore {
	
	public DataStore(WebDriver driver) {
		
		PageFactory.initElements(driver,this);
	}

	public String searchWord = "data catalog";
	public String firstAuthorName = "Francis M 1890- Ed Turner";
	public String WebtittleActual = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
	
	//Product details:- Paperback Pages
	public String paperbackPages = "536 pages";
	public String publisherNameAndDate = "Nabu Press (2 October 2013)";
	public String language = "English";
	public String iSBNTen =  "1287809618";
	public String iSBNThrtheen =  "978-1287809616";
	public String productDimensions = "18.9 x 2.7 x 24.6 cm";
	
	//Product details:- Paperback Pages
		public String pagesHard = "538 pages";
		public String publisherNameAndDateHard = "Sagwan Press (25 August 2015)";
		public String languageHard = "English";
		public String iSBNTenHard =  "1340327953";
		public String iSBNThrtheenHard =  "978-1340327958";
		public String productDimensionsHard = "15.6 x 3 x 23.4 cm";
	
	

}
