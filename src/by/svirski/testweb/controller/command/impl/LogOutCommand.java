package by.svirski.testweb.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.controller.command.ActionCommand;

public class LogOutCommand implements ActionCommand {
	
	private static final String WELCOME_PAGE = "/welcome.jsp";

	public LogOutCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		request.getServletContext().getRequestDispatcher(WELCOME_PAGE).forward(request, response);
	}

}
