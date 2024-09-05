package base;

import org.openqa.selenium.WebDriver;


import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReportTest {
	
	
	public static ExtentReports reports;
	public static ExtentSparkReporter sparkReport;
	
	@BeforeSuite
	public void reportSetup() {
		reports = new ExtentReports();
		sparkReport = new ExtentSparkReporter("ExtentReport.html");
		reports.attachReporter(sparkReport);
		
	}
	
	
	@AfterSuite
	public void reportTeardown() {
		reports.flush();
	}

}
