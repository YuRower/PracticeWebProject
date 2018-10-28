package ua.shvidkoy.webproject.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.shvidkoy.webproject.command.user.UpdateUserCommand;
import ua.shvidkoy.webproject.constant.Path;
import ua.shvidkoy.webproject.exception.ConnectionException;
import ua.shvidkoy.webproject.exception.LogicException;
import ua.shvidkoy.webproject.logic.GuestLogic;
import ua.shvidkoy.webproject.model.entity.User;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private final static Logger LOGGER = Logger.getLogger(SearchServlet.class);

	GuestLogic guestLogic = new GuestLogic();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		User user = null;
		String targetId = request.getParameter("id");
		LOGGER.info(targetId);
		StringBuffer sb = new StringBuffer();
		if (action.equals("complete")) {
			if (targetId != null) {
				targetId = targetId.trim().toLowerCase();
			} else {
				dispatch(request, response, Path.PAGE_ERROR_PAGE);
			}

			boolean namesAdded = false;

			if (!targetId.equals("")) {

				List<User> users = null;
				try {
					users = guestLogic.findAllUsers();
				} catch (LogicException | ConnectionException | NullPointerException e) {
					e.printStackTrace();
				}
				if (users.isEmpty()) {
					LOGGER.info("zero users ");
				}
				Iterator<User> it = users.iterator();
				while (it.hasNext()) {
					user = it.next();
					LOGGER.info(user.getFirstName());

					if (user.getLastName().trim().toLowerCase().startsWith(targetId)
							|| user.getLogin().trim().toLowerCase().startsWith(targetId)) {
						LOGGER.info(user.getFirstName());
						sb.append("<searchedUser>");
						sb.append("<id>" + user.getId() + "</id>");
						sb.append("<firstName>" + user.getFirstName() + "</firstName>");
						sb.append("<lastName>" + user.getLastName() + "</lastName>");
						sb.append("</searchedUser>");
						namesAdded = true;
						session.setAttribute("userProfile", user);
					}
				}
			}

			if (namesAdded) {
				response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("<searchedUsers>" + sb.toString() + "</searchedUsers>");
			} else {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}

		}
		if (action.equals("lookup")) {
			if ((targetId != null)) {
				LOGGER.info("dispatch");
				dispatch(request, response, Path.PAGE_PROFILE);
			}
		}
	}

	protected void dispatch(HttpServletRequest request, HttpServletResponse response, String page)
			throws javax.servlet.ServletException, java.io.IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}