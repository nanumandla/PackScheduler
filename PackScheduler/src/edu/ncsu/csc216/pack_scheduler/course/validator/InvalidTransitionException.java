package edu.ncsu.csc216.pack_scheduler.course.validator;
/**
 * Checks for Conflict Exceptions in Schedule
 * @author Ashwin Mahesh
 * @author Taylor Liu
 */
public class InvalidTransitionException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs ConflictException object
	 * @param message specifies message for Exception and is passed to parent's constructor
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
	
	/**
	 * Constructs parameterless ConflictException object 
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}

}
