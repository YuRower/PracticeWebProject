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

public class DeleteUserCommand extends CommandStrategy {
	private static final Logger LOGGER = Logger.getLogger(DeleteUserCommand.class);

	private AdminLogic adminLogic;

	public DeleteUserCommand(AdminLogic adminLogic) {
		this.adminLogic = adminLogic;
	}

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ApplicationException {
		LOGGER.debug("Command starts");
		HttpSession session = request.getSession();

		int userId = getUserId(request);
		deleteAdminById(userId);

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

	private void deleteAdminById(int userId) throws ApplicationException {
		adminLogic.getEntityById(userId);
		LOGGER.info("Admin will deleted --> " + userId);
	}
}
