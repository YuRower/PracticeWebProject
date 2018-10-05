package ua.shvidkoy.webproject.command.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.shvidkoy.webproject.command.CommandStrategy;
import ua.shvidkoy.webproject.constant.Path;
import ua.shvidkoy.webproject.controller.Router;
import ua.shvidkoy.webproject.controller.Router.RouteType;
import ua.shvidkoy.webproject.exception.ApplicationException;
import ua.shvidkoy.webproject.logic.UserLogic;
import ua.shvidkoy.webproject.model.entity.Photo;
import ua.shvidkoy.webproject.model.entity.User;

public class RedirectToProfileCommand extends CommandStrategy {
	private final static Logger LOGGER = Logger.getLogger(RedirectToProfileCommand.class);
	UserLogic userLogic;

	public RedirectToProfileCommand(UserLogic userLogic) {
		this.userLogic = userLogic;
	}

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ApplicationException {
		HttpSession session = request.getSession();

		LOGGER.debug("Command starts");
		LOGGER.trace("Redirecting to profile page");

		if (request.getParameter("param") != null) {
			int id = getUserId(request);

			User user = userLogic.getUserByID(id);

			LOGGER.info("selected user --> " + user);
			session.setAttribute("userProfile", user);
			LOGGER.debug("Set the session attribute: userProfile --> " + user);
			Photo userPhoto = userLogic.loadPhotoById(id);
			session.setAttribute("userPhoto", userPhoto);
			LOGGER.debug("Set the session attribute: photo --> " + userPhoto);
		}

		LOGGER.debug("Command finished");

		return new Router(RouteType.FORWARD, Path.PAGE_PROFILE);
	}

	private int getUserId(HttpServletRequest request) {
		String userId = request.getParameter("param");
		LOGGER.info("Request parameter: action --> " + userId);
		return Integer.parseInt(userId);
	}
}
