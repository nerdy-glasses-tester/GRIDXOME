package webTests;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import base.TestBase;
import common.ScreenshotURL;
import webPageObjects.FilterMoreResults_Page;
import webPageObjects.Search;

public class FilterMoreResultsBySquareFeet extends TestBase{
	
    //****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	
	static SoftAssert softAssert = new SoftAssert();
	final static Logger log = LogManager.getLogger(FilterMoreResultsBySquareFeet.class);
	
	
	static String className = FilterMoreResultsBySquareFeet.class.getSimpleName();
 	static Date date1= new Date();
 	static String originaltimestamp = new Timestamp(date1.getTime()).toString();
 	static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
	static String foldername = className+timestamp;
	static String errorname = "";

	@Parameters({"browser", "platform"})
	@Test(groups= {"smoke", "regression"}, dataProvider = "getData") 
	public void filterResultsBySqFeet (String searchkeyword, String minsqfeet, String maxsqfeet) throws IOException, InterruptedException
	{
		RemoteWebDriver driver = getDriver();
		Search.searchByCity(driver, searchkeyword);
		FilterMoreResults_Page.clickOpenMoreFilters(driver);
		FilterMoreResults_Page.filterBySqFeet(driver, minsqfeet, maxsqfeet);
		FilterMoreResults_Page.applyMoreFilters(driver);
		String diditfilter = FilterMoreResults_Page.verifyFilterBySqFeet(driver, minsqfeet, maxsqfeet);

		try{
			Assert.assertEquals(diditfilter, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't filter by square feet.", e.getMessage());
			errorname = "didntfilterbysqft";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}

		   softAssert.assertAll();
		
	}
}
