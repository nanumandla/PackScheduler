package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * The Course Catalog class
 * 
 * @author Jack Farrell
 * @author Ashwin Mahesh
 * @author Marco Torres
 */
public class CourseCatalog {

	/** SortedList catalog field */
	public SortedList<Course> catalog;

	/**
	 * CourseCatalog constructor
	 */
	public CourseCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * For empty course
	 */
	public void newCourseFromCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * Public method to load courses in from given file name
	 * 
	 * @param fileName file name in directory
	 * @throws IllegalArgumentException if unable to read file
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}

	/**
	 * Public method that adds course to catalog and returns boolean value if
	 * completed or not
	 * 
	 * @param name         Course's String name
	 * @param title        Course's String title
	 * @param section      Course's String section
	 * @param credits      Course's integer of credits
	 * @param instructorId Course's String instructor ID
	 * @param meetingDays  Course's String of which meeting day
	 * @param startTime    Course's integer of start time
	 * @param endTime      Course's integer of end time
	 * @param enrollmentCap enrollment cap for Course
	 * @return boolean value stating whether it is completed or not
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId,
			int enrollmentCap, String meetingDays, int startTime, int endTime) {
		Course newCourse = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays,
				startTime, endTime);

		for (int i = 0; i < catalog.size(); i++) {
			Course courses = catalog.get(i);
			if (courses.getName().equals(newCourse.getName()) && courses.getSection().equals(newCourse.getSection())) {
				return false;
			}
		}

		catalog.add(newCourse);
		return true;
	}

	/**
	 * Return's whether the course is within the catalog or not
	 * 
	 * @param name    Course's String name
	 * @param section section of catalog
	 * @return true if course removed
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			Course course = catalog.get(i);
			if (course.getName().equals(name) && course.getSection().equals(section)) {
				catalog.remove(i);
				return true; // Successfully removed
			}
		}
		return false; // Course not found
	}

	/**
	 * Public method that gets a specific course from catalog dependent on its name
	 * and section
	 * 
	 * @param name    Course's String name
	 * @param section Course's String section
	 * @return null if cannot get or the catalog if found
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		return null;
	}

	/**
	 * Public method to display course catalog
	 * 
	 * @return Double String Array
	 */
	public String[][] getCourseCatalog() {
		String[][] catalogArray = new String[catalog.size()][5];

		if (catalog.size() == 0) {
			return new String[0][0];
		}

		for (int i = 0; i < catalog.size(); i++) {
			Course courseCat = catalog.get(i);
			catalogArray[i] = courseCat.getShortDisplayArray();
		}

		return catalogArray;
	}

	/**
	 * Public method saves course name
	 * 
	 * @param fileName String of file name
	 * @throws IllegalArgumentException if unable to write file
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file ");
		}
	}

	/**
	 * Constructs an empty Course Catalog object
	 */
	public void newCourseCatalog() {

		catalog = new SortedList<Course>();
	}
}
