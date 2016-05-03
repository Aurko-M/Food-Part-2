

/**
 * 
 * @author Aurko Mallick
 *
 */
abstract class Consumable {

	public Consumable(char id, String name) {
		this.setId(id);
		this.setName(name);
	}

 
	abstract boolean isAFood();

	abstract boolean isARecipe();

	/**
	 * @return the id
	 */
	public char getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(char id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the calories
	 */
	public double getCalories() {
		return calories*quantity;
	}

	/**
	 * @param calories
	 *            the calories to set
	 */
	public void setCalories(double calories) {
		this.calories = calories;
	}

	/**
	 * @return the fat
	 */
	public double getFat() {
		return fat*quantity;
	}

	/**
	 * @param fat
	 *            the fat to set
	 */
	public void setFat(double fat) {
		this.fat = fat;
	}

	/**
	 * @return the protein
	 */
	public double getProtein() {
		return protein*quantity;
	}

	/**
	 * @return the carbohydrates
	 */
	public double getCarbohydrates() {
		return carbohydrates*quantity;
	}

	/**
	 * @param carbohydrates
	 *            the carbohydrates to set
	 */
	public void setCarbohydrates(double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	/**
	 * @param protein
	 *            the protein to set
	 */
	public void setProtein(double protein) {
		this.protein = protein;
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
		if (!(obj instanceof Consumable)) {
			return false;
		}
		Consumable other = (Consumable) obj;
		if (Double.doubleToLongBits(calories) != Double.doubleToLongBits(other.calories)) {
			return false;
		}
		if (Double.doubleToLongBits(carbohydrates) != Double.doubleToLongBits(other.carbohydrates)) {
			return false;
		}
		if (Double.doubleToLongBits(fat) != Double.doubleToLongBits(other.fat)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (Double.doubleToLongBits(protein) != Double.doubleToLongBits(other.protein)) {
			return false;
		}
		if (quantity != other.quantity) {
			return false;
		}
		return true;
	}


	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		if(this.quantity == 1){
			this.quantity = quantity;
		}
		else{
			this.quantity += quantity;
		}
	}

	/*
	 * Instance Variables
	 */
	protected char id;
	protected String name;
	protected double calories = 0;
	protected double carbohydrates = 0;
	protected double fat = 0;
	protected double protein = 0;
	protected int quantity = 1;
}
