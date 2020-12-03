package by.svirski.testweb.util.validator.realisation;

import java.util.Map;
import java.util.Map.Entry;

import by.svirski.testweb.bean.type.TypeOfParameters.OrderType;
import by.svirski.testweb.util.validator.PreparedValidatorsChain;

/**
 * class represents base validator for order maps
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class IntermidiateOrderValidator extends PreparedValidatorsChain<OrderType> {

	protected final static String REG_EXP_DATE = "\\d{4}[.-]{1}\\d{1,2}[.-]{1}\\d{1,2}";
	
	public IntermidiateOrderValidator() {
	}

	/**
	 * overridden method {@link PreparedValidatorsChain#validate(Map)} to check parameters on empty
	 */
	@Override
	public boolean validate(Map<OrderType, String> params) {
		for (Entry<OrderType, String> entry : params.entrySet()) {
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
