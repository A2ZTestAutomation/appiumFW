package testscripts;

import java.net.MalformedURLException;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class DragAndDropTest {
	public static AppiumDriver driver;
	public Actions actions;

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
		driver = new AppiumDriver(url, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("UI Application Started");
	}

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
		System.out.println("In swipe method.....");
		PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
		Sequence swipe = new Sequence(input, 0);
		swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
		swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
		swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(ImmutableList.of(swipe));
		System.out.println("Done with Swipe......");

	}

	private static Point getCenter(WebElement el) {
		Point location = el.getLocation();
		Dimension size = el.getSize();
		return new Point(location.x + size.getWidth() / 2, location.y + size.getHeight() / 2);
	}

	@Test
	public static void dragAndDropTest() throws InterruptedException {

		Thread.sleep(4000);
		Duration SCROLL_DUR = Duration.ofMillis(300);
		driver.findElement(AppiumBy.accessibilityId("Drag")).click();
		WebElement source = driver.findElement(AppiumBy.accessibilityId("drag-c1"));
		WebElement target = driver.findElement(AppiumBy.accessibilityId("drop-c1"));
		Point pSource = getCenter(source);
		Point pTarget = getCenter(target);
		swipe(pSource, pTarget, SCROLL_DUR);
		System.out.println("Swipe done......");
	}

	@AfterTest
	public void teardown() {
		if (null != driver) {
			// driver.quit();
		}
	}
}
