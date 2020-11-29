package by.svirski.testweb.util.validator.realisation;

import java.util.Map;
import java.util.Map.Entry;

import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.util.validator.PreparedValidatorsChain;

public class IntermidiateUserLink extends PreparedValidatorsChain<UserType> {

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
