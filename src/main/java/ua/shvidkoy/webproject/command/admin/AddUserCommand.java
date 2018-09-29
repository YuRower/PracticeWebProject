package ua.shvidkoy.webproject.command.admin;

import java.io.IOException;

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
import ua.shvidkoy.webproject.logic.AdminLogic;
import ua.shvidkoy.webproject.model.entity.User;
import ua.shvidkoy.webproject.utill.PasswordHasher;

public class AddUserCommand extends CommandStrategy {
	private final static Logger LOGGER = Logger.getLogger(RedirectToProfileCommand.class);
	private AdminLogic adminLogic;

	public AddUserCommand(AdminLogic adminLogic) {
		this.adminLogic = adminLogic;
	}
	
	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ApplicationException {
		LOGGER.debug("Command starts");
		HttpSession session = request.getSession();
		String firstName = request.getParameter("First Name");
		LOGGER.info("Request parameter: first name --> " + firstName);

		String lastName = request.getParameter("Last Name");
		LOGGER.info("Request parameter: password --> " + lastName);
		String login = request.getParameter("Login");
		LOGGER.info("Request parameter: login --> " + login);
		int roleId = getRoleId(request);
		LOGGER.info("Request parameter: role --> " + roleId);
		String action = request.getParameter("action");
		LOGGER.info("Request parameter: action --> " + action);
		session.setAttribute("action", action);


		/*
		 * String old_password = request.getParameter("old_password");
		 * LOGGER.info("Request parameter: password --> " + role); String new_password =
		 * request.getParameter("confirmed_password");
		 * LOGGER.info("Request parameter: password --> " + role);
		 */
		String confirmed_password = request.getParameter("confirmed_password");
		LOGGER.info("Request parameter: password --> " + confirmed_password);
		confirmed_password = PasswordHasher.getHash(confirmed_password);
		LOGGER.info("Request parameter: password --> " + confirmed_password);
		User user = new User(firstName,lastName,login,roleId,confirmed_password);
		LOGGER.trace("Created user --> " + user);
		adminLogic.newUserWithDefaultValues(user);

		LOGGER.debug("Command finished");
		return new Router(RouteType.FORWARD, Path.PAGE_COMPLETED_ADDITION);
	}
	private int getRoleId(HttpServletRequest request) {
		String userId = request.getParameter("Role");
		LOGGER.trace("Role id --> " + userId);
		return Integer.parseInt(userId);
	}
}
