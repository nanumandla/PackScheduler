/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Checks and handles Conflicting Activities in a schedule
 * @author Ashwin Mahesh
 */
public interface Conflict {
	/**
	 * Checks for Conflicting activities in schedule
	 * @param possibleConflictingActivity potential conflicted activity
	 * @throws ConflictException if scheduling conflicts occur
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
