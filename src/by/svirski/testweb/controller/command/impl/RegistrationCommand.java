package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.service.CustomService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.ServiceException;

public class RegistrationCommand implements ActionCommand {

	private static final String PASS_TO_SIGN_IN = "/sign_in.jsp";
	private static final String PASS_TO_ERROR = "/error_page.jsp";
	private static final String PASS_TO_REGISTRATION = "/registration.jsp";
	
	private static final String MESSAGE = "message"; 
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String PASSWORD = "pass";
	private static final String LOGIN = "login";
	private static final String GENDER = "gender";
	private static final String PASSPORT_ID = "passport_id";
	private static final String PASSPORT_NUMBER = "passport_number";
	private static final String DATE_OF_BIRTH = "date_of_birth";
	private static final String PHONE = "phone";
	private static final String ERROR = "type_error";
	private static final String COLLOR = "collor";

	public RegistrationCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter(NAME);
		String surname = request.getParameter(SURNAME);
		String login = request.getParameter(LOGIN);
		String pass = Integer.toString(encryptPassword(request.getParameter(PASSWORD)));
		String gender = request.getParameter(GENDER);
		String passportId = request.getParameter(PASSPORT_ID);
		String passportNumber = request.getParameter(PASSPORT_NUMBER);
		String dateOfBirth = request.getParameter(DATE_OF_BIRTH);
		String phone = request.getParameter(PHONE);
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
			CustomService service = factory.getUserService();
			boolean result = false;
			try {
				result = service.registrate(parametersMap);
				if (result) {
					request.setAttribute(COLLOR, "green");
					request.setAttribute(MESSAGE, "вы успешно зарегистрированы");
					request.getServletContext().getRequestDispatcher(PASS_TO_SIGN_IN).forward(request, response);
				} else {
					request.setAttribute(COLLOR, "red");
					request.setAttribute(MESSAGE, "такой пользователь уже существует!");
					request.getServletContext().getRequestDispatcher(PASS_TO_REGISTRATION).forward(request, response);
				}
			} catch (ServiceException e) {
				request.setAttribute(ERROR, e.getMessage());
				request.getServletContext().getRequestDispatcher(PASS_TO_ERROR).forward(request, response);
			}
			
		} else {
			request.setAttribute(COLLOR, "red");
			request.setAttribute(MESSAGE, "не все поля заполнены");
			request.getServletContext().getRequestDispatcher(PASS_TO_REGISTRATION).forward(request, response);
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
