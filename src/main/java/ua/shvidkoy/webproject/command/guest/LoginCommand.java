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
import ua.shvidkoy.webproject.utill.PasswordHasher.CannotPerformOperationException;
import ua.shvidkoy.webproject.utill.PasswordHasher.InvalidHashException;

public class LoginCommand extends CommandStrategy {
	private GuestLogic guestLogic;
	private final static Logger LOGGER = Logger.getLogger(CommandContainer.class);

	public LoginCommand(GuestLogic guestLogic) {
		this.guestLogic = guestLogic;
	}

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ApplicationException {
		LOGGER.debug("Command starts");

		HttpSession session = request.getSession();

		String login = request.getParameter("login");
		LOGGER.trace("Request parameter: login --> " + login);

		String password = request.getParameter("password");
		if (login == null || password == null || login.trim().isEmpty() || password.trim().isEmpty()) {
			throw new ApplicationException("Email/password cannot be empty");
		}

		User user = guestLogic.findUserByLogin(login);
		LOGGER.trace("Found in DB: user --> " + user);

		try {
			if (user == null || !PasswordHasher.verifyPassword(password, user.getPassword())) {
				throw new ApplicationException("Cannot find user with such login/password");
			}
		} catch (CannotPerformOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidHashException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Role userType = Role.getRole(user);
		LOGGER.trace("userType --> " + userType);

		String forward;

		switch (userType) {
		case ADMIN:
			forward = Path.COMMAND_LIST_REQUESTS;
			break;
		case USER:
			forward = Path.COMMAND_INITIALIZE_USER_SESSION;
			break;
		case GUEST:
			forward = Path.COMMAND_LIST_ADMINS;
			break;
		default:
			throw new ApplicationException("Unresolved usertype");
		}

		session.setAttribute("user", user);
		LOGGER.trace("Set the session attribute: user --> " + user);

		session.setAttribute("userType", userType);
		LOGGER.trace("Set the session attribute: userType --> " + userType);

		LOGGER.info("User " + user + " logged as " + userType.toString().toLowerCase());

		LOGGER.debug("Command finished");
		return new Router(RouteType.REDIRECT, forward);
	}

}