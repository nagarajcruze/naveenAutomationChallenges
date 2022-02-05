package codebase.functionLibrary;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Wrapper Methods are available here for reusability.
 * @author Nagarajcruze
 *
 */
public class geneMethods {
	WebDriverWait wait;

	public WebDriver driver;

	public geneMethods(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(forElementVisible(locator, 30));
		return element;
	}

	public List<WebElement> getElements(By locator) {
		List<WebElement> element = driver.findElements(locator);
		return element;
	}

	public By forElementVisible(By ele, int timeout) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOfElementLocated(ele));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ele;
	}

}
