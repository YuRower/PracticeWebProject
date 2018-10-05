package ua.shvidkoy.webproject.command.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.shvidkoy.webproject.command.CommandStrategy;
import ua.shvidkoy.webproject.controller.Router;
import ua.shvidkoy.webproject.exception.ApplicationException;
import ua.shvidkoy.webproject.logic.UserLogic;

public class UpdatePhotoCommand extends CommandStrategy {
	private final static Logger LOGGER = Logger.getLogger(UpdatePhotoCommand.class);

	UserLogic userLogic;

	public UpdatePhotoCommand(UserLogic userLogic) {
		this.userLogic = userLogic;
	}

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ApplicationException {
		LOGGER.debug("Command starts");
		HttpSession session = request.getSession();

		
		String photo = request.getParameter("pic");
		LOGGER.info("Request parameter: first name --> " + photo);
		return null;
	}

}
