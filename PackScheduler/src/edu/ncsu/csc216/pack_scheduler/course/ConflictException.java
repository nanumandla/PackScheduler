package edu.ncsu.csc216.pack_scheduler.course;
/**
 * Checks for Conflict Exceptions in Schedule
 * @author Ashwin Mahesh
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs ConflictException object
	 * @param message specifies message for Exception and is passed to parent's constructor
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Constructs parameterless ConflictException object 
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}

}
