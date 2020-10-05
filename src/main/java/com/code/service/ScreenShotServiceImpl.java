package com.code.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.code.controller.HomeController;
import com.code.exception.InvalidUrlException;
import com.code.utils.DriverUtil;

@Service
public class ScreenShotServiceImpl implements ScreenShotService {
	
	// Creating logger object
		Logger logger = LoggerFactory.getLogger(ScreenShotServiceImpl.class);
		
	@Override
	public byte[] capture(String url) {
		logger.debug("Inside capture method");
		logger.debug("Calling appendHttpsProtocol method on url: "+url);
		// Append https protocol if not provided in request
		url = appendHttpsProtocol(url);
		logger.debug("Url after appendHttpsProtocol: "+url);
		logger.debug("Initializing webdriver");
		// get chromedriver object
		WebDriver driver = DriverUtil.initDriver();
		logger.debug("Initialization complete");
		try {
			
			logger.debug("Calling url via selenium: "+url);
			// opening the url whose screenshot need to be captured
			driver.get(url);
			logger.debug("Url loaded success");
		} catch (WebDriverException e) {
			logger.debug("Failed to load url");
			// close the driver if exception occurs
			DriverUtil.destroyDriver();
			throw new InvalidUrlException("Invalid url: " + url);
		}

		byte[] bytes = null;

		// javascript function to hide the scrollbar on webpage while taking screenshot
		((JavascriptExecutor) driver).executeScript("document.body.style.overflow = 'hidden';");

		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {
			// Using selenium shutterbug to capture screenshot
			// write the image to outputstream
			logger.debug("Taking screenshot started");
			ImageIO.write(Shutterbug.shootPage(driver, Capture.FULL, 1000).getImage(), "png", outputStream);
			// convert the outputstream to bytes array
			logger.debug("Taking screenshot completed");
			bytes = outputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("Closing the driver");
		// closes the driver
		DriverUtil.destroyDriver();
		logger.debug("Returning data to controller");
		return bytes;

	}

	private String appendHttpsProtocol(String url) {
		logger.debug("Inside appendHttpsProtocol");
		// verifying if the url contains http protocol if not append it to url
		if (!(url.contains("https://") || url.contains("http://")))
			return "https://" + url;
		return url;
	}

}
