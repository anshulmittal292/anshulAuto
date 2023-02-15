package utilities;


import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.html5.RemoteSessionStorage;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webdriver.ltree.utils.WaitSetup;

import webAutomation.BaseSetup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;
/**
 * Wait Class to be used by all POM's
 *
 * @author asharda, akumarjain
 *
 */
public class Wait implements WaitSetup {

    public int timeout = 0;

    /**
     * Explicit wait.Time is configurable here . This will be read from the wait properties file.
     */
    @Override
    public void explicitWait(WebDriver driver, int time, WebElement element) {
        try {
            waitForPageToLoad();
        } catch (WebDriverException e) {
            // do nothing in catch block, for Mobile app, waitForPageToLoad doesn't work and throws
            // WebDriverException: Not yet implemented
        }
        timeout = time / 1000;
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
    }
    /**
     * Wait to avoid StaleElement exception
     * @param driver
     * @param time
     * @param element
     */
    public void waitToAvoidStaleException(WebDriver driver, int time, WebElement element) {
        try {
            waitForPageToLoad();
        } catch (WebDriverException e) {
            // do nothing in catch block, for Mobile app, waitForPageToLoad doesn't work and throws
            // WebDriverException: Not yet implemented
        }
        timeout = time / 1000;
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
    } 

    /**
     * Waits for element to not be displayed
     *
     * @param timeMilliseconds
     * @param element
     * @author ewermuth
     */
    public void waitElementToNotBeDisplayed(int timeMilliseconds, WebElement element) {
        new FluentWait<>(element)
                .withTimeout(Duration.ofMillis(timeMilliseconds))
                .ignoring(StaleElementReferenceException.class)
                .until(el -> {
                    try {
                        return !el.isDisplayed();
                    } catch (NoSuchElementException ignored) {
                        return true;
                    }
                });
    }

    /**
     * Explicit wait.Time is configurable here . This will be read from the wait properties file.
     */
    @Override
    public WebElement explicitWaitBy(WebDriver driver, int time, By locator) {
        try {
            waitForPageToLoad();
        } catch (WebDriverException e) {
            // do nothing in catch block, for Mobile app, waitForPageToLoad doesn't work and throws
            // WebDriverException: Not yet implemented
        }
        timeout = time / 1000;
        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * This wait is used to Check the element visibility. This will be read from the wait properties
     * file.
     */
    @Override
    public void waitForElementToVisible(WebDriver driver, int time, WebElement element) {
        int timeout = time / 1000;
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * This wait is used to Check the element visibility. This will be read from the wait properties
     * file.
     */
    @Override
    public WebElement waitForElementToVisibleBy(WebDriver driver, int time, By locator) {
        int timeout = time / 1000;
        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * This wait is used to Check the element visibility. This will be read from the wait properties
     * file.
     */
    @Override
    public void waitForElementToBeInvisible(WebDriver driver, int time, WebElement element) {
        int timeout = time / 1000;
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * This wait is used to Check the element visibility using Fluent wait, polling every 10ms
     */
    @Override
	public void waitForElementToBeInvisibleFastAndFluent(WebDriver driver, int timeout, WebElement element) {
        FluentWait<WebDriver> fwait =
                new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(timeout)).pollingEvery(Duration.ofMillis(10)).ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        fwait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * This wait is used to Check the element visibility using Fluent wait, polling every 10ms
     */
    public void waitForElementToBeVisibleFastAndFluent(WebDriver driver, int timeout, WebElement element) {
        FluentWait<WebDriver> fwait =
                new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(timeout)).pollingEvery(Duration.ofMillis(10)).ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        fwait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * This Wait is used to wait until the element text visible
     *
     * @author sghosh
     */
    @Override
    public void waitForElementTextToBeVisible(WebDriver driver, int time, WebElement element, String text) {
        int timeout = time / 1000;
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }
    
    /**
     * Specify a condition to wait for. Poll every 10 ms. TimeUnit in MS.
     *
     * @param driver
     * @param timeout
     * @param element
     */
    public void fluentWait(WebDriver driver, int timeout, WebElement element) {
        FluentWait<WebDriver> fwait = new FluentWait<>(driver).withTimeout(Duration.ofMillis(timeout)).pollingEvery(Duration.ofMillis(50)).ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        fwait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Override
    public void fluenttWait() {
        // TODO Auto-generated method stub

    }

    /**
     * Add the waitForPageToLoad Method to wait for the ready state of the window Waits for the ready
     * state of the window
     *
     * @author ckaur
     */
    public static void waitForPageToLoad() {
        WebDriver Driver = BaseSetup.driver;
        JavascriptExecutor js = (JavascriptExecutor) Driver;
        String pageLoadStatus = null;
        do {
            pageLoadStatus = (String) js.executeScript("return document.readyState");
        } while (!pageLoadStatus.equals("complete"));
    }

    /**
     * This wait is used to Check that the string is present in URL This will be read from the wait
     * properties file.
     */
    public boolean waitStringPresentInURL(WebDriver driver, int time, String strURL) {
        int timeout = time / 1000;
        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.urlContains(strURL));
    }

    /**
     * Wait till the number of Browser Windows matches the desired count, used in switchControlWindow
     *
     * @param driver
     * @param numberOfWindows
     * @param timeInMiliSeconds
     * @author skancharla
     */
    public void waitForNumberOfWindowsToEqual(WebDriver driver, final int numberOfWindows, int timeInMiliSeconds) {
        timeout = timeInMiliSeconds / 1000;
        new WebDriverWait(driver, Duration.ofSeconds(timeout)) {}.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (driver.getWindowHandles().size() == numberOfWindows);
            }
        });
    }

    /**
     * Will wait till the given text is present in the element, not in its value
     * @param driver
     * @param time
     * @param element
     * @param text
     * @author psikri
     */
    public void waitForTextToBePresentInElement(WebDriver driver, int time, WebElement element, String text) {
        int timeout = time / 1000;
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    /**
     * Wait till a file is downloaded in the specified directory
     * @param directoryName, String expectedFileName, int timeoutDuration, int pollingDuration
     * @param expectedFileName
     * @throws IOException
     * @author rkulkarni
     */
    public void waitForFileDownload(WebDriver driver, String directoryName, String expectedFileName, int timeoutDuration, int pollingDuration) throws IOException {
        FluentWait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofMillis(timeoutDuration)).pollingEvery(Duration.ofMillis(pollingDuration));
        File fileToCheck = Paths.get(directoryName).resolve(expectedFileName).toFile();
        wait.until((WebDriver wd) -> fileToCheck.exists());
    }

    /**
     * Wait till the given attribute contains the given value
     * @param driver
     * @param time
     * @param element
     * @param value
     * @param attribute
     * @author psikri
     */
    public void waitForAttributeValueToContain(WebDriver driver, int time, WebElement element, String value, String attribute) {
        int timeout = time / 1000;
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    /**
     * Wait till the given attribute not contains the given value i,e wait till condition is true
     * @param driver
     * @param time
     * @param element
     * @param value
     * @param attribute
     * @author nikumar
     */
    public void waitUntilAttributeContainsGivenValue(WebDriver driver, int time, WebElement element, String value, String attribute) {
        int timeout = time / 1000;
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(new ExpectedCondition<Boolean>() {
            @Override
			public Boolean apply(WebDriver driver) {
                String attributeValue = element.getAttribute(attribute);
                if (!attributeValue.equals(value))
                    return true;
                else
                    return false;
            }
        });
    }

    /**
     * Wait till the Spinner gets invisible
     * @param driver
     * @param time
     * @param element
     * @author dchaudhary
     * @return boolean
     */
    
	public boolean waitTillSpinnerDisable(WebDriver driver, int time, WebElement element) {
        int timeout = time / 1000;
        FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver);
		fWait.withTimeout(Duration.ofSeconds(timeout));
		fWait.pollingEvery(Duration.ofMillis(500));
		fWait.ignoring(NoSuchElementException.class);
		Function<WebDriver, Boolean> func = new Function<WebDriver, Boolean>() 	{
			@Override
			public Boolean apply(WebDriver driver) {
				if(element.getCssValue("display").equalsIgnoreCase("none")){
					return true;}
				return false;
			}
		};
		return fWait.until(func);
    }


    /**
     * Waits for Given element till specified time
     * @author deepakc
     * @param driver
     * @param time
     * @param element
     * @param elementText
     * @return boolean
     */
    
	public boolean fluentWaitForElementToVisible(WebDriver driver, int time, WebElement element, String elementText) {
        int timeout = time / 1000;
        FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver);
		fWait.withTimeout(Duration.ofSeconds(timeout));
		fWait.pollingEvery(Duration.ofMillis(500));
		fWait.ignoring(NoSuchElementException.class);
		Function<WebDriver, Boolean> func = new Function<WebDriver, Boolean>() 	{
			@Override
			public Boolean apply(WebDriver driver) {
				return element.getText().contains(elementText);
			}
		};
		return fWait.until(func);
    }
    /**
     * Waits till the specified list contains the given number of elements
     * @param driver
     * @param time
     * @param elements
     * @param size
     * @author psikri
     */
    public <T> void waitForNumberOfElementsInListToBe(WebDriver driver, int time, List<T> elements, int size) {
        FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(time)).pollingEvery(Duration.ofMillis(10)).ignoring(IndexOutOfBoundsException.class);
        fwait.until((WebDriver wd) -> elements.size() == size);
    }

    /**
     * Wait till the number of elements to be less than given number
     * @param driver
     * @param time
     * @param locator
     * @param number
     * @author Styagi
     */
    public void waitForNumberOfElementsToBeLess(WebDriver driver, int time, By locator, Integer number) {
        int timeout = time / 1000;
        new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.numberOfElementsToBeLessThan(locator, number));
    }

    /**
     * Waits till the number of elements to be more than given number
     * @param driver
     * @param time
     * @param elements
     * @param number
     * @return int - number of elements
     * @author mbogdanov
     */
    public <T> int waitForNumberOfElementsToBeMore(WebDriver driver, int time, List<T> elements, int number) {
        FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(time)).pollingEvery(Duration.ofMillis(10)).ignoring(IndexOutOfBoundsException.class);
        fwait.until((WebDriver wd) -> elements.size() > number);
        return elements.size();
    }

    /**
     * Wait till the number of elements to be more than given number
     * @param driver
     * @param time
     * @param locator
     * @param number
     * @author Styagi
     */
    public List<WebElement> waitForNumberOfElementsToBeMore(WebDriver driver, int time, By locator, Integer number) {
        int timeout = time / 1000;
        return new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, number));
    }

    /**
     * Wait till the Session storage is not null
     * @param driver
     * @param timeout
     * @param sessionKey
     * @author Styagi
     */
    public void waitForSessionStorageToBeNotNull(WebDriver driver, int timeout, String sessionKey) {
        FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(timeout)).pollingEvery(Duration.ofMillis(50));
        fwait.until((WebDriver wd) -> new RemoteSessionStorage(new RemoteExecuteMethod((RemoteWebDriver) driver)).getItem(sessionKey) != null);
    }


    /**
     * Waits for the URL of the current page to be a specific URL
     * @param driver
     * @param time
     * @param strURL
     * @author adjain
     */
    public void waitURLToBe(WebDriver driver, int time, String strURL) {
        new WebDriverWait(driver, Duration.ofSeconds(time / 1000)).until(ExpectedConditions.urlToBe(strURL));
    }

    /**
     * Waits till the Alert is visible
     * @param driver
     * @param time
     * @author rdarolia
     */
    public void waitForAlertToBePresent(WebDriver driver, int time) {
        new WebDriverWait(driver, Duration.ofSeconds(time / 1000)).until(ExpectedConditions.alertIsPresent());
    }

    /**
     * Wait for the given attribute of the element to not exist (equals null)
     * @param driver
     * @param seconds
     * @param element
     * @param attributeName
     * @author ewermuth
     */
    public void waitAttributeToNotBeDisplayed(WebDriver driver, int seconds, WebElement element, String attributeName) {
        new WebDriverWait(driver,  Duration.ofSeconds(seconds)).until(drv -> element.getAttribute(attributeName) == null);
    }

    /**
     * Wait for frame to be available and switch to it
     *
     * @param driver
     * @param time
     * @param frame id or name
     * @author mbogdanov
     */
    @Override
    public void waitForFrameToBeAvailableAndSwitchToIt(WebDriver driver, int time, String frame) {
    	timeout = time / 1000;
        new WebDriverWait(driver, Duration.ofSeconds(time)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }

    /**
     *This methods waits until Ajax calling gets done
     *@author deepakc
     * @param driver
     * @param time
     */
    public  void waitForAjax(WebDriver driver, int time) {
    	timeout = time / 1000;
        new WebDriverWait(driver, Duration.ofSeconds(time)).until(new ExpectedCondition<Boolean>(){
            @Override
			public Boolean apply(WebDriver driver) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                return (Boolean) js.executeScript("return jQuery.active == 0");
            }
        });
    }
	@Override
	public void implicitWait(WebDriver driver, int timeout) {
		// TODO Auto-generated method stub
		
	}

    }
