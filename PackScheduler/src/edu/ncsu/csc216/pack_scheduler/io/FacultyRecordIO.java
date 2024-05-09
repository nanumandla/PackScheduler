package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * method that reads in data from afile and loads it into the faculty record class.
 * @author David Giang
 * @author Ashwin Mahesh
 * @author Nirvan Reddy Anumandla
 */
public class FacultyRecordIO {
	
	/**
	 * reads in the faculty records
	 * @param file is the file we are reading from
	 * @return a linkedlist with all faculty read in
	 * @throws FileNotFoundException if the file could not be found or read in
	 */
	public static LinkedList<Faculty> readFacultyRecords(String file) throws FileNotFoundException{
		Scanner fileReader = new Scanner(new FileInputStream(file));
		
		LinkedList<Faculty> faculties = new LinkedList<Faculty>();
		
		while(fileReader.hasNextLine()) {
			try {
				Faculty faculty = processFaculty(fileReader.nextLine());
				
				faculties.add(faculty);
			}
			
			catch(IllegalArgumentException e) {
				//skip the line
			}
		}
		
		fileReader.close();
		return faculties;
	}
	
	/**
	 * processes each line of faculty data
	 * @param nextLine is the line with faculty data
	 * @return the faculty object based on the info given
	 * @throws IllegalArgumentException if too many pieces fo info on one line
	 */
	private static Faculty processFaculty(String nextLine) {
		Scanner scnr = new Scanner(nextLine);
		scnr.useDelimiter(",");
		
		try {
			String first = scnr.next();
			String last = scnr.next();
			String id = scnr.next();
			String email = scnr.next();
			String password = scnr.next();
			int maxCredits = scnr.nextInt();
			
			if(scnr.hasNext()) {
				throw new IllegalArgumentException("Invalid");
			}
			
			return new Faculty(first, last, id, email, password, maxCredits);
		}
		
		catch(Exception e) {
			throw new IllegalArgumentException("Invalid");
		}
		
		finally {
			scnr.close();
		}
	}
	
	/**
	 * writes the faculty records to a text file
	 * @param fileName the file name we wish to write to
	 * @param faculty is the linkedlist holding all the faculty objects
	 * @throws IOException if the file could not be written to
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> faculty) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
		
		for(int i = 0; i < faculty.size(); i++) {
			fileWriter.println(faculty.get(i).toString());
		}
		
		fileWriter.close();
	}
	
	
}
