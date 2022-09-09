package WebPagePackage;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class meeshoHomePage {

	public meeshoHomePage(WebDriver driver)throws Exception {
		super();
		PageFactory.initElements(driver,this);
	}
	public boolean validateHomePage() {
		return profile.isDisplayed();
	}

	@FindBy(xpath="//div[@data-testid='profileSection']/div[contains(.,'Beautyfy')]")
	WebElement profile;
}
