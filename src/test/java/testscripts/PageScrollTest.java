package testscripts;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class PageScrollTest {
	
	
	public static AppiumDriver driver;
	
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
	
	public static void scrollTill(By elem) {
		String prevPageSource = "";
		while(!isEndPage(prevPageSource)) {
			prevPageSource = driver.getPageSource();
			
			try {
				driver.findElement(elem).click();
			}
			catch(NoSuchElementException e) {
				scroll("DOWN", 0.5);
			}
		}
	}
	
	public static boolean isEndPage(String pageSource) {
		return pageSource.equals(driver.getPageSource());
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
	
	public static void scroll(String pageDirection, double scrollRatio) {
		  Duration SCROLL_DUR = Duration.ofMillis(300);
	        if (scrollRatio < 0 || scrollRatio > 1) {
	            throw new Error("Scroll distance must be between 0 and 1");
	        }
	        
	        Dimension size = driver.manage().window().getSize();
	        System.out.println("Screen Size = "+size);
	        System.out.println("");

	        Point midPoint = new Point((int)(size.width * 0.5),(int)(size.height * 0.5));
	        
	        int a = (int)(midPoint.x * scrollRatio);
	        int b = (int)(midPoint.y * scrollRatio);
	        
	        int bottom = midPoint.y + (int)(midPoint.y * scrollRatio); // 50 + 25        B
	        int top = midPoint.y - (int)(midPoint.y * scrollRatio); // 50 - 25           A
	        int left = midPoint.x - (int)(midPoint.x * scrollRatio); // 25 - 12.5         M
	        int right = midPoint.x + (int)(midPoint.x * scrollRatio); // 25 + 12.5        N

	        System.out.println("Midpoint: "+ midPoint);
	        
	        System.out.println("Midpoint x: "+ midPoint.x);   
	        System.out.println("a: "+ a);  
	        
	        System.out.println("Midpoint y: "+ midPoint.y);
	        System.out.println("b: "+ b);        

	        System.out.println("");
	        System.out.println("Bottom: "+ bottom);
	        System.out.println("Top: "+ top);
	        System.out.println("Right: "+ right);
	        System.out.println("Left: "+ left);
	        System.out.println("--------------------");
	        
	        if (pageDirection == "UP") {
	        	//Swipe Top to bottom, Page will go UP
	            swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
	        } else if (pageDirection == "DOWN") {
	            swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
	        } else if (pageDirection == "LEFT") {
	            swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), SCROLL_DUR);
	        } else {
	        	//RIGHT
	            swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), SCROLL_DUR);
	        }
		}
	
	@BeforeTest
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

  
  
  @Test
	public void horizontalScroll() throws InterruptedException {
		scrollTill(AppiumBy.accessibilityId("Views"));
		Thread.sleep(2000);
		scrollTill(AppiumBy.accessibilityId("Seek Bar"));
		Thread.sleep(2000);
		
	}
  
  @Test
	public void verticalScrollTest() throws InterruptedException {
	  scrollTill(AppiumBy.accessibilityId("Views"));
		scrollTill(AppiumBy.accessibilityId("Seek Bar"));
		
		WebElement seekBar = driver.findElement(AppiumBy.id("io.appium.android.apis:id/seek"));
		
		Thread.sleep(1000);
		longPress(seekBar);
		Thread.sleep(2000);
		String value1 = driver.findElement(AppiumBy.id("io.appium.android.apis:id/progress")).getText();
		System.out.println("Initial Value : " + value1);
		
		scroll("RIGHT", 0.7);
		
		seekBar.click();
		Thread.sleep(1000);
		value1 = driver.findElement(AppiumBy.id("io.appium.android.apis:id/progress")).getText();
		System.out.println("After Progress Value : " + value1);
		
		
	}

	
}
