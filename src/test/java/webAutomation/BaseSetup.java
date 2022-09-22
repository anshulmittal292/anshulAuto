package webAutomation;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
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

import MobilePagePackage.mobileAppPage;
import WebPagePackage.DashboardPage;
import WebPagePackage.HomePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseSetup {

	public static WebDriver driver=null;
	public static WebDriverWait wait;
	public DashboardPage dp ;
	public HomePage hp;
	public mobileAppPage mobileAppPage;
	
	public static ExtentTest test;
	public static ExtentReports report;
	static AppiumDriverLocalService service;
	static AppiumServiceBuilder builder;
	static DesiredCapabilities caps;
	
	public BaseSetup() {

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
		caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "sdk_gphone_x86");
		caps.setCapability("deviceId", "emulator-5554"); //DeviceId from "adb devices"
		caps.setCapability("platformName", "Android");
		caps.setCapability("automationName", "UiAutomator2");
		caps.setCapability("appPackage","com.google.android.permissioncontroller");
		caps.setCapability("platformVersion", "11.0");
		caps.setCapability("app", "C:\\Users\\anshulm\\Downloads\\APKPure_v3.17.12_apkpure.com.apk");
        // caps.setCapability("noReset", "false");
		startServer();
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		
	}
	
	public static boolean startServer() {
	    //Build the Appium service
	    builder = new AppiumServiceBuilder();
	    builder.withIPAddress("127.0.0.1");
	    builder.usingPort(4723);
	    builder.withCapabilities(caps);
	    builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
	    builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

	    //Start the server with the builder
	    service = AppiumDriverLocalService.buildService(builder);
	    service.start();
	    return true;
	}
	
	public static void stopServer() {
	    service.stop();
	}
	
	public static String captureBase64() throws IOException {
        String encodedBase64 = null;
        FileInputStream fileInputStream = null;
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File("src/../BStackImages/" + System.currentTimeMillis()
        + ".png");
        String errflpath = Dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, Dest);
         
        try {
            
            fileInputStream =new FileInputStream(Dest);
            byte[] bytes =new byte[(int)Dest.length()];
            fileInputStream.read(bytes);
            encodedBase64 = new String(Base64.encodeBase64(bytes));
            System.out.println("Base64 screenshot captured");

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return "data:image/png;base64,"+encodedBase64;
        
        
        }
	
	public static String captureScreenShot(String fileName) {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File Dest = new File("Screenshots/"+fileName+".png");
		try{
			FileUtils.copyFile(source, Dest);
			System.out.println("Screenshot taken");
		}
		catch (Exception e)
		{
			System.out.println("Exception while taking screenshot "+e.getMessage());
		} 
		return Dest.getAbsolutePath();
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if(ITestResult.FAILURE==result.getStatus())
		{
			test.log(LogStatus.FAIL,"Test Failed");
		//	test.addBase64ScreenShot(captureBase64());
			System.out.println(captureScreenShot("testName"));
			test.addScreenCapture(captureScreenShot("testName"));
			report.endTest(test);
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
		report.flush();
		driver.quit();
		if(startServer()==true) {
		stopServer();
		}
	}
	
	@AfterSuite
	public void quitDriver() {
		System.out.println("aftersuite");
		try {
		//	driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
