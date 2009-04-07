package org.adempiere.pipo.exception;

public class DatabaseAccessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DatabaseAccessException() {
		super();
	}

	public DatabaseAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatabaseAccessException(String message) {
		super(message);
	}

	public DatabaseAccessException(Throwable cause) {
		super(cause);
	}

}
