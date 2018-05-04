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

public class WebAppTitleTest {

	public static RemoteWebDriver driver;
	public static String appURL = "http://localhost:8080/WebApp-1.0.0-SNAPSHOT/sample.html";
	
	@BeforeClass
	@Parameters({ "browser" })
	public void setUp(String browser) throws MalformedURLException {

		driver = Browser.getDriver(browser);
		driver.manage().window().maximize();
	}
	
	@Test
	public void testGooglePageTitleInIE() throws Exception{
		driver.navigate().to("http://localhost:8080/WebApp-1.0.0-SNAPSHOT/sample.html");
		System.out.println("*********Testing inprogress**********");
		String strPageTitle = driver.getTitle();
		System.out.println("Actual sample html page title is: "+strPageTitle);
		this.takeSnapShot(driver, "WebAppsample-IE.png") ;
		Assert.assertTrue(strPageTitle.equalsIgnoreCase("SamplePage"), "Page title doesn't match");
		
		List <WebElement> list = driver.findElements(By.tagName("h1"));
		System.out.println("Number of links: "+list.size());
		for(int i = 0; i < list.size(); i++){
			System.out.println(list.get(i).getText());
		}
		System.out.println("*********Testing completed**********");
	}

	@AfterClass
	public void tearDown() {
		if(driver!=null) {
			System.out.println("Closing the browser");
			driver.quit();
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
