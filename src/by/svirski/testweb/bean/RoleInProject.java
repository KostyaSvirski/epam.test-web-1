package by.svirski.testweb.bean;

/**
 * 
 * enumeration representing role of user in project
 * @see User
 * @author Kostya Svirski
 * @version 1.0
 */
public enum RoleInProject {
	
	/**
	 *  represents Admin position
	 */
	ADMIN {
		@Override
		public String toString() {
			return "admin";
		}
	},
	/**
	 * represents User position
	 */
	USER {
		@Override
		public String toString() {
			return "user";
		}
	};
	
	/**
	 * abstract method for output role in simple view
	 * @see RoleInProject.USER#toString()
	 * @see RoleInProject.ADMIN#toString()
	 */
	public abstract String toString();
}
