package by.svirski.testweb.bean.parser.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;

import by.svirski.testweb.bean.parser.CustomParser;
import by.svirski.testweb.bean.parser.exception.CustomParseException;

public class DateParser implements CustomParser<Calendar> {

	private static final String DELIMETER = "[.-]";
	
	public DateParser() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Calendar parse(String parameterToParse) throws CustomParseException {
		String[] values = parameterToParse.split(DELIMETER);
		if(values.length < 3) {
			throw new CustomParseException("error in parsing");
		} 
		for(String checkValue: values) {
			if(checkValue.isBlank()) {
				throw new CustomParseException("error in parsing");
			}
		}
		Calendar date = new GregorianCalendar();
		if (Integer.parseInt(values[2]) <= 2020 && Integer.parseInt(values[2]) >= 1800) {
			date.set(Calendar.YEAR, Integer.parseInt(values[2]));
			if (Integer.parseInt(values[1]) >= 1 && Integer.parseInt(values[1]) <= 12) {
				date.set(Calendar.MONTH, Integer.parseInt(values[1]) - 1);
				if (Integer.parseInt(values[0]) >= date.getActualMinimum(Calendar.DAY_OF_MONTH)
						&& Integer.parseInt(values[0]) <= date.getActualMaximum(Calendar.DAY_OF_MONTH)) {
					date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(values[0]));
					return date;
				}

			}
		}
		throw new CustomParseException("error in parsing");
	}

	
}
