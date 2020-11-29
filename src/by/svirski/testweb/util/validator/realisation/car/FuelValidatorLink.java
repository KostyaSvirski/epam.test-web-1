package by.svirski.testweb.util.validator.realisation.car;

import java.util.Map;

import by.svirski.testweb.bean.Fuel;
import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.util.validator.realisation.IntermidiateCarLink;

public class FuelValidatorLink extends IntermidiateCarLink {

	public FuelValidatorLink() {
		// TODO Auto-generated constructor stub
	}

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
