package by.issoft.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {

	private static WebDriverSingleton instance;
	private WebDriver driver;

	private WebDriverSingleton() throws MalformedURLException {

		if (System.getProperty("environment").equalsIgnoreCase("local")) {
			driver = new ChromeDriver();
		} else if (System.getProperty("environment").equalsIgnoreCase("saucelabs")) {
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setCapability("platform", "Windows 8.1");
			firefoxOptions.setCapability("version", "39.0");

			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("username", "olgadenisyuk");
			caps.setCapability("accessKey", "e8a21a8a-89ab-4081-bbf7-5f06be8e4803");
			caps.setCapability("moz:firefoxOptions", firefoxOptions);

			driver = new RemoteWebDriver(new URL("https://ondemand.eu-central-1.saucelabs.com/wd/hub"), caps);
		}

		if (driver != null) {
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	}

	public static WebDriverSingleton getInstance() throws MalformedURLException {
		if (instance == null) {
			synchronized (WebDriverSingleton.class) {
				if (instance == null) {
					instance = new WebDriverSingleton();
				}
			}
		}
		return instance;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void closeWebDriver() {
		if (instance != null) {
			driver.quit();
			instance = null;
		}
	}

	public void goToPage(String URL) {
		driver.get(URL);
	}
}
