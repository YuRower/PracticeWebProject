package ua.shvidkoy.webproject.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ua.shvidkoy.webproject.command.Command;
import ua.shvidkoy.webproject.command.user.RedirectToProfileCommand;
import ua.shvidkoy.webproject.constant.Path;
import ua.shvidkoy.webproject.controller.Router;
import ua.shvidkoy.webproject.controller.Router.RouteType;
import ua.shvidkoy.webproject.exception.ApplicationException;


public class RedirectAfterAction  extends Command {
	private final static Logger LOGGER = Logger.getLogger(RedirectToProfileCommand.class);
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ApplicationException {
		LOGGER.debug("Command starts");
		LOGGER.debug("Redirecting to action page");
		LOGGER.debug("Command finished");
		return new Router(RouteType.FORWARD, Path.PAGE_COMPLETED_ADDITION);
	}
}
