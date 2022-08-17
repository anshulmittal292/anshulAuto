package WebdriverSetup;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.support.ui.Wait;



public class ElementImp implements Element {
	 private WebElement element = null;
	 
	   public void ElementImpl(final WebElement element) {
	        this.element = element;
	    }
	 
	@Override
	public void click() {
		// TODO Auto-generated method stub
		element.click();
		
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendKeys(CharSequence... keysToSend) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTagName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WebElement> findElements(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement findElement(By by) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCssValue(String propertyName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement getWrappedElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Coordinates getCoordinates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void smartClick() {
		element.click();		
	}

}
