package webAutomation;


import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.Properties;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import WebPagePackage.DashboardPage;
import WebPagePackage.HomePage;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utilities.webUtilities;

public class NewTestTest extends BaseSetup{
	public NewTestTest() throws Exception  {
		super();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
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
	
			
	@Test(groups={"GroupA"},dataProvider = "getData",invocationCount = 1, retryAnalyzer = utilities.RetryAnalyzer.class)
	public void validatePageTitle1() throws Exception {
		
		test = report.startTest("validatePageTitle1");
		dp=new DashboardPage(driver);
		dp.navigateTo("https://mahaonline.digital/sample-data-for-excel-practice/");
		File newFolder;
		newFolder = dp.createFolder();
		Thread.sleep(5000);
		dp.clickDownloadLink();
		Thread.sleep(5000);
		assertTrue(dp.ValidateFileExists(newFolder), "file downloaded success");
		Thread.sleep(5000);
		//Properties dt = webUtilities.getData(System.getProperty("user.dir")+
	//			"/src/test/resources/testData.properties");
	//	dp.login(dt.getProperty("userName"), dt.getProperty("password")); 
	}
	
	@DataProvider(name = "getData")
    public static Object[][] testData() {
        return new Object[][]{
                {"Amar", 16},
                {"Akbar", 16, 75}
        };
    }

}
