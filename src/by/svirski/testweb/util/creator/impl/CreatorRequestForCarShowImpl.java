package by.svirski.testweb.util.creator.impl;

import java.util.Map;
import java.util.Map.Entry;

import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.util.creator.RequestCreator;

public class CreatorRequestForCarShowImpl implements RequestCreator<CarType>{
	
	private static final String DELIMETER_FOR_PAIRS = " and ";
	private static final String START_OF_ENUM_CONDITIONS = " where ";
	private static final String DELIMITER_FOR_PRICES = " between ";
	private static final String PLACE_FOR_ARGUMENT = "?";
	private static final String DELIMETER_FOR_KEY_AND_VALUE = " = ";
	private static final String PREFIX_BOOK_LIST = "book_list.";

	public CreatorRequestForCarShowImpl() {
	}

	@Override
	public String createRequest(String abstractRequest, Map<CarType, String> mapOfAdditionalParams) {
		if (mapOfAdditionalParams.isEmpty()) {
			return abstractRequest;
		}
		StringBuffer sb = new StringBuffer(abstractRequest);
		sb.append(START_OF_ENUM_CONDITIONS);
		int count = 0;
		for (Entry<CarType, String> entry : mapOfAdditionalParams.entrySet()) {
			count++;
			if (count >= 2) {
				sb.append(DELIMETER_FOR_PAIRS);
			}
			String key = entry.getKey().name().toLowerCase();
			if(key.equals(CarType.IS_BOOKED.name())) {
				sb.append(PREFIX_BOOK_LIST);
			}
			if(key.equals(CarType.COST.name().toLowerCase())) {
				sb.append(key);
				sb.append(DELIMITER_FOR_PRICES);
				sb.append(PLACE_FOR_ARGUMENT);
				sb.append(DELIMETER_FOR_PAIRS);
				sb.append(PLACE_FOR_ARGUMENT);
			} else {
				sb.append(key);
				sb.append(DELIMETER_FOR_KEY_AND_VALUE);
				sb.append(PLACE_FOR_ARGUMENT);				
			}

		}
		return sb.toString();
	}	

}
