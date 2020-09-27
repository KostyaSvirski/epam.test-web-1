package by.svirski.testweb.bean;

public class User implements BeanIndicator{
	
	private String login;
	private int password;
	private boolean isBlocked;
	private String roleInProject;	

	public User() {
		
	}

	public User(String login, int password, boolean isBlocked, String roleInProject) {
		super();
		this.login = login;
		this.password = password;
		this.isBlocked = isBlocked;
		this.roleInProject = roleInProject;
	}

	public String getLogin() {
		return login;
	}

	public int getPassword() {
		return password;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public String getRoleInProject() {
		return roleInProject;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public void setRoleInProject(String roleInProject) {
		this.roleInProject = roleInProject;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isBlocked ? 1231 : 1237);
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + password;
		result = prime * result + ((roleInProject == null) ? 0 : roleInProject.hashCode());
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
		if (password != other.password) {
			return false;
		}
		if (roleInProject == null) {
			if (other.roleInProject != null) {
				return false;
			}
		} else if (!roleInProject.equals(other.roleInProject)) {
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
		builder.append("]");
		return builder.toString();
	}
	
	

}
