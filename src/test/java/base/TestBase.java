package base;

import java.net.MalformedURLException;



import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;


public class TestBase extends ExtentReportTest{

	public static AppiumDriver driver;

	public DesiredCapabilities cap;
	//Before test for Android device

	public void setupAndroidRealDevice() {
		cap = new DesiredCapabilities();
		//Can set in three ways
		//cap.setCapability("platformName", "ANDROID");
		//cap.setCapability(CapabilityType.PLATFORM_NAME, "ANDROID");
		cap.setCapability("platformName", "ANDROID");
		cap.setCapability("platformVersion", "7.0");
		cap.setCapability("deviceName", "9026T");
		cap.setCapability("udid", "LBFYHEPN7LGASKQS");
		cap.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
//		cap.setCapability("browserName", "Chrome");
		try {
			URL url = new URL("http://localhost:4723/wd/hub");
			driver = new AppiumDriver(url, cap);
			//driver = new AndroidDriver<MobileElement>(url, cap);
			//driver = new IOSDriver<MobileElement>(url, cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	@BeforeTest
	public void setupEmulator() throws MalformedURLException, InterruptedException {
		cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "Android Emulator");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "11.0");
		cap.setCapability("automationName", "UiAutomator2");
//		 cap.setCapability("app", "F:\\AndroidDemoApk\\ApiDemos-debug.apk");
//		cap.setCapability("appPackage", "io.appium.android.apis");
//		cap.setCapability("appActivity", ".ApiDemos");
		cap.setCapability("browserName", "Chrome");

		URL url = new URL("http://localhost:4723/wd/hub");
		driver = new AppiumDriver(url, cap);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		System.out.println("UI Application Started");
	}

	@AfterTest
	public void teardown() {
//		driver.close();
	}
}
