package by.svirski.testweb.bean;

/**
 * enumeration represents available drivev units for car in project
 * @see Car
 * @author Kostya Svirski
 * @version 1.0
 */
public enum DriveUnit{

	/**
	 * represents all wheel drive 
	 */
	AWD {
		@Override
		public String toString() {
			return "Полный привод";
		}
	},
	
	/**
	 * represents rear wheel drive
	 */
	RWD {
		@Override
		public String toString() {
			return "Задний привод";
		}
	},
	
	/**
	 * represents front wheel drive
	 */
	FWD {
		@Override
		public String toString() {
			return "Передний привод";
		}
	};

	/**
	 * abstract method for output drive unit in simple view
	 * @see DriveUnit.AWD#toSring()
	 * @see DriveUnit.RWD#toSring()
	 * @see DriveUnit.FWD#toSring()
	 */
	public abstract String toString();

}
