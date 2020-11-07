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
import by.svirski.testweb.service.exception.InvalidParameterException;
import by.svirski.testweb.service.exception.ServiceException;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;

public class EditInfoCommand implements ActionCommand {

	private static Logger logger = LogManager.getLogger(EditInfoCommand.class);

	public EditInfoCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		Map<UserType, String> userParametersMap = new EnumMap<TypeOfParameters.UserType, String>(
				TypeOfParameters.UserType.class);
		User user = null;
		try {
			user = (User) request.getSession().getAttribute(RequestParameters.USER);
			if (user == null) {
				logger.log(Level.FATAL, "пользователь не получен");
				throw new RuntimeException("пользователь не получен");
			}
		} catch (ClassCastException e) {
			logger.log(Level.FATAL, "ошибка при касте пользователя");
			request.setAttribute(RequestParameters.ERROR, "что-то пошло не так");
			response.sendRedirect(request.getContextPath() + PagePath.ERROR_PAGE);
		}
		userParametersMap.put(UserType.ID, Integer.toString(user.getId()));
		userParametersMap.put(UserType.NAME, request.getParameter(RequestParameters.NAME));
		userParametersMap.put(UserType.SURNAME, request.getParameter(RequestParameters.SURNAME));
		userParametersMap.put(UserType.GENDER, request.getParameter(RequestParameters.GENDER));
		userParametersMap.put(UserType.PASSPORT_ID, request.getParameter(RequestParameters.PASSPORT_ID));
		userParametersMap.put(UserType.PASSPORT_NUMBER, request.getParameter(RequestParameters.PASSPORT_NUMBER));
		userParametersMap.put(UserType.DATE_OF_BIRTH, request.getParameter(RequestParameters.DATE_OF_BIRTH));
		userParametersMap.put(UserType.PHONE_NUMBER, request.getParameter(RequestParameters.PHONE));
		ServiceFactory factory = ServiceFactory.getInstance();
		CustomUserService service = factory.getUserService();
		try {
			User updatedUser = service.editUser(userParametersMap);
			if (updatedUser != null) {
				request.getSession().removeAttribute(RequestParameters.USER);
				request.getSession().setAttribute(RequestParameters.USER, updatedUser);
				response.sendRedirect(request.getContextPath() + PagePath.INDEX_PAGE);
			} else {
				logger.log(Level.ERROR, "пользователь оказался null");
				request.setAttribute(RequestParameters.ERROR, "что-то пошло не так");
				response.sendRedirect(request.getContextPath() + PagePath.ERROR_PAGE);
			}
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "ошибка в сервисе");
			request.setAttribute(RequestParameters.ERROR, e.getMessage());
			response.sendRedirect(request.getContextPath() + PagePath.ERROR_PAGE);
		} catch (InvalidParameterException e) {
			request.setAttribute(RequestParameters.COLLOR, "red");
			request.setAttribute(RequestParameters.MESSAGE, e.getMessage());
			request.getServletContext().getRequestDispatcher(PagePath.EDIT_USER_PAGE).forward(request, response);
		}

	}

}
