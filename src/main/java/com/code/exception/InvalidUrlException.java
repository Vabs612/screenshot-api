package com.code.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidUrlException extends RuntimeException {
	
	// Custom exception class to handle invalid url provided by user
	public InvalidUrlException(String message) {
		super(message);
	}
}