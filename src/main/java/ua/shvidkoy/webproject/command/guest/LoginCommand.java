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
		LOGGER.info("Request parameter: login --> " + login);

		String password = request.getParameter("password");
		LOGGER.info("Request parameter: password --> " + password);
		//password = PasswordHash1.createHash(password);

		if (login == null || password == null || login.trim().isEmpty() || password.trim().isEmpty()) {
			throw new ApplicationException("Email/password cannot be empty");
		}

		User user = guestLogic.findUserByLogin(login);
		LOGGER.trace("Found in DB: user --> " + user);

		/*try {
			if (user == null || !PasswordHasher.verifyPassword(password, user.getPassword())) {
				throw new ApplicationException("Cannot find user with such login/password");
			}
		} catch (CannotPerformOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidHashException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		Role userRole = Role.getRole(user);
		LOGGER.trace("userRole --> " + userRole);

		String forward;

		switch (userRole) {
		case ADMIN:
			forward = Path.COMMAND_INITIALIZE_USER_SESSION;
			break;
		case USER:
			forward = Path.COMMAND_INITIALIZE_USER_SESSION;
			break;
		case GUEST:
		//	forward = Path.COMMAND_LIST_ADMINS;
			forward = Path.COMMAND_INITIALIZE_USER_SESSION;

			break;
		default:
			throw new ApplicationException("Unresolved usertype");
		}

		session.setAttribute("user", user);
		LOGGER.trace("Set the session attribute: user --> " + user);

		session.setAttribute("userRole", userRole);
		LOGGER.trace("Set the session attribute: userType --> " + userRole);

		LOGGER.info("User " + user + " logged as " + userRole.toString().toLowerCase());

		LOGGER.debug("Command finished");
		return new Router(RouteType.REDIRECT, forward);
	}

}