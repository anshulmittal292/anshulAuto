package mobileAutomation;

import org.testng.annotations.Test;
import MobilePagePackage.mobileAppPage;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import webAutomation.BaseSetup;

public class testMobileApp extends BaseSetup {
	
	public testMobileApp() throws Exception {
		super();
	}
	
	@Test
	public void validatePageTitle() throws Exception {
		mobileAppPage=new mobileAppPage(driver);
		Thread.sleep(6000);
	//	mobileAppPage.alertAccepts();
}
}
