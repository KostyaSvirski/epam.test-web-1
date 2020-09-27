package by.svirski.testweb.bean;

public enum Fuel {
	
	PETROL {
		@Override
		public String toString() {
			return "Бензин";
		}
	},
	DIESEL {
		@Override
		public String toString() {
			return "Дизель";
		}
	},
	ELECTRO {
		@Override
		public String toString() {
			return "Электричество";
		}
	};
	
	public abstract String toString();

}
