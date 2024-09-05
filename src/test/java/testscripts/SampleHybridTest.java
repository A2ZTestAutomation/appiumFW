package testscripts;

import java.net.MalformedURLException;


import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;


public class SampleHybridTest {
	public static AndroidDriver driver;
	
	@BeforeTest
	public void setupDevice() throws MalformedURLException, InterruptedException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "Android Emulator");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "11.0");
		cap.setCapability("automationName", "UiAutomator2");
		cap.setCapability("appPackage", "com.wdiodemoapp");
		cap.setCapability("appActivity", ".MainActivity");
		URL url = new URL("http://localhost:4723/wd/hub");
		driver = new AndroidDriver(url, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("UI Application Started");
	}
	
	
	@Test
	public static void nativeToWebToNative() throws InterruptedException {
		System.out.println("Current Context..."+ driver.getContext());
		System.out.println("Current Context..."+ driver.getContextHandles());
	
		driver.findElement(AppiumBy.accessibilityId("Webview")).click();
		Thread.sleep(2000);
		System.out.println("Current Context..."+ driver.getContextHandles());
		//Get all contexts
		Set<String> handles = driver.getContextHandles();
		String webContext = new ArrayList<String>(handles).get(1);
		System.out.println("Fetch WebContext...."+ webContext);
		
		//Native to webview
		driver.context(webContext);
		System.out.println("After switching to Webcontext..."+ driver.getContext());
		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[contains(text(),'Get Started')]")).click();
		
		Thread.sleep(3000);
		//Webview to Native
		handles = driver.getContextHandles();
		String nativeContext = new ArrayList<String>(handles).get(0);
		System.out.println("Fetch NativeContext...."+ nativeContext);
		driver.context(nativeContext);
		Thread.sleep(1000);
		driver.findElement(AppiumBy.accessibilityId("Home")).click();
		Thread.sleep(1000);
		System.out.println("Current contextHandle..."+ driver.getContext());
				
		
	}

	    @AfterTest
	    public void tearDown() {
	        if (driver != null) {
//	            driver.quit();
	        }
	    }

	   
}
