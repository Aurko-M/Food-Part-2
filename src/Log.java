

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

public final class Log implements LogManager {
	/**
	 * Default Constructor uses log.csv
	 */
	public Log() {
		csvFile = "log.csv";
	}
	/**
	 * Parameterized Constructor uses given filename
	 */
	public Log(String filename) {
		csvFile = filename;
	}
	
	/**
	 * Function reads the log file and returns the entire log file's content as
	 * a string.
	 */
	@Override
	public String read() {
		String returnString = "";
		String line = "";
		try {
			reader = new BufferedReader(new FileReader(csvFile));
			while ((line = reader.readLine()) != null) {
				// Use Coma as separator
				String[] val = line.split(csvSplitBy);
				// Loop to read each value in line.
				for (int i = 0; i < val.length; i++) {
					returnString += val[i] + " "; //
				} // end line loop
				returnString += newLine;
			} // end While
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} // end finally
		return returnString;
	}// End read()

	/**
	 * Write function will take an array list and write contents into a csv file
	 * 
	 * @param recipe
	 * @return
	 */
	@Override
	public boolean write(ArrayList<String> recipe) {
		// Import the system's time and date
		String year = Calendar.getInstance().get(Calendar.YEAR) + csvSplitBy;
		String month = Calendar.getInstance().get(Calendar.MONTH) + csvSplitBy;
		String day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + csvSplitBy;
		//SimpleDateFormat date = new SimpleDateFormat ("yyyy,MM,dd");

		try {
			writer = new BufferedWriter(new FileWriter(csvFile, true));
			for (int i = 0; i < recipe.size(); i++) {
				if(recipe.size()-1 != i){
					writer.write(recipe.get(i) + csvSplitBy);
				}else{
					writer.write(recipe.get(i));
				}
			}
			writer.write(this.newLine); // Being a new line
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
				e.printStackTrace(); // Nothing can be done at this point
			}
		} // end finally

		return false;
	}// end write()

	@Override
	public boolean modify(String date, String modifyText) {
		File fileLog = new File(csvFile);
		try {
			reader = new BufferedReader(new FileReader(fileLog));
			if (!fileLog.isFile()) {
				System.out.println("file does not exist");
				return false;
			}

			// create temp file
			File temporaryFile = new File(fileLog.getAbsolutePath() + ".tmp");
			PrintWriter pw = new PrintWriter(new FileWriter(temporaryFile));
			String line = null;
			//replace the line
			while ((line = reader.readLine()) != null) {
				if (line.trim().equals(date)) {
					pw.write(modifyText);
					pw.flush();
				}
			}
			pw.close();
			reader.close();

			if (!fileLog.delete()) {
				System.out.println("unable to delete file");
				return false;
			}
			// Rename to the original file
			if (!temporaryFile.renameTo(fileLog))
				System.out.println("unable to rename file");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return false;
	} // end modify
	
	public boolean deleteLogContents(String log){
		File fileLog = new File(log);
		try {
			reader = new BufferedReader(new FileReader(fileLog));
			
			if (!fileLog.isFile()) {
				System.out.println("file does not exist");
				return false;
			}
			PrintWriter pw = new PrintWriter(new FileWriter(log));
			pw.print("");
			pw.close();
			return true;
			
		}catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * Remove a line based on date.
	 */
	@Override
	public boolean remove(String date) {
		File fileLog = new File(csvFile);
		try {
			reader = new BufferedReader(new FileReader(fileLog));

			if (!fileLog.isFile()) {
				System.out.println("file does not exist");
				return false;
			}
			// create temp file
			File temporaryFile = new File(fileLog.getAbsolutePath() + ".tmp");
			PrintWriter pw = new PrintWriter(new FileWriter(temporaryFile));
			String line = null;

			while ((line = reader.readLine()) != null) {
				if (line.trim().equals(date)) {
					pw.flush();
				}
			}
			pw.close();
			reader.close();

			if (!fileLog.delete()) {
				System.out.println("unable to delete file");
				return false;
			}
			// Rename to the original file
			if (!temporaryFile.renameTo(fileLog))
				System.out.println("unable to rename file");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException ex) {
			ex.printStackTrace();
			return false;
		}
		return true;

	}

	/*
	 * Instance Variables
	 */
	private List<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	private BufferedWriter writer = null;
	private BufferedReader reader = null;
	private String csvSplitBy = ",";
	private String csvFile;
	/*
	 * Line seperator value across all systems.
	 */
	private static String newLine = System.getProperty("line.separator");
}
