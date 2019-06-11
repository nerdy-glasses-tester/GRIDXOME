package webTests;

import base.TestBase;
import common.ScreenshotURL;
import webPageObjects.Search;
import webPageObjects.FilterMoreResults_Page;
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


public class FilterMoreResultsByStatus extends TestBase{
	
    //****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	
	static SoftAssert softAssert = new SoftAssert();
	final static Logger log = LogManager.getLogger(FilterMoreResultsByStatus.class);
	
	
	static String className = FilterMoreResultsByStatus.class.getSimpleName();
 	static Date date1= new Date();
 	static String originaltimestamp = new Timestamp(date1.getTime()).toString();
 	static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
	static String foldername = className+timestamp;
	static String errorname = "";

	@Parameters({"browser", "platform"})
	@Test( priority = 1, groups= {"smoke", "regression"}, dataProvider = "getData") 
	public void filterResultsByPendingStatus (String searchkeyword) throws IOException, InterruptedException
	{
		RemoteWebDriver driver = getDriver();
		Search.searchByCity(driver, searchkeyword);
		FilterMoreResults_Page.clickOpenMoreFilters(driver);
		FilterMoreResults_Page.uncheckedAllStatus(driver);
		FilterMoreResults_Page.filterByPendingStatus(driver);
		FilterMoreResults_Page.applyMoreFilters(driver);
		String diditfilter = FilterMoreResults_Page.verifyFilterByPendingStatus(driver);
		
		try{
			Assert.assertEquals(diditfilter, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't filter by pending status.", e.getMessage());
			errorname = "didntfilterbypendingstatus";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}

		   softAssert.assertAll();
	}
	

	@Parameters({"browser", "platform"})
	@Test(priority = 2, groups= {"smoke", "regression"}, dataProvider = "getData") 
	public void filterResultsBySoldStatus (String searchkeyword) throws IOException, InterruptedException
	{
		RemoteWebDriver driver = getDriver();
		Search.searchByCity(driver, searchkeyword);
		FilterMoreResults_Page.clickOpenMoreFilters(driver);
		FilterMoreResults_Page.uncheckedAllStatus(driver);
		FilterMoreResults_Page.filterBySoldStatus(driver);
	    FilterMoreResults_Page.applyMoreFilters(driver);
		String diditfilter = FilterMoreResults_Page.verifyFilterBySoldStatus(driver);
		
		try{
			Assert.assertEquals(diditfilter, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't filter by sold status.", e.getMessage());
			errorname = "didntfilterbysoldstatus";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
		
		
		   softAssert.assertAll();
	}
	
	@Parameters({"browser", "platform"})
	@Test(priority = 3, groups= {"smoke", "regression"}, dataProvider = "getData") 
	public void filterResultsByForSaleStatus (String searchkeyword) throws IOException, InterruptedException
	{
		RemoteWebDriver driver = getDriver();
		Search.searchByCity(driver, searchkeyword);
		FilterMoreResults_Page.clickOpenMoreFilters(driver);
		FilterMoreResults_Page.uncheckedAllStatus(driver);
		FilterMoreResults_Page.filterByForSaleStatus(driver);
	    FilterMoreResults_Page.applyMoreFilters(driver);
		String diditfilter = FilterMoreResults_Page.verifyFilterByForSaleStatus(driver);
		
		try{
			Assert.assertEquals(diditfilter, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't filter by for sale status.", e.getMessage());
			errorname = "didntfilterbyforsalestatus";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
		
		
		   softAssert.assertAll();
	}
	
}
