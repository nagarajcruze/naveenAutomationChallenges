package codebase.TestScripts;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import codebase.base.browserInvoker;
import codebase.functionLibrary.geneMethods;

public class sauceLabsChallenge {

	public WebDriver driver;
	browserInvoker bI = new browserInvoker();
	geneMethods objInv;
	public String Browser = "edge";
	public String URL = "https://www.saucedemo.com/";
	public int highPriceCount = 0;

	public By usrUsername = By.id("user-name");
	public By usrPassword = By.id("password");
	public By btnLogin = By.id("login-button");
	public By productPrices = By.xpath("//div[@class='inventory_item_price']");
	public By cartLogo = By.xpath("//a[@class='shopping_cart_link']");
	public By cartItemName = By.xpath("//div[@class='inventory_item_name']");
	public By cartItemPrice = By.xpath("//div[@class='inventory_item_price']");

	@BeforeClass
	public void setUp() {
		try {
			driver = bI.createInstance(Browser, URL);
			objInv = new geneMethods(driver);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void challengeOne() throws MalformedURLException, InterruptedException {

		try {
			objInv.getElement(usrUsername).sendKeys("standard_user");

			objInv.getElement(usrPassword).sendKeys("secret_sauce");

			objInv.getElement(btnLogin).click();

			List<WebElement> prices = objInv.getElements(By.xpath("//div[@class='inventory_item_price']"));

			double highestPrice = 0;

			for (int i = 0; i < prices.size(); i++) {
				double txtPrice = Double.parseDouble(prices.get(i).getText().substring(1));
				if (txtPrice > highestPrice) {
					highestPrice = txtPrice;
					highPriceCount = i + 1;
				}
			}

			objInv.getElement(
					By.xpath("(//div[@class='inventory_item_price'])[" + highPriceCount + "]/parent::div/button"))
					.click();

			objInv.getElement(cartLogo).click();

			System.out.println("Item Added to Cart : " + objInv.getElement(cartItemName).getText() + " | Price : "
					+ objInv.getElement(cartItemPrice).getText());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}

}
