package ua.shvidkoy.webproject.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;


/**
 * Servlet Filter implementation class s
 */
public class EncodingFilter implements Filter {
    private String encoding;
	private final static Logger LOGGER = Logger.getLogger(EncodingFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
        LOGGER.info("Filter init " );
    }
    
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        String requestEncoding = request.getCharacterEncoding();
        String responseEncoding = response.getCharacterEncoding();
        if (encoding != null &&
                (!encoding.equalsIgnoreCase(requestEncoding) || !encoding.equalsIgnoreCase(responseEncoding))) {
            request.setCharacterEncoding(encoding);
            response.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }
    
    public void destroy() {
        LOGGER.info("Filter destroy " );

        encoding = null;
}
}
