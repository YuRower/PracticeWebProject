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
import ua.shvidkoy.webproject.model.entity.Photo;

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

		String action = request.getParameter("action");
		LOGGER.info("Request parameter: action --> " + action);
		session.setAttribute("action", action);
		String photoName = request.getParameter("pic");
		LOGGER.info("Request parameter: pic --> " + photoName);
		int id = getUserId(request);
		Photo checkPhoto = userLogic.loadPhotoById(id);
		if (checkPhoto == null) {
			Photo photo = new Photo(id, photoName);
			userLogic.insertPhoto(photo);
			userLogic.insertPhotoToUser(id);
			LOGGER.info("Created photo --> " + photo);
		} else {
			Photo photoForUpdate = userLogic.loadPhotoById(id);
			photoForUpdate.setName(photoName);
			userLogic.updatePhoto(photoForUpdate);
			session.setAttribute("action", "Your photo updated");
		}
		LOGGER.debug("Command finished");
		return new Router(RouteType.REDIRECT, Path.COMMAND_REDIRECT_AFTER_ACTION);
	}
	private int getUserId(HttpServletRequest request) {
		String userId = request.getParameter("user_photo_id");
		LOGGER.info("Request parameter: user_photo_id --> " + userId);
		return Integer.parseInt(userId);
	}

}
