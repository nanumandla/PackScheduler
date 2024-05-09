package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InvalidTransitionExceptionTest {
	
	/**
	 * Tests constructing a non parameterized ConflictException
	 */
	@Test
	void testInvalidTransitionExceptionString() {
		InvalidTransitionException ce = new InvalidTransitionException("Custom exception message");
		assertEquals("Custom exception message", ce.getMessage());
	}
	
	/**
	 * Tests constructing a parameterized ConflictException
	 */
	@Test
	void testInvalidTransitionException() {
		InvalidTransitionException ce = new InvalidTransitionException();
		assertEquals("Invalid FSM Transition.", ce.getMessage());
	}

}
