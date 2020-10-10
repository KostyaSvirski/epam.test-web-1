package by.svirski.testweb.util.parser;

import by.svirski.testweb.util.parser.exception.CustomParseException;

public interface CustomParser<T> {
	
	T parse(String parameterToParse) throws CustomParseException;
}
