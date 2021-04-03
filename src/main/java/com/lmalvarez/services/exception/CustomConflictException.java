package com.lmalvarez.services.exception;

public class CustomConflictException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5836285165535342191L;

	public CustomConflictException(String message) {
		super(message);
	}
	
	public CustomConflictException(String message, Throwable cause) {
		super(message, cause);
	}
}
