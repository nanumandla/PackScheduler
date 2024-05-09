/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Handles the Courses in the WolfScheduler program
 * Handles information about the course, such as name, section, credits, instructorId,
 * and can set and return those values
 * Also checks if Course is duplicate
 * @author Ashwin Mahesh
 */
public class Course extends Activity implements Comparable<Course> {
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Minimum length for course */
	public static final int MIN_NAME_LENGTH = 5;
	/** Maximum length for course */
	public static final int MAX_NAME_LENGTH = 8;
	/** Minimum number of letters for course */
	public static final int MIN_LETTER_COUNT = 1;
	/** Maximum number of letters for course */
	public static final int MAX_LETTER_COUNT = 4;
	/** Number of digits in a course */
	public static final int DIGIT_COUNT = 3;
	/** Required length for a course section */
	public static final int SECTION_LENGTH = 3;
	/** Minimum number of credits for a course */
	public static final int MIN_CREDITS = 1;
	/** Maximum number of credits for a course */
	public static final int MAX_CREDITS = 5;
	/** CourseNameValidator class object */
	private CourseNameValidator validator;
	/** course of roll */
	private CourseRoll roll;
	
	/**
	 * Returns the Course's name 
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the Course's name
	 * @param name the name to set
	 * @throws IllegalArgumentException if Course name is invalid
	 */
	 private void setName(String name) {
	        try {
	        	 if (name == null) {
	        	        throw new IllegalArgumentException("Invalid course name.");
	        	    }
	        	    if (name.isEmpty()) {
	        	        throw new IllegalArgumentException("Invalid course name: " + name);
	        	    }
	            if (!validator.isValid(name)) {
	                throw new IllegalArgumentException("Invalid course name: " + name);
	            }
	            this.name = name;
	        } catch (InvalidTransitionException e) {
	            throw new IllegalArgumentException("Invalid course name: " + name, e);
	        }
	 }
	/**
	 * Returns the Course's section
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * Sets the Course's section
	 * @param section the section to set
	 * @throws IllegalArgumentException if Course section is invalid
	 */
	public void setSection(String section) {
		if(section == null || section.length() != SECTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		
		for(int i = 0; i < section.length(); i++) {
			char ch = section.charAt(i);
			if(!Character.isDigit(ch)) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		this.section = section;
	}
	/**
	 * Returns the Course's credits
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	/**
	 * Sets the Course's credits 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if Course credits are invalid
	 */
	public void setCredits(int credits) {
		if(credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		this.credits = credits;
	}
	/**
	 * Returns the Course's Instructor Id
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}
	/**
	 * Sets the Course's Instructor Id
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if instructor id is null or empty
	 */
	public void setInstructorId(String instructorId) {
		if("".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}
	
	/**
	 * Sets the Course's meeting days and time 
	 * @param meetingDays meeting days of Course
	 * @param startTime start time of Course
	 * @param endTime end time of Course
	 * @throws IllegalArgumentException if instructor id is null or empty
	 * @throws IllegalArgumentException if start time or end time does not equal 0
	 * @throws IllegalArgumentException if any of the days meets more than once that week
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if(meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		if("A".equals(meetingDays)) {
			if(startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}
		else {
			int meetMon = 0;
			int meetTues = 0;
			int meetWed = 0;
			int meetThurs = 0;
			int meetFri = 0;
			
			for(int i = 0; i < meetingDays.length(); i++) {
				char ch = meetingDays.charAt(i);
				if(ch == 'M') {
					meetMon++;
				}
				else if(ch == 'T') {
					meetTues++;
				}
				else if(ch == 'W') {
					meetWed++;
				}
				else if(ch == 'H') {
					meetThurs++;
				}
				else if(ch == 'F') {
					meetFri++;
				}
				else {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			
			if(meetMon > 1 || meetTues > 1 || meetWed > 1 || meetThurs > 1 || meetFri > 1) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
		}
		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Constructs a Course object with values for all fields 
	 * @param name name of Course 
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours of Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime start time for Course 
	 * @param endTime end time for Course
	 * @param enrollmentCap enrollment cap for Course
	 * @throws IllegalArgumentException if course name is invalid
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays,
            int startTime, int endTime) {
        super(title, meetingDays, startTime, endTime);
        if (name == null) {
            throw new IllegalArgumentException("Invalid course name.");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Invalid course name.");
        }
        validator = new CourseNameValidator();
        try {
            if (!validator.isValid(name)) {
                throw new IllegalArgumentException("Invalid course name.");
            }
        } catch (InvalidTransitionException e) {
            throw new IllegalArgumentException("Invalid course name.", e);
        }
        validator = new CourseNameValidator();
        setName(name);
        setSection(section);
        setCredits(credits);
        setInstructorId(instructorId);
        setEnrollmentCap(enrollmentCap);
        this.roll = new CourseRoll(this, enrollmentCap);
    }
	/**
	 * Creates a course with the given name, title, section, credits, instructorId, and meetingDays for
	 * courses that are arranged
	 * @param name name of Course 
	 * @param title title of Course 
	 * @param section section of Course 
	 * @param credits credit hours for Course 
	 * @param instructorId instructor's unity id 
	 * @param meetingDays meeting days for Course as series of chars 
	 * @param enrollmentCap enrollmentCap for Course
	 */
	
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmentCap, String meetingDays) {
	    this(name, title, section, credits, instructorId, enrollmentCap, meetingDays, 0, 0);
	}
	
	/**
	 * sets the enrollment cap for the course
	 * @param enrollmentCap is the enrollment cap we wish to set for the course
	 * @throws IllegalArgumentException if the enrollment capacity < 10 or > 250
	 */
	 private void setEnrollmentCap(int enrollmentCap) {
	        if (enrollmentCap < 10 || enrollmentCap > 250) {
	            throw new IllegalArgumentException("Enrollment capacity must be between 10 and 250.");
	        }
	    }

	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
//	@Override
//	public String toString() {
//	    if ("A".equals(meetingDays)) {
//	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
//	    }
//	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime(); 
//	}
	

    @Override
    public String toString() {
        if ("A".equals(getMeetingDays())) {
            return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," +
                    roll.getEnrollmentCap() + "," + getMeetingDays();
        }
        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," +
                roll.getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
    }
	/**
	 * Creates a hash code value for an object
	 * @return result hash code value for an object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}
	
	/**
	 * Determines whether this object is equal to another object
	 * @param obj object for comparison
	 * @return true if this object is equal to other object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}
	
	/**
	 * Creates short display array of Course, which includes name, section, and title
	 * @return shortArray short display array of Course
	 */
//	@Override
//	public String[] getShortDisplayArray() {
//		String[] shortArray = new String[4];
//		shortArray[0] = name;
//		shortArray[1] = section;
//		shortArray[2] = title;
//		shortArray[3] = getMeetingString();
//		return shortArray;
//	}
	@Override
	    public String[] getShortDisplayArray() {
	        String[] shortDisplayArray = new String[5];
	        shortDisplayArray[0] = name;
	        shortDisplayArray[1] = section;
	        shortDisplayArray[2] = getTitle();
	        shortDisplayArray[3] = getMeetingString();
	        shortDisplayArray[4] = String.valueOf(roll.getOpenSeats());
	        return shortDisplayArray;
	    }

	
	/**
	 * Creates long display array of Course, which includes name, section, title, credits, instructorId
	 * meetingString
	 * @return longArray long display array of Course 
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longArray = new String[7];
		longArray[0] = name;
		longArray[1] = section;
		longArray[2] = title;
		longArray[3] = Integer.toString(credits);
		longArray[4] = instructorId;
		longArray[5] = getMeetingString();
		longArray[6] = "";
		return longArray;
	}
	
	/**
	 * Checks if parameter is instance of Course and checks if duplicate is found
	 * @param activity instance of Course
	 * @return true if duplicate is found
	 */
	public boolean isDuplicate(Activity activity) {
	    if (activity instanceof Course) {
	        Course otherCourse = (Course) activity;
	        return this.getName().equals(otherCourse.getName());
	    }
	    return false;
	}
	
	/**
	 * Gets the Course roll
	 * @return roll the course roll
	 */
	public CourseRoll getCourseRoll() {
	        return roll;
	    }
	
	/**
	 * Compares 2 courses to see if they are equal
	 * @param c Course
	 * @return 0 if course is equal, 1 if the section is greater than 0, and -1 if they are not equal
	 * @throws NullPointerException if student is null
	 */
	@Override
	public int compareTo(Course c) {
		if(c == null) {
			throw new NullPointerException("Student equals null");
		}
		if (this.getName().compareTo(c.getName()) == 0) {
			if (this.getSection().compareTo(c.getSection()) == 0) {
				return 0;
			}
			else if (this.getSection().compareTo(c.getSection()) > 0) {
				return 1;				
			}
			else {
				return -1;
			}
		}
		
		else if (this.getName().compareTo(c.getName()) > 0) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	
		

}
