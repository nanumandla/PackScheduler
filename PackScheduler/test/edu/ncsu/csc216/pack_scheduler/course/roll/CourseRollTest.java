package edu.ncsu.csc216.pack_scheduler.course.roll;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Test CourseRoll class
 * @author Ashwin Mahesh
 * @author Taylor Liu
 */
public class CourseRollTest {
	
	/** Test Student's first name. */
	private String firstName = "first";
	/** Test Student's last name */
	private String lastName = "last";
	/** Test Student's id */
	private String id = "flast";
	/** Test Student's email */
	private String email = "first_last@ncsu.edu";
	/** Test Student's hashed password */
	private String hashPW = "password";
	
	
	
	/**
	 * Resets course_records.txt for use in other tests.
	 */
	@Before
	public void setUp() throws Exception {
		//Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	
	/**
	 * Tests CourseRoll.CourseRoll
	 */
	@Test
	public void testCourseRollConstructor() {
		Course course1 = new Course("CSC216", "Software Development Fundamentals", "001", 4, "sesmith5", 10, "MW",
				1330, 1445);
		CourseRoll roll = course1.getCourseRoll();
		
		assertEquals(10, roll.getEnrollmentCap());
		assertEquals(10, roll.getOpenSeats());
		assertThrows(IllegalArgumentException.class, () -> new CourseRoll(null, 100));
    }
	
	/**
	 * Tests CourseRoll.setEnrollmentCap
	 */
	@Test
	public void testSetEnrollmentCap() {
		Course course1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 150, "MW", 1330, 1445);
	    CourseRoll roll = course1.getCourseRoll();
		assertDoesNotThrow(() -> roll.setEnrollmentCap(70));
	}
	
	/**
	 * Tests CourseRoll.setEnrollmentCap when invalid
	 */
	@Test
	public void testSetEnrollmentCapInvalid() {
		Course course1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 150, "MW", 1330, 1445);
	    CourseRoll roll = course1.getCourseRoll();
	    assertThrows(IllegalArgumentException.class, () -> roll.setEnrollmentCap(-1));
	}
	
	/**
	 * Tests CourseRoll.enroll when invalid
	 */
	@Test
	public void testEnrollInvalid() {
		Course course1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 150, "MW",
				1330, 1445);
		CourseRoll roll = course1.getCourseRoll();
		Student student = null; 
	    assertThrows(IllegalArgumentException.class, () -> roll.enroll(student));
	}
	
	/**
	 * Tests CourseRoll.enroll
	 */
	@Test
	public void testEnroll() {
		Course course1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 150, "MW",
				1330, 1445);
		CourseRoll roll = course1.getCourseRoll();
		Student student = new Student("Ashwin", "Mahesh", "amahesh", "amahesh@ncsu.edu", "321", 16);
		assertTrue(roll.canEnroll(student));
		roll.enroll(student);
		assertFalse(roll.canEnroll(student)); 
		assertThrows(IllegalArgumentException.class, () -> roll.enroll(new Student("Ashwin", "Mahesh", "amahesh", "amahesh@ncsu.edu", "321", 16)));
	}
	
	/**
	 * Tests CourseRoll.drop
	 */
	@Test
	public void testDrop() {
		Course course1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 150, "MW",
				1330, 1445);
		CourseRoll roll = course1.getCourseRoll();
		Student student = new Student("Ashwin", "Mahesh", "amahesh", "amahesh@ncsu.edu", "321", 16);
		assertTrue(roll.canEnroll(student));
		roll.enroll(student);
		assertFalse(roll.canEnroll(student)); 
		roll.drop(student);
		assertTrue(roll.canEnroll(student)); 
		assertEquals(0, roll.getNumberOnWaitlist());
		
	}
	
	/**
	 * Tests CourseRoll.drop when invalid
	 */
	@Test
	public void testDropInvalid() {
		Course course1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 150, "MW",
				1330, 1445);
		CourseRoll roll = course1.getCourseRoll();
		Student student = null;
		assertThrows(IllegalArgumentException.class, () -> roll.drop(student));
		
	}
	
	/**
	 * tests the drop method for waitlist
	 */
	@Test
	public void testWaitListDrop() {
		Course course1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW",
				1330, 1445);
		// lines 149-173 taken from David Giang's lab 8
		assertDoesNotThrow(() -> course1.getCourseRoll());
		assertThrows(IllegalArgumentException.class, () -> course1.getCourseRoll().enroll(null));
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertDoesNotThrow(() -> course1.getCourseRoll().enroll(s1));
		assertThrows(IllegalArgumentException.class, () -> course1.getCourseRoll().enroll(s1));
		Student s2 = new Student("david", lastName, "djgiang1", email, hashPW);
		assertDoesNotThrow(() -> course1.getCourseRoll().enroll(s2));
		Student s3 = new Student("spencer", lastName, "djgiang2", email, hashPW);
		assertDoesNotThrow(() -> course1.getCourseRoll().enroll(s3));
		Student s4 = new Student("marco", lastName, "djgiang3", email, hashPW);
		assertDoesNotThrow(() -> course1.getCourseRoll().enroll(s4));
		Student s5 = new Student(firstName, "giang", "djgiang4", email, hashPW);
		assertDoesNotThrow(() -> course1.getCourseRoll().enroll(s5));
		Student s6 = new Student(firstName, "harman", "djgiang5", email, hashPW);
		assertDoesNotThrow(() -> course1.getCourseRoll().enroll(s6));
		Student s7 = new Student(firstName, "torres-blanco", "djgiang6", email, hashPW);
		assertDoesNotThrow(() -> course1.getCourseRoll().enroll(s7));
		Student s8 = new Student("dj", lastName, "djgiang7", email, hashPW);
		assertDoesNotThrow(() -> course1.getCourseRoll().enroll(s8));
		Student s9 = new Student(firstName, "burns", "djgiang8", email, hashPW);
		assertDoesNotThrow(() -> course1.getCourseRoll().enroll(s9));
		Student s10 = new Student(firstName, "o'connell", "djgiang9", email, hashPW);
		assertDoesNotThrow(() -> course1.getCourseRoll().enroll(s10));
		Student s11 = new Student("michael", lastName, "djgiang10", email, hashPW);
		assertDoesNotThrow(() -> course1.getCourseRoll().enroll(s11));
		course1.getCourseRoll().drop(s1);
		assertEquals(0, course1.getCourseRoll().getNumberOnWaitlist());
		
		Student s12 = new Student("george", lastName, "djgiang11", email, hashPW);
		assertDoesNotThrow(() -> course1.getCourseRoll().enroll(s12));
		//missing s11
		course1.getCourseRoll().drop(s12);
		assertEquals(0, course1.getCourseRoll().getNumberOnWaitlist());
	}
	


}
