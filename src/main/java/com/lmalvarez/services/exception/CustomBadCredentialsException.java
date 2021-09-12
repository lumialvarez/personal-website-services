package com.lmalvarez.services.exception;

public class CustomBadCredentialsException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5154018582403726971L;

	public CustomBadCredentialsException(String msg) {
		super(msg);
	}

	public CustomBadCredentialsException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
