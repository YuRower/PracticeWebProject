package ua.shvidkoy.webproject.command.guest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.shvidkoy.webproject.command.CommandStrategy;
import ua.shvidkoy.webproject.constant.Path;
import ua.shvidkoy.webproject.controller.Router;
import ua.shvidkoy.webproject.controller.Router.RouteType;
import ua.shvidkoy.webproject.exception.ApplicationException;

public class RedirectCanvas extends CommandStrategy {
	private final static Logger LOGGER = Logger.getLogger(RedirectCanvas.class);

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ApplicationException {


		
		LOGGER.debug("Command starts");
		LOGGER.debug("Redirecting to action page");
		LOGGER.debug("Command finished");
		return new Router(RouteType.FORWARD, Path.CANVAS_PAGE);

	}

}
