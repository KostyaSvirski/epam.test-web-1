package by.svirski.testweb.bean;

public class Car {
	
	private int id;
	private String brand;
	private String model;
	private String carClass;
	private int power;
	private String engine;
	private float acceleration;
	private DriveUnit driveUnit;
	private Fuel fuel;
	private long cost;
	private String image;
	private boolean isBooked;

	public Car() {
		
	}

	public Car(int id, String brand, String model, String carClass, int power, String engine, float acceleration,
			DriveUnit driveUnit, Fuel fuel, long cost, String image, boolean isBooked) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.carClass = carClass;
		this.power = power;
		this.engine = engine;
		this.acceleration = acceleration;
		this.driveUnit = driveUnit;
		this.fuel = fuel;
		this.cost = cost;
		this.image = image;
		this.isBooked = isBooked;
	}

	public int getId() {
		return id;
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

	public String getEngine() {
		return engine;
	}

	public float getAcceleration() {
		return acceleration;
	}

	public String getDriveUnit() {
		return driveUnit.toString();
	}

	public String getFuel() {
		return fuel.toString();
	}

	public long getCost() {
		return cost;
	}

	public String getImage() {
		return image;
	}

	public boolean getIsBooked(){
		return isBooked;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setEngine(String engine) {
		this.engine = engine;
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

	public void setImage(String image) {
		this.image = image;
	}

	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
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
		result = prime * result + ((engine == null) ? 0 : engine.hashCode());
		result = prime * result + ((fuel == null) ? 0 : fuel.hashCode());
		result = prime * result + id;
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + (isBooked ? 1231 : 1237);
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
		if (engine == null) {
			if (other.engine != null) {
				return false;
			}
		} else if (!engine.equals(other.engine)) {
			return false;
		}
		if (fuel != other.fuel) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (image == null) {
			if (other.image != null) {
				return false;
			}
		} else if (!image.equals(other.image)) {
			return false;
		}
		if (isBooked != other.isBooked) {
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
		builder.append("Car [id=");
		builder.append(id);
		builder.append(", brand=");
		builder.append(brand);
		builder.append(", model=");
		builder.append(model);
		builder.append(", carClass=");
		builder.append(carClass);
		builder.append(", power=");
		builder.append(power);
		builder.append(", engine=");
		builder.append(engine);
		builder.append(", acceleration=");
		builder.append(acceleration);
		builder.append(", driveUnit=");
		builder.append(driveUnit);
		builder.append(", fuel=");
		builder.append(fuel);
		builder.append(", cost=");
		builder.append(cost);
		builder.append(", image=");
		builder.append(image);
		builder.append(", isBooked=");
		builder.append(isBooked);
		builder.append("]");
		return builder.toString();
	}

	
	
	
}
