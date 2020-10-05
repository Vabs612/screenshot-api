package com.code.exception;

import java.util.Date;

/*
This class is use to provide generic exception handling structure for all the resources
 */
public class ExceptionResponse {
	// TimeStamp when the exception occurs
	private Date timestamp;
	// Exception message
	private String message;
	// Exception details
	private String details;

	// constructor
	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

    // getters
	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

}