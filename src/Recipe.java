

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
/**
 * 
 * @author Aurko Mallick
 *
 */
public final class Recipe extends Consumable {
	/**
	 * Recipe Constructor will create a new Recipe collection after calling the
	 * super constructor, the function will add the given consumable object to
	 * it's collection. After adding to the collection, the function will add
	 * the consumable's nutritional values to recipe's values.
	 * 
	 * @param id
	 * @param name
	 * @param consumable
	 */
	public Recipe(char id, String name, ArrayList<Consumable> consumable) {
		super(id, name);
		this.addIngredient(consumable);
	}


	/**
	 * isAFood determines whether object is a food or not
	 * 
	 * @return false
	 */
	public boolean isAFood() {
		return false;
	}

	/**
	 * isARecipe determines whether object is a recipe or not
	 * 
	 * @return true
	 */
	public boolean isARecipe() {
		return true;
	}

	/**
	 * @return the recipe
	 */
	public ArrayList<Consumable> getRecipe() {
		return recipe;
	}

	/**
	 * @param recipe
	 *            the recipe to set
	 */
	public void setRecipe(ArrayList<Consumable> recipe) {
		this.recipe = recipe;
	}

	/**
	 * Function adds a consumable to the recipe the function then adds the
	 * nutrient values of the consumable to object's nutrient values.
	 * 
	 * @param ingredient
	 */
	public boolean addIngredient(ArrayList<Consumable> ingredient) {
		try {
			// Loop through each consumable in collection
			for (int i = 0; i < ingredient.size(); i++) {
				Consumable temp = ingredient.get(i);
				if(this.recipe.contains(temp)){

				}else{
					this.recipe.add(temp);
					this.calories += temp.getCalories();
					this.carbohydrates += temp.getCarbohydrates();
					this.fat += temp.getFat();
					this.protein += temp.getProtein();
				}
			} // end for
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} // end catch
		return true;
	}

	public boolean editRecipe(char id, String name, int quantity){
		for(int i = 0; i<= recipe.size(); i++){
			if(recipe.get(i).getId() == id){
				recipe.get(i).setQuantity(quantity);
            recipe.get(i).setName(name);
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove function will remove an ingredient based
	 * 
	 * @param id
	 * @return
	 */
	public boolean removeIngredient(char id) {
		boolean result = this.recipe.remove(this.searchIngredient(id));
		return result;
	}

	/**
	 * Function loops through collection to see if the id of consumable was
	 * found
	 * 
	 * @param id
	 * @return
	 */
	public Consumable searchIngredient(char id) {
		for (int i = 0; i < this.recipe.size(); i++) {
			if (this.recipe.get(i).getId() == id) {
				return recipe.get(i);
			}
		}
		return null;// recipe not found
	}

	/**
	 * To String method
	 * 
	 */
	public String toString() {
		String output = "Recipe";
		output += ",ID," + this.getId();
		output += ",name," + this.getName();
		output += ",calories," + calories + ",fat," + fat + ",carbohydrates," + carbohydrates
				+ ",protein," + protein + ",id," + id+",qty,"+getQuantity();
		output += "\n\tIngredients;";
		for (int i = 0; i < this.recipe.size(); i++) {
			output += "\n\tname," + this.recipe.get(i).getName()+",qty,"+this.recipe.get(i).getQuantity();
		}
		output +=";";
		return output;
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
		if (!(obj instanceof Recipe)) {
			return false;
		}
		Recipe other = (Recipe) obj;
		if (recipe == null) {
			if (other.recipe != null) {
				return false;
			}
		} else if (!recipe.equals(other.recipe)) {
			return false;
		}
		return true;
	}



	/*
	 * Instance Variables
	 */
	private ArrayList<Consumable> recipe = new ArrayList<Consumable>();
}
