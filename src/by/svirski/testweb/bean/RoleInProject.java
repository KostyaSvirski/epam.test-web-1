package by.svirski.testweb.bean;

public enum RoleInProject {
	
	ADMIN {
		@Override
		public String toString() {
			return "admin";
		}
	},
	USER {
		@Override
		public String toString() {
			return "user";
		}
	},
	VIP {
		@Override
		public String toString() {
			return "vip";
		}
	};
	
	public abstract String toString();
}
