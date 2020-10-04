package by.svirski.testweb.bean.builder.impl;

import java.util.Map;

import by.svirski.testweb.bean.RoleInProject;
import by.svirski.testweb.bean.User;
import by.svirski.testweb.bean.builder.Builder;
import by.svirski.testweb.bean.type.TypeOfParameters.UserType;

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
		
		return user;
	}

	
}
