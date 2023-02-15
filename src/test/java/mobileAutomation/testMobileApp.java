package mobileAutomation;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import MobilePagePackage.mobileAppPage;
import io.appium.java_client.android.AndroidDriver;
import webAutomation.BaseSetup;

public class testMobileApp extends BaseSetup {

	public testMobileApp() throws Exception {
		super();
	}

	@Test
	public void validatePageTitle() throws Exception {
		test = report.startTest("validatePageTitle");
		mobileAppPage = new mobileAppPage(driver);
		if( driver instanceof AndroidDriver) {
		    System.out.println("Android it is!!");
		    driver.findElement(By.xpath("//android.widget.EditText[@content-desc='test-Username']")).sendKeys("standard_user");
		    driver.findElement(By.xpath("//android.widget.EditText[@content-desc='test-Password']")).sendKeys("secret_sauce");
		    driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]/android.widget.TextView")).click();
		    mobileAppPage.scrollToElementFromBottomToTopWithTextContains(driver, driver.findElement(By.xpath("//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.widget.TextView[6]")));
		Thread.sleep(5000);  
		}
		
		// mobileAppPage.alertAccepts();
	}
}
