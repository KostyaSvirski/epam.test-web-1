package by.svirski.testweb.util.validator.realisation.order;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.svirski.testweb.bean.type.TypeOfParameters.OrderType;
import by.svirski.testweb.util.validator.realisation.IntermidiateOrderValidator;

/**
 * class represents validator for date of start order
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class DateStartValidatorLink extends IntermidiateOrderValidator {

	public DateStartValidatorLink() {
	}

	/**
	 * overridden method {@link IntermidiateOrderLink#validate(Map)} to check validity of start date
	 */
	@Override
	public boolean validate(Map<OrderType, String> params) {
		Pattern pattern = Pattern.compile(REG_EXP_DATE);
		Matcher matcher = pattern.matcher(params.get(OrderType.DATE_OF_START));
		if (matcher.matches()) {
			return checkNextLink(params);
		}
		return false;
	}
	
	

}
