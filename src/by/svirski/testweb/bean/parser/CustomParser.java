package by.svirski.testweb.bean.parser;

import by.svirski.testweb.bean.parser.exception.CustomParseException;

public interface CustomParser<T> {
	
	T parse(String parameterToParse) throws CustomParseException;
}
