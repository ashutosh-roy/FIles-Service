package com.algomox.filesservice.exception;

/**
 * Exception Class to handle persistence exception.
 *
 */

public class WEBPersistenceException extends WEBAppBaseException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("all")
	private  Object   data;
	
	/**
	 * Instantiates a new WEB persistence exception.
	 */
	public WEBPersistenceException() {
		super();
	}
	
	/**
	 * Instantiates a new WEB persistence exception.
	 *
	 * @param e the Exception Object
	 */
	public WEBPersistenceException(Exception e) {
		super(e);
	}

	/**
	 * Instantiates a new WEB persistence exception.
	 *
	 * @param message the exception message
	 * @param e the e
	 */
	public WEBPersistenceException(String message, Exception e) {
		super(message, e);
	}

	/**
	 * Instantiates a new WEB persistence exception.
	 *
	 * @param message the exception message
	 */
	public WEBPersistenceException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new WEB persistence exception.
	 *
	 * @param e the Throwable Object
	 */
	public WEBPersistenceException(Throwable e) {
		super(e);
	}
	
	/**
	 * Instantiates a new WEB persistence exception.
	 *
	 * @param message the exception message
	 * @param data any data object to be thrown with exception
	 */
	public WEBPersistenceException(String message, Object data) {
		super(message);
		this.data = data;
	}
	
	/**
	 * Instantiates a new WEB persistence exception.
	 *
	 * @param e the Throwable Object
	 * @param data any data object to be thrown with exception
	 */
	public WEBPersistenceException(Throwable e, Object data) {
		super(e);
		this.data = data;
	}
	
	/**
	 * Instantiates a new WEB persistence exception.
	 *
	 * @param message the exception message
	 * @param e the e
	 */
	public WEBPersistenceException(String message, Exception e, Object data) {
		super(message, e);
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
