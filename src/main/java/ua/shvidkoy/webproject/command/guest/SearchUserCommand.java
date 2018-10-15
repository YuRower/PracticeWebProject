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
import ua.shvidkoy.webproject.model.entity.Photo;
import ua.shvidkoy.webproject.model.entity.User;

public class SearchUserCommand extends CommandStrategy {

	private GuestLogic guestLogic;
	private final static Logger LOGGER = Logger.getLogger(SearchUserCommand.class);

	public SearchUserCommand(GuestLogic guestLogic) {
		this.guestLogic = guestLogic;
	}

	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ApplicationException {

		LOGGER.debug("Command starts");

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("user");
		LOGGER.debug("User --> " + user);
		
		String seekedUser = request.getParameter("seeked_lastName");
		LOGGER.debug("User --> " + seekedUser);

		/*List<User> users = guestLogic.findAllUsers();
		if (users.isEmpty()) {
			LOGGER.info("zero users ");
		}
		LOGGER.info(users.toString());

		List<Photo> photos = guestLogic.loadPhoto();
		session.setAttribute("photos", photos);
		LOGGER.debug("Set the session attribute: photo --> " + photos);
		LOGGER.debug("Command finished");
	
		session.setAttribute("users", users);
		LOGGER.debug("Set the session attribute: users --> " + users);
		
		LOGGER.debug("Command finished");*/

		return new Router(RouteType.FORWARD, Path.PAGE_START);

	}


}
