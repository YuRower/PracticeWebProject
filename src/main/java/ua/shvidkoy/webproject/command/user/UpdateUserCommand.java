package ua.shvidkoy.webproject.command.user;

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
import ua.shvidkoy.webproject.logic.UserLogic;
import ua.shvidkoy.webproject.model.entity.User;

public class UpdateUserCommand extends CommandStrategy {
	private final static Logger LOGGER = Logger.getLogger(UpdateUserCommand.class);
	UserLogic userLogic;

	public UpdateUserCommand(UserLogic userLogic) {
		this.userLogic = userLogic;
	}

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ApplicationException {
		LOGGER.debug("Command starts");
		HttpSession session = request.getSession();

		int userId = getUserId(request);
		String firstName = request.getParameter("FirstName");
		LOGGER.info("Request parameter: first name --> " + firstName);

		String lastName = request.getParameter("LastName");
		LOGGER.info("Request parameter: LastName --> " + lastName);
		String login = request.getParameter("Login");
		LOGGER.info("Request parameter: login --> " + login);
		String photo = request.getParameter("pic");
		LOGGER.info("Request parameter: photo --> " + photo);
		int roleId = getRoleId(request);
		LOGGER.info("Request parameter: role --> " + roleId);

		String oldPassword = request.getParameter("old_password");
		LOGGER.info("Request parameter: oldPassword --> " + oldPassword);
		String newPassword = request.getParameter("new_password");
		LOGGER.info("Request parameter: new_password --> " + newPassword);
		String confirmedPassword = request.getParameter("confirmed_password");
		LOGGER.info("Request parameter: confirmedPassword --> " + confirmedPassword);

		User user = userLogic.getUserByID(userId);
		LOGGER.info("get user " + user);
		LOGGER.info("get user " + user.getPassword());

		LOGGER.info(user.getPassword().equals(oldPassword));
		if (user.getPassword().equals(oldPassword)) {
			user.setPassword(newPassword);
			LOGGER.info(user.getPassword().equals(confirmedPassword));

			if (user.getPassword().equals(confirmedPassword)) {
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setLogin(login);
				user.setUserRoleId(roleId);
				userLogic.updateUser(user);
				LOGGER.info("Update completed");
				LOGGER.info("get user " + user);
			} else {
				throw new ApplicationException("new password and confirmed problem");
			}

		} else {
			throw new ApplicationException("old and entered password problem");
		}

		String action = request.getParameter("action");
		LOGGER.info("Request parameter: action --> " + action);
		session.setAttribute("action", action);

		LOGGER.debug("Command finished");
		return new Router(RouteType.REDIRECT, Path.COMMAND_REDIRECT_AFTER_ACTION);
	}

	private int getUserId(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		LOGGER.info("User id --> " + userId);
		return Integer.parseInt(userId);
	}

	private int getRoleId(HttpServletRequest request) {
		String userId = request.getParameter("Role");
		LOGGER.info("Role id --> " + userId);
		return Integer.parseInt(userId);
	}
}
