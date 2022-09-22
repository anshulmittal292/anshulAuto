package webAutomation;


import java.util.Properties;
import org.testng.annotations.Test;


import WebPagePackage.DashboardPage;
import WebPagePackage.HomePage;
import utilities.webUtilities;

public class NewTestTest extends BaseSetup{
	public NewTestTest() throws Exception  {
		super();
	}
	@Test(retryAnalyzer = utilities.RetryAnalyzer.class)
	public void validatePageTitle() throws Exception {
		test = report.startTest("validatePageTitle");
		dp=new DashboardPage(driver);
		dp.navigateTo("https://www.cleartrip.com/");
		Properties dt = webUtilities.getData(System.getProperty("user.dir")+
				"/src/test/resources/testData.properties");
		dp.login(dt.getProperty("userName"), dt.getProperty("password")); 
		hp=new HomePage(driver);
		//	hp.validateHomePage();
	}

	@Test(invocationCount = 1, retryAnalyzer = utilities.RetryAnalyzer.class)
	public void validatePageTitle1() throws Exception {
		test = report.startTest("validatePageTitle1");
		dp=new DashboardPage(driver);
		dp.navigateTo("https://supplier.meesho.com/panel/v3/new/root/login");
		Properties dt = webUtilities.getData(System.getProperty("user.dir")+
				"/src/test/resources/testData.properties");
		dp.login(dt.getProperty("userName"), dt.getProperty("password")); 
		hp=new HomePage(driver);
		hp.validateHomePage();
	}

}
