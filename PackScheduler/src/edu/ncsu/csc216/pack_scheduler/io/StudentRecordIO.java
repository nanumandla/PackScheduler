package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;
/**
 * Reads Student records from text files.  Writes a set of Student Records to course file.
 * 
 * @author Ashwin Mahesh
 * @author Marco Torres
 * @author Jack Farrell
 */
public class StudentRecordIO {
	/**
     * Reads student records from a file and generates a list of valid Courses. If the file to read cannot be found or 
     * the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Students
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Student> students = new SortedList<Student>();
		while (fileReader.hasNextLine()) {
			try {
				Student student = readStudent(fileReader.nextLine());
				
				students.add(student);
			}
			
			catch(IllegalArgumentException e) {
				//Catch
			}
		}
		fileReader.close();
		return students;
		
	}
	
	/**
     * Reads the information about the courses in the file
     * @param nextLine file name
     * @return new Course object information about courses
     * @throws IllegalArgumentException if file cannot be read 
     */
    private static Student readStudent(String nextLine) {
		Scanner scnr = new Scanner(nextLine);
		scnr.useDelimiter(",");
		
		try {
			String firstName = scnr.next();
			String lastName = scnr.next();
			String id = scnr.next();
			String email = scnr.next();
			String password = scnr.next();
			int maxCredits = scnr.nextInt();
			
			if(scnr.hasNext()) {
				throw new IllegalArgumentException("Invalid");
			}
			return new Student(firstName, lastName, id, email, password, maxCredits);
			
		}
		catch (Exception e) {
			throw new IllegalArgumentException("Invalid");
		}
		finally {	
			scnr.close();
		}
		
	}
    
    /**
     * Writes the given list of Students to 
     * @param fileName file to write schedule of Courses to
     * @param studentDirectory list of Students to write
     * @throws IOException if cannot write to file
     */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
		    fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();
		
	}

}
