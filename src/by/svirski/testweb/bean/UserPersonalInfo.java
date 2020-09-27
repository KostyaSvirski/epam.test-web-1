package by.svirski.testweb.bean;

import java.util.Date;

public class UserPersonalInfo extends User {

	private String name;
	private String surname;
	private String passportId;
	private String passportNumber;
	private Date dateOfBirth;
	private String email;
	private String phoneNumber;	
	private Gender gender;
	
	public UserPersonalInfo(String name, String surname, String passportId, String passportNumber, Date dateOfBirth,
			String email, String phoneNumber, Gender gender) {
		super();
		this.name = name;
		this.surname = surname;
		this.passportId = passportId;
		this.passportNumber = passportNumber;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}
	
	public UserPersonalInfo() {
		super();
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
		int result = super.hashCode();
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((passportId == null) ? 0 : passportId.hashCode());
		result = prime * result + ((passportNumber == null) ? 0 : passportNumber.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		UserPersonalInfo other = (UserPersonalInfo) obj;
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
		builder.append("UserPersonalInfo [name=");
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
