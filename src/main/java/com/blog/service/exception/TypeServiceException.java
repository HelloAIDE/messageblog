package com.blog.service.exception;

import java.io.Serializable;

public class TypeServiceException extends RuntimeException implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TypeServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypeServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public TypeServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TypeServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public TypeServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
