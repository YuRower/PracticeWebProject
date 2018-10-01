package ua.shvidkoy.webproject.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class ChangeCaptionProfile
 */
@WebFilter("/ChangeCaptionProfile")
public class ChangeCaptionProfile implements Filter {
	private final static Logger LOGGER = Logger.getLogger(ChangeCaptionProfile.class);
	private static final String PARAM_NAME_COMMAND = "command";

	private ServletContext context;

	static class FilteredRequest extends HttpServletRequestWrapper {

		public FilteredRequest(ServletRequest request) {
			super((HttpServletRequest) request);
		}

		public String getParameter(String paramName) {
			String value = super.getParameter(paramName);
			if (value == null)
				return null;
			String name = paramName;
			LOGGER.debug("::Request Params::{" + name + "=} val Changed to " + value);
			// Name
			//if (name.startsWith("redirect_profile"))
			value = extractNumber(name);
			LOGGER.debug("::Request Params::{" + name + "=} val Changed to " + value);

			return value;
		}
	}

	public static String extractNumber(final String str) {

		if (str == null || str.isEmpty())
			return "";

		StringBuilder sb = new StringBuilder();
		boolean found = false;
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				sb.append(c);
				found = true;
			} else if (found) {
				// If we already found a digit before and this char is not a digit, stop looping
				break;
			}
		}

		return sb.toString();
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		LOGGER.debug("RequestLoggingFilter initialized");
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getRequestURI().substring(req.getContextPath().length());
		LOGGER.debug("RequestLoggingFilter initialized"+path);

		//if (path.startsWith("/front_controller")) {
			chain.doFilter(new FilteredRequest(request), response);
		//} else {
		//	chain.doFilter(req, response); // Goes to default servlet.
			// request.getRequestDispatcher("/pages" + path).forward(req, response);
		//}
	}

}

/*
 * HttpServletRequest newRequest = (HttpServletRequest) new
 * MyHttpServletRequestWrapper(originalRequest); String command =
 * newRequest.getParameter(PARAM_NAME_COMMAND);
 * LOGGER.info("command ----------> " + command); String updatedCommand =
 * extractNumber(command); session.setAttribute("command", updatedCommand);
 */