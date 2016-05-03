
/**
 * Exercise class
 */

/**
 * @author Aurko Mallick
 *
 */
public class Exercise extends Activity {



	public Exercise(String name, String desc, int calories) {
		super(name, desc, calories);
	}

	public boolean isAExercise(){
		return true;
	}
	public boolean isAWorkoutExercise(){
		return false;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Exercise [name=" + name + ", desc=" + desc + ", calories=" + calories + "]";
	}


}
