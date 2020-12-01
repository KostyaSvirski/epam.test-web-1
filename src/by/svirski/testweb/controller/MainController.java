package by.svirski.testweb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.controller.command.ActionCommand;
import by.svirski.testweb.controller.provider.CommandProvider;
import by.svirski.testweb.dao.exception.ConnectionPoolException;
import by.svirski.testweb.dao.pool.ConnectionPool;

/**
 * class represents main controller of project used to process all requests
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(MainController.class);
	
	/**
	 * default controller
	 */
	public MainController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.DEBUG, "пришел запрос GET");
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.log(Level.DEBUG, "пришел запрос POST");
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandToExecute = request.getParameter(RequestParameters.COMMAND);
		CommandProvider provider = new CommandProvider();
		ActionCommand command = provider.defineCommand(commandToExecute);
		command.execute(request, response);
		logger.log(Level.DEBUG, "запрос был обработан");
	}

	@Override
	public void destroy() {
		try {
			ConnectionPool.getInstance().destroyPool();
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
		}
		super.destroy();
	}
	
	

}