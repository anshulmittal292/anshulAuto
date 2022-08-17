package webAutomation;

import java.util.Properties;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import PagePackage.BaseSetup;
import PagePackage.meeshoDashboardPage;
import PagePackage.meeshoHomePage;
import utilities.webUtilities;

public class testMeeshoSupplier extends BaseSetup{
	public testMeeshoSupplier() throws Exception  {
		super();
}
	@Test
	public void validatePageTitle() throws Exception {
		dp=new meeshoDashboardPage(driver);
		dp.navigateTo("https://supplier.meesho.com/panel/v3/new/root/login");
		Properties dt = webUtilities.getData(System.getProperty("user.dir")+"/src/test/resources/testData.properties");
		dp.login(dt.getProperty("userName"), dt.getProperty("password"));
		hp=new meeshoHomePage(driver);
		hp.validateHomePage();
	}
	
	@Test
	public void validatePageTitle1() throws Exception {
		dp=new meeshoDashboardPage(driver);
		dp.navigateTo("https://supplier.meesho.com/panel/v3/new/root/login");
		Properties dt = webUtilities.getData(System.getProperty("user.dir")+"/src/test/resources/testData.properties");
		dp.login(dt.getProperty("userName"), dt.getProperty("password"));
		hp=new meeshoHomePage(driver);
		hp.validateHomePage();
	}
	
	
}
