package by.svirski.testweb.bean;

public class Car implements BeanIndicator {
	
	private String brand;
	private String model;
	private String carClass;
	private int power;
	private double engineCapacity;
	private float acceleration;
	private DriveUnit driveUnit;
	private Fuel fuel;
	private long cost;

	public Car() {
		
	}

	public Car(String brand, String model, String carClass, int power, double engineCapacity, float acceleration,
			DriveUnit driveUnit, Fuel fuel, long cost) {
		super();
		this.brand = brand;
		this.model = model;
		this.carClass = carClass;
		this.power = power;
		this.engineCapacity = engineCapacity;
		this.acceleration = acceleration;
		this.driveUnit = driveUnit;
		this.fuel = fuel;
		this.cost = cost;
	}

	public String getBrand() {
		return brand;
	}

	public String getModel() {
		return model;
	}

	public String getCarClass() {
		return carClass;
	}

	public int getPower() {
		return power;
	}

	public double getEngineCapacity() {
		return engineCapacity;
	}

	public float getAcceleration() {
		return acceleration;
	}

	public DriveUnit getDriveUnit() {
		return driveUnit;
	}

	public Fuel getFuel() {
		return fuel;
	}

	public long getCost() {
		return cost;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setCarClass(String carClass) {
		this.carClass = carClass;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setEngineCapacity(double engineCapacity) {
		this.engineCapacity = engineCapacity;
	}

	public void setAcceleration(float acceleration) {
		this.acceleration = acceleration;
	}

	public void setDriveUnit(DriveUnit driveUnit) {
		this.driveUnit = driveUnit;
	}

	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}

	public void setCost(long cost) {
		this.cost = cost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(acceleration);
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((carClass == null) ? 0 : carClass.hashCode());
		result = prime * result + (int) (cost ^ (cost >>> 32));
		result = prime * result + ((driveUnit == null) ? 0 : driveUnit.hashCode());
		long temp;
		temp = Double.doubleToLongBits(engineCapacity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((fuel == null) ? 0 : fuel.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + power;
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
		Car other = (Car) obj;
		if (Float.floatToIntBits(acceleration) != Float.floatToIntBits(other.acceleration)) {
			return false;
		}
		if (brand == null) {
			if (other.brand != null) {
				return false;
			}
		} else if (!brand.equals(other.brand)) {
			return false;
		}
		if (carClass == null) {
			if (other.carClass != null) {
				return false;
			}
		} else if (!carClass.equals(other.carClass)) {
			return false;
		}
		if (cost != other.cost) {
			return false;
		}
		if (driveUnit != other.driveUnit) {
			return false;
		}
		if (Double.doubleToLongBits(engineCapacity) != Double.doubleToLongBits(other.engineCapacity)) {
			return false;
		}
		if (fuel != other.fuel) {
			return false;
		}
		if (model == null) {
			if (other.model != null) {
				return false;
			}
		} else if (!model.equals(other.model)) {
			return false;
		}
		if (power != other.power) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Car [brand=");
		builder.append(brand);
		builder.append(", model=");
		builder.append(model);
		builder.append(", carClass=");
		builder.append(carClass);
		builder.append(", power=");
		builder.append(power);
		builder.append(", engineCapacity=");
		builder.append(engineCapacity);
		builder.append(", acceleration=");
		builder.append(acceleration);
		builder.append(", driveUnit=");
		builder.append(driveUnit);
		builder.append(", fuel=");
		builder.append(fuel);
		builder.append(", cost=");
		builder.append(cost);
		builder.append("]");
		return builder.toString();
	}
	
	

}
