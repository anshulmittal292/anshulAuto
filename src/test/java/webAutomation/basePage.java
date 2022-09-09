package webAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import WebdriverSetup.ElementImp;
import utilities.webUtilities;

public class basePage extends ElementImp  {
	public webUtilities webUtils = new webUtilities();
	public basePage(WebDriver driver,Object className){
		PageFactory.initElements(driver,this);
}
}
