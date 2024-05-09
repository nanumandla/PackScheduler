package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * tests the FacultyDirectory class for proper functionality
 * @author David Giang
 * @author Ashwin Mahesh
 * @author Nirvan Reddy Anumandla
 */
public class FacultyDirectoryTest {
	
	/** is the name of the file we will be using */
    private static final String FILE = "test-files/faculty_records.txt";
    /** is the name of the faculty member */
    private static final String FIRST_NAME = "John";
    /** is the last name of the faculty member */
    private static final String LAST_NAME = "Doe";
    /** is the id of the faculty member */
    private static final String ID = "jdoe";
    /** is the email of the faculty member */
    private static final String EMAIL = "jdoe@ncsu.edu";
    /** is the password of the faculty member */
    private static final String PASSWORD = "password";
    /** is the max courses allowed for the faculty member */
    private static final int MAX_COURSES = 3;
    
    /**
     * sets up the faculty directory
     * @throws Exception if error
     */
    @Before
    public void setUp() throws Exception {
        Path sourcePath = FileSystems.getDefault().getPath("test-files");
        Path destinationPath = FileSystems.getDefault().getPath("test-files");
        try {
            Files.deleteIfExists(destinationPath);
            Files.copy(sourcePath, destinationPath);
        } catch (IOException e) {
 //           fail("Unable to reset files");
        }
    }
    
    /**
     * tests the facultyDirectory constructor
     */
    @Test
    public void testFacultyDirectory() {
        FacultyDirectory fd = new FacultyDirectory();
        assertNotNull(fd);
    }

    /**
     * test the facultyDirectory to make sure that the size gets reset when a new facultyDirectory is made
     */
    @Test
    public void testNewFacultyDirectory() {
        FacultyDirectory fd = new FacultyDirectory();
        fd.loadFacultyFromFile(FILE);
        assertEquals(8, fd.getFacultyDirectory().length);
        fd.newFacultyDirectory();
        assertEquals(0, fd.getFacultyDirectory().length);
    }

    /**
     * tests the loadFacultyFromFile method
     */
    @Test
    public void testLoadFacultyFromFile() {
        FacultyDirectory fd = new FacultyDirectory();
        fd.loadFacultyFromFile(FILE);
        assertEquals(8, fd.getFacultyDirectory().length);
    }
    
    
    /**
     * tests the addFaculty method
     */
    @Test
    public void testAddFaculty() {
        FacultyDirectory fd = new FacultyDirectory();
        assertTrue(fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES));
        String[][] facultyDirectory = fd.getFacultyDirectory();
        assertEquals(1, facultyDirectory.length);
        assertEquals(FIRST_NAME, facultyDirectory[0][0]);
        assertEquals(LAST_NAME, facultyDirectory[0][1]);
        assertEquals(ID, facultyDirectory[0][2]);
    }
    
    /**
     * tests the addFaculty method for null and empty objects
     */
    @Test
    public void testAddFacultyNullorEmpty() {
        FacultyDirectory fd = new FacultyDirectory();
        assertThrows(IllegalArgumentException.class,
                () -> fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "", PASSWORD, MAX_COURSES));
        assertThrows(IllegalArgumentException.class,
                () -> fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, null, PASSWORD, MAX_COURSES));
        assertThrows(IllegalArgumentException.class,
                () -> fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, "", MAX_COURSES));
        assertThrows(IllegalArgumentException.class,
                () -> fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, null, MAX_COURSES));
    }
    
    
    /**
     * tests the add faculty method for if theres no matching password
     */
    @Test
    public void testAddFacultyNoMatchPass() {
        FacultyDirectory fd = new FacultyDirectory();
        assertThrows(IllegalArgumentException.class,
                () -> fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, "pass", PASSWORD, MAX_COURSES));
    }
    
    /**
     * tests the removeFaculty method
     */
    @Test
    public void testRemoveFaculty() {
        FacultyDirectory fd = new FacultyDirectory();
        fd.loadFacultyFromFile(FILE);
        assertEquals(8, fd.getFacultyDirectory().length);
        assertTrue(fd.removeFaculty("awitt"));
        assertEquals(7, fd.getFacultyDirectory().length);
    }
    
    /**
     * tests the saveFacultyDirectory method
     */
    @Test
    public void testSaveFacultyDirectory() {
        FacultyDirectory fd = new FacultyDirectory();
        fd.addFaculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", "pw", 2);
        assertEquals(1, fd.getFacultyDirectory().length);
        fd.addFaculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", "pw", "pw", 3);
        assertEquals(2, fd.getFacultyDirectory().length);
        fd.addFaculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", "pw", "pw", 1);
        assertEquals(3, fd.getFacultyDirectory().length);
        fd.saveFacultyDirectory("test-files/actual_faculty_records.txt");
        checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
    }

    /**
     * this method checks the files during the test to make sure they expected and actual files match
     * @param expFile is the file with the expected output
     * @param actFile is the file with the actual output
     */
    private void checkFiles(String expFile, String actFile) {
        try {
            Scanner expScanner = new Scanner(new FileInputStream(expFile));
            Scanner actScanner = new Scanner(new FileInputStream(actFile));

            while (expScanner.hasNextLine()) {
                assertEquals(expScanner.nextLine(), actScanner.nextLine());
            }

            expScanner.close();
            actScanner.close();
        } catch (IOException e) {
            fail("Error reading files.");
        }
    }

    /**
     * tests the getFacultyById method
     */
    @Test
    public void testGetFacultyByIdFound() {
        FacultyDirectory fd = new FacultyDirectory();
        fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
        Faculty faculty = fd.getFacultyById("jdoe");
        assertNotNull(faculty);
        assertEquals("John", faculty.getFirstName());
        assertEquals("Doe", faculty.getLastName());
        assertEquals("jdoe", faculty.getId());
        assertEquals("jdoe@ncsu.edu", faculty.getEmail());
        assertEquals(3, faculty.getMaxCourses());
    }
    
    /**
     * tests the getFacultyById method for no id
     */
    @Test
    public void testGetFacultyByIdNotFound() {
        FacultyDirectory fd = new FacultyDirectory();
        fd.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_COURSES);
        assertNull(fd.getFacultyById("jsmith"));
    }
}
