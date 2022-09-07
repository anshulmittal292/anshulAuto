package PagePackage;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import utilities.webUtilities;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseSetup {

	public static WebDriver driver=null;
	public static WebDriverWait wait;
	public meeshoDashboardPage dp ;
	public meeshoHomePage hp;
	
	public ExtentTest test;
	public ExtentReports report;

	public BaseSetup() throws Exception  {

	}

	@BeforeSuite
	public void reportSetup() {
		report = new ExtentReports(System.getProperty("user.dir")+"/ExtentReportResults.html");
	}
	@BeforeMethod
	public void setup() throws IOException{
		String getEnv=System.getProperty("user.dir")+"/src/test/resources/testData.properties";
		Properties dt = webUtilities.getData(getEnv);
		String env =dt.getProperty("env");
		switch(env) {
		case("android"):
			androidDriver();
		break;
		case("chrome"):
			chromeDriver();
		break;
		case("firefox"):
			firefoxDriver();
		break;
		}

	}

	public static void chromeDriver() {
		WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();

	}

	public static void firefoxDriver() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();

	}


	public static void androidDriver() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "Pixel XL API 30");
		caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices"
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "11.0");
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if(ITestResult.FAILURE==result.getStatus())
		{
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			report.endTest(test); 
			test.log(LogStatus.FAIL,"Test Failed");
			try{
				FileHandler.copy(source, new File("./Screenshots/"+result.getName()+".png"));
				System.out.println("Screenshot taken");
			}
			catch (Exception e)
			{
				System.out.println("Exception while taking screenshot "+e.getMessage());
			} 

		}
		else if(ITestResult.SUCCESS==result.getStatus())
		{
			System.out.println("Test passed");
			report.endTest(test); 
			test.log(LogStatus.PASS,"Test Passed");
		}
		
		else if(ITestResult.SKIP==result.getStatus())
		{
			System.out.println("Test skipped");
			report.endTest(test); 
			test.log(LogStatus.SKIP,"Test Skipped");
		}
		driver.close();
	}
	
	@AfterSuite
	public void quitDriver(ITestResult result) {
		System.out.println("aftersuite");
		try {
			report.flush();
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
