package ua.shvidkoy.webproject.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.shvidkoy.webproject.controller.Router;
import ua.shvidkoy.webproject.exception.ApplicationException;

public abstract class Command {

	
	public abstract Router execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException,
			ApplicationException;

	@Override
	public final String toString() {
		return getClass().getSimpleName();
	}
}