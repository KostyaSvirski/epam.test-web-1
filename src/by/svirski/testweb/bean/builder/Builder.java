package by.svirski.testweb.bean.builder;

import java.util.Map;

public interface Builder<T, U> {
	
	T build(Map<U, String> parameters);
}
