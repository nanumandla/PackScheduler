package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * Handles Registration information for all users, such as login, logout, and
 * getting currentUser
 * 
 * @author Taylor Liu
 * @author Ashwin Mahesh
 */
public class RegistrationManager {

	/** Instance of RegistrationManager */
	private static RegistrationManager instance;
	/** CourseCatalog object */
	private CourseCatalog courseCatalog;
	/** StudentDirectory object */
	private StudentDirectory studentDirectory;
	/** User object */
	private User registrar;
	/** User object for current user */
	private User currentUser;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Prop file */
	private static final String PROP_FILE = "registrar.properties";
	/** Faculty Directory object */
	private FacultyDirectory facultyDirectory;
	
	/**
	 * Creates Registrar, new student directory, and course catalog
	 */
	private RegistrationManager() {
		createRegistrar();
		studentDirectory = new StudentDirectory();
		courseCatalog = new CourseCatalog();
		facultyDirectory = new FacultyDirectory();
	}
	
	/**
	 * Creates new registrar for the registrationManager
	 * @throws IllegalArgumentException if registrar cannot be created
	 */
	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	/**
	 * Gets hashPW
	 * 
	 * @param pw password
	 * @return hashPW for user
	 * @throws IllegalArgumentException if hash password cannot be created
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Gets instance of RegistrationManager
	 * 
	 * @return instance instance of RegistrationManager
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Gets course catalog
	 * 
	 * @return courseCatalog
	 */
	public CourseCatalog getCourseCatalog() {
		return courseCatalog;
	}

	/**
	 * Gets student directory
	 * 
	 * @return studentDirectory
	 */
	public StudentDirectory getStudentDirectory() {
		return studentDirectory;
	}

	/**
	 * Creates a login for user
	 * 
	 * @param id       id of user
	 * @param password password of user
	 * @return true if login can be made
	 * @throws IllegalArgumentException if user doesn't exist
	 */
	public boolean login(String id, String password) {

		if (currentUser != null) {
			return false;
		}

		String localHashPW = hashPW(password);

		if (registrar.getId().equals(id)) {
			if (registrar.getPassword().equals(localHashPW)) {
				currentUser = registrar;
				return true;
			}

		} else {
			Student s = studentDirectory.getStudentById(id);
			if (s == null) {
				Faculty f = facultyDirectory.getFacultyById(id);
				if(f == null) {
					throw new IllegalArgumentException("User doesn't exist.");
				}
				else if(f.getPassword().equals(localHashPW)) {
					currentUser = f;
					return true;
				}
			}
			else if (s.getPassword().equals(localHashPW)) {
				currentUser = s;
				return true;
			}
		}

		return false;

	}

	/**
	 * Logs the currentUser out
	 */
	public void logout() {
		currentUser = null;
	}

	/**
	 * Returns current user
	 * 
	 * @return currentUser current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Clears course catalog and student directory
	 */
	public void clearData() {
		courseCatalog.newCourseCatalog();
		studentDirectory.newStudentDirectory();
		facultyDirectory.newFacultyDirectory();
	}
	
	/**
	 * Registrar class for User
	 */
	private static class Registrar extends User {
		/**
		 * Create a registrar user.
		 * 
		 * @param firstName first name
		 * @param lastName  last name
		 * @param id        id of Registrar
		 * @param email     email of Registrar
		 * @param hashPW    hashPW of Registrar
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
	/**
	 * Returns true if the logged in student can enroll in the given course.
	 * @param c Course to enroll in
	 * @return true if enrolled
	 * @throws IllegalArgumentException if the current User is not a student
	 */
	public boolean enrollStudentInCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        CourseRoll roll = c.getCourseRoll();
	        
	        if (s.canAdd(c) && roll.canEnroll(s)) {
	            schedule.addCourseToSchedule(c);
	            roll.enroll(s);
	            return true;
	        }
	        
	    } catch (IllegalArgumentException e) {
	        return false;
	    }
	    return false;
	}

	/**
	 * Returns true if the logged in student can drop the given course.
	 * @param c Course to drop
	 * @return true if dropped
	 * @throws IllegalArgumentException if the current user is not a student
	 */
	public boolean dropStudentFromCourse(Course c) {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        c.getCourseRoll().drop(s);
	        return s.getSchedule().removeCourseFromSchedule(c);
	    } catch (IllegalArgumentException e) {
	        return false; 
	    }
	}

	/**
	 * Resets the logged in student's schedule by dropping them
	 * from every course and then resetting the schedule.
	 * @throws IllegalArgumentException if the current user is not a student
	 */
	public void resetSchedule() {
	    if (!(currentUser instanceof Student)) {
	        throw new IllegalArgumentException("Illegal Action");
	    }
	    try {
	        Student s = (Student)currentUser;
	        Schedule schedule = s.getSchedule();
	        String [][] scheduleArray = schedule.getScheduledCourses();
	        for (int i = 0; i < scheduleArray.length; i++) {
	            Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
	            c.getCourseRoll().drop(s);
	        }
	        schedule.resetSchedule();
	    } catch (IllegalArgumentException e) {
	        //do nothing 
	    }
	}
	
	/**
	 * Gets the faculty directory for registration
	 * @return facultyDirectory faculty directory used for the registration
	 */
	public FacultyDirectory getFacultyDirectory() {
		return facultyDirectory;
	}
	
	public void addFacultyToCourse(Course course, Faculty faculty) {
        if (currentUser != null && currentUser instanceof Registrar) {
            FacultySchedule facultySchedule = faculty.getSchedule();
            facultySchedule.addCourseToSchedule(course);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void removeFacultyFromCourse(Course course, Faculty faculty) {
        if (currentUser != null && currentUser instanceof Registrar) {
            FacultySchedule facultySchedule = faculty.getSchedule();
            facultySchedule.removeCourseFromSchedule(course);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void resetFacultySchedule(Faculty faculty) {
        if (currentUser != null && currentUser instanceof Registrar) {
            FacultySchedule facultySchedule = faculty.getSchedule();
            facultySchedule.resetSchedule();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
