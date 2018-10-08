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
import ua.shvidkoy.webproject.utill.PasswordHasher;

public class ChangePasswordCommand extends CommandStrategy {

	private final static Logger LOGGER = Logger.getLogger(ChangePasswordCommand.class);
	UserLogic userLogic;

	public ChangePasswordCommand(UserLogic userLogic) {
		this.userLogic = userLogic;
	}

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ApplicationException {
		LOGGER.debug("Command starts");
		HttpSession session = request.getSession();

		String oldPassword = request.getParameter("old_password");
		LOGGER.info("Request parameter: oldPassword --> " + oldPassword);
		String newPassword = request.getParameter("new_password");
		LOGGER.info("Request parameter: new_password --> " + newPassword);
		String confirmedPassword = request.getParameter("confirmed_password");
		LOGGER.info("Request parameter: confirmedPassword --> " + confirmedPassword);
		int userId = getUserId(request);
		String roleUser = request.getParameter("roleUser");
		LOGGER.info("Request parameter: role User --> " + roleUser);
		User user = userLogic.getUserByID(userId);
		LOGGER.info("User --> " + user);

		if (roleUser.equals("admin")) {
			LOGGER.info("True " );
			confirmedPassword = PasswordHasher.getHash(confirmedPassword);

			user.setPassword(confirmedPassword);
			userLogic.updateUser(user);

		} else {

			LOGGER.info(PasswordHasher.checkPassword(oldPassword, user.getPassword()));
			if (PasswordHasher.checkPassword(oldPassword, user.getPassword())) {
				newPassword = PasswordHasher.getHash(newPassword);

				user.setPassword(newPassword);
				LOGGER.info(PasswordHasher.checkPassword(confirmedPassword, user.getPassword()));

				if (PasswordHasher.checkPassword(confirmedPassword, user.getPassword())) {

					userLogic.updatePassword(user);
					LOGGER.info("Update completed");
					LOGGER.info("Updated user -->" + user);
				} else {
					throw new ApplicationException("New password does not match with Entered password");
				}

			} else {
				throw new ApplicationException("Entered password does not match with old password");
			}
		}

		String action = request.getParameter("action");
		LOGGER.info("Request parameter: action --> " + action);
		session.setAttribute("action", "Password changed");

		LOGGER.debug("Command finished");
		return new Router(RouteType.REDIRECT, Path.COMMAND_REDIRECT_AFTER_ACTION);
	}

	private int getUserId(HttpServletRequest request) throws ApplicationException {
		try {
			String userId = request.getParameter("userId");
			LOGGER.info("User id --> " + userId);
			return Integer.parseInt(userId);
		} catch (NumberFormatException e) {
			throw new ApplicationException("User didn't find: Cannot Update User ");

		}
	}
}