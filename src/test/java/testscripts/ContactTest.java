package testscripts;

import java.net.MalformedURLException;


import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;


public class ContactTest {
	 AppiumDriver driver;
	 DesiredCapabilities cap;
	@BeforeTest
	public void setup() throws MalformedURLException {
		cap = new DesiredCapabilities();
		//For Tab
//		cap.setCapability("deviceName", "9026T");
//		cap.setCapability("udid", "LBFYHEPN7LGASKQS");
//		cap.setCapability("platformName", "Android");
//		cap.setCapability("platformVersion", "7.0");
		
		cap.setCapability("platformName", "Android");
        cap.setCapability("automationName", "UiAutomator2");
        cap.setCapability("platformVersion", "11.0");
        cap.setCapability("deviceName", "Android Emulator");
        
	}
	@BeforeMethod
	public void appSetup() throws MalformedURLException {
		cap.setCapability("appPackage", "com.android.contacts");
		cap.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity");

		URL url = new URL("http://localhost:4723/wd/hub");
		driver = new AppiumDriver(url, cap);
		System.out.println("Contacts Application Started");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	}
	@Test(priority=1)
	public void addContact() {
		driver.findElement(AppiumBy.id("com.android.contacts:id/floating_action_button")).click();
//		driver.findElement(AppiumBy.id("com.android.contacts:id/add_account_button")).click();
//		driver.findElement(AppiumBy.id("com.android.contacts:id/left_button")).click();
		
//		driver.findElementById("android:id/text1").click();
		
		driver.findElements(AppiumBy.className("android.widget.EditText")).get(0).sendKeys("CCC");
		driver.findElements(AppiumBy.className("android.widget.EditText")).get(1).sendKeys("Test");
		driver.navigate().back();
		WebElement element = driver.findElement(AppiumBy.androidUIAutomator("Phone"));
		element.sendKeys("888888888");
		driver.findElement(AppiumBy.id("com.android.contacts:id/editor_menu_save_button")).click();
	}

//	@Test(priority=2)
	public void searchAndDialTest() {

		driver.findElement(AppiumBy.id("com.android.contacts:id/search_view")).sendKeys("AVD");
		List<WebElement> contactlist = driver.findElements(AppiumBy.className("android.widget.TextView"));
		for (WebElement contact : contactlist) {
			if (contact.getText().equalsIgnoreCase("AVD")) {
				contact.click();
				break;
			}
		}
		driver.findElements(AppiumBy.className("android.view.ViewGroup")).get(1).click();
	}

	@AfterTest
	public void teardown() {
		if (null != driver) {
//	            driver.quit();
		}
	}
}
