package com.trishana.examples.ScreenShots;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

public class GoogleTest {
	WebDriver driver;

	@BeforeMethod
	public void createBrowser() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//drivers//chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.google.com");
	}

	@AfterMethod
	public void close() {
		driver.quit();
	}

	@Test
	public void getGoogleLogoImage() throws IOException {
		WebElement gLogo = driver.findElement(By.xpath("//img[@id='hplogo']"));

		Screenshot googleImg = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, gLogo);
		ImageIO.write(googleImg.getImage(), "png",
				new File(System.getProperty("user.dir") + "\\ImageAndRecordings\\GoogleLogo.png"));
	}

	@Test
	public void getGoogleButtonImage() throws IOException {
		WebElement gSearchButton = driver.findElement(By.xpath("//div[@class='FPdoLc VlcLAe']//input[@name='btnK']"));
		Screenshot googleImg = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver,
				gSearchButton);
		ImageIO.write(googleImg.getImage(), "png",
				new File(System.getProperty("user.dir") + "\\ImageAndRecordings\\GoogleSearch.png"));
	}
}
