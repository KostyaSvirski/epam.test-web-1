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
@WebFilter(urlPatterns = { UrlPatterns.MY_PAGE, UrlPatterns.EDIT_USER,
		UrlPatterns.ERROR_PAGE, UrlPatterns.ORDERS, UrlPatterns.RENT_AUTO,
		UrlPatterns.SIGN_OUT, UrlPatterns.ADD_CAR, UrlPatterns.DETAIL_DENY, 
		UrlPatterns.DETAIL_RELEASE, UrlPatterns.PENALTY, UrlPatterns.THIS_USER,
		UrlPatterns.USERS}, initParams = {
				@WebInitParam(name = "page", value = PagePath.SIGN_IN_PAGE) })
public class AccessSesurityFilterUser implements Filter {

	private String page;
	
	public AccessSesurityFilterUser() {
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
