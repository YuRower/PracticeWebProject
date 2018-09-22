package ua.shvidkoy.webproject.command.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
import ua.shvidkoy.webproject.logic.GuestLogic;
import ua.shvidkoy.webproject.model.entity.User;
import ua.shvidkoy.webproject.model.enums.Role;

public class UserListCommand extends CommandStrategy {
	private GuestLogic guestLogic;
	Logger LOGGER = Logger.getLogger(UserListCommand.class);

	public UserListCommand(GuestLogic guestLogic) {
		this.guestLogic = guestLogic;
	}

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ApplicationException {
		// TODO Auto-generated method stub

		LOGGER.debug("Command starts");

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");
		LOGGER.debug("User --> " + user);

		List<User> users = guestLogic.findAllUsers();
		if (users.isEmpty()) {
			LOGGER.info("zero users ");
		}
		LOGGER.info(users.toString());
		
		 
		Role userRole = Role.getRole(1);
		LOGGER.info("userRole --> " + userRole);
		
		session.setAttribute("users", users);

		LOGGER.debug("Set the session attribute: users --> " + users);
		session.setAttribute("userRole", userRole);
		LOGGER.info("Set the session attribute: userRole--> " + userRole);

		LOGGER.debug("Command finished");
		// String forward = Path.COMMAND_INITIALIZE_USER;

		return new Router(RouteType.FORWARD, Path.PAGE_START);

		// return new Router(RouteType.REDIRECT, forward);
	}

}
