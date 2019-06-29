package com.trishana.examples.Flash;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlashYoutubeTest {

	WebDriver driver;

	@BeforeMethod
	public void createBrowserDriver() throws InterruptedException {
		driver = getDriver();
	}

	private WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
		return new ChromeDriver();
	}

	@Test
	public void testYoutube() throws InterruptedException, FindFailed {
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.get("https://www.youtube.com/watch?v=rDoFiOjoC2Y");

		Screen screen = new Screen();
		
		Pattern mute = new Pattern(System.getProperty("user.dir") + "\\mute.png");
		screen.wait(mute, 2000);
		screen.click();

		Pattern pause = new Pattern(System.getProperty("user.dir") + "\\pause.png");
		screen.wait(pause, 2000);
		screen.click();
		screen.click();

		Pattern play = new Pattern(System.getProperty("user.dir") + "\\play.png");
		screen.wait(play, 2000);
		screen.click();
		screen.click();
		
		/*
		 * mute = new Pattern(System.getProperty("user.dir") + "\\mute.png");
		 * screen.wait(mute, 2000); screen.click();
		 */

	}

	@AfterMethod
	public void closeBrowserDriver() throws InterruptedException {
		driver.quit();
	}

}
