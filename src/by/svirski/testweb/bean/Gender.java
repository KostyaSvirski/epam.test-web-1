package by.svirski.testweb.bean;

/**
 * enumeration represents available genders for user in project 
 * @see User
 * @author Kostya Svirski
 * @version 1.0
 */
public enum Gender {
	
	/**
	 * represents male gender
	 */
	MALE {
		@Override
		public String toString() {
			return "Мужчина";
		}
	},
	/**
	 * represents female gender
	 */
	FEMALE {
		@Override
		public String toString() {
			return "Женщина";
		}
	};
	/**
	 * abstract method for output gender in simple view 
	 * @see Gender.FEMALE#toString()
	 * @see Gender.MALE#tosString()
	 */
	public abstract String toString();

}
