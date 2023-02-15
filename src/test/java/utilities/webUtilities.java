package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import webAutomation.BaseSetup;

public class webUtilities {
	public static WebDriver driver;
	public WebElement el;

	public webUtilities() {
		driver = BaseSetup.driver;
	}

	public void navigateTo(String url) {
		driver.navigate().to(url);
		;
	}

	public static Properties getData(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;
	}

}
