

/**
 * 
 * @author Aurko Mallick
 *
 */
public final class Food extends Consumable {
	/**
	 * Parameterized Food Constructor
	 * 
	 * @param id
	 * @param name
	 * @param calories
	 * @param fat
	 * @param protein
	 */
	public Food(char id, String name, double calories, double carbohydrates, double fat, double protein) {
		super(id, name);
		this.setCalories(calories);
		this.setCarbohydrates(carbohydrates);
		this.setFat(fat);
		this.setProtein(protein);
	}

	/**
	 * isAFood determines whether object is a food or not
	 * 
	 * @return true
	 */
	public boolean isAFood() {
		return true;
	}

	/**
	 * isARecipe determines whether object is a recipe or not
	 * 
	 * @return false
	 */
	public boolean isARecipe() {
		return false;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Food,name," + name + ",calories," + calories + ",fat," + fat + ",carbohydrates," + carbohydrates
				+ ",protein," + protein + ",id," + id;
	}
}
