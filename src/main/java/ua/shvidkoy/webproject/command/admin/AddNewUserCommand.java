package ua.shvidkoy.webproject.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.shvidkoy.webproject.command.CommandStrategy;
import ua.shvidkoy.webproject.controller.Router;
import ua.shvidkoy.webproject.exception.ApplicationException;

public class AddNewUserCommand extends CommandStrategy {

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ApplicationException {
		return null;
	}

}
