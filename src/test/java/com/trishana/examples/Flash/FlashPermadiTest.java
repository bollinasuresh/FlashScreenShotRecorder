package com.trishana.examples.Flash;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlashPermadiTest {
	WebDriver driver;
	FlashSeleniumWebDriver flashDriver;

	@BeforeMethod
	public void createBrowserDriver() throws InterruptedException {
		// driver = chrome();
		driver = firefox();
	}

	private WebDriver firefox() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("javascript.enabled", true);
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(profile);
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
		return new FirefoxDriver(options);
	}

	private WebDriver chrome() {
		ChromeOptions options = new ChromeOptions();
		// disable ephemeral flash permissions flag
		options.addArguments("--disable-features=EnableEphemeralFlashPermission");
		Map<String, Object> prefs = new HashMap<String, Object>();
		// Enable flash for all sites for Chrome
		prefs.put("profile.content_settings.exceptions.plugins.*,*.setting", 1);
		options.setExperimentalOption("prefs", prefs);
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
		return driver = new ChromeDriver(options);
	}

	@Test
	public void play() throws InterruptedException {
		driver.get("http://www.permadi.com/tutorial/flashjscommand/");
		Thread.sleep(10);
		flashDriver = new FlashSeleniumWebDriver(driver, "myFlashMovie");
		flashDriver.call("play");
	}

}
