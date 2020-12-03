package by.svirski.testweb.util.validator.realisation;

import java.util.Map;
import java.util.Map.Entry;

import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.util.validator.PreparedValidatorsChain;

/**
 * class represents base validator for user maps
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class IntermidiateUserLink extends PreparedValidatorsChain<UserType> {

	/**
	 * overridden method {@link PreparedValidatorsChain#validate(Map)} to check parameters on empty
	 */
	@Override
	public boolean validate(Map<UserType, String> params) {
		for (Entry<UserType, String> entry : params.entrySet()) {
			String value = entry.getValue();
			if (value == null) {
				return false;
			}
			if (value.isEmpty() || value.isBlank()) {
				return false;
			}
		}
		return checkNextLink(params);
	}

	
}
