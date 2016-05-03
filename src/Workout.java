import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Aurko Mallick
 *
 */
public class Workout extends Activity {

	/**
	 * @param name
	 * @param desc
	 * @param calories
	 */
	public Workout(String name, String desc, int calories, ArrayList<Activity> workouts) {
		super(name, desc, calories);
		this.addWorkout(workouts);		
	}
	public void addWorkout(Activity workout){
		this.workoutCollection.add(workout);
		this.calories += workout.getCalories();
		this.desc += "-" + workout.getName();
	}
	/**
	 * Adds a workout to the regimen.
	 * @param workouts
	 */
	public void addWorkout(ArrayList<Activity> workouts){
		for(int i = 0; i< workouts.size(); i++){
			this.workoutCollection.add(workouts.get(i));
			this.calories =+ workouts.get(i).getCalories();
			if(i==0){
				this.desc += "-";
			}
			this.desc += workouts.get(i).getName();
			if(i != workouts.size()-1){
				this.desc += "-";
			}
		}
	}
	public ArrayList<Activity> getWorkout(){
		return this.workoutCollection;
	}
	public boolean isAExercise(){
		return false;
	}
	public boolean isAWorkoutExercise(){
		return true;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WorkoutExercise [name=" + name + ", desc=" + desc + ", calories=" + calories + "]";
	}
	
	ArrayList<Activity> workoutCollection = new ArrayList<Activity>();
}
