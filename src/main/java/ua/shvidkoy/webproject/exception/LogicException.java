package ua.shvidkoy.webproject.exception;

public class LogicException extends ApplicationException {

	public LogicException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogicException(Throwable cause) {
        super(cause);
}

	
}
