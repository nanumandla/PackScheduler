package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;



/**
 * 
 * A test developed to test the StudentRecordIO and validate that the test-files
 * are being read and written
 *  
 * @author Marco Torres
 * @author Ashwin Mahesh
 * @author Jack Farrell
 */
class StudentRecordIOTest {
	/** Expected Student 1 information */
	private String validStudent0 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	
	/** Expected Student 2 information */
	private String validStudent1 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	
	/** Expected Student 3 information */
	private String validStudent2 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	
	/** Expected Student 4 information */
	private String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	
	/** Expected Student 5 information */
	private String validStudent4 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	
	/** Expected Student 6 information */
	private String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	
	/** Expected Student 7 information */
	private String validStudent6 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	
	/** Expected Student 8 information */
	private String validStudent7 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";
	
	/** Expected Student 9 information */
	private String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";
	
	/** Expected Student 10 information */
	private String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";
	
	/** Array to hold expected results */
	private String [] validStudents = {validStudent3, validStudent6, validStudent4, validStudent5, validStudent2, validStudent8, validStudent0,
	        validStudent9, validStudent1, validStudent7};
	/** Password for the students that will be tested */
	private String hashPW;
	
	/** form of password encryption that is used for the passwords */
	private static final String HASH_ALGORITHM = "SHA-256";

	@BeforeEach
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = Base64.getEncoder().encodeToString(digest.digest());
	        
	        for (int i = 0; i < validStudents.length; i++) {
	            validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}

	@Test
	void testReadStudentRecords() {
		try {
			SortedList<Student> students = StudentRecordIO.readStudentRecords("test-files/student_records.txt");
			assertEquals(10, students.size());
			
			for (int i = 0; i < validStudents.length; i++) {
				assertEquals(validStudents[i], students.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + "test-files/student_records.txt");
		}
	}

	@Test
	public void testWriteStudentRecords() {
		SortedList<Student> students = new SortedList<Student>();
		students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		students.add(new Student("Cassandra", "Schwartz", "cschwartz", "semper@imperdietornare.co.uk", hashPW, 4));
		students.add(new Student("Shannon", "Hansen", "shansen", "convallis.est.vitae@arcu.ca", hashPW, 14));
		students.add(new Student("Demetrius", "Austin", "daustin", "Curabitur.egestas.nunc@placeratorcilacus.co.uk", hashPW, 18));
		students.add(new Student("Raymond", "Brennan", "rbrennan", "litora.torquent@pellentesquemassalobortis.ca", hashPW, 12));
		students.add(new Student("Emerald", "Frost", "efrost", "adipiscing@acipsumPhasellus.edu", hashPW, 3));
		students.add(new Student("Lane", "Berg", "lberg", "sociis@non.org", hashPW, 14));
		students.add(new Student("Griffith", "Stone", "gstone", "porta@magnamalesuadavel.net", hashPW, 17));
		students.add(new Student("Althea", "Hicks", "ahicks", "Phasellus.dapibus@luctusfelis.com", hashPW, 11));
		students.add(new Student("Dylan", "Nolan", "dnolan", "placerat.Cras.dictum@dictum.net", hashPW, 5));
		try {
			StudentRecordIO.writeStudentRecords("test-files/student_records.txt", students);
		} catch (IOException e) {
			fail("Cannot write to course records file");
		}
		
		checkFiles("test-files/student_records.txt", "test-files/student_records.txt");
	}
	
	@Test
	public void testWriteStudentRecordsNoPermissions() {
	    SortedList<Student> students = new SortedList<Student>();
	    students.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));

	   assertThrows(IOException.class, 
	            () -> StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", students));
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new FileInputStream(expFile));
			 Scanner actScanner = new Scanner(new FileInputStream(actFile));) {
			
			while (expScanner.hasNextLine()  && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals(exp, act, "Expected: " + exp + " Actual: " + act); 
				//The third argument helps with debugging!
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
