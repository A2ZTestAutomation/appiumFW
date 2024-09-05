package base;

import java.net.MalformedURLException;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;

import io.appium.java_client.android.AndroidDriver;


public class EmulatorTestBase {

	AppiumDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("deviceName", "Android Emulator");
        //To install .apk
        caps.setCapability("app", "F:\\AndroidDemoApk\\ApiDemos-debug.apk");
        //To access apk
//        caps.setCapability("appPackage","com.touchboarder.android.api.demos");
        caps.setCapability("appPackage", "io.appium.android.apis");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
    }

   

    @AfterTest
    public void tearDown() {
        if (null != driver) {
//            driver.quit();
        }
    }
}
