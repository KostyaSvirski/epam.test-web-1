package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.bean.User;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;
import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.service.CustomAdminService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.ServiceException;

/**
 * class represents command to show all users from side of admin
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class ShowUsersCommand implements ActionCommand {

	public ShowUsersCommand() {
	}

	/**
	 * overriden method {@link ActionCommand#execute(HttpServletRequest, HttpServletResponse)} to show all users from side of admin
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		ServiceFactory factory = ServiceFactory.getInstance();
		CustomAdminService service = factory.getAdminService();
		List<User> resultList = null;
		try {
			resultList = service.showAllUsers();
			request.setAttribute(RequestParameters.USERS, resultList);
			request.getServletContext().getRequestDispatcher(PagePath.ALL_USERS_PAGE).forward(request, response);
		} catch (ServiceException e) {
			request.setAttribute(RequestParameters.ERROR, "что-то пошло не так");
			request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
		}
		
	}

}
