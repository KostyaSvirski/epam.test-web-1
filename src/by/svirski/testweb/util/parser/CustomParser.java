package by.svirski.testweb.util.parser;

import by.svirski.testweb.util.parser.exception.CustomParseException;

/**
 * interface represents parser
 * 
 * @param <T> - type of value that parsed
 * 
 * @author Kostya Svirski
 * @version 1.0
 * 
 */
public interface CustomParser<T> {
	
	/**
	 * method for parse parameter form String to T
	 * @param parameterToParse - parameter that should be parsed
	 * @return parsed value
	 * @throws CustomParseException if value can not be parsed
	 */
	T parse(String parameterToParse) throws CustomParseException;
}
