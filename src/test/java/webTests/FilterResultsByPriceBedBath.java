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
import webPageObjects.FilterResults_Page;
import webPageObjects.Search;


public class FilterResultsByPriceBedBath extends TestBase{
	
    //****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	
	static SoftAssert softAssert = new SoftAssert();
	final static Logger log = LogManager.getLogger(FilterResultsByPriceBedBath.class);
	
	
	static String className = FilterResultsByPriceBedBath.class.getSimpleName();
 	static Date date1= new Date();
 	static String originaltimestamp = new Timestamp(date1.getTime()).toString();
 	static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
	static String foldername = className+timestamp;
	static String errorname = "";

	@Parameters({"browser", "platform"})
	@Test(groups= {"smoke", "regression"}, dataProvider = "getData") 
	public void filterResultsByPrice (String searchkeyword, String price1, String price2) throws IOException, InterruptedException
	{
		RemoteWebDriver driver = getDriver();
		Search.searchByCity(driver, searchkeyword);
		FilterResults_Page.filterByPrice(driver, price1, price2);
		String diditfilter = FilterResults_Page.verifyFilterByPrice(driver, price1, price2);
		
		try{
			Assert.assertEquals(diditfilter, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't filter by price.", e.getMessage());
			errorname = "didntfilterbyprice";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
		
		
		   softAssert.assertAll();
	}


	@Parameters({"browser", "platform"})
	@Test(groups= {"smoke", "regression"}, dataProvider = "getData") 
	public void filterResultsByBeds (String searchkeyword, String bed) throws IOException, InterruptedException
	{
		RemoteWebDriver driver = getDriver();
		Search.searchByCity(driver, searchkeyword);
		FilterResults_Page.filterByBeds(driver, bed);
		String diditfilter = FilterResults_Page.verifyFilterByBeds(driver, bed);
		
		try{
			Assert.assertEquals(diditfilter, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't filter by bed.", e.getMessage());
			errorname = "didntfilterbybed";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
		
		
		   softAssert.assertAll();
	}
	
	@Parameters({"browser", "platform"})
	@Test(groups= {"smoke", "regression"}, dataProvider = "getData") 
	public void filterResultsBybaths (String searchkeyword, String bath) throws IOException, InterruptedException
	{
		RemoteWebDriver driver = getDriver();
		Search.searchByCity(driver, searchkeyword);
		FilterResults_Page.filterByBaths(driver, bath);
		String diditfilter = FilterResults_Page.verifyFilterByBaths(driver, bath);
		
		try{
			Assert.assertEquals(diditfilter, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't filter by bath.", e.getMessage());
			errorname = "didntfilterbybath";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
		
		
		   softAssert.assertAll();
	}
	
}
