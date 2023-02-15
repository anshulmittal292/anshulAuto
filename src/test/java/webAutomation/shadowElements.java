package webAutomation;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.Wait;

public class shadowElements {
	WebDriver driver;	
	@BeforeTest
	public void setBrowser() {
		WebDriverManager.chromedriver().setup();
	//	System.setProperty("webdriver.chrome.driver", "C:/Users/anshulm/Desktop/AnshulAutomation/src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://applicant-openanywhere-nightly-qa.banno-uat.com/");
	}

	@Test
	public void testElement() {
		driver.switchTo().frame("pact");
		driver.findElement(By.id("tea")).sendKeys("Green tea");
	}

	@Test
	public void openAnyWhereTest1() throws InterruptedException {
		Thread.sleep(7000);
		driver.findElement(By.xpath("//jha-button")).click();
		driver.findElement(By.xpath("//span[contains(.,'I have Online Banking with Financial Institution.')]")).click();
		driver.findElement(By.xpath("//button/div[contains(.,'Test1')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("anshul@gmail.com");
		
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebElement element = (WebElement)jse.executeScript("return document.querySelector(\"#snacktime\").shadowRoot.querySelector(\"#tea\")");
		String js = "arguments[0].setAttribute('value','green masala chai')";
		jse.executeScript(js, element);
	}
	
	@Test
	public void openAnyWhereTest2() throws InterruptedException {
		Thread.sleep(7000);
		driver.findElement(By.xpath("//jha-button")).click();
		driver.findElement(By.xpath("//span[contains(.,'I will be opening an account for the first time with this Financial Institution.')]")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("(//jha-form-email-input[@type='email'])[1]")).click();
		driver.findElement(By.xpath("(//jha-form-email-input[@type='email'])[1]")).sendKeys("anshul");
//		driver.findElement(By.xpath("(//jha-form-email-input[@type='email'])[1]")).getShadowRoot().findElement(By.tagName("jha-form-floating-group")).getShadowRoot().findElement(By.tagName("input")).sendKeys("anshul.mittal");
		
//		JavascriptExecutor jse = (JavascriptExecutor)driver;
//		WebElement element = (WebElement)jse.executeScript("return document.queryselector(jha-form-email-input[type=email]).shadowRoot.querySelector('input')");
//		String js = "arguments[0].setAttribute('value','anshul@gmail.com')";
//		jse.executeScript(js, element);
//		driver.findElement(By.xpath("(//jha-form-email-input[@type='email'])[1]")).sendKeys("testmail.mailinator.com");
//		driver.findElement(By.xpath("(//jha-form-email-input[@type='email'])[2]")).sendKeys("testmail.mailinator.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//jha-button")).click();
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
