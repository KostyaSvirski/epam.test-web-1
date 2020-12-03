package by.svirski.testweb.util.validator.realisation.car;

import java.util.Map;

import by.svirski.testweb.bean.Fuel;
import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.util.validator.realisation.IntermidiateCarLink;

/**
 * class represents validator for fuel
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class FuelValidatorLink extends IntermidiateCarLink {

	public FuelValidatorLink() {
	}

	/**
	 * overridden method {@link IntermidiateCarLink#validate(Map)} to check validity of fuel
	 * @see Fuel
	 */
	@Override
	public boolean validate(Map<CarType, String> params) {
		Fuel[] values = Fuel.values();
		for (Fuel fuel : values) {
			if (fuel.name().equalsIgnoreCase(params.get(CarType.FUEL))
					|| fuel.toString().equalsIgnoreCase(params.get(CarType.FUEL))) {
				return checkNextLink(params);
			}
		}
		return false;
	}

}
