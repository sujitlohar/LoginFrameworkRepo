package FWpack;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import resources.Base;

public class TwoTest extends Base{
	WebDriver driver;

	@Test
	public void TestTwo() throws IOException {
		driver = initializeBrowser();
		driver.get(prop.getProperty("url"));
		System.out.println("test two");
		
	}
	
	@AfterMethod
	public void closure() {
		driver.close();
	}
	}
