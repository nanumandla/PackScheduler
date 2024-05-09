package edu.ncsu.csc216.pack_scheduler.util;
import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

/**
 * this class tests the ArrayStack class for proper functionality. 
 * @author David Giang
 * @author Ashwin Mahesh
 * @author Nirvan Reddy Anumandla
 */
public class ArrayStackTest {
	
	/**
	 * tests the constructor for the ArrayStack
	 */
	@Test
	public void testConstructor() {
		assertDoesNotThrow(() -> new ArrayStack<String>(10));
		ArrayStack<String> stack = new ArrayStack<>(5);
		assertTrue(stack.isEmpty());
	}
	
	/**
	 * tests the push method for the ArrayStack
	 */
	@Test
	public void testPush() {
		ArrayStack<String> stack = new ArrayStack<>(5);
		stack.push("check");
		assertThrows(IllegalArgumentException.class, () -> stack.push("check"));
		assertThrows(NullPointerException.class, () -> stack.push(null));
		stack.push("check2");
		stack.push("check3");
		stack.push("check4");
		stack.push("check5");
		assertThrows(IllegalArgumentException.class, () -> stack.push("check6"));
		assertFalse(stack.isEmpty());
		assertEquals(5, stack.size());
	}
	
	/**
	 * tests the setCapacity method for the ArrayStack
	 */
	@Test
	public void testSetCapacity() {
		ArrayStack<String> stack = new ArrayStack<>(5);
		stack.push("check");
		stack.push("check2");
		stack.push("check3");
		stack.push("check4");
		stack.push("check5");
		assertThrows(IllegalArgumentException.class, () -> stack.setCapacity(4));
		assertThrows(IllegalArgumentException.class, () -> stack.setCapacity(-1));
	}
	
	/**
	 * tests the pop method for the ArrayStack
	 */
	@Test
	public void testPop() {
		ArrayStack<String> stack = new ArrayStack<>(5);
		assertThrows(EmptyStackException.class, () -> stack.pop());
		stack.push("check");
		stack.push("check2");
		stack.push("check3");
		stack.push("check4");
		stack.push("check5");
		assertEquals(0, "check5".compareTo(stack.pop()));
	}
}
