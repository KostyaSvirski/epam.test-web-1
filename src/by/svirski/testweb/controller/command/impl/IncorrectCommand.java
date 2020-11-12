package by.svirski.testweb.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.controller.PagePath;
import by.svirski.testweb.controller.RequestParameters;

public class IncorrectCommand implements ActionCommand {
	
	public IncorrectCommand() {
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(RequestParameters.ERROR, "не распознана команда");
		request.getServletContext().getRequestDispatcher(PagePath.ERROR_PAGE).forward(request, response);
	}

}
