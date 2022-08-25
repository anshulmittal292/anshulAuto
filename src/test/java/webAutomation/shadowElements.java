package webAutomation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class shadowElements {
	WebDriver driver;	
	@BeforeTest
	public void setBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://selectorshub.com/xpath-practice-page/");
	}

	@Test
	public void testElement() {
		driver.switchTo().frame("pact");
		driver.findElement(By.id("tea")).sendKeys("Green tea");
	}

	@Test
	public void testShadowElement() {
		driver.switchTo().frame("pact");
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		WebElement element = (WebElement)jse.executeScript("return document.querySelector(\"#snacktime\").shadowRoot.querySelector(\"#tea\")");
		String js = "arguments[0].setAttribute('value','green masala chai')";
		jse.executeScript(js, element);
	
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
