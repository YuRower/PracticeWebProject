package ua.shvidkoy.webproject.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ua.shvidkoy.webproject.command.CommandStrategy;
import ua.shvidkoy.webproject.constant.Path;
import ua.shvidkoy.webproject.controller.Router;
import ua.shvidkoy.webproject.controller.Router.RouteType;

public class LogoutCommand extends CommandStrategy {
	
	Logger LOGGER = Logger.getLogger(LogoutCommand.class);

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("Command starts");
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		LOGGER.debug("Command finished");
		return new Router(RouteType.FORWARD, Path.INDEX);
	}

}