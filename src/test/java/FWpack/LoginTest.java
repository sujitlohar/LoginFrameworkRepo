package FWpack;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class LoginTest extends Base {

	Logger log;
	WebDriver driver;
	
	@BeforeMethod
	public void openApplication() throws IOException {
		
		log = LogManager.getLogger(LoginTest.class.getName());
		
		driver = initializeBrowser();
		log.debug("browser got launched");
		driver.get(prop.getProperty("url"));
		log.debug("navigated to application url");
	}

	@Test(dataProvider = "getLoginData")
	public void login(String email, String password, String expectedResult) throws IOException, InterruptedException {
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.myAccountDropdown().click();
		log.debug("clicked on my account dropdown");
		landingPage.loginOption().click();
		log.debug("clicked on login option");
		
		Thread.sleep(2000);

		LoginPage loginPage = new LoginPage(driver);
//		loginPage.emailAddressField().sendKeys(prop.getProperty("email"));  //when single data is passed
//		loginPage.passwordField().sendKeys(prop.getProperty("password"));
		loginPage.emailAddressField().sendKeys(email);   //when multiple data is passed from dataprovider
		log.debug("email address got entered");
		loginPage.passwordField().sendKeys(password);
		log.debug("password got entered");
		loginPage.loginButton().click();
		log.debug("clicked on login button");

		AccountPage accountPage = new AccountPage(driver);
		String actualResult = null;
		try {
			if(accountPage.editAccountInfoOption().isDisplayed()) {
			actualResult = "Successful";
			log.debug("user got logged in");
			}
		} catch (Exception e) {
			actualResult = "Failure";
			log.debug("user didn't logged in");
		}
		Assert.assertEquals(actualResult, expectedResult);
		log.info("Login test got passed");
	}

	@DataProvider
	public Object[][] getLoginData() {

		Object[][] data = { { "sujitlohar1010@gmail.com", "sujit@123", "Successful" },
				{ "sujit@gmail.com", "123456", "Failure" } };
		return data;
	}

	@AfterMethod
	public void closure() {
		driver.close();
		log.debug("driver got closed");
	}
}
