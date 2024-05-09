package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Stores the information of students pertaining to their college credentials
 * 
 * @author Ashwin Mahesh
 * @author Marco Torres-Blanco
 * @author Jack Farrell
 */
public class Student extends User implements Comparable<Student> {
	
	
	/** student's max course credits*/
	private int maxCredits;
	
	/** max credit for students*/
	public static final int MAX_CREDITS = 18;
	/** new schedule*/ 
	private Schedule schedule = new Schedule();
	
	/**
	 * Creates the student objects with values of all fields
	 * 
	 * @param firstName string of the students first name.
	 * @param lastName string of the students last name.
	 * @param id the Unity ID of the student.
	 * @param email Email of the student.
	 * @param hashPW password of the student.
	 * @param maxCredits the course credit of a student.
	 * password was hashPW
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
//		setFirstName(firstName);
//		setLastName(lastName);
//		setId(id);
//		setEmail(email);
//		setPassword(hashPW);
		setMaxCredits(maxCredits);
		
	}
	
	/**
	 * Creates the student objects with values for all fields except maxCredits (total student course credits)
	 * 
	 * @param firstName string of the students first name.
	 * @param lastName string of the students last name.
	 * @param id the Unity ID of the student.
	 * @param email Email of the student.
	 * @param hashPW password of the student.
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, 18);
	}
	
	
	/**
	 * returns the max credits of a student
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * takes the given credits of a student and compares its validity
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException whenever the credits are below three
	 * or above 18
	 */
	public void setMaxCredits(int maxCredits) {
		
		if (maxCredits < 3 || maxCredits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid max credits");
		}
		this.maxCredits = maxCredits;
	}


	

	/**
	 * Creates String for Student information, which is first name,
	 * last name, id, email, password, max credits
	 * @return string containing student information
	 */
	@Override
	public String toString() {
		return getFirstName() + "," + getLastName() + "," + getId() + "," + getEmail()
				+ "," + getPassword() + "," + getMaxCredits();
	}
	
	/**
	 * Creates hash code for student object
	 * @return result hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}
	
	/**
	 * Checks if 2 students are equal to each other
	 * @param obj student object
	 * @return true if equal, else false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return maxCredits == other.maxCredits;
	}
	
	/**
	 * Compares 2 students with one another
	 * @return 0 if equal, 1 if id is greater than 1, and -1 if not equal
	 * @throws NullPointerException if student is null
	 */
	@Override
	public int compareTo(Student s) {
		if(s == null) {
			throw new NullPointerException("Student equals null");
		}
		if (this.getLastName().compareTo(s.getLastName()) == 0) {
			if (this.getFirstName().compareTo(s.getFirstName()) == 0) {
				if (this.getId().compareTo(s.getId()) == 0) {
					return 0;
				}
				else if (this.getId().compareTo(s.getId()) > 0) {
					return 1;
					}
				else
					return -1;
				}
			else if (this.getFirstName().compareTo(s.getFirstName()) > 0) {
				return 1;				
			}
			else 
				return -1;
		}
		
		else if (this.getLastName().compareTo(s.getLastName()) > 0) {
			return 1;
		}
		else {
			return -1;
		}
			
	}
	
	/**
	 * Determines whether a course can be added
	 * @param course the course to be added
	 * @return true if course can be added, else it is false
	 */
	public boolean canAdd(Course course) {
		boolean isAdd = true;
		
		if(!schedule.canAdd(course)) {
			isAdd = false;
		}
		
		else {
			isAdd = schedule.getScheduleCredits() + course.getCredits() <= getMaxCredits();
		}
		
		return isAdd;
	}
	
	/**
	 * Gets the student's schedule
	 * @return schedule student schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}


}
