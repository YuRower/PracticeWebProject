package ua.shvidkoy.webproject.command.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.shvidkoy.webproject.command.CommandStrategy;
import ua.shvidkoy.webproject.command.guest.LoginCommand;
import ua.shvidkoy.webproject.constant.Path;
import ua.shvidkoy.webproject.controller.Router;
import ua.shvidkoy.webproject.controller.Router.RouteType;
import ua.shvidkoy.webproject.exception.ApplicationException;
import ua.shvidkoy.webproject.logic.AdminLogic;
import ua.shvidkoy.webproject.logic.GuestLogic;

public class RedirectToProfileCommand extends CommandStrategy {
	private final static Logger LOGGER = Logger.getLogger(RedirectToProfileCommand.class);

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ApplicationException {
		HttpSession session = request.getSession();
	
		LOGGER.debug("Command starts");
		LOGGER.trace("Redirecting to profile page");
		String param = request.getParameter("param");
		LOGGER.info("Request parameter: action --> " + param);

		LOGGER.debug("Command finished");
		return new Router(RouteType.FORWARD, Path.PAGE_PROFILE);
	}

}
