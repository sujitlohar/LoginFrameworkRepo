package FWpack;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import resources.Base;

public class FourTest extends Base {
	
	public WebDriver driver;
	
	@Test
	public void TestFour() throws IOException{
		
		System.out.println("test four");
		driver = initializeBrowser();
		driver.get(prop.getProperty("url"));
		Assert.assertTrue(false);
		
	}
	
	@AfterMethod
	public void closure() {
		driver.close();
	}
}