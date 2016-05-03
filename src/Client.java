

/**
 * 
 * @author Aurko Mallick, 
 *
 */
public class Client {

	/**
	 * Default Constructor creates a default object
	 */
	public Client(){
		this.setName("John Smith");
		this.setAge(20);
		this.setWeight(150);
		this.setMaxCalories(2000);
	}
	
	/**
	 * Paramterized Constructor creates object
	 * based on given fields.
	 * @param name
	 * @param age
	 * @param weight
	 */
	public Client(String name, int age, double weight) {
		this.setName(name);
		this.setAge(age);
		this.setWeight(weight);
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	/**
	 * @return the maxCalories
	 */
	public double getMaxCalories() {
		return maxCalories;
	}

	/**
	 * @param maxCalories the maxCalories to set
	 */
	public void setMaxCalories(double maxCalories) {
		this.maxCalories = maxCalories;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Client [name=" + name + ", age=" + age + ", weight=" + weight + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Client)) {
			return false;
		}
		Client other = (Client) obj;
		if (age != other.age) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight)) {
			return false;
		}
		return true;
	}



	/*
	 * Instance Variables
	 */
	private String name;
	private int age;
	private double weight;
	private double maxCalories = 2000;
}

