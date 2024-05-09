package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Validates the Course names
 * 
 * @author Ashwin Mahesh
 * @author Taylor Liu
 */
public class CourseNameValidator {
	/** State object that represents its current state */
	private State currentState;
	/** Number of digits counted in course */
	int digitCount = 0;
	/** Number of letters counted in course */
	int letterCount = 0;

	/**
	 * Determines if course is valid and updates the current state of the course to another state
	 * @param str course name
	 * @return true if course is valid
	 * @throws InvalidTransitionException if transition is invalid
	 */
	public boolean isValid(String str) throws InvalidTransitionException {
		currentState = new InitialState();
		letterCount = 0;
		digitCount = 0;
		char c;
		boolean isValidCourse = false;

		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (Character.isLetter(c)) {
				currentState.onLetter();
			}

			else if (Character.isDigit(c)) {
				currentState.onDigit();
			}

			else {
				currentState.onOther();
			}
		}
		
		if(digitCount == 3 && letterCount > 0 && letterCount < 5) {
			isValidCourse = true;
		}

		return isValidCourse;

	}
	
	/**
	 * Abstract class that represents the state of each course name, such as 
	 * letter state, digit state, and other
	 */
	abstract class State {
		/**
		 * Handles transition if there is a letter
		 * @throws InvalidTransitionException if transition is invalid
		 */
		abstract void onLetter() throws InvalidTransitionException;
		
		/**
		 * Handles transition if there is a digit
		 * @throws InvalidTransitionException if transition is invalid
		 */
		abstract void onDigit() throws InvalidTransitionException;
		
		/**
		 * Handles transition when characters are not letters or digits
		 * @throws InvalidTransitionException if transition is not a letter or digit
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}

	}
	
	/**
	 * Represents transition state when the character is a letter
	 */
	class LetterState extends State {
		/**
		 * Represents transition state when the next character is a letter
		 * @throws InvalidTransitionException when letter is more than 4 letters
		 */
		public void onLetter() throws InvalidTransitionException {
			if (letterCount < 4) {
				currentState = new LetterState();
				letterCount++;
			} else {
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
			}
		}
		
		/**
		 * Represents transition state when the next character is a digit
		 */
		public void onDigit() {
			currentState = new DigitState();
			digitCount++;
		}

	}
	
	/**
	 * Represents transition state when the character is a digit
	 */
	class SuffixState extends State {
		/**
		 * Represents transition state when the next character is a letter
		 * @throws InvalidTransitionException when letter is more than 1 letter suffix
		 */
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
		}
		/**
		 * Represents transition state when the next character is a digit
		 * @throws InvalidTransitionException when letter is more than 1 digit after suffix
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
		}
	}
	
	/**
	 * Represents Initial state of the course
	 */
	class InitialState extends State {
		/**
		 * Represents transition state when the next character is a letter
		 */
		public void onLetter() {
			currentState = new LetterState();
			letterCount++;
		}
		
		/**
		 * Represents transition state when the next character is a digit
		 * @throws InvalidTransitionException if course name does not start with letter
		 */
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");
		}
	}
	
	/**
	 * Represents when the Course is in the digit state
	 */
	class DigitState extends State {
		/**
		 * Represents transition state when the next character is a letter
		 * @throws InvalidTransitionException if course does not have 3 digits
		 */
		public void onLetter() throws InvalidTransitionException {
			if (digitCount == 3) {
				currentState = new SuffixState();
			} else {
				throw new InvalidTransitionException("Course name must have 3 digits.");
			}
		}
		
		/**
		 * Represents transition state when the next character is a digit
		 * @throws InvalidTransitionException if course does not have 3 digits
		 */
		public void onDigit() throws InvalidTransitionException {
			if (digitCount == 3) {
				throw new InvalidTransitionException("Course name can only have 3 digits.");
			} else {
				currentState = new DigitState();
				digitCount++;
			}
		}
	}
}
