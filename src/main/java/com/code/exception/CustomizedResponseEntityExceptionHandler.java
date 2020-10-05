package com.code.exception;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.code.utils.DriverUtil;

//Applicable across all the controllers
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	// Creating logger object
	static Logger logger = LoggerFactory.getLogger(DriverUtil.class);
	
	//Handle all the exceptions response
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		logger.debug("Inside handleAllExceptions");
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.toString(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	//Handle invalid url exception response
	@ExceptionHandler(InvalidUrlException.class)
	public final ResponseEntity<Object> handleInvalidUrlException(InvalidUrlException ex, WebRequest request) {
		logger.debug("Inside InvalidUrlException");
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
}