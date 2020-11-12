package by.svirski.testweb.bean;

public enum Gender {
	
	MALE {
		@Override
		public String toString() {
			return "Мужчина";
		}
	},
	FEMALE {
		@Override
		public String toString() {
			return "Женщина";
		}
	};
	
	public abstract String toString();

}
