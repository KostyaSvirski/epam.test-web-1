package by.svirski.testweb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String LOGIN = "login";
	private static final String PASSWORD = "pass";
	private static final String CORRECT_PASSWORD = "1";
	private static final String PASS_TO_JSP = "/hello.jsp";
	private static final String PASS_TO_INCORRECT_JSP = "/not_valid_pass.jsp";
       
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String login = request.getParameter(LOGIN);
		String pass = request.getParameter(PASSWORD);
		if(pass.equals(CORRECT_PASSWORD)) {
			request.setAttribute("name", "Костя");
			request.setAttribute("login", login);
			getServletContext().getRequestDispatcher(PASS_TO_JSP).forward(request, response);
		} else {
			getServletContext().getRequestDispatcher(PASS_TO_INCORRECT_JSP).forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
