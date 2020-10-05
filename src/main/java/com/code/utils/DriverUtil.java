package com.code.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.code.controller.HomeController;

import io.github.bonigarcia.wdm.WebDriverManager;

/*
 * Class provides utility methods to create and destroy selenium webdriver instance
 */
public class DriverUtil {
	
	// Creating logger object
	static Logger logger = LoggerFactory.getLogger(DriverUtil.class);
	
	private static WebDriver driver = null;

	public static WebDriver initDriver() {
		logger.debug("Inside initDriver");
		Class<ChromeDriver> driverClass = ChromeDriver.class;
		logger.debug("Setting up options for chrome driver");
		// Setting up ChromeOptions to enable headless chrome and other options
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-popup-blocking,--ignore-certificate-errors",
				"--window-size=1080,1080");

		// Use to resolve the chromedriver.exe version based on chrome browser version
		// present on client system where api is running
		WebDriverManager.getInstance(driverClass).setup();
		logger.debug("Getting instance of chrome driver");
		try {
			// create chrome driver instance using options
			driver = new ChromeDriver(options);
			logger.debug("Created instance of chrome driver");
			// define pageload timeout
			driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		} catch (WebDriverException ex) {
			ex.printStackTrace();
		}
		return driver;
	}

	public static void destroyDriver() {
		// close webdriver
		logger.debug("Closing chrome driver");
		driver.quit();
	}
}
