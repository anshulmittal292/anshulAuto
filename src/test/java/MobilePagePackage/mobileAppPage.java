package MobilePagePackage;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import webAutomation.BaseSetup;
import webAutomation.basePage;

public class mobileAppPage extends basePage {

	public mobileAppPage(WebDriver driver) {
		super(driver, mobileAppPage.class);
	}

	public void alertAccepts() {
		allowAllert.click();
	}
	
	public void scrollToElementFromBottomToTopWithTextContains(WebDriver driver, WebElement element) {
        try {
        	JavascriptExecutor js = (JavascriptExecutor) driver;
        	Map<String, Object> params = new HashMap<>();
        	params.put("direction", "down");
        	params.put("element", ((RemoteWebElement) element));
        	while(!element.isDisplayed()) {
        	js.executeScript("mobile: scroll", params);
        	}
        } catch (Exception e) {
        	System.out.println("scroll exception"+e);
        }
        }
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Allow']")
	MobileElement allowAllert;

	@AndroidFindBy(id = "android:id/title")
	MobileElement element;
}
