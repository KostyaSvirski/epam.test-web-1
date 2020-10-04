package by.svirski.testweb.bean;

import java.util.Date;

public class User implements BeanIndicator{
	
	private String login;
	private String password;
	private boolean isBlocked;
	private RoleInProject roleInProject;
	private String name;
	private String surname;
	private String passportId;
	private String passportNumber;
	private Date dateOfBirth;
	private String email;
	private String phoneNumber;	
	private Gender gender;

	public User() {
		
	}

	public User(String login, String password, boolean isBlocked, RoleInProject roleInProject, String name,
			String surname, String passportId, String passportNumber, Date dateOfBirth, String email,
			String phoneNumber, Gender gender) {
		super();
		this.login = login;
		this.password = password;
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

	public User(String login, String password, boolean isBlocked, RoleInProject roleInProject, String name,
			String surname) {
		super();
		this.login = login;
		this.password = password;
		this.isBlocked = isBlocked;
		this.roleInProject = roleInProject;
		this.name = name;
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public RoleInProject getRoleInProject() {
		return roleInProject;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getPassportId() {
		return passportId;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Gender getGender() {
		return gender;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public void setRoleInProject(RoleInProject roleInProject) {
		this.roleInProject = roleInProject;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

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
		result = prime * result + (isBlocked ? 1231 : 1237);
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((passportId == null) ? 0 : passportId.hashCode());
		result = prime * result + ((passportNumber == null) ? 0 : passportNumber.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
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
		builder.append("User [login=");
		builder.append(login);
		builder.append(", password=");
		builder.append(password);
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
