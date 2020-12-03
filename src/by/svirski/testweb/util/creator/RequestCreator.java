package by.svirski.testweb.util.creator;

import java.util.Map;

/**
 * interface represents creator for SQL requests
 * 
 * @param <T> - type of keys in parameters map
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public interface RequestCreator<T> {
	
	/**
	 * method for creating custom request from abstract
	 * @param abstractRequest - base request
	 * @param mapOfAdditionalParams - parameters to add in request
	 * @return custom request
	 */
	String createRequest(String abstractRequest, Map<T, String> mapOfAdditionalParams);
}
