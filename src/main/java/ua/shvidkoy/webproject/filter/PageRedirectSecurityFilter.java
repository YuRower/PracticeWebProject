package ua.shvidkoy.webproject.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.shvidkoy.webproject.constant.Path;

import java.io.IOException;

/*
@WebFilter(
filterName = "PageRedirectFilter",
urlPatterns = ("/jsp/*"),
initParams = {@WebInitParam(name = "PAGE_INDEX", value = Path.INDEX)}
)
public class PageRedirectSecurityFilter implements Filter {
	private String indexPath;
	private final static Logger LOGGER = Logger.getLogger(PageRedirectSecurityFilter.class);

	public void init(FilterConfig fConfig) throws ServletException {
		LOGGER.info("Filter init ");
		 indexPath = fConfig.getInitParameter("PAGE_INDEX");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
		chain.doFilter(request, response);
	}

	public void destroy() {
		LOGGER.info("Filter destroy ");
		 indexPath = null;
	}
}*/