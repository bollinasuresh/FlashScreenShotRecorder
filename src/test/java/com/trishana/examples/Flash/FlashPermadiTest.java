package com.trishana.examples.Flash;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlashPermadiTest {
	WebDriver driver;
	FlashSeleniumWebDriver flashDriver;

	@BeforeMethod
	public void createBrowserDriver() throws InterruptedException {
		driver = getDriver();
	}

	private WebDriver getDriver() {
		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\drivers\\IEDriverServer.exe");
		return new InternetExplorerDriver();
	}

	@Test
	public void testPermadi() throws InterruptedException {
		driver.get("http://www.permadi.com/tutorial/flashjscommand/");
		Thread.sleep(10);
		flashDriver = new FlashSeleniumWebDriver(driver, "myFlashMovie");

		flashDriver.call("Play"); // first number
		Thread.sleep(3000L);
		flashDriver.call("StopPlay"); // operation

		System.out.println(flashDriver.call("GetVariable", "/:message"));
		flashDriver.call("SetVariable", "/:message", "Testing Flash App");
		System.out.println(flashDriver.call("GetVariable", "/:message"));
		Thread.sleep(3000L);
		flashDriver.call("Rewind");
		Thread.sleep(3000L);
	}

	@AfterMethod
	public void closeBrowserDriver() throws InterruptedException {
		driver.quit();
	}
}
