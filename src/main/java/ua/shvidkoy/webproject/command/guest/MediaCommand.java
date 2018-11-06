package ua.shvidkoy.webproject.command.guest;

import java.io.IOException;
import java.util.List;

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
import ua.shvidkoy.webproject.logic.GuestLogic;
import ua.shvidkoy.webproject.model.entity.Media;

public class MediaCommand extends CommandStrategy {
	private GuestLogic guestLogic;
	private final static Logger LOGGER = Logger.getLogger(MediaCommand.class);

	public MediaCommand(GuestLogic guestLogic) {
		this.guestLogic = guestLogic;
	}

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ApplicationException {
		LOGGER.debug("Command starts");

		HttpSession session = request.getSession();

		List<Media> media= guestLogic.loadMedia();
		session.setAttribute("media", media);
		LOGGER.debug("Set the session attribute: medias --> " + media);
		LOGGER.debug("Command finished");

		return new Router(RouteType.FORWARD, Path.COMMAND_REDIRECT_TO_MEDIA);

	}

}
