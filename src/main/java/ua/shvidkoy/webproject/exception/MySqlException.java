package ua.shvidkoy.webproject.exception;

public class MySqlException extends ApplicationException {

	public MySqlException(String message, Throwable cause) {
		super(message, cause);
	}

	public MySqlException(Throwable e) {
		super(e);
	}

}
