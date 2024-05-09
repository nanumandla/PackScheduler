package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the Schedule class
 * 
 * @author Ashwin Mahesh
 * @author Taylor Liu
 */
public class ScheduleTest {

	/**
	 * Tests Schedule()
	 */
	@Test
	public void testSchedule() {
		Schedule schedule = new Schedule();
		assertEquals("My Schedule", schedule.getTitle());
		assertEquals(0, schedule.getScheduledCourses().length);
	}

	/**
	 * Tests Schedule.addCourseToSchedule()
	 */
	@Test
	public void testAddCourseToSchedule() {
		Schedule schedule = new Schedule();

		Course course1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 150, "MW",
				1330, 1445);
		Course course2 = new Course("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", 200, "MW", 1150,
				1200);

		assertTrue(schedule.addCourseToSchedule(course1));
		assertTrue(schedule.addCourseToSchedule(course2));
		assertEquals(2, schedule.getScheduledCourses().length);

	}

	/**
	 * Tests Schedule.removeCourseFromSchedule()
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		Schedule schedule = new Schedule();

		Course course1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 150, "MW",
				1330, 1445);
		Course course2 = new Course("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", 200, "MW", 1150,
				1200);
		schedule.addCourseToSchedule(course1);
		schedule.addCourseToSchedule(course2);

		schedule.removeCourseFromSchedule(course2);
		assertEquals(1, schedule.getScheduledCourses().length);
	}

	/**
	 * Tests Schedule.resetSchedule()
	 */
	@Test
	public void testResetSchedule() {
		Schedule schedule = new Schedule();

		Course course1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 150, "MW",
				1330, 1445);
		Course course2 = new Course("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", 200, "MW", 1150,
				1200);

		schedule.addCourseToSchedule(course1);
		schedule.addCourseToSchedule(course2);

		schedule.resetSchedule();

		assertEquals(0, schedule.getScheduledCourses().length);
		assertEquals("My Schedule", schedule.getTitle());
	}

	/**
	 * Tests Schedule.getScheduledCourses()
	 */
	@Test
	public void testGetScheduledCourses() {
		Schedule schedule = new Schedule();

		Course course1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 150, "MW",
				1330, 1445);
		Course course2 = new Course("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", 200, "MW", 1150,
				1200);

		schedule.addCourseToSchedule(course1);
		schedule.addCourseToSchedule(course2);

		assertEquals("CSC216", schedule.getScheduledCourses()[0][0]);
		assertEquals("001", schedule.getScheduledCourses()[0][1]);
		assertEquals("Software Development Fundamentals", schedule.getScheduledCourses()[0][2]);
		assertEquals("MW 1:30PM-2:45PM", schedule.getScheduledCourses()[0][3]);

		assertEquals("CSC116", schedule.getScheduledCourses()[1][0]);
		assertEquals("003", schedule.getScheduledCourses()[1][1]);
		assertEquals("Intro to Programming - Java", schedule.getScheduledCourses()[1][2]);
		assertEquals("MW 11:50AM-12:00PM", schedule.getScheduledCourses()[1][3]);

	}

	/**
	 * Tests Schedule.setTitle()
	 */
	@Test
	public void testSetTitle() {
		Schedule schedule = new Schedule();
		schedule.setTitle("New Title");
		assertEquals("New Title", schedule.getTitle());
	}

	/**
	 * Tests Schedule.setTitle() when null
	 */
	@Test
	public void testSetTitleNull() {
		Schedule schedule = new Schedule();
		assertThrows(IllegalArgumentException.class, () -> schedule.setTitle(null));
	}
	
	/**
	 * Tests Schedule.getScheduleCredits()
	 */
	@Test
	public void testGetScheduleCredits() {
		Schedule schedule = new Schedule();
		Course course1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 150, "MW",
						 1330, 1445);
		Course course2 = new Course("CSC116", "Intro to Java", "001", 3, "amahesh7", 150, "MW",
				 1030, 1130);
		
		schedule.addCourseToSchedule(course1);
		schedule.addCourseToSchedule(course2);
		
		assertEquals(6, schedule.getScheduleCredits());
	}
	
	/**
	 * Tests Schedule.canAdd()
	 */
	@Test
	public void testCanAdd() {
		Schedule schedule = new Schedule();
		Course course1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 150, "MW",
						 1330, 1445);
		assertTrue(schedule.canAdd(course1));
	}
}
