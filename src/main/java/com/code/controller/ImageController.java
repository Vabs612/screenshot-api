package com.code.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.service.ScreenShotService;

@RestController

public class ImageController {

	@Autowired
	ScreenShotService service;


	@GetMapping(value = "/api", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getImageWithMediaType(@RequestParam("url") String url, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		//Set content type
		String contentType = "application/octet-stream";
		response.setContentType(contentType);
		
		//Calling ScreenShotService to return the byte[] of image
		byte[] capture = service.capture(url);
		
		//return byte[] to api and set the response status to 200 OK 
		return ResponseEntity.ok(capture);
	}

	
}
