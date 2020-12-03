package by.svirski.testweb.bean;

import java.util.Calendar;

/**
 * class represented User Bean with fields:
 * <b>id</b>, <b>login</b>, <b>isBlocked</b>, <b>roleInProject</b>, <b>name</b>,
 * <b>surname</b>, <b>passportId</b>, <b>passportNumber</b>, <b>dateOfBirth</b>, 
 * <b>email</b>, <b>phoneNumber</b>, <b>gender</b>
 *  
 * @author Kostya Svirski
 * @version 1.0
 */

public class User{
	/**
	 * field represented id of User in db
	 */
	private int id;
	/**
	 * field represented login under which the user was registered
	 */
	private String login;
	/**
	 * field represented status of user (blocked or not blocked)
	 */
	private boolean isBlocked;
	/**
	 * field represented role of user
	 * @see RoleInProject
	 */
	private RoleInProject roleInProject;
	/**
	 * field represented name of user
	 */
	private String name;
	/**
	 * field represented surname of user
	 */
	private String surname;
	/**
	 * field represented passport id of user
	 */
	private String passportId;
	/**
	 * field represented passport number of user
	 */
	private String passportNumber;
	/**
	 * field represented date of birth of user
	 */
	private Calendar dateOfBirth;
	/**
	 * field represented email of user
	 */
	private String email;
	/**
	 * field represented phone number of user
	 */
	private String phoneNumber;
	/**
	 * field represented gender of user
	 * @see Gender
	 */
	private Gender gender;

	/**
	 * default constructor
	 * @see User#User(int, String, boolean, RoleInProject, String, String, String, String, Calendar, String, String, Gender)
	 */
	public User() {
		
	}
	
	/**
	 * constructor with all parameters necessary for build object 
	 * @param id - id of user
	 * @param login - login of user
	 * @param isBlocked - blocked or active user is
	 * @param roleInProject - role in project
	 * @param name - name of user
	 * @param surname - surname of user
	 * @param passportId - passport id of user
	 * @param passportNumber - passport number of user
	 * @param dateOfBirth - date of birth of user
	 * @param email - email of user
	 * @param phoneNumber - phone number of user
	 * @param gender - gender of user
	 * @see User#User()
	 */
	public User(int id, String login, boolean isBlocked, RoleInProject roleInProject, String name, String surname,
			String passportId, String passportNumber, Calendar dateOfBirth, String email, String phoneNumber,
			Gender gender) {
		super();
		this.id = id;
		this.login = login;
		this.isBlocked = isBlocked;
		this.roleInProject = roleInProject;
		this.name = name;
		this.surname = surname;
		this.passportId = passportId;
		this.passportNumber = passportNumber;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}

	/**
	 * method for getting id of user
	 * @return id of this user
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * method for getting login of user
	 * @return login of this user
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * method for getting status of user
	 * @return status of this user (true - blocked, false - active)
	 */
	public boolean getIsBlocked() {
		return isBlocked;
	}

	/**
	 * method for getting role of user
	 * @return role of this user
	 * @see RoleinProject
	 */
	public RoleInProject getRoleInProject() {
		return roleInProject;
	}

	/**
	 * method for getting name of user
	 * @return name of this user
	 */
	public String getName() {
		return name;
	}

	/**
	 * method for getting surname of user
	 * @return surname of this user
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * method for getting passport id of user
	 * @return passport id of this user
	 */
	public String getPassportId() {
		return passportId;
	}

	/**
	 * method for getting passport number of user
	 * @return passport number of this user
	 */
	public String getPassportNumber() {
		return passportNumber;
	}

	/**
	 * method for getting date of birth in simple view of user
	 * @return date of birth like string of this user
	 */
	public String getDateOfBirth() {
		String dayOfMonth = Integer.toString(dateOfBirth.get(Calendar.DAY_OF_MONTH));
		String month = Integer.toString(dateOfBirth.get(Calendar.MONTH)+1);
		String year = Integer.toString(dateOfBirth.get(Calendar.YEAR));
		StringBuilder sb = new StringBuilder();
		sb.append(dayOfMonth);
		sb.append(".");
		sb.append(month);
		sb.append(".");
		sb.append(year);
		return sb.toString();
	}

	/**
	 * method for getting email of user
	 * @return email of this user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * method for getting phone number of user
	 * @return phone number of this user
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * method for getting gender of user
	 * @return gender of this user
	 * @see Gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * method of setting id of user
	 * @param id - id of user
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * method of setting login of user
	 * @param login - login of user
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * method of setting status of user
	 * @param isBlocked - status (true - blocked, false - active) of user
	 */
	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	/**
	 * method of setting role in project of user
	 * @param roleInProject - role in project of user
	 * @see RoleinProject
	 */
	public void setRoleInProject(RoleInProject roleInProject) {
		this.roleInProject = roleInProject;
	}

	/**
	 * method of setting name of user
	 * @param name - name of user
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * method of setting surname of user
	 * @param surname - surname of user
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * method of setting passport id of user
	 * @param passportId - passport id of user
	 */
	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}

	/**
	 * method of setting passport number of user
	 * @param passportNumber - passport number of user
	 */
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	/**
	 * method of setting date of birth of user
	 * @param dateOfBirth - date of birth of user
	 */
	public void setDateOfBirth(Calendar dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * method of setting email of user
	 * @param email - email of user
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * method of setting phone number of user
	 * @param phoneNumber - phone number of user
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * method of setting gender of user
	 * @param gender - gender of user
	 * @see Gender
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + id;
		result = prime * result + (isBlocked ? 1231 : 1237);
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((passportId == null) ? 0 : passportId.hashCode());
		result = prime * result + ((passportNumber == null) ? 0 : passportNumber.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((roleInProject == null) ? 0 : roleInProject.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null) {
				return false;
			}
		} else if (!dateOfBirth.equals(other.dateOfBirth)) {
			return false;
		}
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (gender != other.gender) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (isBlocked != other.isBlocked) {
			return false;
		}
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (passportId == null) {
			if (other.passportId != null) {
				return false;
			}
		} else if (!passportId.equals(other.passportId)) {
			return false;
		}
		if (passportNumber == null) {
			if (other.passportNumber != null) {
				return false;
			}
		} else if (!passportNumber.equals(other.passportNumber)) {
			return false;
		}
		if (phoneNumber == null) {
			if (other.phoneNumber != null) {
				return false;
			}
		} else if (!phoneNumber.equals(other.phoneNumber)) {
			return false;
		}
		if (roleInProject != other.roleInProject) {
			return false;
		}
		if (surname == null) {
			if (other.surname != null) {
				return false;
			}
		} else if (!surname.equals(other.surname)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", login=");
		builder.append(login);
		builder.append(", isBlocked=");
		builder.append(isBlocked);
		builder.append(", roleInProject=");
		builder.append(roleInProject);
		builder.append(", name=");
		builder.append(name);
		builder.append(", surname=");
		builder.append(surname);
		builder.append(", passportId=");
		builder.append(passportId);
		builder.append(", passportNumber=");
		builder.append(passportNumber);
		builder.append(", dateOfBirth=");
		builder.append(dateOfBirth);
		builder.append(", email=");
		builder.append(email);
		builder.append(", phoneNumber=");
		builder.append(phoneNumber);
		builder.append(", gender=");
		builder.append(gender);
		builder.append("]");
		return builder.toString();
	}
	
}
