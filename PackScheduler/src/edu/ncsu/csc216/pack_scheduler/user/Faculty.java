/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;

/**
 * this class stores the data for each faculty object, and checks to make sure
 * all facilty parameters are legal
 * 
 * @author David Giang
 * @author Ashwin Mahesh
 * @author Nirvan Reddy Anumandla
 */
public class Faculty extends User {

	/** faculty's max courses they can teach */
	private int maxCourses;
	/** max courses for faculty */
	public static final int MAX_COURSES = 3;
	/** min courses for faculty */
	public static final int MIN_COURSES = 1;
	/** Faculty's schedule */
	private FacultySchedule schedule;

	/**
	 * constructor for the faculty object. stores all information into the faculty
	 * object
	 * 
	 * @param firstName  is the first name of the faculty member
	 * @param lastName   is the last name of the faculty member
	 * @param id         is the id of the faculty member
	 * @param email      is the email of the faculty member
	 * @param hashPW     is the hashed password of the faculty member
	 * @param maxCourses is the max courses the faculty member will be teaching
	 */
	public Faculty(String firstName, String lastName, String id, String email, String hashPW, int maxCourses) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCourses(maxCourses);
		this.schedule = new FacultySchedule(id);
	}

	/**
	 * method that sets the max amount of courses a faculty member can teach
	 * 
	 * @param courses is the courses we wish to set for the max courses a faculty
	 *                member can teach
	 * @throws IllegalArgumentException if invalid max courses
	 */
	public void setMaxCourses(int courses) {
		if (courses < 1 || courses > MAX_COURSES) {
			throw new IllegalArgumentException("Invalid max courses");
		}
		maxCourses = courses;
	}

	/**
	 * method that returns the max amount of courses a faculty member can teach
	 * 
	 * @return the number of courses a faculty members can teach
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * generates the hashcode for the faculty object
	 * 
	 * @return the hashcode of the object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/**
	 * checks if two faculty objects are equal
	 * 
	 * @param obj is the object we are comparing to the current faculty object
	 * @return true if equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		return maxCourses == other.maxCourses;
	}

	/**
	 * generates the string representation of the faculty object's information
	 * 
	 * @return a String that shows the information of the faculty object, in this
	 *         case maxCourses
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail() + "," + getPassword() + ","
				+ getMaxCourses();
	}

	/**
	 * Returns the FacultySchedule.
	 * 
	 * @return the FacultySchedule of the faculty
	 */
	public FacultySchedule getSchedule() {
		return schedule;
	}

	/**
	 * Checks if the faculty member is overloaded.
	 * 
	 * @return true if the number of scheduled courses is greater than the Faculty's
	 *         maxCourses
	 */
	public boolean isOverloaded() {
		boolean OverLoaded = false;
		if (schedule.getNumScheduledCourses() > maxCourses) {
			OverLoaded = true;
		}
		return OverLoaded;
	}

}
