package by.issoft.po;

import by.issoft.config.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

import static java.lang.String.format;

public class SentEmailsPage extends PageBase {

	private final WebDriver driver;
	private static final By loggedInIcon = By.cssSelector("a[aria-label*='Google Account']");
	private final String inputMessage = "//tr[contains(.//span[@email='%s'], '%s') and contains(.//span[@class='bog'], '%s')]";

	public SentEmailsPage() throws MalformedURLException {
		this.driver = WebDriverSingleton.getInstance().getDriver();
	}

	public void hideLeftPanel() {
		actions.moveToElement(driver.findElement(loggedInIcon)).build().perform();
	}

	public boolean checkSentEmail(String email, String username, String emailTitle) {
		return isElementPresent(By.xpath(format(inputMessage, email, username, emailTitle)));
	}
}
