package edu.ncsu.csc216.pack_scheduler.course;

/**
 * This program represents as a parent class for the common elements in both
 * Course and Event Shares common elements and behaviors between those two
 * classes Programs checks duplicates in Course and Events and can return an
 * array depending if it longDisplayArray or shortDisplayArray
 * 
 * @author Ashwin Mahesh
 */
public abstract class Activity implements Conflict {

	/** Course's title. */
	protected String title;
	/** Course's meeting days */
	protected String meetingDays;
	/** Course's starting time */
	protected int startTime;
	/** Course's ending time */
	protected int endTime;
	/** Upper number of hours in military time */
	public static final int UPPER_HOUR = 23;
	/** Upper number of minutes in military time */
	public static final int UPPER_MINUTE = 59;

	/**
	 * Constructs Activity object with values from all fields
	 * 
	 * @param title       title of Course
	 * @param meetingDays meeting days for course
	 * @param startTime   start time for course
	 * @param endTime     end time for course
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Returns the Course's title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is invalid
	 */
	public void setTitle(String title) {
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}
		this.title = title;
	}

	/**
	 * Returns the Course's meeting days
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Returns the Course's start time
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Course's end time
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Returns the Courses's meeting days and times
	 * 
	 * @return the meetingSchedule
	 */
	public String getMeetingString() {
		int startHours = startTime / 100;
		int startMins = startTime % 100;
		String startDays = "AM";

		int endHours = endTime / 100;
		int endMins = endTime % 100;
		String endDays = "AM";

		String startHoursString = Integer.toString(startHours);
		String endHoursString = Integer.toString(endHours);

		String startMinsString = Integer.toString(startMins);
		String endMinsString = Integer.toString(endMins);

		if (startHours > 12 || startHours == 12) {
			startHours = startHours - 12;
			startDays = "PM";
			startHoursString = Integer.toString(startHours);
		}

		if (endHours > 12 || endHours == 12) {
			endHours = endHours - 12;
			endDays = "PM";
			endHoursString = Integer.toString(endHours);
		}

		if (startHours == 0) {
			startHours = 12;
			startHoursString = Integer.toString(startHours);
		}

		if (endHours == 0) {
			endHours = 12;
			endHoursString = Integer.toString(endHours);
		}

		if (startMins < 10) {
			startMinsString = "0" + startMinsString;
		}

		if (endMins < 10) {
			endMinsString = "0" + endMinsString;
		}

		String meetingSchedule = meetingDays + " " + startHoursString + ":" + startMinsString + startDays + "-"
				+ endHoursString + ":" + endMinsString + endDays;

		if ("A".equals(meetingDays)) {
			meetingSchedule = "Arranged";
		}

		return meetingSchedule;
	}

	/**
	 * Sets the Course's meeting days and time
	 * 
	 * @param meetingDays meeting days for the Course
	 * @param startTime   start time for the Course
	 * @param endTime     end time for the Course
	 * @throws IllegalArgumentException if meeting days and times are invalid
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {

		int startHour = startTime / 100;
		int startMin = startTime % 100;

		int endHour = endTime / 100;
		int endMin = endTime % 100;

		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if (startHour > endHour) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if (startHour < 0 || startHour > UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if (startMin < 0 || startMin > UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if (endHour < 0 || endHour > UPPER_HOUR) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		if (endMin < 0 || endMin > UPPER_MINUTE) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}

		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Creates a hash value for the object
	 * 
	 * @return result hash value for the object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Determines whether other object is equal to this object
	 * 
	 * @param obj object for comparison
	 * @return true if this object is same as other object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Returns an array containing Course name, section, title, and meeting String
	 * 
	 * @return shortArray array of name, section, title, meeting String
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Returns an array containing Course name, section, title, credits,
	 * instructorId, meeting String, and an empty String
	 * 
	 * @return longArray array of name, section, title, credits, instructorId,
	 *         meeting String, and an empty String
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Checks if parameter is an instance of a class and checks if the parameter is
	 * a duplicate
	 * 
	 * @param activity instance of course or event
	 * @return true if a duplicate is found
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Checks if this activity is a possible conflicting activity of another
	 * activity
	 * 
	 * @param possibleConflictingActivity possible activity that is conflicting
	 * @throws ConflictException if there is a schedule conflict
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		for (int i = 0; i < this.getMeetingDays().length(); i++) {
			if (possibleConflictingActivity.getMeetingDays().indexOf(this.getMeetingDays().charAt(i)) != -1
					&& !this.getMeetingDays().equals("A") && !possibleConflictingActivity.getMeetingDays().equals("A")
					&& (this.startTime >= possibleConflictingActivity.startTime
							&& this.startTime < possibleConflictingActivity.endTime
							|| this.endTime > possibleConflictingActivity.startTime
									&& this.endTime <= possibleConflictingActivity.endTime
							|| this.startTime <= possibleConflictingActivity.startTime
									&& this.endTime >= possibleConflictingActivity.endTime
							|| this.endTime == possibleConflictingActivity.startTime
							|| this.startTime == possibleConflictingActivity.endTime)) {
				throw new ConflictException("Schedule conflict.");
			}
		}
	}

}