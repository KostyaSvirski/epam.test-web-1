package by.svirski.testweb.bean;

public enum DriveUnit{

	AWD {
		@Override
		public String toString() {
			return "Полный привод";
		}
	},
	RWD {
		@Override
		public String toString() {
			return "Задний привод";
		}
	},
	FWD {
		@Override
		public String toString() {
			return "Передний привод";
		}
	};

	public abstract String toString();

}
