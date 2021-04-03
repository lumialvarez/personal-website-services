package com.lmalvarez.services.exception;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ApiException {
	private final String message;
	private final List<String> details;
	@JsonIgnore
	private final Throwable throwable;
	private final Integer statusCode;
	private final String statusDetail;
	private final ZonedDateTime timestamp;

	public ApiException(String message, List<String> details, Throwable throwable, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.details = details;
		this.throwable = throwable;
		this.statusCode = httpStatus.value();
		this.statusDetail = httpStatus.getReasonPhrase();
		this.timestamp = ZonedDateTime.now();
	}

	public String getMessage() {
		return message;
	}

	public List<String> getDetails() {
		return details;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public String getStatusDetail() {
		return statusDetail;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}
	

}
