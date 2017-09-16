package com.blog.service.exception;

import java.io.Serializable;

public class PostServiceException extends RuntimeException implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PostServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PostServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PostServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PostServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
