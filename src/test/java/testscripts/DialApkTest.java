package testscripts;

import java.net.MalformedURLException;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.reporters.jq.Main;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;


public class DialApkTest {
	AppiumDriver driver;

	
	@BeforeTest
	public void openDialer() throws MalformedURLException, InterruptedException {
	
	DesiredCapabilities cap = new DesiredCapabilities();
//	cap.setCapability("deviceName", "9026T");
//	cap.setCapability("udid", "LBFYHEPN7LGASKQS");
//	cap.setCapability("platformName", "Android");
//	cap.setCapability("platformVersion", "7.0");
	
	cap.setCapability("platformName", "Android");
    cap.setCapability("automationName", "UiAutomator2");
    cap.setCapability("platformVersion", "11.0");
    cap.setCapability("deviceName", "Android Emulator");
	cap.setCapability("appPackage", "com.android.dialer");
//	cap.setCapability("appActivity", "com.android.dialer.app.DialtactsActivity");
	cap.setCapability("appActivity", "com.android.dialer.main.impl.MainActivity");
	
	URL	url = new URL("http://localhost:4723/wd/hub");
	driver = new AppiumDriver(url, cap);
	
	Thread.sleep(5000);
	System.out.println("Contacts Application Started");
	
	}
	@Test
	public void dialNumberTest() {
	driver.findElement(AppiumBy.id("com.android.dialer:id/floating_action_button")).click();
	driver.findElement(AppiumBy.id("com.android.dialer:id/nine")).click();
	driver.findElement(AppiumBy.id("com.android.dialer:id/eight")).click();
	driver.findElement(AppiumBy.id("com.android.dialer:id/four")).click();
	driver.findElement(AppiumBy.id("com.android.dialer:id/one")).click();
	driver.findElement(AppiumBy.id("com.android.dialer:id/four")).click();
	driver.findElement(AppiumBy.id("com.android.dialer:id/three")).click();
	driver.findElement(AppiumBy.id("com.android.dialer:id/five")).click();
	driver.findElement(AppiumBy.id("com.android.dialer:id/one")).click();
	driver.findElement(AppiumBy.id("com.android.dialer:id/five")).click();
	driver.findElement(AppiumBy.id("com.android.dialer:id/three")).click();
	
	driver.findElement(AppiumBy.id("com.android.dialer:id/dialpad_floating_action_button")).click();
	
	}
	
	@AfterTest
	public void teardown() {
		 if (null != driver) {
//	            driver.quit();
	        }
	}
}
