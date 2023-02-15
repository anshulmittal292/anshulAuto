package WebPagePackage;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import webAutomation.basePage;

public class DashboardPage extends basePage {

	public DashboardPage(WebDriver driver) throws Exception {
		super(driver, DashboardPage.class);

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
	
	public void clickDownloadLink() {
		excelFileDownlodLink.click();
	}

	/**
	 * create a new folder in system directory
	 * 
	 * @author anshulm
	 */
	public File createFolder() {
		File outputFile = new File(System.getProperty("user.dir") + "/downloadedFiles");
		outputFile.mkdir();
		return outputFile;
	}
	
	/**
	 * validate if the file exist in the folder
	 * 
	 * @return boolean
	 * @author anshulm
	 */
	public boolean ValidateFileExists(File directory) {
		boolean flag = false;
		File directory1 = new File(directory.getAbsolutePath());
		if (directory1.isDirectory()) {
			if (directory1.list().length > 0) {
				flag = true;
			}
		}
		return flag;
	}
	@FindBy(xpath = "//input[@name='emailOrPhone']")
	protected WebElement username;

	@FindBy(xpath = "//a[contains(.,'Download  Excel File 1')]")
	protected WebElement excelFileDownlodLink;
	
	@FindBy(id = "loginbutton")
	protected WebElement loginbutton;

	@FindBy(xpath = "//input[@name='password']")
	protected WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	protected WebElement LoginSubmit;

}
