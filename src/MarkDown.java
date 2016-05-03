

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
import java.util.Scanner;

public final class MarkDown implements LogManager {
	/**
	 * Default Constructor uses log.csv
	 */
	public MarkDown() {
		mdFile = "log.md";
	}
	/**
	 * Parameterized Constructor uses given filename
	 */
	public MarkDown(String filename) {
		mdFile = filename;
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
			reader = new BufferedReader(new FileReader(mdFile));
			while ((line = reader.readLine()) != null) {
				// Use Coma as separator
				String[] val = line.split(newLine);
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
	public boolean write(ArrayList<String> value) {
		// Import the system's time and date
		String year = Calendar.getInstance().get(Calendar.YEAR)+"";
		String month = Calendar.getInstance().get(Calendar.MONTH)+"";
		String day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"";
		//SimpleDateFormat date = new SimpleDateFormat ("yyyy,MM,dd");

		try {
			writer = new BufferedWriter(new FileWriter(mdFile, true));
			writer.write(this.newLine); // Being a new line
			writer.write("* Date:");
			writer.write(month+"/");
			writer.write(day+"/");
			writer.write(year+"\n");
			for (int i = 0; i < value.size(); i++) {
				writer.write(value.get(i));
			}
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
	
				try {
					reader = new BufferedReader(new FileReader(mdFile));
				
				String line;
				String input = "";
				while ((line = reader.readLine()) != null) input += line + '\n';
				input = input.replace(date, modifyText);
				writer = new BufferedWriter(new FileWriter(mdFile, true));
				writer.write(input);
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
				try {
					reader.close();
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return true;

		} // end modify

	/**
	 * Remove a line based on date.
	 * Read the file into memory then remove the line needed.
	 * Write the file and rename it to log.md
	 */
	@Override
	public boolean remove(String date) {
		File fileLog = new File(mdFile);
		try {
			reader = new BufferedReader(new FileReader(fileLog));

			if (!fileLog.isFile()) {
				System.out.println("file does not exist");
				return false;
			}
			
//			final Scanner scanner = new Scanner(mdFile);
//			while (scanner.hasNextLine()) {
//			   final String firstDate = scanner.nextLine();
//			   StringBuffer f = new StringBuffer();
//			  //f.
//			  //f.append(firstDate);
//			   if(firstDate.contains("Date")) { 
//				   final String secondDate = scanner.nextLine();
//				   
//			   }
//			}
	
			// create temp file
			File temporaryFile = new File(fileLog.getAbsolutePath() + ".tmp");
			pw = new PrintWriter(new FileWriter(temporaryFile));
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
	private String mdFile;
	private PrintWriter pw;
	/*
	 * Line seperator value across all systems.
	 */
	private static String newLine = System.getProperty("line.separator");

	/*
	 * Main Method for testing logging.
	 */
//	public static void main(String[] args) {
//		MarkDown myLog = new MarkDown();
//		ArrayList<String> testArr = new ArrayList<String>();
//		testArr.add("w");
//		//SimpleDateFormat date = new SimpleDateFormat ("yyyy,MM,dd,hh,mm");
//		if(myLog.write(testArr)){
//			System.out.println(myLog.remove("2015,10,30"));
//		}
//			
//		
//		// System.out.println(myLog.read());
//	}

}
