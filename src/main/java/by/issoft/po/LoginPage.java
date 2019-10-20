package by.issoft.po;

import by.issoft.config.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class LoginPage extends PageBase {

	private final WebDriver driver;

	private static final By emailInput = By.cssSelector("input[type='email']");
	private static final By nextButtonOnEmailPage = By.cssSelector("div[id='identifierNext']");
	private static final By passwordInput = By.cssSelector("input[type='password']");
	private static final By nextButtonOnPasswordPage = By.cssSelector("div[id='passwordNext']");
	private static final By googleLogo = By.id("logo");

	public LoginPage() throws MalformedURLException {
		this.driver = WebDriverSingleton.getInstance().getDriver();
	}

	public LoggedInPage login(String email, String password) throws MalformedURLException {
		findByWithWait(emailInput).clear();
		driver.findElement(emailInput).sendKeys(email);
		driver.findElement(nextButtonOnEmailPage).click();
		findByWithWait(passwordInput).clear();
		driver.findElement(passwordInput).sendKeys(password);
		driver.findElement(nextButtonOnPasswordPage).click();
		return new LoggedInPage();
	}

	public boolean validateUserIsLoggedOut() {
		return findByWithWait(googleLogo).isDisplayed();
	}
}
