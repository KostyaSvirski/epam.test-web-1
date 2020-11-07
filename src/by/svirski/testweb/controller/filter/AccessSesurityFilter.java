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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.svirski.testweb.bean.User;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;
import by.svirski.testweb.controller.UrlPatterns;

/**
 * Servlet Filter implementation class AccessSesurity
 */
@WebFilter(urlPatterns = { UrlPatterns.URL_PATTERN_MY_PAGE, UrlPatterns.URL_PATTERN_EDIT_USER,
		UrlPatterns.URL_PATTERN_ERROR_PAGE, UrlPatterns.URL_PATTERN_ORDERS, UrlPatterns.URL_PATTERN_RENT_AUTO,
		UrlPatterns.URL_PATTERN_SIGN_OUT }, initParams = {
				@WebInitParam(name = "page", value = PagePath.SIGN_IN_PAGE) })
public class AccessSesurityFilter implements Filter {

	private String page;
	
	public AccessSesurityFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		HttpSession session = hRequest.getSession();
		User user = (User) session.getAttribute(RequestParameters.USER);
		if (user == null) {
			hResponse.sendRedirect(hRequest.getContextPath() + page);
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		page = fConfig.getInitParameter("page");
	}

}
