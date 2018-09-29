package ua.shvidkoy.webproject.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class PageRedirectSecurityFilter
 
@WebFilter(urlPatterns = { "/*.jsp" }, initParams =
{ @WebInitParam(name = "INDEX_PATH", value = "index.jsp") })
public class PageRedirectSecurityFilter implements Filter {
	private final static Logger LOGGER = Logger.getLogger(PageRedirectSecurityFilter.class);
	private String indexPath;

	/**
	 * Default constructor.
	 
	public PageRedirectSecurityFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 
	public void destroy() {
		LOGGER.info("Filter destroy");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.info("doFilter" );

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
		LOGGER.info("Redirect on certain page" + indexPath);

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 
	public void init(FilterConfig fConfig) throws ServletException {
		LOGGER.info("Filter init");
		indexPath = fConfig.getInitParameter("INDEX_PATH");

	}

}*/
