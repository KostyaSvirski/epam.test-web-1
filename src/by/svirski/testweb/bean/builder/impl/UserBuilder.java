package by.svirski.testweb.bean.builder.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import by.svirski.testweb.bean.Gender;
import by.svirski.testweb.bean.RoleInProject;
import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.builder.Builder;
import by.svirski.testweb.bean.parser.CustomParser;
import by.svirski.testweb.bean.parser.exception.CustomParseException;
import by.svirski.testweb.bean.parser.impl.DateParser;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.bean.validator.CustomValidator;
import by.svirski.testweb.bean.validator.impl.DateValidator;
import by.svirski.testweb.bean.validator.impl.PhoneValidator;

public class UserBuilder implements Builder<User> {

	public UserBuilder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User build(Map<UserType, String> parameters) {
		User user = new User();
		user.setLogin(parameters.get(UserType.LOGIN));
		user.setPassword(parameters.get(UserType.PASSWORD));
		user.setBlocked(false);
		user.setRoleInProject(RoleInProject.USER);
		user.setEmail(parameters.get(UserType.EMAIL));
		user.setName(parameters.get(UserType.NAME));
		user.setSurname(parameters.get(UserType.SURNAME));
		user.setPassportId(parameters.get(UserType.PASSPORT_ID));
		user.setPassportNumber(parameters.get(UserType.PASSPORT_NUMBER));
		CustomValidator validator = new DateValidator();
		if(validator.validate(parameters.get(UserType.DATE_OF_BIRTH))) {
			CustomParser<Calendar> parser = new DateParser();
			Calendar dateOfBirth = null;
			try {
				dateOfBirth = parser.parse(parameters.get(UserType.DATE_OF_BIRTH));
			} catch (CustomParseException e) {
				dateOfBirth = new GregorianCalendar();
			}
			user.setDateOfBirth(dateOfBirth);
		} 
		validator = new PhoneValidator();
		if(validator.validate(parameters.get(UserType.PHONE_NUMBER))) {
			user.setPhoneNumber(parameters.get(UserType.PHONE_NUMBER));
		}
		Gender[] genders = Gender.values();
		for(Gender gender : genders) {
			if(gender.toString().equalsIgnoreCase(parameters.get(UserType.GENDER))) {
				user.setGender(gender);
			}
		}
		return user;
	}

	
}
