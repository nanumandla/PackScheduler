package edu.ncsu.csc216.pack_scheduler.user;
/**
 * Handles all User information, such as first name, last name, id, email, and password
 * @author Ashwin Mahesh
 * @author Taylor Liu
 */
public abstract class User {

	/** student's first name */
	private String firstName;
	/** student's last name */
	private String lastName;
	/** student's Unity ID */
	private String id;
	/** student's email */
	private String email;
	/** student's password */
	private String password;
	
	/**
	 * Creates a User constructor
	 * @param firstName first name of User
	 * @param lastName last name of User
	 * @param id id of User
	 * @param email email of User
	 * @param password password of User
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.email = email;
		this.password = password;
	}

	/**
	 * returns the student email
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * sets the students school email
	 * 
	 * @param email the email to set
	 * @throws IllegalArgumentException if string is empty or null, does not contain
	 *                                  an "@" or "." or if "." is before "@".
	 */
	public void setEmail(String email) {
		if (email == null || "".equals(email)) {
			throw new IllegalArgumentException("Invalid email");
		}

		if (!email.contains("@")) {
			throw new IllegalArgumentException("Invalid email");
		}

		if (!email.contains(".")) {
			throw new IllegalArgumentException("Invalid email");
		}

		if (email.lastIndexOf(".") < email.indexOf("@")) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * returns the student password
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * sets the student password
	 * 
	 * @param password the password to set
	 * @throws IllegalArgumentException whenever a string is empty or null
	 */
	public void setPassword(String password) {
		if (password == null || "".equals(password)) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

	/**
	 * Sets the first name of the student and checks for validity
	 * 
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException if string is empty or null
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Sets the last name of the student and checks for validity
	 * 
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException if string is empty or null
	 */
	public void setLastName(String lastName) {
		if (lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Sets the Unity ID of the students and checks for validity
	 * 
	 * @param id the id to set
	 * @throws IllegalArgumentException if string is empty or null
	 */
	public void setId(String id) {
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Returns first name of student
	 * 
	 * @return null
	 */
	public String getFirstName() {

		return firstName;
	}
	
	/**
	 * Generates hash code for the user
	 * @return result hash code
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}
	
	/**
	 * Determines if 2 users are equal to each other
	 * @param obj user object
	 * @return true if 2 users are equal, else it is false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	/**
	 * Returns last name of student
	 * 
	 * @return null
	 */
	public String getLastName() {

		return lastName;
	}

	/**
	 * Returns Unity ID name of student
	 * 
	 * @return null
	 */
	public String getId() {

		return id;
	}

}