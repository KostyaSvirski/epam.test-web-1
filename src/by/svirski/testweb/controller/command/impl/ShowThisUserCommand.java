package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.EnumMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;
import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.service.CustomAdminService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.ServiceException;

/**
 * class represents command to show this specific user
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class ShowThisUserCommand implements ActionCommand {

	public ShowThisUserCommand() {
	}

	/**
	 * overriden method {@link ActionCommand#execute(HttpServletRequest, HttpServletResponse)} to show this specific user
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		Map<UserType, String> parametersMap = new EnumMap<UserType, String>(UserType.class);
		parametersMap.put(UserType.ID, request.getParameter(RequestParameters.USER_ID));
		ServiceFactory factory = ServiceFactory.getInstance();
		CustomAdminService service = factory.getAdminService();
		try {
			User user = service.showThisUser(parametersMap);
			request.setAttribute(RequestParameters.USER, user);
			request.getServletContext().getRequestDispatcher(PagePath.THIS_USER_PAGE).forward(request, response);
		} catch (ServiceException e) {
			request.setAttribute(RequestParameters.ERROR, e);
			request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
		}
		

	}

}
