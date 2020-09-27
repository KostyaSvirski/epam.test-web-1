package by.svirski.testweb.bean;

public enum Gender {
	
	MALE {
		@Override
		public String ToString() {
			return "Мужчина";
		}
	},
	FEMALE {
		@Override
		public String ToString() {
			return "Женщина";
		}
	};
	
	public abstract String ToString();

}
