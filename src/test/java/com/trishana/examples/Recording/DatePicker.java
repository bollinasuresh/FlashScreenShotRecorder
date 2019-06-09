package com.trishana.examples.Recording;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DatePicker {
	WebDriver driver;

	@BeforeMethod
	public void createBrowser() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://portfolio.rediff.com/portfolio-login");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(10);
	}

	@Test
	public void dateRecordingTest() throws Exception {
		MyScreenRecorder.startRecording("rediffDate");
		testDynamicDate();
		MyScreenRecorder.stopRecording();
	}

	private void testDynamicDate() throws ParseException {
		String purchaseDate = "20/11/2024";
		Date pDate = new SimpleDateFormat("dd/MM/yyyy").parse(purchaseDate);
		String day = new SimpleDateFormat("dd").format(pDate);
		String month = new SimpleDateFormat("MMMM").format(pDate);
		String year = new SimpleDateFormat("yyyy").format(pDate);
		System.out.println(day + "-" + month + "-" + year);
		Date curDate = new Date();
		// November 2024
		String reqDateFormat = month + " " + year;
		driver.findElement(By.name("email-id")).sendKeys("bollinasuresh@rediffmail.com");
		driver.findElement(By.xpath("//*[@value='Continue']")).click();
		driver.findElement(By.name("passwd")).sendKeys("Rediff85");
		driver.findElement(By.xpath("//*[@value='Continue']")).click();
		System.out.println(driver.findElement(By.xpath("//*[@id='portfolioidName']")).getText());
		driver.findElement(By.id("addStock")).click();
		driver.findElement(By.xpath("//*[@class='stockCtnt']/div/div[2]/div[2]/a")).click();
		System.out.println(driver.findElement(By.xpath("//*[@id='datepicker']/table/tbody/tr[1]/td[3]/div")).getText());
		while (true) {
			String currentMonthYear = driver.findElement(By.cssSelector(".dpTitleText")).getText();
			if (reqDateFormat.equals(currentMonthYear)) {
				driver.findElement(By.xpath("//td[text()='" + day + "']")).click();
				break;
			} else {

				if (pDate.compareTo(curDate) > 0)
					driver.findElement(By.xpath("//*[@id='datepicker']/table/tbody/tr[1]/td[4]/button")).click();
				else if (pDate.compareTo(curDate) < 0)
					driver.findElement(By.xpath("//*[@id='datepicker']/table/tbody/tr[1]/td[2]/button")).click();

			}
		}
	}

	@AfterMethod
	public void close() {
		driver.quit();
	}
}
