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
import javax.servlet.http.HttpSession;

import by.svirski.testweb.bean.User;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;
import by.svirski.testweb.controller.UrlPatterns;

/**
 * Servlet Filter implementation class StatusControlFilter
 */
@WebFilter(urlPatterns = { UrlPatterns.ALL }, initParams = {
		@WebInitParam(name = "page", value = PagePath.ERROR_PAGE) })
public class StatusControlFilter implements Filter {

	private String page;

	/**
	 * Default constructor.
	 */
	public StatusControlFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpSession session = hRequest.getSession();
		User user = (User) session.getAttribute(RequestParameters.USER);
		if (user != null && user.getIsBlocked()) {
			request.setAttribute(RequestParameters.ERROR, "вы заблокированы соре");
			hRequest.getServletContext().getRequestDispatcher(page).forward(request, response);
			session.invalidate();
		} else {
			chain.doFilter(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		page = fConfig.getInitParameter("page");
	}

}
