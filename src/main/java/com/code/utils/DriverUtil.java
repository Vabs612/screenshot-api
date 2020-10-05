package com.code.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * Class provides utility methods to create and destroy selenium webdriver instance
 */
public class DriverUtil {

	private static WebDriver driver = null;

	public static WebDriver initDriver() {
		Class<ChromeDriver> driverClass = ChromeDriver.class;
		// Setting up ChromeOptions to enable headless chrome and other options
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-popup-blocking,--ignore-certificate-errors",
				"--window-size=1080,1080");

		// Use to resolve the chromedriver.exe version based on chrome browser version
		// present on client system where api is running
		WebDriverManager.getInstance(driverClass).setup();

		try {
			// create chrome driver instance using options
			driver = new ChromeDriver(options);
			// define pageload timeout
			driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		} catch (WebDriverException ex) {
			ex.printStackTrace();
		}
		return driver;
	}

	public static void destroyDriver() {
		// close webdriver
		driver.quit();
	}
}
