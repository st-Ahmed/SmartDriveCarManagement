public class Car {

    // Instance variables (attributes)
    private String plate;
    private String brand;
    private String model;
    private int year;
    private int speed;

    // Constructor
    public Car(String plate, String brand, String model, int year, int speed) {
        this.setPlate(plate);
        this.setBrand(brand);
        this.setModel(model);
        this.setYear(year);
        this.setSpeed(speed);
    }

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
    
    
}

