package by.svirski.testweb.bean.parser;

public interface CustomParser<T> {
	
	T parse(String parameterToParse);
}
