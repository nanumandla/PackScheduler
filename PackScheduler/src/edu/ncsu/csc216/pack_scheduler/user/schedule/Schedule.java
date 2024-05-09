package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * This program handles all information relating to the schedule of the courses 
 * as it can add a course, remove course, or even reset course
 * @author Ashwin Mahesh
 * @author Taylor Liu
 */

public class Schedule {
	/** Custom ArrayList of Courses */
	private ArrayList<Course> schedule;
	/** The schedule's title */
	private String title;
	
	/**
	 * Constructs schedule and title
	 */
	public Schedule(){
		schedule = new ArrayList<Course>();
		title = "My Schedule";
	}
	
	/**
	 * Returns the title of the Course
	 * @return title title of Course
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Adds course to the schedule
	 * @param course course that will be added
	 * @return true if course added else it is false and is not added
	 * @throws NullPointerException if course null
	 * @throws IllegalArgumentException if already enrolled or cannot enroll due to a conflict
	 */
	public boolean addCourseToSchedule(Course course) {
		if(course == null) {
			throw new NullPointerException("Null");
		}
		
		try {
			for(int i = 0; i < schedule.size(); i++) {
				if(course.isDuplicate(schedule.get(i))) {
					throw new IllegalArgumentException("You are already enrolled in " + course.getName());
				}
				course.checkConflict(schedule.get(i));
			}
			schedule.add(course);
		}
		
		catch(ConflictException e) {
			throw new IllegalArgumentException("The course cannot be added due to a conflict.");
		}
		
		return true;
	}
	
	/**
	 * Removes course from schedule
	 * @param course course that will be removed
	 * @return true if course is added else it is false
	 */
	public boolean removeCourseFromSchedule(Course course) {
		for(int i = 0; i < schedule.size(); i++) {
			if(course == schedule.get(i)) {
				schedule.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Resets the schedule into an empty schedule ArrayList and default title
	 */
	public void resetSchedule() {
		title = "My Schedule";
		schedule = new ArrayList<Course>();
	}
	
	/**
	 * Gets the courses and creates a 2D array of the information, which
	 * includes section, title, name, and meeting times
	 * @return scheduleCourses 2D array of the course information
	 */
	public String[][] getScheduledCourses() {
		String[][] scheduleCourses = new String[schedule.size()][5];
		
		for(int i = 0; i < schedule.size(); i++) {
			scheduleCourses[i] = schedule.get(i).getShortDisplayArray();
		}
		
		return scheduleCourses;
	}
	
	/**
	 * Sets the title of the Courses
	 * @param title title of Course
	 * @throws IllegalArgumentException if title null
	 */
	public void setTitle(String title) {
		if(title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		
		this.title = title;
	}
	
	/**
	 * Checks if course can be added
	 * @param course the student's course
	 * @return true if course can be added else false is returned 
	 */
	public boolean canAdd(Course course) {
		boolean isAdd = true;
		
		if(course == null) {
			isAdd = false;
		}
		
		for(int i = 0; i < schedule.size(); i++) {
			if(schedule.get(i).isDuplicate(course)) {
				isAdd = false;
			}
			
			try {
				schedule.get(i).checkConflict(course);
			}
			
			catch(ConflictException e) {
				isAdd = false;
			}
		}
		
		return isAdd;
	}
	
	/**
	 * Gets the total schedule credits for a student
	 * @return totalCredits the total schedule credits
	 */
	public int getScheduleCredits() {
		int totalCredits = 0;
		for(int i = 0; i < schedule.size(); i++) {
			totalCredits += schedule.get(i).getCredits();
		}
		return totalCredits;
	}

}
