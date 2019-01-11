package ua.shvidkoy.webproject.exception;

import java.io.Serializable;

public class ApplicationException extends Exception implements Serializable {
	

	public ApplicationException() {
		super();
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(Throwable cause) {
	        super(cause);
	}	
}