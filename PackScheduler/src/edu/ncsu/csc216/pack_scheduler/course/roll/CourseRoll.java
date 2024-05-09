package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;

/**
 * Represents the Course roll in the PackScheduler application This class can
 * set enrollment cap, drop or enroll in a course, and determines whether a
 * student can enroll in a course
 * 
 * @author Ashwin Mahesh
 * @author Taylor Liu
 */
public class CourseRoll {
	/** Custom LinkedAbstract for students in course */
    private LinkedAbstractList<Student> roll;
	
	/** Waitlist for students */
    private LinkedQueue<Student> waitlist;

	/** Enrollment cap for the course */
	private int enrollmentCap;

	/** Course associated with this CourseRoll */
	private Course course;

	/** Smallest class size */
	public static final int MIN_ENROLLMENT = 10;

	/** Largest class size */
	public static final int MAX_ENROLLMENT = 250;

	/**
	 * Constructs CourseRoll object
	 * 
	 * @param enrollmentCap enrollment cap for the course
	 * @param course represents the Course object
	 */
	public CourseRoll(Course course, int enrollmentCap) {
		if (course == null) {
			throw new IllegalArgumentException("Course cannot be null");
		}
		this.course = course;
		roll = new LinkedAbstractList<>(enrollmentCap);
		waitlist = new LinkedQueue<Student>(10);
		setEnrollmentCap(enrollmentCap);
	}

	/**
	 * Gets the available number of seats in Course by subtracting the enrollment
	 * cap by number of students in course
	 * 
	 * @return the number of available seats in Course
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}

	/**
	 * Gets the enrollment cap
	 * 
	 * @return enrollmentCap the enrollmentCap for the course
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}

	/**
	 * Sets the enrollment cap for the course
	 * 
	 * @param enrollmentCap the enrollment cap for the course
	 * @throws IllegalArgumentException if the enrollment cap is too large or too big
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT) {
			throw new IllegalArgumentException();
		}

		roll.setCapacity(enrollmentCap);
		this.enrollmentCap = enrollmentCap;
	}

	/**
	 * Enrolls a student in a course
	 * 
	 * @param student the student to be enrolled in the course
	 * @throws IllegalArgumentException if the waitlist is full
	 */
	public void enroll(Student student) {
		if (student == null) {
			throw new IllegalArgumentException();
		}
		if (roll.size() < enrollmentCap) {
			roll.add(student);
		} else if (roll.size() == enrollmentCap) {
			waitlist.enqueue(student);
		} else {
			throw new IllegalArgumentException("Waitlist is full.");
		}
	}

	/**
	 * Drops a student in a course
	 * 
	 * @param student the student to be dropped in the course
	 * @throws IllegalArgumentException if the index goes out of bounds
	 */
	public void drop(Student student) {
		//taken from David Giang's lab8
		if (student == null) {
			throw new IllegalArgumentException();
		}
		try {
			for (int i = 0; i < roll.size(); i++) { 
				if (roll.get(i).getId().equals(student.getId())) {
					roll.remove(i);
					//student.getSchedule().removeCourseFromSchedule(course);
					if(waitlist.size() > 0) {
						Student s = waitlist.dequeue();
						//enroll(s);
						roll.add(roll.size(), s);
						s.getSchedule().addCourseToSchedule(course);
					}
				}
				else if(waitlist.contains(student)) {
					LinkedQueue<Student> waitlist2 = new LinkedQueue<Student>(10);
					for(int j = 0; j < waitlist.size(); j++){
						Student s = waitlist.dequeue();
						if(!s.getId().equals(student.getId())) {
							waitlist2.enqueue(s);
						}
						j--;
					}
					waitlist = waitlist2;
				}
			}
		}
		catch (IndexOutOfBoundsException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Determines whether a student can be enrolled in a course
	 * 
	 * @param student the student in a course
	 * @return true if student can be enrolled, else it is false
	 */
	public boolean canEnroll(Student student) {
		boolean canEnroll = true;
   
        if (waitlist.contains(student) || roll.contains(student)) {
        	canEnroll = false;
        }

        if (roll.size() > enrollmentCap || waitlist.size() > 10) {
        	canEnroll = false;
        }
		return canEnroll;
	}
	
	/**
     * Returns the number of students on the waitlist
     * 
     * @return the number of students on the waitlist
     */
    public int getNumberOnWaitlist() {
        return waitlist.size();
    }

}
