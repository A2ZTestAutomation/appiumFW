package testscripts;

import java.net.MalformedURLException;

import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;

public class FirstRealDevSample {
	AppiumDriver driver;
	@BeforeTest
	public void setup() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "9026T");
		caps.setCapability("udid", "LBFYHEPN7LGASKQS");
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "7.0");
        //To install .apk
      caps.setCapability("app", "F:\\AndroidDemoApk\\ApiDemos.apk");
//        caps.setCapability("app", "F:\\AndroidDemoApk\\APKInfo.apk");
        //To access apk

        driver = new AppiumDriver(new URL("http://localhost:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	@Test
    public void click_App_Button() {
        // code
        driver.findElement(AppiumBy.accessibilityId("App")).click();
        driver.findElement(AppiumBy.accessibilityId("Alert Dialogs")).click();
      
    }

	@AfterTest
	public void teardown() {
		 if (null != driver) {
	            driver.quit();
	        }
	}
}
