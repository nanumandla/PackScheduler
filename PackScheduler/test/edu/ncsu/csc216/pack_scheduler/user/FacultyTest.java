/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.FacultySchedule;



/**
 * this is a class that tests the Faculty object for proper functionality
 * @author David Giang
 * @author Ashwin Mahesh
 * @author Nirvan Reddy Anumandla
 */
public class FacultyTest {

	/** Test Faculty's first name. */
	private String firstName = "first";
	/** Test Faculty's last name */
	private String lastName = "last";
	/** Test Faculty's id */
	private String id = "flast";
	/** Test Faculty's email */
	private String email = "first_last@ncsu.edu";
	/** Test Faculty's hashed password */
	private String hashPW;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	/** Test faculty */
	private Faculty faculty;
	private FacultySchedule facultySchedule;
	
	// This is a block of code that is executed when the StudentTest object is
		// created by JUnit. Since we only need to generate the hashed version
		// of the plaintext password once, we want to create it as the StudentTest
		// object is
		// constructed. By automating the hash of the plaintext password, we are
		// not tied to a specific hash implementation. We can change the algorithm
		// easily.
		{
			try {
				String plaintextPW = "password";
				MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(plaintextPW.getBytes());
				this.hashPW = Base64.getEncoder().encodeToString(digest.digest());
			} catch (NoSuchAlgorithmException e) {
				fail("An unexpected NoSuchAlgorithmException was thrown.");
			}
		}
		
		/**
		 * testing for throwing IllegalArgumentException whenever courses are too low
		 */
		@Test
		public void testSetMaxCoursesBelowMinimum() {
			assertThrows(IllegalArgumentException.class, () -> new Faculty(firstName, lastName, id, email, hashPW, 0));
		}

		/**
		 * testing for throwing exception whenever courses are over the maximum allowed
		 */
		@Test
		public void testSetMaxCoursesAboveMaximum() {
			assertThrows(IllegalArgumentException.class, () -> new Faculty(firstName, lastName, id, email, hashPW, 4));
		}
		
		/**
		 * checking throw exception from setFirstName method
		 */
		@Test
		public void testSetFirstNameNullandEmpty() {
			Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, 2);
			assertThrows(IllegalArgumentException.class, () -> s1.setFirstName(null));
			assertThrows(IllegalArgumentException.class, () -> s1.setFirstName(""));
		}

		/**
		 * checking throw exception from setLastName method
		 */
		@Test
		public void testSetLastNameNullandEmpty() {
			Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, 2);
			assertThrows(IllegalArgumentException.class, () -> s1.setLastName(null));
			assertThrows(IllegalArgumentException.class, () -> s1.setLastName(""));
		}
		
		/**
		 * checking throw exception from setId method
		 */
		@Test
		public void testSetIdNullandEmpty() {
			Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, 2);
			assertThrows(IllegalArgumentException.class, () -> s1.setId(null));
			assertThrows(IllegalArgumentException.class, () -> s1.setId(""));
		}

		/**
		 * checking throw exception from setPassword method
		 */
		@Test
		public void testSetPasswordNullandEmpty() {
			Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, 2);
			assertThrows(IllegalArgumentException.class, () -> s1.setPassword(null));
			assertThrows(IllegalArgumentException.class, () -> s1.setPassword(""));
		}

		/**
		 * checking throw exceptions from setEmail method if null or empty
		 */
		@Test
		public void testSetEmailNullandEmpty() {
			Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, 2);
			assertThrows(IllegalArgumentException.class, () -> s1.setEmail(null));
			assertThrows(IllegalArgumentException.class, () -> s1.setEmail(""));
		}

		/**
		 * checking throw exceptions from setEmail method if no @
		 */
		@Test
		public void testSetEmailNoAt() {
			Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, 2);
			assertThrows(IllegalArgumentException.class, () -> s1.setEmail("realemailATgmail.com"));
		}

		/**
		 * checking throw exceptions from setEmail method if no dot
		 */
		@Test
		public void testSetEmailNoDot() {
			Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, 2);
			assertThrows(IllegalArgumentException.class, () -> s1.setEmail("realemail@gmailDOTcom"));
		}

		/**
		 * checking throw exceptions from setEmail method if dot before @
		 */
		@Test
		public void testSetEmailDotBeforeAt() {
			Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, 2);
			assertThrows(IllegalArgumentException.class, () -> s1.setEmail("realemail.@gmailDOTcom"));
		}

		@Test
		void testHashCode() {
			Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, 2);
			Faculty s2 = new Faculty(firstName, lastName, id, email, hashPW, 2);

			assertEquals(s1.hashCode(), s2.hashCode());
		}

		@Test
		void testEqualsObject() {
			Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, 2);
			Faculty s2 = new Faculty(firstName, lastName, id, email, hashPW, 2);
			Faculty s3 = new Faculty("forstNaem", lastName, id, email, hashPW, 2);

			assertEquals(s1, s2);
			assertEquals(s2, s1);

			assertNotEquals(s1, s3);

		}

		/**
		 * Test toString() method.
		 */
		@Test
		public void testToString() {
			Faculty s1 = new Faculty(firstName, lastName, id, email, hashPW, 2);
			assertEquals("first,last,flast,first_last@ncsu.edu," + hashPW + ",2", s1.toString());
		}
		

//	    @Test
//	    public void testIsOverloaded_Overloaded() {
//	        Course course1 = new Course("CSC216", "Programming Concepts - Java", "001", 4, "instructorId", 10, "MWF", 1200, 1330);
//	        Course course2 = new Course("CSC226", "Discrete Mathematics for Computer Scientists", "001", 3, "instructorId", 10, "TH", 1330, 1445);
//	        Course course3 = new Course("CSC116", "Introduction to Java Programming", "001", 3, "instructorId", 10, "MW", 800, 915);
//	        Course course4 = new Course("CSC230", "Introduction to C", "001", 3, "instructorId", 10, "F", 800, 915);
//	        
//	        facultySchedule.addCourseToSchedule(course1);
//	        facultySchedule.addCourseToSchedule(course2);
//	        facultySchedule.addCourseToSchedule(course3);
//	        facultySchedule.addCourseToSchedule(course4);
//	        assertTrue(faculty.isOverloaded());
//	    }
}
