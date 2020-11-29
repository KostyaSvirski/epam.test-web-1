package by.svirski.testweb.util.validator.realisation;

import java.util.Map;
import java.util.Map.Entry;

import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.util.validator.PreparedValidatorsChain;

public class IntermidiateCarLink extends PreparedValidatorsChain<CarType> {

	public IntermidiateCarLink() {
	}

	@Override
	public boolean validate(Map<CarType, String> params) {
		for (Entry<CarType, String> entry : params.entrySet()) {
			if (entry.getKey().equals(CarType.DETAIL)) {
				continue;
			}
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
