package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * this class holds a directory of all faculty members. all faculty members are stored in a linkedlist with their information. faculty members can also be read in from a file
 * @author David Giang
 * @author Ashwin Mahesh
 * @author Nirvan Reddy Anumandla
 */
public class FacultyDirectory {
	/** List of faculty in the directory */
	private LinkedList<Faculty> facultyDirectory;
	/** Hashing algorithm */
    private static final String HASH_ALGORITHM = "SHA-256";
    
    /**
     * Creates an empty faculty directory.
     */
    public FacultyDirectory() {
        newFacultyDirectory();
    }
    
    /**
     * Initializes the faculty directory to a new, empty list.
     */
    public void newFacultyDirectory() {
        facultyDirectory = new LinkedList<Faculty>();
    }
    
    /**
     * Loads faculty members from a file.
     * @param fileName the name of the file to load faculty members from
     * @throws IllegalArgumentException if the file cannot be found
     */
    public void loadFacultyFromFile(String fileName) {
        try {
            facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Unable to read file " + fileName);
        }
    }
    
    /**
     * Adds a new faculty member to the directory.
     * @param firstName the first name of the faculty member
     * @param lastName the last name of the faculty member
     * @param id the unique id of the faculty member
     * @param email the email address of the faculty member
     * @param password the password of the faculty member
     * @param repeatPassword the repeated password for verification
     * @param maxCourses the maximum number of courses the faculty member can teach
     * @return true if the faculty member is added successfully, false otherwise
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    public boolean addFaculty(String firstName, String lastName, String id, String email, String password,
                              String repeatPassword, int maxCourses) {
        String hashPW = "";
        String repeatHashPW = "";
        if (password == null || repeatPassword == null || password.isEmpty() || repeatPassword.isEmpty()) {
            throw new IllegalArgumentException("Invalid password");
        }

        hashPW = hashString(password);
        repeatHashPW = hashString(repeatPassword);

        if (!hashPW.equals(repeatHashPW)) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        Faculty faculty = new Faculty(firstName, lastName, id, email, hashPW, maxCourses);

        for (int i = 0; i < facultyDirectory.size(); i++) {
            Faculty f = facultyDirectory.get(i);
            if (f.getId().equals(faculty.getId())) {
                return false;
            }
        }
        return facultyDirectory.add(faculty);
    }
    
    /**
     * Removes a faculty member from the directory.
     * @param facultyId the unique id of the faculty member to remove
     * @return true if the faculty member is removed successfully, false otherwise
     */
    public boolean removeFaculty(String facultyId) {
        for (int i = 0; i < facultyDirectory.size(); i++) {
            Faculty f = facultyDirectory.get(i);
            if (f.getId().equals(facultyId)) {
                facultyDirectory.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a 2D array representation of the faculty directory.
     * @return a 2D array where each row represents a faculty member, and each column represents their first name,
     *         last name, and id
     */
    public String[][] getFacultyDirectory() {
    	if(facultyDirectory.size() == 0) {
    		String[][] directory = new String[0][0];
    		return directory;
    	}
        String[][] directory = new String[facultyDirectory.size()][3];
        for (int i = 0; i < facultyDirectory.size(); i++) {
            Faculty f = facultyDirectory.get(i);
            directory[i][0] = f.getFirstName();
            directory[i][1] = f.getLastName();
            directory[i][2] = f.getId();
        }
        return directory;
    }

    /**
     * Saves the faculty directory to a file.
     * @param fileName the name of the file to save the faculty directory to
     * @throws IllegalArgumentException if unable to write to the file
     */
    public void saveFacultyDirectory(String fileName) {
        try {
            FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to write to file " + fileName);
        }
    }

    /**
     * Hashes a String according to the SHA-256 algorithm and returns the encoded digest in base64 format.
     * @param toHash the String to hash
     * @return the encoded digest of the hash algorithm in base64
     * @throws IllegalArgumentException if unable to hash the password
     */
    private static String hashString(String toHash) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.update(toHash.getBytes());
            return Base64.getEncoder().encodeToString(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Cannot hash password");
        }
    }
    
    /**
     * searches for a faculty members based on their id
     * @param facultyId is the id of the faculty member we wish to search for
     * @return the faculty member if found
     */
    public Faculty getFacultyById(String facultyId) {
	    for (int i = 0; i < facultyDirectory.size(); i++) {
	         Faculty faculty = facultyDirectory.get(i);
	        if (faculty.getId().equals(facultyId)) {
	            return faculty;
	        }
	    }
	    return null; 
	}
}
