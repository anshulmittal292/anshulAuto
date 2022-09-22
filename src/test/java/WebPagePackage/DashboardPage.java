package WebPagePackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import webAutomation.basePage;

public class DashboardPage extends basePage{
	
	public DashboardPage(WebDriver driver) throws Exception{
		super(driver,DashboardPage.class);
		
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
	protected WebElement username;
	
	@FindBy(id="loginbutton") 
	protected WebElement loginbutton;
	
	@FindBy(xpath ="//input[@name='password']") 
	protected WebElement password;
	
	@FindBy(xpath ="//button[@type='submit']") 
	protected WebElement LoginSubmit;
	
	
}
