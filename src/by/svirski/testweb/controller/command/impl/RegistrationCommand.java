package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.service.CustomUserService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.InvalidParameterException;
import by.svirski.testweb.service.exception.ServiceException;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;

public class RegistrationCommand implements ActionCommand {

	private static Logger logger = LogManager.getLogger(RegistrationCommand.class);

	public RegistrationCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String name = request.getParameter(RequestParameters.NAME);
		String surname = request.getParameter(RequestParameters.SURNAME);
		String login = request.getParameter(RequestParameters.LOGIN);
		String pass = request.getParameter(RequestParameters.PASSWORD);
		String repeatPass = request.getParameter(RequestParameters.REPEAT_PASSWORD);
		String gender = request.getParameter(RequestParameters.GENDER);
		String passportId = request.getParameter(RequestParameters.PASSPORT_ID);
		String passportNumber = request.getParameter(RequestParameters.PASSPORT_NUMBER);
		String dateOfBirth = request.getParameter(RequestParameters.DATE_OF_BIRTH);
		String phone = request.getParameter(RequestParameters.PHONE);
		Map<TypeOfParameters.UserType, String> parametersMap = new EnumMap<TypeOfParameters.UserType, String>(
				TypeOfParameters.UserType.class);
		parametersMap.put(UserType.NAME, name);
		parametersMap.put(UserType.SURNAME, surname);
		parametersMap.put(UserType.LOGIN, login);
		parametersMap.put(UserType.PASSWORD, pass);
		parametersMap.put(UserType.REPEAT_PASS, repeatPass);
		parametersMap.put(UserType.GENDER, gender);
		parametersMap.put(UserType.PASSPORT_ID, passportId);
		parametersMap.put(UserType.PASSPORT_NUMBER, passportNumber);
		parametersMap.put(UserType.DATE_OF_BIRTH, dateOfBirth);
		parametersMap.put(UserType.PHONE_NUMBER, phone);
		parametersMap.put(UserType.IS_BLOCKED, "not_blocked");
		parametersMap.put(UserType.ROLE_IN_PROJECT, "user");
		ServiceFactory factory = ServiceFactory.getInstance();
		CustomUserService service = factory.getUserService();
		boolean result = false;
		try {
			result = service.registrate(parametersMap);
			if (result) {
				request.setAttribute(RequestParameters.COLLOR, "green");
				request.setAttribute(RequestParameters.MESSAGE, "вы успешно зарегистрированы");
				request.getServletContext().getRequestDispatcher(PagePath.SIGN_IN_PAGE).forward(request, response);
				logger.log(Level.INFO, "пользователь зарегистрирован");
			} else {
				request.setAttribute(RequestParameters.COLLOR, "red");
				request.setAttribute(RequestParameters.MESSAGE, "такой пользователь уже существует!");
				request.getServletContext().getRequestDispatcher(PagePath.SIGN_UP_PAGE).forward(request, response);
				logger.log(Level.INFO, "такой пользователь уже существует!");
			}
		} catch (ServiceException e) {
			request.setAttribute(RequestParameters.ERROR, e.getMessage());
			request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
			logger.log(Level.ERROR, e.getMessage());
		} catch (InvalidParameterException e) {
			request.setAttribute(RequestParameters.COLLOR, "red");
			request.setAttribute(RequestParameters.MESSAGE, e.getMessage());
			request.getServletContext().getRequestDispatcher(PagePath.SIGN_UP_PAGE).forward(request, response);
		}

	}

}
