package by.svirski.testweb.bean.builder;

import java.util.Map;

/**
 * interface represents builder for beans
 * @param <T> - bean that will be built
 * @param <U> - keys in map with parameters on which bean will be built
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public interface Builder<T, U> {
	
	/**
	 * method for build bean
	 * @param parameters - parameters on which bean will be built
	 * @return bean 
	 */
	T build(Map<U, String> parameters);
}
