package by.issoft.po;

import by.issoft.config.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class LoggedInPage extends PageBase {

	private final WebDriver driver;
	private final By loggedInIcon = By.cssSelector("a[href*='SignOutOptions']");
	private final By signOutButton = By.cssSelector("a[href*='Logout']");

	public LoggedInPage() throws MalformedURLException {
		this.driver = WebDriverSingleton.getInstance().getDriver();
	}

	public boolean validateUserIsLoggedIn() {
		return findByWithWait(loggedInIcon).isDisplayed();
	}

	public ChangeDeleteAccountPage logout() throws MalformedURLException {
		findByWithWait(loggedInIcon).click();
		findByWithWait(signOutButton).click();
		return new ChangeDeleteAccountPage();
	}
}
