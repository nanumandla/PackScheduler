package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * tests the FacultyRecordIO class
 * @author David Giang
 * @author Ashwin Mahesh
 * @author Nirvan Reddy Anumandla
 */
public class FacultyRecordIOTest {
	/** Expected Faculty 1 information */
	private String validFaculty0 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,pw,2";
	
	/** Expected Faculty 2 information */
	private String validFaculty1 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,pw,3";
	
	/** Expected Faculty 3 information */
	private String validFaculty2 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,pw,1";
	
	/** Expected Faculty 4 information */
	private String validFaculty3 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,pw,3";
	
	/** Expected Faculty 5 information */
	private String validFaculty4 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,pw,1";
	
	/** Expected Faculty 6 information */
	private String validFaculty5 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,pw,3";
	
	/** Expected Faculty 7 information */
	private String validFaculty6 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,pw,1";
	
	/** Expected Faculty 8 information */
	private String validFaculty7 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,pw,2";
	/** Password for the students that will be tested */
	private String hashPW;
	/** form of password encryption that is used for the passwords */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/** string array that holds valid faculty strings */
	private String[] validFaculty = {validFaculty0, validFaculty1, validFaculty2, validFaculty3, validFaculty4, validFaculty5, validFaculty6, validFaculty7};
	
	/**
	 * sets up the faculty directory object
	 */
	@BeforeEach
	public void setUp() {
	    try {
	        String password = "pw";
	        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
	        digest.update(password.getBytes());
	        hashPW = Base64.getEncoder().encodeToString(digest.digest());
	        
	        for (int i = 0; i < validFaculty.length; i++) {
	            validFaculty[i] = validFaculty[i].replace(",pw,", "," + hashPW + ",");
	        }
	    } catch (NoSuchAlgorithmException e) {
	        fail("Unable to create hash during setup");
	    }
	}
	
	/**
	 * tests the readFacultyRecords method
	 */
	@Test
	void testReadFacultyRecords() {
		try {
			LinkedList<Faculty> faculties = FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt");
			assertEquals(8, faculties.size());
			
			for (int i = 0; i < validFaculty.length; i++) {
				assertEquals(validFaculty[i], faculties.get(i).toString());
			}
		} catch (FileNotFoundException e) {
			fail("Unexpected error reading " + "test-files/faculty_records.txt");
		}
	}
	
	/**
	 * tests the writeFacultyRecords method
	 */
	@Test
	public void testWriteFacultyRecords() {
		LinkedList<Faculty> faculties = new LinkedList<Faculty>();
		faculties.add(new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", hashPW, 2));
		faculties.add(new Faculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", hashPW, 3));
		faculties.add(new Faculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", hashPW, 1));
		
		try {
			FacultyRecordIO.writeFacultyRecords("test-files/actual_faculty_records.txt", faculties);
		} catch (IOException e) {
			fail("Cannot write to course records file");
		}
		
		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_faculty_records.txt");
	}
	
	/**
	 * tests the writeSFacultyRecords method without permissions
	 */
	@Test
	public void testWriteFacultyRecordsNoPermissions() {
		LinkedList<Faculty> faculties = new LinkedList<Faculty>();
		faculties.add(new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", hashPW, 2));
		faculties.add(new Faculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", hashPW, 3));
		faculties.add(new Faculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", hashPW, 1));

	   assertThrows(IOException.class, 
	            () -> FacultyRecordIO.writeFacultyRecords("/home/sesmith5/actual_student_records.txt", faculties));
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