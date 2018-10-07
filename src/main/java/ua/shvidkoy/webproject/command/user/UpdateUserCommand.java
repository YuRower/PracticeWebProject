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
		int roleId = getRoleId(request);
		LOGGER.info("Request parameter: role --> " + roleId);
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
		LOGGER.info("Request parameter: roleUser == Role.getRole(1) --> " + roleUser.equals("admin"));

		/*
		 * if (roleUser.equals("admin")) { user.setPassword(confirmedPassword);
		 * userLogic.updateUser(user);
		 * 
		 * } else {
		 */
		/*
		 * LOGGER.info(PasswordHasher.checkPassword(oldPassword, user.getPassword()));
		 * if (PasswordHasher.checkPassword(oldPassword, user.getPassword())) {
		 * newPassword = PasswordHasher.getHash(newPassword);
		 * 
		 * user.setPassword(newPassword);
		 * LOGGER.info(PasswordHasher.checkPassword(confirmedPassword,
		 * user.getPassword()));
		 * 
		 * if (PasswordHasher.checkPassword(confirmedPassword, user.getPassword())) {
		 * user.setFirstName(firstName); user.setLastName(lastName);
		 * user.setLogin(login); user.setUserRoleId(roleId); userLogic.updateUser(user);
		 * LOGGER.info("Update completed"); LOGGER.info("Updated user -->" + user); }
		 * else { throw new
		 * ApplicationException("New password does not match with Entered password"); }
		 * 
		 * } else { throw new
		 * ApplicationException("Entered password does not match with old password"); }
		 */
		// }

		String action = request.getParameter("action");
		LOGGER.info("Request parameter: action --> " + action);
		session.setAttribute("action", action);

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

	private int getRoleId(HttpServletRequest request) {
		String userId = request.getParameter("Role");
		LOGGER.info("Role id --> " + userId);
		return Integer.parseInt(userId);
	}
}
