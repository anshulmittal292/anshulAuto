package webAutomation;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import MobilePagePackage.mobileAppPage;
import WebdriverSetup.ElementImp;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilities.webUtilities;

public class basePage extends ElementImp  {
	public webUtilities webUtils = new webUtilities();
	public basePage(WebDriver driver,Object className){
		//PageFactory.initElements(driver,this);
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(3)), mobileAppPage.class);
}
}
