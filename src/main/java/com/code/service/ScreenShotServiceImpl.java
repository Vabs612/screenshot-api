package com.code.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.springframework.stereotype.Service;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.code.exception.InvalidUrlException;
import com.code.utils.DriverUtil;

@Service
public class ScreenShotServiceImpl implements ScreenShotService {

	@Override
	public byte[] capture(String url) {

		// Append https protocol if not provided in request
		url = appendHttpsProtocol(url);

		// get chromedriver object
		WebDriver driver = DriverUtil.initDriver();
		try {

			// opening the url whose screenshot need to be captured
			driver.get(url);

		} catch (WebDriverException e) {
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
			ImageIO.write(Shutterbug.shootPage(driver, Capture.FULL, 1000).getImage(), "png", outputStream);
			// convert the outputstream to bytes array
			bytes = outputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// closes the driver
		DriverUtil.destroyDriver();
		return bytes;

	}

	private String appendHttpsProtocol(String url) {
		// verifying if the url contains http protocol if not append it to url
		if (!(url.contains("https://") || url.contains("http://")))
			return "https://" + url;
		return url;
	}

}
