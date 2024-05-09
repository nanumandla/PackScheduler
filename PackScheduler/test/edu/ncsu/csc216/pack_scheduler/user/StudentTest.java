package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.jupiter.api.Assertions.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the Student object.
 * 
 * @author SarahHeckman
 */
public class StudentTest {

	/** Test Student's first name. */
	private String firstName = "first";
	/** Test Student's last name */
	private String lastName = "last";
	/** Test Student's id */
	private String id = "flast";
	/** Test Student's email */
	private String email = "first_last@ncsu.edu";
	/** Test Student's hashed password */
	private String hashPW;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";

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
	 * testing for throwing IllegalArgumentException whenever credits are too low
	 */
	@Test
	public void testSetMaxCreditsBelowMinimum() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertThrows(IllegalArgumentException.class, () -> s1.setMaxCredits(2));
	}

	/**
	 * testing for throwing exception whenever credits are over the maximum allowed
	 */
	@Test
	public void testSetMaxCreditsAboveMaximum() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertThrows(IllegalArgumentException.class, () -> s1.setMaxCredits(19));
	}

	/**
	 * checking throw exception from setFirstName method
	 */
	@Test
	public void testSetFirstNameNullandEmpty() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertThrows(IllegalArgumentException.class, () -> s1.setFirstName(null));
		assertThrows(IllegalArgumentException.class, () -> s1.setFirstName(""));
	}

	/**
	 * checking throw exception from setLastName method
	 */
	@Test
	public void testSetLastNameNullandEmpty() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertThrows(IllegalArgumentException.class, () -> s1.setLastName(null));
		assertThrows(IllegalArgumentException.class, () -> s1.setLastName(""));
	}

	/**
	 * checking throw exception from setId method
	 */
	@Test
	public void testSetIdNullandEmpty() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertThrows(IllegalArgumentException.class, () -> s1.setId(null));
		assertThrows(IllegalArgumentException.class, () -> s1.setId(""));
	}

	/**
	 * checking throw exception from setPassword method
	 */
	@Test
	public void testSetPasswordNullandEmpty() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertThrows(IllegalArgumentException.class, () -> s1.setPassword(null));
		assertThrows(IllegalArgumentException.class, () -> s1.setPassword(""));
	}

	/**
	 * checking throw exceptions from setEmail method if null or empty
	 */
	@Test
	public void testSetEmailNullandEmpty() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertThrows(IllegalArgumentException.class, () -> s1.setEmail(null));
		assertThrows(IllegalArgumentException.class, () -> s1.setEmail(""));
	}

	/**
	 * checking throw exceptions from setEmail method if no @
	 */
	@Test
	public void testSetEmailNoAt() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertThrows(IllegalArgumentException.class, () -> s1.setEmail("realemailATgmail.com"));
	}

	/**
	 * checking throw exceptions from setEmail method if no dot
	 */
	@Test
	public void testSetEmailNoDot() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertThrows(IllegalArgumentException.class, () -> s1.setEmail("realemail@gmailDOTcom"));
	}

	/**
	 * checking throw exceptions from setEmail method if dot before @
	 */
	@Test
	public void testSetEmailDotBeforeAt() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertThrows(IllegalArgumentException.class, () -> s1.setEmail("realemail.@gmailDOTcom"));
	}

	@Test
	void testHashCode() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		Student s2 = new Student(firstName, lastName, id, email, hashPW);

		assertEquals(s1.hashCode(), s2.hashCode());
	}

	@Test
	void testEqualsObject() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		Student s2 = new Student(firstName, lastName, id, email, hashPW);
		Student s3 = new Student("forstNaem", lastName, id, email, hashPW);

		assertEquals(s1, s2);
		assertEquals(s2, s1);

		assertNotEquals(s1, s3);

	}

	/**
	 * Test toString() method.
	 */
	@Test
	public void testToString() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		assertEquals("first,last,flast,first_last@ncsu.edu," + hashPW + ",18", s1.toString());
	}

	/**
	 * Test Student.compareTo().
	 */
	@Test
	public void testCompareTo() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		Student s2 = new Student(firstName, lastName, id, email, hashPW);
		Student s3 = new Student(firstName, "Chris", id, email, hashPW);
		Student s4 = new Student(firstName, lastName, "invald2", email, hashPW);
		Student s5 = new Student("Alexander", lastName, id, email, hashPW);
		Student s6 = new Student("Alexander", "Campbell", id, email, hashPW);
		Student s7 = new Student(firstName, lastName, "flas", email, hashPW);

		assertEquals(0, s1.compareTo(s2));
		assertEquals(0, s2.compareTo(s1));
		assertEquals(1, s1.compareTo(s3));
		assertEquals(-1, s1.compareTo(s4));
		assertEquals(1, s1.compareTo(s5));
		assertEquals(1, s1.compareTo(s6));
		assertEquals(1, s1.compareTo(s7));
		assertEquals(-1, s5.compareTo(s1));

		assertThrows(NullPointerException.class, () -> s1.compareTo(null));
	}

	/**
	 * Test Student.canAdd()
	 */
	@Test
	public void testCanAdd() {
		Student s1 = new Student(firstName, lastName, id, email, hashPW);
		Course course1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 150, "MW",
				1330, 1445);
		assertTrue(s1.canAdd(course1));

	}

}
