package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.EnumMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.service.CustomUserService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.ServiceException;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;

public class AuthorizationCommand implements ActionCommand {

	public AuthorizationCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		if (request.getParameter(RequestParameters.LOGIN) != null
				&& request.getParameter(RequestParameters.PASSWORD) != null) {
			String login = request.getParameter(RequestParameters.LOGIN);
			String password = request.getParameter(RequestParameters.PASSWORD);
			Map<TypeOfParameters.UserType, String> mapParameters = new EnumMap<TypeOfParameters.UserType, String>(
					TypeOfParameters.UserType.class);
			mapParameters.put(TypeOfParameters.UserType.PASSWORD, password);
			mapParameters.put(TypeOfParameters.UserType.LOGIN, login);
			ServiceFactory factory = ServiceFactory.getInstance();
			CustomUserService service = factory.getUserService();
			try {
				User user = service.authorize(mapParameters);
				if (user != null) {
					request.getSession().setAttribute("user", user);
					response.sendRedirect(request.getContextPath() + PagePath.INDEX_PAGE);
				} else {
					request.setAttribute(RequestParameters.COLLOR, "red");
					request.setAttribute(RequestParameters.MESSAGE, "неверный пароль или логин");
					request.getServletContext().getRequestDispatcher(PagePath.SIGN_IN_PAGE).forward(request, response);
				}
			} catch (ServiceException e) {
				request.setAttribute(RequestParameters.ERROR, e.getMessage());
				response.sendRedirect(request.getContextPath() + PagePath.ERROR_PAGE);
			}

		} else {
			request.setAttribute(RequestParameters.COLLOR, "red");
			request.setAttribute(RequestParameters.MESSAGE, "не все поля указаны");
			request.getServletContext().getRequestDispatcher(PagePath.SIGN_IN_PAGE).forward(request, response);
		}

	}

}
