package br.com.testng.selenium.tests;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MainTest {
	
	WebDriver driver;

	@BeforeMethod
	public void setupBeforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=pt");
		driver = new ChromeDriver(options);
		this.driver.manage().window().maximize();
	}

	@AfterMethod
	public void setupAfterTest() {
		 driver.quit();
	}
	
	@Test
	public void sort () throws InterruptedException {
		driver.get("https://www.google.com/");
		
		// Take a picture before
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("screenshot-result\\" + timestamp.getTime() + "0-before.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// test
		Thread.sleep(500);
		driver.findElement(By.className("gLFyf")).sendKeys("Selenium tests");
		driver.findElement(By.className("gLFyf")).sendKeys(Keys.ENTER);
		Thread.sleep(1500);
		
		// Take a picture after
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("screenshot-result\\" + timestamp.getTime() + "1-after.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
