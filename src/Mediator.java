
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * 
 * @author Aurko
 *
 */
public class Mediator {
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean addToRecipe(char id, String values) {
		try {
			((Recipe) getConsumable(id)).addIngredient(makeListOfIngredients(values));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Searches for consumable in the collection returns the recipe object if
	 * found, else return null
	 * 
	 * @param id
	 * @return
	 */
	public Consumable getConsumable(char id) {
		// Loop through collection to find if the recipe exists.
		for (int i = 0; i < this.recipeBook.size(); i++) {
			if (this.recipeBook.get(i).getId() == id) {
				return recipeBook.get(i);
			}
		}
		return null;
	}

	public Activity getActivity(String name) {
		// Loop through collection to find if the recipe exists.
		for (int i = 0; i < this.exerciseList.size(); i++) {
			if (this.exerciseList.get(i).getName() == name) {
				return exerciseList.get(i);
			}
		}
		return null;
	}

	/**
	 * Edits a recipe quantity
	 * 
	 * @return
	 */
	public boolean editRecipe(char cid, char rid, String name, int qty) {
		for (int i = 0; i < recipeBook.size(); i++) {
			if (recipeBook.get(i).getId() == cid) {
				Recipe temp = (Recipe) recipeBook.get(i);
				temp.editRecipe(rid, name, qty);
				recipeBook.set(i, temp);
				return true;
			}
		}
		return false;
	}

	/**
	 * Edit the recipe's properties
	 * 
	 * @param id
	 * @param name
	 * @param qty
	 * @return
	 */
	public boolean editRecipeProperties(char id, String name, int qty) {
		for (int i = 0; i < recipeBook.size(); i++) {
			if (recipeBook.get(i).getId() == id) {
				recipeBook.get(i).setName(name);
				recipeBook.get(i).setQuantity(qty);
				return true;
			}
		}
		return false;
	}

	/**
	 * Edit Food function, searches the collection for food by the given id,
	 * modifies the attributes of food object if found.
	 * 
	 * @param id
	 * @param name
	 * @param calories
	 * @param carbohydrates
	 * @param fat
	 * @param protein
	 * @return
	 */
	public boolean editFood(char id, String name, double calories, double carbohydrates, double fat, double protein) {
		try {
			for (int i = 0; i < recipeBook.size(); i++) {
				if (recipeBook.get(i).getId() == id) {
					recipeBook.get(i).setName(name);
					recipeBook.get(i).setCalories(calories);
					recipeBook.get(i).setCarbohydrates(carbohydrates);
					recipeBook.get(i).setFat(fat);
					recipeBook.get(i).setProtein(protein);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Function will create a new recipe
	 * 
	 * @param id
	 * @param name
	 * @param recipe
	 * @return
	 */
	public boolean createRecipe(char id, String name, String recipe) {
		try {
			ArrayList<Consumable> listOfIngredients = makeListOfIngredients(recipe);
			if (listOfIngredients != null) {
				Recipe r = new Recipe(id, name, listOfIngredients);
				this.recipeBook.add(r);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Function removes reference to the recipe
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteConsumable(char id) {
		return recipeBook.remove(getConsumable(id));
	}
	
	public boolean deleteActivity(String name){
		return exerciseList.remove(getActivity(name));
	}

	/**
	 * 
	 * @param id
	 * @param n
	 * @param cal
	 * @param carb
	 * @param fat
	 * @param protein
	 * @return
	 */
	public boolean createFood(char id, String n, double cal, double carb, double fat, double protein) {
		boolean result = false;
		try {
			Consumable food = new Food(id, n, cal, carb, fat, protein);
			this.recipeBook.add(food);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Function returns an arraylist of all consumables.
	 * 
	 * @return
	 */
	public ArrayList<Consumable> getConsumables() {
		return recipeBook;
	}

	/**
	 * Function returns an arraylist of all consumables.
	 * 
	 * @return
	 */
	public ArrayList<Activity> getWorkouts() {
		return exerciseList;
	}

	/**
	 * writeLog function will take an id for a consumable and see if it
	 * exists/is found, if it is found then the function will extract the
	 * consumable's values and store them into an arraylist of strings which
	 * will be pass to the log class' write function which will store the
	 * information into a CSV file.
	 * 
	 * @param id
	 * @return
	 */
	public boolean writeLog(char id) {
		Consumable storeFood = this.getConsumable(id);
		if (storeFood != null) {
			ArrayList<String> loggedConsumable = new ArrayList<String>();
			String year = Calendar.getInstance().get(Calendar.YEAR) + "";
			String month = Calendar.getInstance().get(Calendar.MONTH) + "";
			String day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "";
			loggedConsumable.add(year);
			loggedConsumable.add(month);
			loggedConsumable.add(day);
			loggedConsumable.add("f");
			loggedConsumable.add(storeFood.getName());
			loggedConsumable.add(storeFood.getQuantity() + "");

			Log logFile = new Log();
			logFile.write(loggedConsumable);
			return true;
		}
		return false;
	}
	
	public boolean writeLog(String id) {
		Activity exer = this.getActivity(id);
		if (exer != null) {
			ArrayList<String> loggedConsumable = new ArrayList<String>();
			String year = Calendar.getInstance().get(Calendar.YEAR) + "";
			String month = Calendar.getInstance().get(Calendar.MONTH) + "";
			String day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "";
			loggedConsumable.add(year);
			loggedConsumable.add(month);
			loggedConsumable.add(day);
			loggedConsumable.add("x");
			loggedConsumable.add(exer.getName());
			loggedConsumable.add(exer.getCalories() + "");

			Log logFile = new Log();
			logFile.write(loggedConsumable);
			return true;
		}
		return false;
	}

	/**
	 * writeLog function will take an arraylist of consumables and extract their
	 * total calorie count; After that it logs the total calling the log
	 * function in the log class
	 * 
	 * @param totalForDay
	 * @return
	 */
	public boolean writeLog(ArrayList<Consumable> totalForDay) {
		try {
			double totalCalories = 0;
			for (int i = 0; i < totalForDay.size(); i++) {
				totalCalories += totalForDay.get(i).getCalories();
			}
			ArrayList<String> loggingInfo = new ArrayList<String>();
			String year = Calendar.getInstance().get(Calendar.YEAR) + "";
			String month = Calendar.getInstance().get(Calendar.MONTH) + "";
			String day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "";
			loggingInfo.add(year);
			loggingInfo.add(month);
			loggingInfo.add(day);
			loggingInfo.add("c");
			loggingInfo.add(totalCalories + "");
			Log logFile = new Log();
			logFile.write(loggingInfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean writeLog(Double consCal, Double actCal) {
		try {
			double netCal = consCal - actCal;
			ArrayList<String> loggingInfo = new ArrayList<String>();
			String year = Calendar.getInstance().get(Calendar.YEAR) + "";
			String month = Calendar.getInstance().get(Calendar.MONTH) + "";
			String day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "";
			loggingInfo.add(year);
			loggingInfo.add(month);
			loggingInfo.add(day);
			loggingInfo.add("c");
			loggingInfo.add(netCal + "");
			Log logFile = new Log();
			logFile.write(loggingInfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * writeExerciseLog will take an arraylist of exercises and loop through the
	 * arraylist storing the values of each exercise after the loop ends the
	 * function will write the values to the exercise.csv file.
	 * 
	 * @param exercises
	 * @return
	 */
	public boolean writeExerciseLog(ArrayList<Activity> exercises) {
		try {
			ArrayList<String> loggingInfo = new ArrayList<String>();
			Log logFile = new Log("exercise.csv");
			for (int i = 0; i < exercises.size(); i++) {
				if (exercises.get(i).isAExercise()) {
					loggingInfo.add("e");
					loggingInfo.add(exercises.get(i).getName() + " (" + exercises.get(i).getDesc()+")");
					loggingInfo.add(exercises.get(i).getCalories() + "");
				} else {
					loggingInfo.add("w");
					loggingInfo.add(exercises.get(i).getName());
					ArrayList<Activity> activities = ((Workout)exercises.get(i)).getWorkout();
					for(int j = 0; j < activities.size(); j++){
						loggingInfo.add(activities.get(j).getName() + " (" + activities.get(j).getDesc()+")");
						loggingInfo.add(activities.get(j).getCalories() + "");
					}
					
				}
				logFile.write(loggingInfo);
				loggingInfo = new ArrayList<String>();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}// end writeExercises

	/**
	 * writeLog function will take an arraylist of consumables and extract their
	 * total calorie count; After that it logs the total calling the log
	 * function in the log class
	 * 
	 * @param totalForDay
	 * @return
	 */
	public boolean writeFoodLog(ArrayList<Consumable> consumables) {
		try {
			ArrayList<String> loggingInfo = new ArrayList<String>();
			Log logFile = new Log("food.csv");
			for (int i = 0; i < consumables.size(); i++) {
				if (consumables.get(i).isAFood()) {
					loggingInfo.add("b");
				} else {
					loggingInfo.add("r");
				}
				loggingInfo.add(consumables.get(i).getId() + "");
				loggingInfo.add(consumables.get(i).getName());
				loggingInfo.add(consumables.get(i).getCalories() + "");
				loggingInfo.add(consumables.get(i).getCarbohydrates() + "");
				loggingInfo.add(consumables.get(i).getFat() + "");
				loggingInfo.add(consumables.get(i).getProtein() + "");
				logFile.write(loggingInfo);
				loggingInfo = new ArrayList<String>();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean writeCSVLogs(){
		Log logfile = new Log("exercise.csv");
		logfile.deleteLogContents("exercise.csv");
		logfile.deleteLogContents("food.csv");
		this.writeExerciseLog(exerciseList);
		this.writeFoodLog(recipeBook);
		return false;
	}
	
	/**
	 * Function will take a double value and save it into an array list which
	 * will be logged to the csv file calling the log class' log function.
	 * 
	 * @param weight
	 * @return
	 */
	public boolean writeLog(double weight) {
		try {
			ArrayList<String> loggingInfo = new ArrayList<String>();
			loggingInfo.add("w");
			loggingInfo.add(weight + "");
			Log logFile = new Log("log.csv");
			logFile.write(loggingInfo);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean changeClientInfo(String name, int age, double weight, double maxCal) {
		try {
			myClient.setName(name);
			myClient.setAge(age);
			myClient.setWeight(weight);
			myClient.setMaxCalories(maxCal);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Client getClient() {
		return myClient;
	}

	/**
	 * Function will call the log function from the log class and return the
	 * given String
	 * 
	 * @return
	 */
	public String printLog(String filename) {
		Log logFile = new Log(filename);
		return logFile.read();
	}

	public boolean removeLog(String date) {
		Log myLog = new Log("log.csv");
		try {
			myLog.remove(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean EditLog(File doc) {
		return false;
	}

	/**
	 * function creates an arraylist of consumables from a passed string
	 * 
	 * @param recipe
	 * @return
	 */
	private ArrayList<Consumable> makeListOfIngredients(String recipe) {
		ArrayList<Consumable> listOfIngredients = new ArrayList<Consumable>();
		for (int i = 0; i < recipe.length();) {
			Consumable ingredient = getConsumable(recipe.charAt(i));
			if (ingredient == null) {
				return null;
			}

			ingredient.setQuantity(Character.getNumericValue(recipe.charAt((i + 1))));
			listOfIngredients.add(ingredient);
			i += 2;
		}
		return listOfIngredients;
	}

	/**
	 * Function adds an Exercise to the collection
	 * 
	 * @param name
	 * @param desc
	 * @param calories
	 * @return
	 */
	public boolean addExercise(String name, String desc, int calories) {
		try {
			this.exerciseList.add(new Exercise(name, desc, calories));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Function edits an Activity.
	 * 
	 * @param name
	 * @param desc
	 * @param calories
	 * @return
	 */
	public boolean editActivity(String name, String newName, String desc, int calories) {
		try {
			for (int i = 0; i < exerciseList.size(); i++) {
				if (exerciseList.get(i).getName() == name) {
					exerciseList.get(i).setName(newName);
					exerciseList.get(i).setDesc(desc);
					if(calories != -1){
						exerciseList.get(i).setCalories(calories);
					}
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Function removes an Activity.
	 * 
	 * @param name
	 * @return
	 */
	public boolean removeActivity(String name) {
		try {
			this.exerciseList.remove(this.getActivity(name));
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Function adds a workout
	 * 
	 * @param name
	 * @param desc
	 * @param calories
	 * @param workouts
	 * @return
	 */
	public boolean addWorkout(String name, String desc, int calories, ArrayList<Activity> workouts) {
		try {
			this.exerciseList.add(new Workout(name, desc, calories, workouts));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * adds activity to existing workout
	 * 
	 * @param name
	 * @param workouts
	 * @return
	 */
	public boolean addToWorkout(String name, ArrayList<Activity> workouts) {
		try {
			Workout myWorkout = (Workout) this.getActivity(name);
			this.exerciseList.remove(exerciseList.indexOf(this.getActivity(name)));
			myWorkout.addWorkout(workouts);
			exerciseList.add(myWorkout);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Function takes an array list of what was consumed for the day returns a
	 * double value containing the total calories.
	 * 
	 * @param dailyConsumed
	 * @return
	 */
	public double calculateConsumed(ArrayList<Consumable> dailyConsumed) {
		double totalCalories = 0;
		for (int i = 0; i < dailyConsumed.size(); i++) {
			totalCalories += dailyConsumed.get(i).getCalories();
		}
		return totalCalories;
	}

	/**
	 * Function takes an Array list of what workouts were dont for the day
	 * returns a double value containing the total calories.
	 * 
	 * @param dailyExpended
	 * @return
	 */
	public double calculateExpended(ArrayList<Activity> dailyExpended) {
		double totalCalories = 0;
		for (int i = 0; i < dailyExpended.size(); i++) {
			totalCalories += dailyExpended.get(i).getCalories();
		}
		return totalCalories;
	}

	/**
	 * writeToMarkDown
	 * 
	 * @param message
	 * @param val
	 * @return
	 */
	public boolean writeToMarkDown(ArrayList<ArrayList<String>> message, String val) {
		// convert String to char array
		char[] markDownType = val.toCharArray();
		// ArrayList to hold message
		ArrayList<String> markDownMessage = new ArrayList<String>();
		// Loop through outter array
		for (int i = 0; i < message.size(); i++) {
			// Add the formatted string to the arraylist
			markDownMessage.add(this.writeMarkDown(markDownType[i], message.get(i)));
		} // end
		MarkDown myLog = new MarkDown();
		return myLog.write(markDownMessage);
	}

	/**
	 * writeMarkDown function takes an arraylist of strings and returns a
	 * formatted string.
	 * 
	 * @param val
	 * @param variables
	 * @return
	 */
	private String writeMarkDown(char val, ArrayList<String> variables) {
		String markDown = "";
		switch (val) {
		case 'w':
			markDown += "\t* Weight\n";
			for (int i = 0; i < variables.size(); i++) {
				markDown += "\t*" + variables.get(i) + " : " + variables.get(++i) + "\n";
			}
			break;
		case 'f':
			markDown += "\t* Food\n";
			for (int i = 0; i < variables.size(); i++) {
				markDown += "\t\t*" + variables.get(i) + " : " + variables.get(++i) + "\n";
			}
			break;
		case 'e':
			markDown += "\t* Exercise\n";
			for (int i = 0; i < variables.size(); i++) {
				markDown += "\t\t*" + variables.get(i) + " : " + variables.get(++i) + "\n";
			}
			break;
		case 'c':
			markDown += "\t* Summary\n";
			for (int i = 0; i < variables.size(); i++) {
				markDown += "\t\t* Weight: " + variables.get(i) + "\n";
				markDown += "\t\t* Calories Limit: " + variables.get(++i) + "\n";
				markDown += "\t\t* Calories Consumed: " + variables.get(++i) + "\n";
				markDown += "\t\t* Calories Expended: " + variables.get(++i) + "\n";
				markDown += "\t\t* Calories Net: " + variables.get(++i) + "\n";
			}
			break;
		}
		return markDown;
	}

	/*
	 * ArrayList to store a collection of consumable objects
	 */
	private static ArrayList<Consumable> recipeBook = new ArrayList<Consumable>();
	private static ArrayList<Activity> exerciseList = new ArrayList<Activity>();
	Client myClient = new Client();
}
