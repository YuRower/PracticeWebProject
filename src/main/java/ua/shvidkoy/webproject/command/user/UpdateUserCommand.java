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
import ua.shvidkoy.webproject.model.enums.Role;
import ua.shvidkoy.webproject.utill.PasswordHasher;

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

		String roleUser = request.getParameter("roleUser");
		LOGGER.info("Request parameter: role User --> " + roleUser);

		String oldPassword = request.getParameter("old_password");
		LOGGER.info("Request parameter: oldPassword --> " + oldPassword);
		String newPassword = request.getParameter("new_password");
		LOGGER.info("Request parameter: new_password --> " + newPassword);
		String confirmedPassword = request.getParameter("confirmed_password");
		LOGGER.info("Request parameter: confirmedPassword --> " + confirmedPassword);

		User user = userLogic.getUserByID(userId);
		LOGGER.info("User --> " + user);
		String userRoleId = request.getParameter("Role");
		int roleId = -1;
		if (userRoleId != null) {
			roleId = Integer.parseInt(userRoleId);
			user.setUserRoleId(roleId);

			LOGGER.info("Request parameter: role --> " + roleId);
		}

		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setLogin(login);
		userLogic.updateUser(user);
		LOGGER.info("Update completed");
		LOGGER.info("Updated user -->" + user);

		

		LOGGER.debug("Command finished");
		return new Router(RouteType.FORWARD, Path.USER_UPDATED);
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

	private int getRoleId(HttpServletRequest request) {
		String userId = request.getParameter("Role");
		LOGGER.info("Role id --> " + userId);
		return Integer.parseInt(userId);
	}
}
