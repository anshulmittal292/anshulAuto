package MobilePagePackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import webAutomation.basePage;

public class mobileAppPage extends basePage {

	public mobileAppPage(WebDriver driver) {
		super(driver,mobileAppPage.class);
	}
	
	public void alertAccepts() {
		allowAllert.click();
	}
	
	@AndroidFindBy(xpath ="//android.widget.Button[@text='Allow']") 
	MobileElement allowAllert;
	
	
}
