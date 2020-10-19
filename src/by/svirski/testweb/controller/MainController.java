package by.svirski.testweb.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.controller.provider.CommandProvider;


@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String COMMAND = "command";
	
	public MainController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String commandToExecute = request.getParameter(COMMAND);
		CommandProvider provider = new CommandProvider();
		ActionCommand command = provider.defineCommand(commandToExecute);
		command.execute(request, response);		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
