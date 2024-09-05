package testscripts;

import java.net.MalformedURLException;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;


public class CalculatorTest {
	AppiumDriver driver;

	@BeforeTest
	public void setupApp() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
//		cap.setCapability("deviceName", "9026T");
//		cap.setCapability("udid", "LBFYHEPN7LGASKQS");
//		cap.setCapability("platformName", "Android");
//		cap.setCapability("platformVersion", "7.0");
//		cap.setCapability("appPackage", "com.tct.tablet.calculator");
//		cap.setCapability("appActivity", "com.tct.tablet.calculator.Calculator");
	
			
		cap.setCapability("platformName", "Android");
        cap.setCapability("automationName", "UiAutomator2");
        cap.setCapability("platformVersion", "11.0");
        cap.setCapability("deviceName", "Android Emulator");
        cap.setCapability("appPackage", "com.google.android.calculator");
        cap.setCapability("appActivity", "com.android.calculator2.Calculator");
        URL url = new URL("http://localhost:4723/wd/hub");
		driver = new AppiumDriver(url, cap);
		
		System.out.println("Application Started");
	}
	
	//In Tablet
//	@Test
//	public void testAddition() throws MalformedURLException {
//		driver.findElement(By.className("android.widget.Button")).click();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		MobileElement btn2 = driver.findElement(By.id("com.tct.tablet.calculator:id/digit_2"));
//		btn2.click();
//		MobileElement plus = driver.findElement(By.id("com.tct.tablet.calculator:id/op_add"));
//		plus.click();
//		MobileElement btn3 = driver.findElement(By.id("com.tct.tablet.calculator:id/digit_3"));
//		btn3.click();
//		MobileElement equal = driver.findElement(By.id("com.tct.tablet.calculator:id/eq"));
//		equal.click();
//		MobileElement result = driver.findElement(By.className("android.widget.EditText"));
//		String resValue = result.getText();
//		System.out.println("Result value is : "+resValue);
//	}
	
	//In Emulator
	@Test
	public void testAddition() throws MalformedURLException, InterruptedException {
//		driver.findElement(By.className("android.widget.Button")).click();
		Thread.sleep(1000);
	
		WebElement btn2 = driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_2"));
//		MobileElement btn2 = driver.findElement(By.id("com.google.android.caculator:id/digit_2"));
		btn2.click();
		WebElement plus = driver.findElement(AppiumBy.id("com.google.android.calculator:id/op_add"));
		plus.click();
		WebElement btn3 = driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_3"));
		btn3.click();
//		MobileElement equal = driver.findElement(By.id("com.google.android.calculator:id/eq"));
//		equal.click();
		WebElement result = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_preview"));
		String resValue = result.getText();
		System.out.println("Result value is : "+resValue);
	}
	@AfterTest
	public void teardown() {
		 if (null != driver) {
//	            driver.quit();
	        }
	}

}
