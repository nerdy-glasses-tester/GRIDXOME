package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import base.TestBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class MiscMethods extends TestBase{
		
		public static ArrayList<Integer> sortDescending(ArrayList<Integer> arr){
		    Comparator<Integer> c = Collections.reverseOrder();
		    Collections.sort(arr,c);
		    return arr;
		  }


		public static ArrayList<Integer> sortAscending(ArrayList<Integer> arr){   
		    Collections.sort(arr);
		    return arr;
		  }
		
	    public static String returnsStringforIntegerArrayList(ArrayList<Integer> arr) {
	    		//String listString = arr.stream().map(Object::toString).collect(Collectors.joining(", ")); //This needs Java 1.8
	    		String listString = arr.toString().replaceAll(",","");
	    		return listString;
	    }

		
	    public static boolean isElementPresent(RemoteWebDriver driver, By selector) {
	    	 driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		        boolean returnVal = true;
		        try{
		            driver.findElement(selector);
		        } catch (NoSuchElementException e){
		            returnVal = false;
		        } finally {
		            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		        }
		        return returnVal;
	    }

	    
	    public static void swipeUp(AppiumDriver<?> appiumDriver) {
	        Dimension size = appiumDriver.manage().window().getSize();
	        int starty = (int) (size.height * 0.2);
	        int endy = (int) (size.height * 0.8);
	        int startx = size.width / 2;
	        new TouchAction(appiumDriver).press(PointOption.point(startx, starty)).waitAction().moveTo(PointOption.point(startx, endy)).release().perform();

	    }

	    public static void swipeDown(AppiumDriver<?> appiumDriver) {
	        Dimension size = appiumDriver.manage().window().getSize();
	        int starty = (int) (size.height * 0.8);
	        int endy = (int) (size.height * 0.2);
	        int startx = size.width / 2;
	        new TouchAction(appiumDriver).press(PointOption.point(startx, starty)).moveTo(PointOption.point(startx, endy)).release().perform();

	    }
	    

	     public static void exec(String deviceId,String command) throws Exception {
	            
	            String commandString;

	            if(deviceId != null) {
	                commandString = String.format("adb -s " + deviceId +" "+ command);
	            }else
	                commandString = String.format("adb shell " + command);
	            System.out.print("Command is "+commandString+"\n");

	            
	            try {
	                //Runtime.getRuntime().exec(commandString).waitFor();
		            Runtime.getRuntime().exec(new String[]{"adb -s ", deviceId, " ", command}).waitFor();
		            //Process p = new ProcessBuilder("adb -s ", deviceId, " ", command).start();
		            //p.waitFor();
	            } catch (Exception e) {
	            	  
	            }
	            
	        }
}
