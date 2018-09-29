package ua.shvidkoy.webproject.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import java.io.IOException;

@WebFilter(urlPatterns = "/jsp/*")
public class PageRedirectSecurityFilter implements Filter {
    private String indexPath;
	private final static Logger LOGGER = Logger.getLogger(PageRedirectSecurityFilter.class);
	private static final String PARAM_NAME_COMMAND = "command";

    public void init(FilterConfig fConfig) throws ServletException {
        LOGGER.info("Filter init " );

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String command = httpRequest.getParameter(PARAM_NAME_COMMAND);

		if (command != null) {
	        LOGGER.info("Command  " + command );

			httpResponse.sendRedirect(httpRequest.getContextPath() + "/front_controller?command=" + command);
		
		} else {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/front_controller?command=init_user_list");
		}
}
    

    public void destroy() {
        LOGGER.info("Filter destroy " );

    }
}