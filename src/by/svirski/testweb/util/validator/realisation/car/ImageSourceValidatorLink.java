package by.svirski.testweb.util.validator.realisation.car;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.svirski.testweb.bean.type.TypeOfParameters.CarType;
import by.svirski.testweb.util.validator.realisation.IntermidiateCarLink;

public class ImageSourceValidatorLink extends IntermidiateCarLink {

	private static final String URL_REGEXP = "http://\\w+(.){0,}(.)[a-z]+/.{0,}(.)(png)|"
			+ "http://\\w+(.){0,}(.)[a-z]+/.{0,}(.)(jpg)|" + "http://\\w+(.){0,}(.)[a-z]+/(.){0,}(.)(jpeg)|"
			+ "https://\\w+(.){0,}(.)[a-z]+/(.){0,}(.)(png)|" + "https://\\w+(.){0,}(.)[a-z]+/(.){0,}(.)(jpg)|"
			+ "https://\\w+(.){0,}(.)[a-z]+/(.){0,}(.)(jpeg)";

	public ImageSourceValidatorLink() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validate(Map<CarType, String> params) {
		Pattern pattern = Pattern.compile(URL_REGEXP);
		Matcher matcher = pattern.matcher(params.get(CarType.IMG));
		if (matcher.matches()) {
			return checkNextLink(params);
		}
		return false;
	}

}
