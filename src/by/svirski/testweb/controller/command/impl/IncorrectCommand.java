package by.svirski.testweb.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.controller.command.ActionCommand;

public class IncorrectCommand implements ActionCommand {
	
	private static final String ERROR = "type_error";
	private static final String PASS_TO_JSP = "/error_page";

	public IncorrectCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(ERROR, "не распознана команда");
		request.getServletContext().getRequestDispatcher(PASS_TO_JSP).forward(request, response);
	}

}
