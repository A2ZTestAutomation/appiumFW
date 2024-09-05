package testscripts;

import java.net.MalformedURLException;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;


public class SMSApkTest {
	
	static AppiumDriver driver;

	public static void main(String[] args) throws InterruptedException {
		try {
			openMessage();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void openMessage() throws MalformedURLException, InterruptedException {
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "9026T");
		cap.setCapability("udid", "LBFYHEPN7LGASKQS");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "7.0");
		cap.setCapability("appPackage", "com.android.mms");
		cap.setCapability("appActivity", "com.android.mms.ui.composeMessageActivity");
		
		URL	url = new URL("http://localhost:4723/wd/hub");
		driver = new AppiumDriver(url, cap);
		
		Thread.sleep(5000);
		System.out.println("SMS Application Started");
	}

}
