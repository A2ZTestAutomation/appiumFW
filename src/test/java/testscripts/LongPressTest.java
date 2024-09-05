package testscripts;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
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

public class LongPressTest {
	public static AppiumDriver driver;
	Dimension size;

	public static void longPress(WebElement elem) {
		Point location = elem.getLocation();
		PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		
		Sequence sequence = new Sequence(input, 0);
		sequence.addAction(
				input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), location.x, location.y));
		sequence.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		sequence.addAction(
				input.createPointerMove(Duration.ofSeconds(1), PointerInput.Origin.viewport(), location.x, location.y));
		sequence.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(ImmutableList.of(sequence));

	}
	
	public static void swipe(Point start, Point end, Duration duration) {
		PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(ImmutableList.of(swipe));

	}

	@BeforeMethod
	public void setupEmulator() throws MalformedURLException, InterruptedException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "Android Emulator");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "11.0");
		cap.setCapability("automationName", "UiAutomator2");
//		 cap.setCapability("app", "F:\\AndroidDemoApk\\ApiDemos-debug.apk");
		cap.setCapability("appPackage", "io.appium.android.apis");
		cap.setCapability("appActivity", ".ApiDemos");

		URL url = new URL("http://localhost:4723/wd/hub");
		driver = new AppiumDriver(url, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("UI Application Started");
	}

	//Views -> Expandable List -> 
	@Test
	public void longPressTest() throws InterruptedException {
		
		Thread.sleep(4000);
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
				+ ".scrollIntoView(new UiSelector().text(\"Views\"))")).click();

		// Scrolldown till particular element
		WebElement element = driver
				.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
						+ ".scrollIntoView(new UiSelector().text(\"Expandable Lists\"))"));
		element.click();
		Thread.sleep(1000);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"1. Custom Adapter\"]")).click();
		WebElement elem = 
				driver.findElement(AppiumBy.xpath("//*[contains(@text, 'Dog')]"));
		Thread.sleep(4000);
		longPress(elem);
		driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Sample action']")).click();
	}

	
}
