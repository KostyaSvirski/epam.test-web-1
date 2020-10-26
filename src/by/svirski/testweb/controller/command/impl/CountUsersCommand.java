package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.service.CustomSystemService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.ServiceException;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;

public class CountUsersCommand implements ActionCommand {
	
	public CountUsersCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		ServiceFactory factory = ServiceFactory.getInstance();
		CustomSystemService siteService = factory.getSiteService();
		int countUsers = 0;
		try {
			countUsers = siteService.countUsers();
			String countStr = Integer.toString(countUsers);
			request.setAttribute(RequestParameters.COUNT_USERS, countStr);
			request.getServletContext().getRequestDispatcher(PagePath.WELCOME_PAGE).forward(request, response);	
		} catch (ServiceException e) {
			request.setAttribute(RequestParameters.ERROR, e);
			response.sendRedirect(request.getContextPath() + PagePath.ERROR_PAGE);
		}
	}

}
