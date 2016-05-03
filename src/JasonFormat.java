

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 
 * JasonFormat class to show the ease
 *
 */
public final class JasonFormat implements LogManager {
	/**
	 * Default Constructor uses log.jason
	 */
	public JasonFormat() {
		jasonFile = "log.jason";
	}
	/**
	 * Parameterized Constructor uses given filename
	 */
	public JasonFormat(String filename) {
		jasonFile = filename;
	}
	
	/**
	 * Function reads the log file and returns the entire log file's content as
	 * a string.
	 */
	@Override
	public String read() {
		
		return "";
	}// End read()

	/**
	 * Write function will take an array list and write contents into a jason file
	 * 
	 * @param recipe
	 * @return
	 */
	@Override
	public boolean write(ArrayList<String> recipe) {
		return false;
		
	}// end write()

	@Override
	public boolean modify(String date, String modifyText) {
		
		return false;
	} // end modify

	/**
	 * Remove a line based on date.
	 */
	@Override
	public boolean remove(String date) {
		return false;
		
	}

	/*
	 * Instance Variables
	 */
	
	private String jasonFile;



}
