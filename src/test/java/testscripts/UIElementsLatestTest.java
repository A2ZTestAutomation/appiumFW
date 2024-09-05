package testscripts;

import java.net.MalformedURLException;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;


public class UIElementsLatestTest {
	public static AppiumDriver driver;
	Dimension size;

	@BeforeMethod
	public void setupEmulator() throws MalformedURLException, InterruptedException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "Android Emulator");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "11.0");
		cap.setCapability("automationName", "UiAutomator2");
		cap.setCapability("appPackage", "io.appium.android.apis");
		cap.setCapability("appActivity", ".ApiDemos");

		URL url = new URL("http://localhost:4723/wd/hub");
		driver = new AppiumDriver(url, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("UI Application Started");
	}

	// views -> controls -> Dark Theme
	@Test
	public void emulatorChkBoxTest() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
				+ ".scrollIntoView(new UiSelector().text(\"Views\"))")).click();
		Thread.sleep(1000);
		// click controls
		driver.findElement(AppiumBy.accessibilityId("Controls")).click();
		Thread.sleep(1000);
		// click Dark Themes
		driver.findElement(AppiumBy.accessibilityId("2. Dark Theme")).click();

		driver.findElement(AppiumBy.id("io.appium.android.apis:id/edit")).sendKeys("Hello Welcome");

		// select checkbox
		driver.findElement(AppiumBy.accessibilityId("Checkbox 2")).click();

		// Select radio
		driver.findElement(AppiumBy.accessibilityId("RadioButton 2")).click();

		// Star
		driver.findElement(AppiumBy.id("io.appium.android.apis:id/star")).click();
		// toggle
		driver.findElement(AppiumBy.id("io.appium.android.apis:id/toggle1")).click();
		// Dropdown
		driver.findElement(AppiumBy.id("android:id/text1")).click();

		driver.findElements(AppiumBy.id("android:id/text1")).get(2).click();

		// driver.findElementByAccessibilityId("Earth").click();
		// Save
		driver.findElement(AppiumBy.id("io.appium.android.apis:id/button")).click();
	}

	

}
