package base;

import java.net.MalformedURLException;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.AppiumDriver;


public class ApkTestBase {
	public static AppiumDriver driver;
	@BeforeTest
	public void setup() {
		DesiredCapabilities cap = new DesiredCapabilities();
		//Can set in three ways
		//cap.setCapability("platformName", "ANDROID");
		//cap.setCapability(CapabilityType.PLATFORM_NAME, "ANDROID");
		
		cap.setCapability("deviceName", "Android Emulator");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "11.0");
		 cap.setCapability("automationName", "UiAutomator2");
	
		URL url = null;
		try {
			url = new URL("http://localhost:4723/wd/hub");
			driver = new AppiumDriver(url, cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void teardown() {
		driver.close();
	}
}
