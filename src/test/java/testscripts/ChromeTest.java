package testscripts;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.collect.ImmutableMap;

import base.TestBase;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;


public class ChromeTest extends TestBase{
	
	
	@BeforeMethod
	public void setupChrome() {
		cap.setCapability("browserName", "Chrome");
	}
		
	@Test
	public void loginTest() throws InterruptedException {
		ExtentTest test = reports.createTest("Chrome Test Run");
		test.log(Status.INFO, "Chrome test started");
		driver.get("https://the-internet.herokuapp.com/login");
		  driver.findElement(AppiumBy.xpath("//input[@id='username']")).sendKeys("tomsmith");
		  driver.findElement(AppiumBy.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword!");
		  driver.findElement(AppiumBy.xpath("//button[@type='submit']")).click();
		  Thread.sleep(2000);
		  Assert.assertTrue(driver.findElement(By.id("flash-messages")).isDisplayed());
	}
	
	

}
