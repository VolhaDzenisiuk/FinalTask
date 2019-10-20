package by.issoft.po;

import by.issoft.config.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class ChangeDeleteAccountPage extends PageBase {

	private final WebDriver driver;
	private static final By changeAccount = By.cssSelector("div > ul > li:nth-last-child(2)");

	public ChangeDeleteAccountPage() throws MalformedURLException {
		this.driver = WebDriverSingleton.getInstance().getDriver();
	}

	public LoginPage changeAccount() throws MalformedURLException {
		actions.moveToElement(findByWithWait(changeAccount)).click().perform();
		return new LoginPage();
	}
}
