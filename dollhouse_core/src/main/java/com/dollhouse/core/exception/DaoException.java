package com.dollhouse.core.exception;

public class DaoException extends Exception {
	private static final long serialVersionUID = 1423342566601204925L;

	public DaoException() {
		super();
	}
	
	public DaoException(String message) {
		super(message);
	}
	
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DaoException(Throwable cause) {
		super(cause);
	}
}