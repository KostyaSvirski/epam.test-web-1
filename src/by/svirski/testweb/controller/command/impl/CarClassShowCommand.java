package by.svirski.testweb.controller.command.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.svirski.testweb.bean.type.TypeOfParameters;
import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.controller.command.ActionCommand;

public class CarClassShowCommand implements ActionCommand{

	public CarClassShowCommand() {
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, ServletException {
		Map<TypeOfParameters.CarType, String> parametersMap = new HashMap<TypeOfParameters.CarType, String>();
		parametersMap = putParametersIntoMap(parametersMap, request);
		
	}

	private Map<CarType, String> putParametersIntoMap(Map<CarType, String> parametersMap, HttpServletRequest request) {
		Map<String, String[]> parametersFromPage = request.getParameterMap();
		CarType[] carTypeValues = CarType.values();
		for(Entry<String, String[]> entry : parametersFromPage.entrySet()) {
			if(entry.getValue() != null && !entry.getValue()[0].isBlank() && !entry.getValue()[0].isEmpty()) {
				for(CarType type : carTypeValues) {
					if(type.name().equalsIgnoreCase(entry.getValue()[0])) {
						parametersMap.put(type, entry.getValue()[0]);
					}
				}
			}
		}
		return parametersMap;
	}

	
}
