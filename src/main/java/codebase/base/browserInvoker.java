package codebase.base;

import java.net.MalformedURLException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Driver invoked here.
 * @author Nagarajcruze
 *
 */
public class browserInvoker {

	public WebDriver createInstance(String Browser,String URL) throws MalformedURLException {
		WebDriver driver = null;
		
		try {
			switch(Browser.toUpperCase()) {
			case "FIREFOX":
				{
					FirefoxOptions options = new FirefoxOptions();
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver(options);
				}
				break;
			case "EDGE":
			{
					EdgeOptions options = new EdgeOptions();
					WebDriverManager.edgedriver().setup();
					driver=new EdgeDriver(options);
				}
				break;
			case "CHROME":
				{
					ChromeOptions options = new ChromeOptions();
					WebDriverManager.chromedriver().setup();
					driver=new ChromeDriver(options);
				}
				break;
			}
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			driver.manage().window().maximize();
			driver.get(URL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}
}
