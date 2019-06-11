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
import webPageObjects.SignUp_Page;

public class SignUp_NegativeTests extends TestBase{
	
	//****************************************//
	//***                                  ***//
	//*** Created by Angela Tong Apr 2018  ***//
	//***                                  ***//
	//****************************************//

	static SoftAssert softAssert = new SoftAssert();
	final static Logger log = LogManager.getLogger(SignUp_NegativeTests.class);
	
	
	static String className = SignUp_NegativeTests.class.getSimpleName();
 	static Date date1= new Date();
 	static String originaltimestamp = new Timestamp(date1.getTime()).toString();
 	static String timestamp = originaltimestamp.replace(':', 'x').substring(11);
	static String foldername = folderpath+className+timestamp;
	static String errorname = "";

	@Parameters({"browser", "platform"})	
	@Test(groups= "P3", dataProvider = "getData")
	public void signUpWithBlankFields (String firstname, String lastname, String email, String password) throws IOException, InterruptedException
	{
		RemoteWebDriver driver = getDriver();
		SignUp_Page.clickSignUp(driver);
		String firstnameisrequired = SignUp_Page.testBlankFirstName(driver);
		
		try
		{
			Assert.assertEquals(firstnameisrequired, "true");
		}
		catch(AssertionError e)
		{ 
			log.error("Didn't get required error message tip for firstname field on sign up page.", e.getMessage());
			errorname = "norequirederrmsgfirstnamesignup";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
		
		String lastnameisrequired = SignUp_Page.testBlankLastName(driver, firstname);
		
		try
		{
			Assert.assertEquals(lastnameisrequired, "true");
		}
		catch(AssertionError e)
		{ 
			log.error("Didn't get required error message tip for lastname field on sign up page.", e.getMessage());
			errorname = "norequirederrmsglastnamesignup";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
		
		String emailisrequired = SignUp_Page.testBlankEmail(driver, firstname, lastname);
		
		try
		{
			Assert.assertEquals(emailisrequired, "true");
		}
		catch(AssertionError e)
		{ 
			log.error("Didn't get required error message tip for email field on sign up page.", e.getMessage());
			errorname = "norequirederrmsgemailsignup";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
		
		
		String pwdisrequired = SignUp_Page.testBlankPassword(driver, firstname, lastname, email);
		
		try
		{
			Assert.assertEquals(pwdisrequired, "true");
		}
		catch(AssertionError e)
		{ 
			log.error("Didn't get required error message tip for password field on sign up page.", e.getMessage());
			errorname = "norequirederrmsgpasswordsignup";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
		
		
		softAssert.assertAll();

	}	

	@Parameters({"browser", "platform"})
	@Test(groups= "P3", dataProvider = "getData")
	public void signUpWithInvalidEmail (String firstname, String lastname, String invalid, String duplicate, String password) throws IOException, InterruptedException
	{
		RemoteWebDriver driver = getDriver();
		SignUp_Page.clickSignUp(driver);
		String invalidmsg = SignUp_Page.testInvalidEmail(driver, firstname, lastname, invalid, password);
		
		try{
			Assert.assertEquals(invalidmsg, "Oops, looks like the e-mail you entered is invalid.");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't get invalid email on sign up page.", e.getMessage());
			errorname = "noinvalidemailmsgonsignup";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
			
	   String duplicatemsg = SignUp_Page.testDuplicateEmail(driver, firstname, lastname, duplicate, password);
		
		try{
			Assert.assertEquals(duplicatemsg, "Good news! We've already got you in our system! Please sign in using the link above.");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't get duplicate email on sign up page.", e.getMessage());
			errorname = "noduplicateemailmsgonsignup";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
		
		softAssert.assertAll();
		
	}
	
	@Parameters({"browser", "platform"})
	@Test(groups="regression", dataProvider = "getData")
	public void signUpWithInvalidPassword (String firstname, String lastname, String email, String not8characters) throws IOException, InterruptedException
	{
		RemoteWebDriver driver = getDriver();
		SignUp_Page.clickSignUp(driver);
		String pwderrmsg = SignUp_Page.testInvalidPassword(driver, firstname, lastname, email, not8characters);
		
		try{
			Assert.assertEquals(pwderrmsg, "Password Requirements - at least 8 characters");
		} 
		catch(AssertionError e)
		{ 
			log.error("Didn't get at least 8 characters for password on sign up page.", e.getMessage());
			errorname = "noatleast8charsforpwdonsignup";
			ScreenshotURL.screenshotURL(driver, foldername, errorname);
			softAssert.fail();
		}
			

		
		softAssert.assertAll();
		
	}
	
}
