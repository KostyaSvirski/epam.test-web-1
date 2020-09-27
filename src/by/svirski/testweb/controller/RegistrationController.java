package by.svirski.testweb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Registration")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static String PASS_TO_JSP = "/CorrectRegistration.jsp";
       
    public RegistrationController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		request.setAttribute("name", name);
		request.setAttribute("surname", surname);
		getServletContext().getRequestDispatcher(PASS_TO_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
