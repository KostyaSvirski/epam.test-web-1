package by.svirski.testweb.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.service.CustomService;
import by.svirski.testweb.service.ServiceFactory;
import by.svirski.testweb.service.exception.ServiceException;

@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String LOGIN = "login";
	private static final String PASSWORD = "pass";
	private static final String PASS_TO_JSP = "/hello.jsp";
	private static final String PASS_TO_INCORRECT_JSP = "/error_page.jsp";
	private static final String ERROR = "type_error";
       
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String login = request.getParameter(LOGIN);
		String pass = request.getParameter(PASSWORD);
		Map<TypeOfParameters.UserType, String> mapParameters = new HashMap<TypeOfParameters.UserType, String>();
		mapParameters.put(TypeOfParameters.UserType.PASSWORD, pass);
		mapParameters.put(TypeOfParameters.UserType.LOGIN, login);
		ServiceFactory factory = ServiceFactory.getInstance();
		CustomService service = factory.getUserService();
		try {
			User user = service.authorize(mapParameters);
			if(user != null) {
				request.setAttribute("name", user.getName());
				request.setAttribute("surname", user.getSurname());
				getServletContext().getRequestDispatcher(PASS_TO_JSP).forward(request, response);
			} else {
				request.setAttribute("type_error", "неверный пароль или логин");
				getServletContext().getRequestDispatcher(PASS_TO_INCORRECT_JSP).forward(request, response);
			}
		} catch (ServiceException e) {
			request.setAttribute(ERROR, e.getMessage());
			getServletContext().getRequestDispatcher(PASS_TO_INCORRECT_JSP).forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
