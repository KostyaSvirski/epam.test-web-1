package by.svirski.testweb.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;


import by.svirski.testweb.controller.UrlPatterns;

/**
 * Servlet Filter implementation class RedirectSecurityFilter
 */
@WebFilter(urlPatterns = { UrlPatterns.URL_PATTERN_ALL }, initParams = {
		@WebInitParam(name = "encoding", value = "UTF-8") })
public class EncodingFilter implements Filter {
	
	private String encoding;
	
	/**
	 * Default constructor.
	 */
	public EncodingFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		encoding = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
	}

}
