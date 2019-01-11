package ua.shvidkoy.webproject.command.guest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.shvidkoy.webproject.command.Command;
import ua.shvidkoy.webproject.constant.Path;
import ua.shvidkoy.webproject.controller.Router;
import ua.shvidkoy.webproject.controller.Router.RouteType;
import ua.shvidkoy.webproject.exception.ApplicationException;
import ua.shvidkoy.webproject.model.entity.User;

public class SortUserCommand extends Command {

	private final static Logger LOGGER = Logger.getLogger(SortUserCommand.class);


	@Override
	public Router execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, ApplicationException {
		LOGGER.debug("Command starts");

		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) session.getAttribute("users");
		LOGGER.info("Can get user from session" + userList);

		if (userList == null) {
			LOGGER.info("Cant get user from session");
			throw new ApplicationException("Can't get user");
		}

		String sortBy = request.getParameter("sort_by");
		LOGGER.info("List will be sorted by " + sortBy);
		switch (sortBy) {

		case "Ascending":
			userList.sort((user1, user2) -> user1.getId() - user2.getId());
			break;
		case "Descending":
			userList.sort((user1, user2) -> user2.getId() - user1.getId());
			break;
		default:
			throw new ApplicationException("Can't get sort parameter");
		}

		session.setAttribute("users", userList);
		LOGGER.info("Get user sorted  list  " + userList);
		LOGGER.debug("Command finished");
		// response.setContentType("text/html;charset=UTF-8");
        // response.getWriter().write("True");
		return new Router(RouteType.FORWARD, Path.TABLE_SORT);
	}

}
