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

import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.UrlPatterns;

/**
 * Servlet Filter implementation class AccessSecurityFilterAdmin
 */
@WebFilter(urlPatterns = { UrlPatterns.MY_PAGE, UrlPatterns.EDIT_USER,
		UrlPatterns.ERROR_PAGE, UrlPatterns.ORDERS, UrlPatterns.RENT_AUTO,
		UrlPatterns.SIGN_OUT }, initParams = {
				@WebInitParam(name = "page", value = PagePath.SIGN_IN_PAGE) })
public class AccessSecurityFilterAdmin implements Filter {

    /**
     * Default constructor. 
     */
    public AccessSecurityFilterAdmin() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
