package by.svirski.testweb.controller.tag.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class TagUtil {
	
	private static final String BASENAME = "locale";
	private static final String DELIMETER = "_";

	public TagUtil() {
	}
	
	public static ResourceBundle getBundle(String locale) {
		String[] parsedLocale = locale.split(DELIMETER);
		String language = parsedLocale[0];
		String country = parsedLocale[1];
		Locale newLocale = new Locale(language, country);
		ResourceBundle rb = ResourceBundle.getBundle(BASENAME, newLocale);		
		return rb;
	}

}
