package tests;

import by.issoft.config.WebDriverSingleton;
import by.issoft.po.*;
import org.testng.annotations.*;

import java.net.MalformedURLException;

import static org.testng.Assert.assertTrue;

@Listeners(Listener.class)
class GmailTest {

	private final String SENDER_EMAIL = "seleniumtests10@gmail.com";
	private final String SENDER_PASS = "060788avavav";
	private final String SENDER_USERNAME = "Selenium Selenium";
	private final String RECEIVER_EMAIL = "seleniumtests30@gmail.com";
	private final String RECEIVER_PASS = "060788avavav";
	private final String RECEIVER_USERNAME = "Selenium";
	private final String EMAIL_TITLE = "Test email title";

	private static LoginPage loginPage;
	private static LoggedInPage loggedInPage;
	private static InboxEmailsPage inboxEmailsPage;
	private static SentEmailsPage sentEmailsPage;
	private static TrashPage trashPage;
	private static NavigationPage navigationPage;
	private static ChangeDeleteAccountPage changeDeleteAccountPage;

	public GmailTest() throws MalformedURLException {
		loginPage = new LoginPage();
		loggedInPage = new LoggedInPage();
		inboxEmailsPage = new InboxEmailsPage();
		sentEmailsPage = new SentEmailsPage();
		trashPage = new TrashPage();
		navigationPage = new NavigationPage();
		changeDeleteAccountPage = new ChangeDeleteAccountPage();
	}

	@BeforeClass(alwaysRun = true)
	public static void setUp() throws MalformedURLException {
		WebDriverSingleton.getInstance().goToPage("https://www.google.com/gmail");
	}

	@AfterClass(alwaysRun = true)
	public static void tearDown() throws MalformedURLException {
		WebDriverSingleton.getInstance().closeWebDriver();
	}

	@DataProvider(name = "loginData")
	public static Object[][] userLoginData() {
		return new Object[][]{{"seleniumtests10", "060788avavav"}, {"seleniumtests30", "060788avavav"}, {"seleniumtests50", "060788avavav"}};
	}

	@Test(dataProvider = "loginData")
	public void testLogin(String email, String password) throws MalformedURLException {
		loggedInPage = loginPage.login(email, password);
		assertTrue(loggedInPage.validateUserIsLoggedIn(), "Can't login to Gmail");
		loggedInPage.logout();
		changeDeleteAccountPage.changeAccount();
	}

	@Test(dataProvider = "loginData")
	void testLogout(String email, String password) throws MalformedURLException {
		loggedInPage = loginPage.login(email, password);
		loggedInPage.logout();
		assertTrue(loginPage.validateUserIsLoggedOut(), "Can't logout from Gmail");
		changeDeleteAccountPage.changeAccount();
	}

	@Test
	void testAbilityToSendEmails() throws MalformedURLException {
		loggedInPage = loginPage.login(SENDER_EMAIL, SENDER_PASS);
		inboxEmailsPage.sendEmail(RECEIVER_EMAIL, EMAIL_TITLE);
		loggedInPage.logout();
		changeDeleteAccountPage.changeAccount();
		loginPage.login(RECEIVER_EMAIL, RECEIVER_PASS);
		assertTrue(inboxEmailsPage.checkEmail(SENDER_EMAIL, SENDER_USERNAME, EMAIL_TITLE), "Can't find sent email");
		loggedInPage.logout();
		changeDeleteAccountPage.changeAccount();
	}

	@Test
	void testSentMailAppearanceInSentMailFolder() throws MalformedURLException {
		loggedInPage = loginPage.login(SENDER_EMAIL, SENDER_PASS);
		inboxEmailsPage.sendEmail(RECEIVER_EMAIL, EMAIL_TITLE);
		sentEmailsPage = navigationPage.goToSentEmailsPage();
		sentEmailsPage.hideLeftPanel();
		assertTrue(sentEmailsPage.checkSentEmail(RECEIVER_EMAIL, RECEIVER_USERNAME, EMAIL_TITLE), "Can't find sent email in Sent folder");
		loggedInPage.logout();
		changeDeleteAccountPage.changeAccount();
	}

	@Test
	void testDeletedEmailIsListedInTrash() throws MalformedURLException {
		loggedInPage = loginPage.login(SENDER_EMAIL, SENDER_PASS);
		inboxEmailsPage.sendEmail(RECEIVER_EMAIL, EMAIL_TITLE);
		loggedInPage.logout();
		changeDeleteAccountPage.changeAccount();
		loginPage.login(RECEIVER_EMAIL, RECEIVER_PASS);
		inboxEmailsPage.deleteEmail(SENDER_EMAIL,SENDER_USERNAME, EMAIL_TITLE);
		trashPage = navigationPage.goToTrashPage();
		assertTrue(trashPage.checkTrashEmail(SENDER_EMAIL,SENDER_USERNAME, EMAIL_TITLE), "Can't find deleted email in Trash folder");
		loggedInPage.logout();
		changeDeleteAccountPage.changeAccount();
	}
}
