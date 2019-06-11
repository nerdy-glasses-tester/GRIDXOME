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

public class FilterMoreResultsByResidentialOptions extends TestBase{
	
    //****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//
	
	static SoftAssert softAssert = new SoftAssert();
	final static Logger log = LogManager.getLogger(FilterMoreResultsByResidentialOptions.class);
	
	
	static String className = FilterMoreResultsByResidentialOptions.class.getSimpleName();
 	static Date date1= new Date();
 	static String originaltimestamp = new Timestamp(date1.getTime()).toString();
 	static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
	static String foldername = className+timestamp;
	static String errorname = "";

	@Parameters({"browser", "platform"})
	@Test(groups= {"smoke", "regression"}, dataProvider = "getData") 
	public void filterBySingleFamily (String searchkeyword) throws IOException, InterruptedException
	{
		RemoteWebDriver driver = getDriver();
		Search.searchByCity(driver, searchkeyword);
		FilterMoreResults_Page.clickOpenMoreFilters(driver);
		FilterMoreResults_Page.scrollDownMoreFiltersPage(driver);
		FilterMoreResults_Page.filterBySingleFamily(driver);
		FilterMoreResults_Page.applyMoreFilters(driver);
		String diditfilter = FilterMoreResults_Page.verifyFilterBySingleFamily(driver);

		try{
			Assert.assertEquals(diditfilter, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't filter by single family.", e.getMessage());
			errorname = "didntfilterbysinglefamily";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}

		   softAssert.assertAll();
	}


	@Parameters({"browser", "platform"})
	@Test(groups= {"smoke", "regression"}, dataProvider = "getData") 
	public void filterByCondo (String searchkeyword) throws IOException, InterruptedException
	{
		RemoteWebDriver webdriver = getDriver();
		Search.searchByCity(webdriver, searchkeyword);
		FilterMoreResults_Page.clickOpenMoreFilters(webdriver);
		FilterMoreResults_Page.scrollDownMoreFiltersPage(webdriver);
		FilterMoreResults_Page.filterByCondo(webdriver);
		FilterMoreResults_Page.applyMoreFilters(webdriver);
		String diditfilter = FilterMoreResults_Page.verifyFilterByCondo(webdriver);

		try{
			Assert.assertEquals(diditfilter, "yes");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't filter by condo/townhouse.", e.getMessage());
			errorname = "didntfilterbycondo";
			ScreenshotURL.screenshotURL(webdriver, foldername, errorname);
			softAssert.fail();
		}

		   softAssert.assertAll();
	}
	

}