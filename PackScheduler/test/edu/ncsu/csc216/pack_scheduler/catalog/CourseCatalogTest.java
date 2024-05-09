/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;

/**
 * Tests the Course Catalog
 * 
 * @author Marco Torres
 * @author Ashwin Mahesh
 * @author Jack Farrell
 */
class CourseCatalogTest {
	/** Valid test file for catalog */
	private final String validTestFile = "test-files/course_records.txt";
	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;
	/** New catalog object for course */
	private CourseCatalog catalog;
	/** Enrollment cap for the course */
	private static final int ENROLLMENT_CAP = 100;
	/** instance of the registration manager that is used to read in faculty records **/
	private RegistrationManager instance = null;

	@BeforeEach
	public void setUp() {
		catalog = new CourseCatalog();
		instance = RegistrationManager.getInstance();
		instance.getFacultyDirectory().loadFacultyFromFile("test-files/faculty_records_extended.txt");
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#CourseCatalog()}.
	 */
	@Test
	void testAddCourseToCatalog() {
		CourseCatalog cc = new CourseCatalog();

		cc.loadCoursesFromFile(validTestFile);
		// Test valid Course
		cc.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS, START_TIME,
				END_TIME);

		assertEquals(13, cc.getCourseCatalog().length);
	}

	@Test
	void testCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		assertFalse(cc.removeCourseFromCatalog("name", "title"));
		assertEquals(0, cc.getCourseCatalog().length);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#newCourseFromCatalog()}.
	 */
	@Test
	void testNewCourseFromCatalog() {
		// Test that if there are students in the directory, they
		// are removed after calling newStudentDirectory().
		CourseCatalog cc = new CourseCatalog();

		cc.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc.getCourseCatalog().length);

		cc.newCourseFromCatalog();
		assertEquals(0, cc.getCourseCatalog().length);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#loadCoursesFromFiles(java.lang.String)}.
	 */
	@Test
	void testLoadCoursesFromFiles() {
		CourseCatalog cc = new CourseCatalog();

		cc.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc.getCourseCatalog().length);
	}


	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#removeCourseToCatalog(java.lang.String, java.lang.String)}.
	 */
	@Test
	void removeCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();

		cc.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc.getCourseCatalog().length);
		assertFalse(cc.removeCourseFromCatalog("CSC 230", "C and Software Tools"));
		String[][] courseCatalog = cc.getCourseCatalog();
		assertEquals(13, courseCatalog.length);
	}

	
	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#getCourseCatalog()}.
	 */

	@Test
	void testGetCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();

		try {
			cc.loadCoursesFromFile(validTestFile);
		} catch (IllegalArgumentException e) {
			fail();
		}

		Course course = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENROLLMENT_CAP, MEETING_DAYS,
				START_TIME, END_TIME);
		assertEquals(cc.getCourseFromCatalog(NAME, SECTION), course);
	}
	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#saveCourseCatalog(java.lang.String)}.
	 */
	
	@Test
    void testSaveCourseCatalog() {
        assertAll("Ensure course is added and saved correctly",
                () -> assertTrue(catalog.addCourseToCatalog("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", ENROLLMENT_CAP, "MW", 910, 1100)),
                () -> assertDoesNotThrow(() -> catalog.saveCourseCatalog("test-save.txt"),
                        "Saving the catalog to a file should not throw."));
    }

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#getCourseFromCatalog(java.lang.String, java.lang.String)}.
	 */

	@Test
	void testGetCourseFromCatalogFound() {
		catalog.loadCoursesFromFile(validTestFile);
		Course course = catalog.getCourseFromCatalog("CSC216", "001");
		assertNotNull(course);
		assertEquals("CSC216", course.getName());
		assertEquals("001", course.getSection());
	}

	@Test
	public void testGetCourseFromCatalogNotFound() {
		catalog.loadCoursesFromFile(validTestFile);
		// Test to ensure a course that does not exist is not found
		Course course = catalog.getCourseFromCatalog("CSC517", "001");
		assertNull(course);
	}
}
