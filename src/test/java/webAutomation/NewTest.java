package webAutomation;

import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import PagePackage.BaseSetup;
import PagePackage.meeshoDashboardPage;
import PagePackage.meeshoHomePage;
import utilities.webUtilities;

public class NewTest extends BaseSetup{
	public NewTest() throws Exception  {
		super();
}
	@Test
	public void validatePageTitle() throws Exception {
		dp=new meeshoDashboardPage(driver);
		  dp.navigateTo("https://supplier.meesho.com/panel/v3/new/root/login");
		  Properties dt = webUtilities.getData(System.getProperty("user.dir")+
		  "/src/test/resources/testData.properties");
		  dp.login(dt.getProperty("userName"), dt.getProperty("password")); 
		  hp=new meeshoHomePage(driver);
		 	hp.validateHomePage();
	}
	
	@Test
	public void validatePageTitle1() throws Exception {
		dp=new meeshoDashboardPage(driver);
		  dp.navigateTo("https://app.impact.com/login.user");
		  Properties dt = webUtilities.getData(System.getProperty("user.dir")+
		  "/src/test/resources/testData.properties");
		  Thread.sleep(9000);
		  driver.findElement(By.id("j_username")).sendKeys("anshul");
		 System.out.println(driver.findElement(By.id("loginButton")).isDisplayed());
	}
	
}
