package by.svirski.testweb.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.service.CustomService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.ServiceException;

@WebServlet("/Registration")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String PASS_TO_JSP = "/CorrectRegistration.jsp";
	private static final String PASS_TO_INC_JSP = "/error_page.jsp";

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

	public RegistrationController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter(NAME);
		String surname = request.getParameter(SURNAME);
		String login = request.getParameter(LOGIN);
		String pass = request.getParameter(PASSWORD);
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
		ServiceFactory factory = ServiceFactory.getInstance();
		CustomService service = factory.getUserService();
		boolean result = false;
		try {
			result = service.registrate(parametersMap);
		} catch (ServiceException e) {
			request.setAttribute(ERROR, e.getMessage());
			getServletContext().getRequestDispatcher(PASS_TO_INC_JSP).forward(request, response);
		}
		if (result) {
			request.setAttribute(NAME, name);
			request.setAttribute(SURNAME, surname);
			getServletContext().getRequestDispatcher(PASS_TO_JSP).forward(request, response);
		} else {
			request.setAttribute(ERROR, "Что-то пошло не так");
			getServletContext().getRequestDispatcher(PASS_TO_INC_JSP).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
