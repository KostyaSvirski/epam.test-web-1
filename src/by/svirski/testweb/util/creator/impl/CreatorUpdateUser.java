package by.svirski.testweb.util.creator.impl;

import java.util.Map;
import java.util.Map.Entry;

import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.util.creator.Creator;

public class CreatorUpdateUser implements Creator<String> {
	
	private Map<UserType, String> mapAdditionalParameters;
	
	public CreatorUpdateUser(Map<UserType, String> mapAdditionalParameters) {
		this.mapAdditionalParameters = mapAdditionalParameters;
	}

	@Override
	public String create(String value) {
		StringBuilder sb = new StringBuilder();
		String delimeter = "|";
		String[] parsedString = value.split(delimeter);
		sb.append(parsedString[0]);
		sb.append(" ");
		int i = 0;
		for(Entry<UserType, String> entry : mapAdditionalParameters.entrySet()) {
			sb.append(entry.getKey().name());
			sb.append(" = ");
			sb.append("? ");
		}
		sb.append(parsedString[1]);
		return sb.toString();
	}

}
