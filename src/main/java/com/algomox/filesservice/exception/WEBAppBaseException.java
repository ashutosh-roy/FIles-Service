package com.algomox.filesservice.exception;

/**
 * This is Base Exception class. All other exception should extend this class.
 *
 */
public abstract class WEBAppBaseException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new WEB app base exception.
	 */
	public WEBAppBaseException() {
		super();
	}
	
	/**
	 * Instantiates a new WEB app base exception.
	 *
	 * @param e the Exception Object
	 */
	public WEBAppBaseException(Exception e) {
		super(e);
	}

	/**
	 * Instantiates a new WEB app base exception.
	 *
	 * @param message the exception message 
	 * @param e the Exception Object
	 */
	public WEBAppBaseException(String message, Exception e) {
		super(message, e);
	}

	/**
	 * Instantiates a new WEB app base exception.
	 *
	 * @param message the exception message
	 */
	public WEBAppBaseException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new WEB app base exception.
	 *
	 * @param e the Exception Object
	 */
	public WEBAppBaseException(Throwable e) {
		super(e);
	}

}
