package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.EnumMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.service.CustomUserService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.ServiceException;
import by.svirski.testweb.util.validator.CustomValidator;
import by.svirski.testweb.util.validator.impl.DateValidator;
import by.svirski.testweb.util.validator.impl.PhoneValidator;

public class EditInfoCommand implements ActionCommand {
	
	private static Logger logger = LogManager.getLogger(EditInfoCommand.class);
	
	private static final String PASS_TO_WELCOME = "/index.jsp";
	private static final String PASS_TO_ERROR = "/error_page.jsp";
	private static final String PASS_TO_EDIT= "/edit_user.jsp";
	
	private static final String MESSAGE = "message"; 
	private static final String ERROR = "type_error";
	private static final String COLLOR = "collor";
	
	private static final String NAME = "name";
	private static final String SURNAME = "surname";
	private static final String GENDER = "gender";
	private static final String PASSPORT_ID = "passport_id";
	private static final String PASSPORT_NUMBER = "passport_number";
	private static final String DATE_OF_BIRTH = "date_of_birth";
	private static final String PHONE = "phone";

	public EditInfoCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		Map<UserType, String> userParametersMap = new EnumMap<TypeOfParameters.UserType, String>(TypeOfParameters.UserType.class);
		try {
			User user = (User) request.getSession().getAttribute("user");
			if(user == null) {
				logger.log(Level.FATAL, "пользователь не получен");
				throw new RuntimeException("пользователь не получен");
			}
			userParametersMap.put(UserType.ID, Integer.toString(user.getId()));
			userParametersMap.put(UserType.NAME, request.getParameter(NAME));
			userParametersMap.put(UserType.SURNAME, request.getParameter(SURNAME));
			userParametersMap.put(UserType.GENDER, request.getParameter(GENDER));
			userParametersMap.put(UserType.PASSPORT_ID, request.getParameter(PASSPORT_ID));
			userParametersMap.put(UserType.PASSPORT_NUMBER, request.getParameter(PASSPORT_NUMBER));
			CustomValidator validatorDate = new DateValidator();
			if(validatorDate.validate(request.getParameter(DATE_OF_BIRTH))) {
				userParametersMap.put(UserType.DATE_OF_BIRTH, request.getParameter(DATE_OF_BIRTH));				
			} else {
				logger.log(Level.INFO, "не валидная дата");
				request.setAttribute(COLLOR, "red");
				request.setAttribute(MESSAGE, "не валидная дата");
				request.getServletContext().getRequestDispatcher(PASS_TO_EDIT).forward(request, response);
				return;
			}
			CustomValidator validatorPhone = new PhoneValidator(); 
			if(validatorPhone.validate(request.getParameter(PHONE))) {
				userParametersMap.put(UserType.PHONE_NUMBER, request.getParameter(PHONE));				
			} else {
				logger.log(Level.INFO, "не валидный телефон");
				request.setAttribute(COLLOR, "red");
				request.setAttribute(MESSAGE, "не валидный телефон");
				request.getServletContext().getRequestDispatcher(PASS_TO_EDIT).forward(request, response);
				return;
			}
			
			ServiceFactory factory = ServiceFactory.getInstance();
			CustomUserService service = factory.getUserService();
			try {
				User updatedUser = service.editUser(userParametersMap);
				if(updatedUser != null) {
					request.getSession().removeAttribute("user");
					request.getSession().setAttribute("user", updatedUser);					
					request.getServletContext().getRequestDispatcher(PASS_TO_WELCOME).forward(request, response);
				} else {
					logger.log(Level.ERROR, "пользователь оказался null");
					request.setAttribute(ERROR,"что-то пошло не так");
					response.sendRedirect(request.getContextPath() + PASS_TO_ERROR);
				}
			} catch (ServiceException e) {
				logger.log(Level.ERROR, "ошибка в сервисе");
				request.setAttribute(ERROR, e.getMessage());
				response.sendRedirect(request.getContextPath() + PASS_TO_ERROR);
			}
			
		} catch (ClassCastException e) {
			logger.log(Level.FATAL, "ошибка при касте пользователя");
			request.setAttribute(ERROR,"ошибка при касте пользователя");
			response.sendRedirect(request.getContextPath() + PASS_TO_ERROR);
		}
		
	}


}
