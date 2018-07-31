package com.test;

import java.net.MalformedURLException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.util.List;

public class WebAppTitleTestOnChrome {

	public static RemoteWebDriver driver;
	public static String appURL = "http://192.168.0.101:8090/WebApp-1.0.0-SNAPSHOT/training.html";
	
	@BeforeClass
	@Parameters({ "browser" })
	public void setUp(String browser) throws MalformedURLException {

		driver = Browser.getDriver(browser);
		driver.manage().window().maximize();
	}
	
	@Test
	public void testGooglePageTitleInChrome() throws Exception{
		driver.navigate().to("http://192.168.0.101:8090/WebApp-1.0.0-SNAPSHOT/training.html");
		System.out.println("*********Testing inprogress On Chrome**********");
		String actualTitle = driver.getTitle();
		String expectedValue = "DevOps Training"; //Clients requirement
		System.out.println("Actual training html page title is: "+actualTitle);
		System.out.println("Expected training html page title is: "+expectedValue);
		this.takeSnapShot(driver, "WebAppsample-Chrome.png") ;
		Assert.assertTrue(actualTitle.equalsIgnoreCase(expectedValue), "Page title doesn't match");
		
		List <WebElement> list = driver.findElements(By.tagName("h1"));
		System.out.println("Number of links: "+list.size());
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i).getText());
		}
		System.out.println("*********Testing completed On Chrome**********");
	}

	@AfterClass
	public void tearDown() {
		if(driver!=null) {
			System.out.println("Closing the Chrome browser");
			//driver.quit();
		}
	}
	public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

		//Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		//Move image file to new destination
		File DestFile=new File(fileWithPath);
		//Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
	}
}
