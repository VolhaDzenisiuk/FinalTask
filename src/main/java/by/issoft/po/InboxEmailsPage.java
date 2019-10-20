package by.issoft.po;

import by.issoft.config.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.MalformedURLException;

import static java.lang.String.format;

public class InboxEmailsPage extends PageBase {

	private final WebDriver driver;

	private final String inputMessage = "//tr[contains(.//span[@email='%s'], '%s') and contains(.//span[@class='bqe'], '%s')]";
	private final String checkbox = inputMessage + "//div[@role='checkbox']";
	private final By deleteIcon = By.cssSelector("div[act='10']");
	private final By composeButton = By.xpath("//div[text()='Compose']");
	private final By toTextArea = By.cssSelector("textarea[name='to']");
	private final By SubjectInput = By.cssSelector("input[name='subjectbox']");
	private final By sendButton = By.xpath("//div[text()='Send']");
	private final By messageSentLabel = By.xpath("//span[text()='Message sent.']");

	public InboxEmailsPage() throws MalformedURLException {
		this.driver = WebDriverSingleton.getInstance().getDriver();
	}

	public void sendEmail(String receiverEmail, String emailTitle) {
		driver.findElement(composeButton).click();
		driver.findElement(toTextArea).sendKeys(receiverEmail);
		driver.findElement(SubjectInput).sendKeys(emailTitle);
		driver.findElement(sendButton).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(messageSentLabel));
	}

	public void deleteEmail(String email, String username, String emailTitle) {
		driver.findElement(By.xpath(format(inputMessage, email, username, emailTitle)));
		driver.findElement(By.xpath(format(checkbox, email, username, emailTitle))).click();
		driver.findElement(deleteIcon).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(messageSentLabel));
	}

	public boolean checkEmail(String email, String username, String emailTitle) {
		return isElementPresent(By.xpath(format(inputMessage, email, username, emailTitle)));
	}
}
