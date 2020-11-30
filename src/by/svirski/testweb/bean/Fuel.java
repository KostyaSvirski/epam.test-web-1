package by.svirski.testweb.bean;

/**
 * enumeration represents available fuel for car in project
 * @see Car
 * @author Kostya Svirski
 * @version 1.0
 */
public enum Fuel {
	
	/**
	 * represents petrol fuel
	 */
	PETROL {
		@Override
		public String toString() {
			return "Бензин";
		}
	},
	/**
	 * represents diesel fuel
	 */
	DIESEL {
		@Override
		public String toString() {
			return "Дизель";
		}
	},
	/**
	 * represents electrical fuel
	 */
	ELECTRO {
		@Override
		public String toString() {
			return "Электричество";
		}
	};
	
	/**
	 * abstract method for output fuel in simple view 
	 * @see Fuel.PETROL#toString()
	 * @see Fuel.DIESEL#toString()
	 * @see Fuel.ELECTRO#toString()
	 */
	public abstract String toString();

}
