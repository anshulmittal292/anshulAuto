package WebPagePackage;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webAutomation.basePage;

public class meeshoDashboardPage extends basePage{
	
	public meeshoDashboardPage(WebDriver driver) throws Exception{
		super(driver,meeshoDashboardPage.class);
		
		}
	
	public void navigateTo(String url) {
		webUtils.navigateTo(url);
	}
	public void login(String UN, String Pass) throws InterruptedException {
		Thread.sleep(4000);
		username.click();
		username.sendKeys(UN);
		password.click();
		password.sendKeys(Pass);
		LoginSubmit.click();
	}
	
	@FindBy(xpath ="//input[@name='emailOrPhone']") 
	WebElement username;
	
	@FindBy(id="loginbutton") 
	WebElement loginbutton;
	
	@FindBy(xpath ="//input[@name='password']") 
	WebElement password;
	
	@FindBy(xpath ="//button[@type='submit']") 
	WebElement LoginSubmit;
	
	
}
