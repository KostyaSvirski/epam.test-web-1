package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.svirski.testweb.controller.RequestParameters;
import by.svirski.testweb.controller.command.ActionCommand;

public class ChangeLocaleCommand implements ActionCommand {

	public ChangeLocaleCommand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		HttpSession session = request.getSession();
		session.removeAttribute(RequestParameters.LANGUAGE);
		String newLocale = request.getParameter(RequestParameters.LANGUAGE);
		session.setAttribute(RequestParameters.LANGUAGE, newLocale);
		String page = request.getParameter(RequestParameters.CURRENT_PAGE);
		response.sendRedirect(page);
	}

}
