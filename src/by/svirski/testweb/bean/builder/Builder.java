package by.svirski.testweb.bean.builder;

import java.util.Map;

import by.svirski.testweb.bean.type.TypeOfParameters;

public interface Builder<T> {
	
	T build(Map<TypeOfParameters.UserType, String> parameters);
}
