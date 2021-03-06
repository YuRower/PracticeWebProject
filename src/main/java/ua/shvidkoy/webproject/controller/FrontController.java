package ua.shvidkoy.webproject.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import ua.shvidkoy.webproject.command.CommandContainer;
import ua.shvidkoy.webproject.command.Command;
import ua.shvidkoy.webproject.constant.Path;
import ua.shvidkoy.webproject.controller.Router.RouteType;
import ua.shvidkoy.webproject.exception.ApplicationException;
import ua.shvidkoy.webproject.model.connectionpool.ConnectionPool;

public class FrontController extends HttpServlet {
	private final static Logger LOGGER = Logger.getLogger(FrontController.class);


	@Override
	public void init() throws ServletException {
		ConnectionPool.getInstance();
		 LOGGER.log(Level.INFO, "Init servlet");
	}

	@Override
	public void destroy() {
		LOGGER.log(Level.INFO, "Destroy servlet");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		LOGGER.debug("Controller starts");
		
		LOGGER.debug(request.getParameter("command"));

		String commandName = request.getParameter("command");
		LOGGER.trace("Request parameter: command --> " + commandName);

		Command command = CommandContainer.get(commandName);
		LOGGER.trace("Obtained command --> " + command);

		Router requestProcessorInfo = new Router(RouteType.FORWARD, Path.PAGE_ERROR_PAGE);
		
		try {
			requestProcessorInfo = command.execute(request, response);
		} catch (ApplicationException ex) {
			ex.printStackTrace();
			request.setAttribute("errorMessage", ex.getMessage());
		}
		LOGGER.trace("Forward address --> " + requestProcessorInfo.getPath());
		LOGGER.debug("Controller finished, now go to forward address --> " +
		 requestProcessorInfo.getPath());

		switch (requestProcessorInfo.getRoute()) {
		case FORWARD:
			request.getRequestDispatcher(requestProcessorInfo.getPath()).forward(request, response);
			break;
		case REDIRECT:
			response.sendRedirect(requestProcessorInfo.getPath());
			break;
		}
	}
}
