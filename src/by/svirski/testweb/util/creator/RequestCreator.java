package by.svirski.testweb.util.creator;

import java.util.Map;

public interface RequestCreator<T> {
	
	String createRequest(String abstractRequest, Map<T, String> mapOfAdditionalParams);
}
