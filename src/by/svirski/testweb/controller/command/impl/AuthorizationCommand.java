package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.service.CustomService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.ServiceException;

public class AuthorizationCommand implements ActionCommand {
			
	private static final String LOGIN = "login";
	private static final String PASSWORD = "pass";
	private static final String PASS_TO_JSP = "/welcome.jsp";
	private static final String PASS_TO_INCORRECT_JSP = "/error_page.jsp";
	private static final String ERROR = "type_error";
	private static final String MESSAGE = "message"; 

	public AuthorizationCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		if (request.getParameter(LOGIN) != null && request.getParameter(PASSWORD) != null) {
			String login = request.getParameter(LOGIN);
			String password = Integer.toString(encryptPassword(request.getParameter(PASSWORD)));
			Map<TypeOfParameters.UserType, String> mapParameters = new HashMap<TypeOfParameters.UserType, String>();
			mapParameters.put(TypeOfParameters.UserType.PASSWORD, password);
			mapParameters.put(TypeOfParameters.UserType.LOGIN, login);
			ServiceFactory factory = ServiceFactory.getInstance();
			CustomService service = factory.getUserService();
			try {
				User user = service.authorize(mapParameters);
				if (user != null) {
					request.setAttribute("name", user.getName());
					request.setAttribute("surname", user.getSurname());
					request.getServletContext().getRequestDispatcher(PASS_TO_JSP).forward(request, response);
				} else {
					request.setAttribute(MESSAGE, "неверный пароль или логин");
					request.getServletContext().getRequestDispatcher(PASS_TO_INCORRECT_JSP).forward(request, response);
				}
			} catch (ServiceException e) {
				request.setAttribute(ERROR, e.getMessage());
				request.getServletContext().getRequestDispatcher(PASS_TO_INCORRECT_JSP).forward(request, response);
			}

		} else {
			
		}

	}

}
