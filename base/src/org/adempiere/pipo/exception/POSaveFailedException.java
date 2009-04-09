package org.adempiere.pipo.exception;

public class POSaveFailedException extends RuntimeException {

	public POSaveFailedException() {
		super();
	}

	public POSaveFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public POSaveFailedException(String message) {
		super(message);
	}

	public POSaveFailedException(Throwable cause) {
		super(cause);
	}
}
