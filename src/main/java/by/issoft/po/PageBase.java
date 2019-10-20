package by.issoft.po;

import by.issoft.config.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;

public abstract class PageBase {

	protected final WebDriver driver;
	protected final WebDriverWait wait;
	protected Actions actions;

	public PageBase() throws MalformedURLException {
		this.driver = WebDriverSingleton.getInstance().getDriver();
		wait = new WebDriverWait(driver, 15);
		actions = new Actions(driver);
	}

	public WebElement findByWithWait(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return driver.findElement(locator);
	}

	public boolean isElementPresent(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return !driver.findElements(locator).isEmpty();
	}
}
