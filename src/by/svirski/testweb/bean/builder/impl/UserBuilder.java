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

/**
 * class represents builder for User Bean
 * 
 * @see User
 * @see UserType
 * @see Builder
 * 
 * @author Kostya Svirski
 * @version 1.0
 */
public class UserBuilder implements Builder<User, UserType> {

	private static Logger logger = LogManager.getLogger(UserBuilder.class);

	/**
	 * default constructor
	 */
	public UserBuilder() {
	}

	/** 
	 * overriden method {@link Builder#build(Map)} for build User Bean
	 * 
	 */
	@Override
	public User build(Map<UserType, String> parameters) {
		User user = new User();
		user.setId(Integer.parseInt(parameters.get(UserType.ID)));
		user.setLogin(parameters.get(UserType.LOGIN));
		if (parameters.get(UserType.IS_BLOCKED).equalsIgnoreCase(UserType.NOT_BLOCKED.name().toLowerCase())) {
			user.setBlocked(false);
		} else if (parameters.get(UserType.IS_BLOCKED).equalsIgnoreCase(UserType.BLOCKED.name().toLowerCase())) {
			user.setBlocked(true);
		}
		RoleInProject[] roles = RoleInProject.values();
		for (RoleInProject role : roles) {
			if (role.toString().equalsIgnoreCase(parameters.get(UserType.ROLE_IN_PROJECT))) {
				user.setRoleInProject(role);
			}
		}
		user.setEmail(parameters.get(UserType.EMAIL));
		user.setName(parameters.get(UserType.NAME));
		user.setSurname(parameters.get(UserType.SURNAME));
		user.setPassportId(parameters.get(UserType.PASSPORT_ID));
		user.setPassportNumber(parameters.get(UserType.PASSPORT_NUMBER));
		CustomParser<Calendar> parser = new DateParser();
		Calendar dateOfBirth = null;
		try {
			dateOfBirth = parser.parse(parameters.get(UserType.DATE_OF_BIRTH));
		} catch (CustomParseException e) {
			dateOfBirth = new GregorianCalendar();
			logger.log(Level.INFO, "не корректная дата");
		}
		user.setDateOfBirth(dateOfBirth);
		user.setPhoneNumber(parameters.get(UserType.PHONE_NUMBER));
		Gender[] genders = Gender.values();
		for (Gender gender : genders) {
			if (gender.name().equalsIgnoreCase(parameters.get(UserType.GENDER))
					|| gender.toString().equalsIgnoreCase(parameters.get(UserType.GENDER))) {
				user.setGender(gender);
			}
		}
		logger.log(Level.DEBUG, "построился объект User");
		return user;
	}

}
