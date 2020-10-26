package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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
		request.setCharacterEncoding(RequestParameters.CHAR_ENCODDING);
		String name = request.getParameter(RequestParameters.NAME);
		String surname = request.getParameter(RequestParameters.SURNAME);
		String login = request.getParameter(RequestParameters.LOGIN);
		String pass = Integer.toString(encryptPassword(request.getParameter(RequestParameters.PASSWORD)));
		String repeatPass = Integer.toString(encryptPassword(request.getParameter(RequestParameters.REPEAT_PASSWORD)));
		if(!repeatPass.equals(pass)) {
			logger.log(Level.INFO, "пароли не совпадают");
			request.setAttribute(RequestParameters.COLLOR, "red");
			request.setAttribute(RequestParameters.MESSAGE, "пароли не совпадают");
			request.getServletContext().getRequestDispatcher(PagePath.SIGN_UP_PAGE).forward(request, response);
		} else {
			String gender = request.getParameter(RequestParameters.GENDER);
			String passportId = request.getParameter(RequestParameters.PASSPORT_ID);
			String passportNumber = request.getParameter(RequestParameters.PASSPORT_NUMBER);
			String dateOfBirth = request.getParameter(RequestParameters.DATE_OF_BIRTH);
			String phone = request.getParameter(RequestParameters.PHONE);
			Map<TypeOfParameters.UserType, String> parametersMap = new HashMap<TypeOfParameters.UserType, String>();
			parametersMap.put(TypeOfParameters.UserType.NAME, name);
			parametersMap.put(TypeOfParameters.UserType.SURNAME, surname);
			parametersMap.put(TypeOfParameters.UserType.LOGIN, login);
			parametersMap.put(TypeOfParameters.UserType.PASSWORD, pass);
			parametersMap.put(TypeOfParameters.UserType.GENDER, gender);
			parametersMap.put(TypeOfParameters.UserType.PASSPORT_ID, passportId);
			parametersMap.put(TypeOfParameters.UserType.PASSPORT_NUMBER, passportNumber);
			parametersMap.put(TypeOfParameters.UserType.DATE_OF_BIRTH, dateOfBirth);
			parametersMap.put(TypeOfParameters.UserType.PHONE_NUMBER, phone);
			parametersMap.put(TypeOfParameters.UserType.IS_BLOCKED, "false");
			parametersMap.put(TypeOfParameters.UserType.ROLE_IN_PROJECT, "user");
			if(checkParameters(parametersMap)) {
				ServiceFactory factory = ServiceFactory.getInstance();
				CustomUserService service = factory.getUserService();
				boolean result = false;
				try {
					result = service.registrate(parametersMap);
					if (result) {
						request.setAttribute(RequestParameters.COLLOR, "green");
						request.setAttribute(RequestParameters.MESSAGE, "вы успешно зарегистрированы");
						request.getServletContext().getRequestDispatcher(PagePath.SIGN_IN_PAGE).forward(request, response);
					} else {
						request.setAttribute(RequestParameters.COLLOR, "red");
						request.setAttribute(RequestParameters.MESSAGE, "такой пользователь уже существует!");
						request.getServletContext().getRequestDispatcher(PagePath.SIGN_UP_PAGE).forward(request, response);
					}
				} catch (ServiceException e) {
					request.setAttribute(RequestParameters.ERROR, e.getMessage());
					response.sendRedirect(request.getContextPath() + PagePath.ERROR_PAGE);		
				}
				
			} else {
				request.setAttribute(RequestParameters.COLLOR, "red");
				request.setAttribute(RequestParameters.MESSAGE, "не все поля заполнены");
				request.getServletContext().getRequestDispatcher(PagePath.SIGN_UP_PAGE).forward(request, response);
			}
		}

	}

	private boolean checkParameters(Map<UserType, String> parametersMap) {
		for(Entry<UserType, String> entry : parametersMap.entrySet()) {
			String value = entry.getValue();
			if(value == null) {
				return false;
			}
			if(value.isEmpty() || value.isBlank()) {
				return false; 
			}
		}
		return true;
	}

}
