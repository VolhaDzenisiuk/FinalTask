package by.issoft.po;

import by.issoft.config.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.MalformedURLException;

public class NavigationPage extends PageBase {

	private final WebDriver driver;
	private final By sentEmailsLink = By.xpath("//a[contains(@href, 'sent')]/../../..");
	private final By trashLink = By.xpath("//div//a[contains(@href, 'trash')]");

	public NavigationPage() throws MalformedURLException {
		this.driver = WebDriverSingleton.getInstance().getDriver();
	}

	public SentEmailsPage goToSentEmailsPage() throws MalformedURLException {
		driver.findElement(sentEmailsLink).click();
		return new SentEmailsPage();
	}

	public TrashPage goToTrashPage() throws MalformedURLException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(trashLink));
		driver.findElement(trashLink).click();
		return new TrashPage();
	}
}
