package by.svirski.testweb.bean.builder.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.svirski.testweb.bean.Gender;
import by.svirski.testweb.bean.RoleInProject;
import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.builder.Builder;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;
import by.svirski.testweb.util.parser.CustomParser;
import by.svirski.testweb.util.parser.exception.CustomParseException;
import by.svirski.testweb.util.parser.impl.DateParser;
import by.svirski.testweb.util.validator.CustomValidator;
import by.svirski.testweb.util.validator.impl.DateValidator;
import by.svirski.testweb.util.validator.impl.PhoneValidator;

public class UserBuilder implements Builder<User, UserType> {
	
	private static Logger logger = LogManager.getLogger(UserBuilder.class);

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
				logger.log(Level.INFO, "не корректная дата");
			}
			user.setDateOfBirth(dateOfBirth);
		} 
		validator = new PhoneValidator();
		if(validator.validate(parameters.get(UserType.PHONE_NUMBER))) {
			user.setPhoneNumber(parameters.get(UserType.PHONE_NUMBER));
		} else {
			logger.log(Level.INFO, "не корректный номер телефона");
			user.setPhoneNumber("no phone");
		}
		Gender[] genders = Gender.values();
		for(Gender gender : genders) {
			if(gender.name().equalsIgnoreCase(parameters.get(UserType.GENDER))) {
				user.setGender(gender);
			}
		}
		logger.log(Level.DEBUG, "построился объект User");
		return user;
	}

	
}
