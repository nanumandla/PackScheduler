package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * @author Marco Torres
 * @author Sarah Heckman
 */
public class CourseRecordIO {

    /**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
    public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
    	Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
        SortedList<Course> courses = new SortedList<Course>(); //Create an empty array of Course objects
        while (fileReader.hasNextLine()) { //While we have more lines in the file
            try { //Attempt to do the following
                //Read the line, process it in readCourse, and get the object
                //If trying to construct a Course in readCourse() results in an exception, flow of control will transfer to the catch block, below
                Course course = readCourse(fileReader.nextLine()); 

                //Create a flag to see if the newly created Course is a duplicate of something already in the list  
                boolean duplicate = false;
                //Look at all the courses in our list
                for (int i = 0; i < courses.size(); i++) {
                    //Get the course at index i
                    Course current = courses.get(i);
                    //Check if the name and section are the same
                    if (course.getName().equals(current.getName()) &&
                            course.getSection().equals(current.getSection())) {
                        //It's a duplicate!
                        duplicate = true;
                        break; //We can break out of the loop, no need to continue searching
                    }
                }
                //If the course is NOT a duplicate
                if (!duplicate) {
                    courses.add(course); //Add to the ArrayList!
                } //Otherwise ignore
            } catch (IllegalArgumentException e) {
                //The line is invalid b/c we couldn't create a course, skip it!
            }
        }
        //Close the Scanner b/c we're responsible with our file handles
        fileReader.close();
        //Return the ArrayList with all the courses we read!
        return courses;
    }
    
    /**
     * reads the Course from a given line to check if the statements
     * are valid. The code have a course object which will contain the tokens
     * broken up using a delimiter and then having them returned after each
     * variable is checked
     * @param nextLine reads the next line of a given string
     * @return null for the time being
     * @throws IllegalArgumentException whenever extra "tokens"(days) found when given A,
     * and extra tokens for the start and end time for a course. Managed to create no leakage by
     * adding the finally declaration to ensures the scanner will close
     */
    private static Course readCourse(String nextLine) {
    	Scanner scanner = new Scanner(nextLine);
        scanner.useDelimiter(",");
        
        Course updatedCourse = null;

        //Course updatedCourse;

        try {
            String name = scanner.next();
            String title = scanner.next();
            String section = scanner.next();
            int credits = scanner.nextInt();
            String instructor = scanner.next();
            //instructor = instructor.isEmpty() ? null: instructor;
            int enrollmentCap = scanner.nextInt();
            String meetingDays = scanner.next();
           // Course updatedCourse;


            if ("A".equals(meetingDays)) {
                if (scanner.hasNext()) {
                    throw new IllegalArgumentException("Extra tokens found after meetingDays 'A'");
                } else {
                    updatedCourse = new Course(name, title, section, credits, null, enrollmentCap, meetingDays);
                	System.out.print(updatedCourse);
                    if (RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructor) != null) {
                    	RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructor).getSchedule().addCourseToSchedule(updatedCourse);
    				}
    				//return updatedCourse;
                }
            } else {
                int startTime = scanner.nextInt();
                int endTime = scanner.nextInt();

                if (scanner.hasNext()) {
                    throw new IllegalArgumentException("Extra tokens found after startTime and endTime");
                } else {
                    updatedCourse = new Course(name, title, section, credits, null, enrollmentCap, meetingDays, startTime, endTime);
                    if (RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructor) != null) {
                    	RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructor).getSchedule().addCourseToSchedule(updatedCourse);
                    	//updatedCourse.setInstructorId(RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(instructor).getId());
    				}
    				//return updatedCourse;
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Missing tokens in the course record");
        }

        finally {
            scanner.close();
        }
        
        return updatedCourse;
	}

	/**
     * Writes the given list of Courses into an array
     * @param fileName file to write schedule of Courses to
     * @param catalog list of Courses to write
     * @throws IOException if cannot write to file
     */
    public static void writeCourseRecords(String fileName, SortedList<Course> catalog) throws IOException {
    	PrintStream fileWriter = new PrintStream(new File(fileName));

    	for (int i = 0; i < catalog.size(); i++) {
    	    fileWriter.println(catalog.get(i).toString());
    	}

    	fileWriter.close();
        
    }

}