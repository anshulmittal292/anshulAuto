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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseSetup {

	public static WebDriver driver=null;
	public static WebDriverWait wait;
	public static String chromeDriverLocation=System.getProperty("user.dir")+"/src/main/resources/chromedriver.exe";
	public meeshoDashboardPage dp ;
	public meeshoHomePage hp;

	public BaseSetup() throws Exception  {

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
		
		 ChromeOptions options = new ChromeOptions();
         Proxy proxy = new Proxy();
         proxy.setHttpProxy("localhost:8080");
         proxy.setSslProxy("localhost:8080");
         options.setHeadless(true);
         options.setCapability(CapabilityType.PROXY, proxy);
         driver = new ChromeDriver(options);

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
			try{
				FileHandler.copy(source, new File("./Screenshots/"+result.getName()+".png"));
				System.out.println("Screenshot taken");
			}
			catch (Exception e)
			{
				System.out.println("Exception while taking screenshot "+e.getMessage());
			} 

		}
		driver.close();
	}
	@AfterSuite
	public void quitDriver() {
		System.out.println("aftersuite");
		try {

			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
			driver.quit();

		} catch (NullPointerException | WebDriverException e) {
			e.printStackTrace();
		}

	}
}
