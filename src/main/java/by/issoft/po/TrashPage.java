package by.issoft.po;

import by.issoft.config.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

import static java.lang.String.format;

public class TrashPage extends PageBase {

	private final WebDriver driver;
	private final String inputMessage = "//tr[contains(.//span[@email='%s'], '%s') and contains(.//span[@class='bqe'], '%s')]";

	public TrashPage() throws MalformedURLException {
		this.driver = WebDriverSingleton.getInstance().getDriver();
	}

	public boolean checkTrashEmail(String email, String username, String emailTitle) {
		return isElementPresent(By.xpath(format(inputMessage, email, username, emailTitle)));
	}
}
