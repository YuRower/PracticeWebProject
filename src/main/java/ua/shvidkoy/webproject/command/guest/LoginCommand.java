package ua.shvidkoy.webproject.command.guest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.shvidkoy.webproject.command.CommandContainer;
import ua.shvidkoy.webproject.command.CommandStrategy;
import ua.shvidkoy.webproject.constant.Path;
import ua.shvidkoy.webproject.controller.Router;
import ua.shvidkoy.webproject.controller.Router.RouteType;
import ua.shvidkoy.webproject.exception.ApplicationException;
import ua.shvidkoy.webproject.logic.GuestLogic;
import ua.shvidkoy.webproject.model.entity.User;
import ua.shvidkoy.webproject.model.enums.Role;
import ua.shvidkoy.webproject.utill.PasswordHasher;


public class LoginCommand extends CommandStrategy {
	private GuestLogic guestLogic;
	private final static Logger LOGGER = Logger.getLogger(LoginCommand.class);

	public LoginCommand(GuestLogic guestLogic) {
		this.guestLogic = guestLogic;
	}

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ApplicationException {
		LOGGER.debug("Command starts");

		HttpSession session = request.getSession();

		String login = request.getParameter("login");
		LOGGER.info("Request parameter: login --> " + login);

		String password = request.getParameter("password");
		LOGGER.info("Request parameter: password --> " + password);

		if (login == null || password == null || login.trim().isEmpty() || password.trim().isEmpty()) {
			throw new ApplicationException("Email/password cannot be empty");
		}

		User user = guestLogic.findUserByLogin(login);
		LOGGER.info("Found in DB: user --> " + user);

		if (user == null || !PasswordHasher.checkPassword(password, user.getPassword())) {
			throw new ApplicationException("Cannot find user with such login/password");
		}

		LOGGER.info("userRole --> " + user);

		Role role = Role.getRole(user);
		LOGGER.info("userRole --> " + role);

		session.setAttribute("user", user);
		LOGGER.info("Set the session attribute: user --> " + user);

		session.setAttribute("role", role);
		LOGGER.info("Set the session attribute: userType --> " + role);

		LOGGER.info("User " + user + " logged as " + role.toString().toLowerCase());

		LOGGER.debug("Command finished");
		return new Router(RouteType.REDIRECT, Path.COMMAND_INITIALIZE_USER_SESSION);
	}

}