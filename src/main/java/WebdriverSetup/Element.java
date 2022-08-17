package WebdriverSetup;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;
import com.google.inject.ImplementedBy;

@ImplementedBy(ElementImp.class)
public interface Element extends WebElement, WrapsElement, Locatable {

	public void smartClick();
	
}
