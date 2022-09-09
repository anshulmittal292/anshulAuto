package webAutomation;


import java.util.Properties;
import org.testng.annotations.Test;

import WebPagePackage.meeshoDashboardPage;
import WebPagePackage.meeshoHomePage;
import utilities.webUtilities;

public class NewTestTest extends BaseSetup{
	public NewTestTest() throws Exception  {
		super();
	}
	@Test
	public void validatePageTitle() throws Exception {
		test = report.startTest("validatePageTitle");
		dp=new meeshoDashboardPage(driver);
		dp.navigateTo("https://supplier.meesho.com/panel/v3/new/root/login");
		Properties dt = webUtilities.getData(System.getProperty("user.dir")+
				"/src/test/resources/testData.properties");
		dp.login(dt.getProperty("userName"), dt.getProperty("password")); 
		hp=new meeshoHomePage(driver);
		//	hp.validateHomePage();
	}

	@Test
	public void validatePageTitle1() throws Exception {
		test = report.startTest("validatePageTitle1");
		dp=new meeshoDashboardPage(driver);
		dp.navigateTo("https://supplier.meesho.com/panel/v3/new/root/login");
		Properties dt = webUtilities.getData(System.getProperty("user.dir")+
				"/src/test/resources/testData.properties");
		dp.login(dt.getProperty("userName"), dt.getProperty("password")); 
		hp=new meeshoHomePage(driver);
		hp.validateHomePage();
	}

}
