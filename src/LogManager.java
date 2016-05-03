

import java.util.ArrayList;

/**
 * Log Manager Interface to handle storing of 
 * information on an external file/system.
 * @author Aurko Mallick
 *
 */
public interface LogManager {

	/*
	 * Read from file
	 */
	public String read();
	
	/*
	 * Write to file
	 */
	public boolean write(ArrayList<String> recipe);
	
	/*
	 * Modify value inside file
	 */
	public boolean modify(String date, String modifyText);
	
	/*
	 * remove item from file
	 */
	public boolean remove(String date);
}
