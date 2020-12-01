package by.svirski.testweb.util.parser.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;

import by.svirski.testweb.util.parser.CustomParser;
import by.svirski.testweb.util.parser.exception.CustomParseException;

/**
 * class represents parser for date
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class DateParser implements CustomParser<Calendar> {

	private static final String DELIMETER = "[.-]";
	
	public DateParser() {
	}

	/**
	 * overridden method {@link CustomParser#parse(String)} to parse date
	 */
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
		if (Integer.parseInt(values[0]) <= 2020 && Integer.parseInt(values[0]) >= 1800) {
			date.set(Calendar.YEAR, Integer.parseInt(values[0]));
			if (Integer.parseInt(values[1]) >= 1 && Integer.parseInt(values[1]) <= 12) {
				date.set(Calendar.MONTH, Integer.parseInt(values[1]) - 1);
				if (Integer.parseInt(values[2]) >= date.getActualMinimum(Calendar.DAY_OF_MONTH)
						&& Integer.parseInt(values[2]) <= date.getActualMaximum(Calendar.DAY_OF_MONTH)) {
					date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(values[2]));
					return date;
				}

			}
		}
		throw new CustomParseException("error in parsing");
	}

	
}
