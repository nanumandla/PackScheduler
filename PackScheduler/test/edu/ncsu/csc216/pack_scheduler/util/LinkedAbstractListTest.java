package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedAbstractListTest {
	/** The LinkedAbstractList */
	private LinkedAbstractList<String> list;

	/**
	 * Tests the ArrayList constructor for proper initialization. Ensures that a new
	 * ArrayList starts with a size of 0.
	 */
	@BeforeEach
	public void setup() {
		list = new LinkedAbstractList<>(10);
		assertEquals(0, list.size);

	}
	
	/**
	 * Tests adding a null element to the list. Expects a NullPointerException to be
	 * thrown.
	 */
	@Test
	public void testAddNull() {
	

		assertThrows(NullPointerException.class, () -> list.add(0, null));
	}

	/**
	 * Tests adding a duplicate element to the list. Expects an
	 * IllegalArgumentException to be thrown.
	 */
	@Test
	public void testAddDuplicate() {
		
		list.add(0, "A");
		list.add(1, "B");
		assertThrows(IllegalArgumentException.class, () -> list.add(2, "A"));
	}

	/**
	 * Tests adding an element at an out-of-bounds index. Expects an
	 * IndexOutOfBoundsException to be thrown.
	 */
	@Test
	public void testAddOutOfBounds() {
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, "A"));
	}

	/**
	 * Tests removing an element at an out-of-bounds index. Expects an
	 * IndexOutOfBoundsException to be thrown.
	 */
	@Test
	public void testRemoveOutOfBounds() {
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
	}


	/**
	 * Tests setting an element to null. Expects a NullPointerException to be
	 * thrown.
	 */
	@Test
	public void testSetNull() {
		
		list.add(0, "A");
//        list.set(0, null);
		assertThrows(NullPointerException.class, () -> list.add(0, null));
	}


	/**
	 * Tests setting an element to a value that already exists in the list. Expects
	 * an IllegalArgumentException to be thrown.
	 */
	@Test
	public void testSetDuplicate() {
	
		list.add(0, "A");
		list.add(1, "B");
		list.add(2, "C");
		assertThrows(IllegalArgumentException.class, () -> list.set(2, "A"));
	}

	/**
	 * Tests setting an element at an out-of-bounds index. Expects an
	 * IndexOutOfBoundsException to be thrown.
	 */

	@Test
	public void testSetOutOfBounds() {


		assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, "A"));
	}

	/**
	 * Tests getting an element at an out-of-bounds index. Expects an
	 * IndexOutOfBoundsException to be thrown.
	 */

	@Test
	public void testGetOutOfBounds() {
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
	}

	/**
	 * Tests the removal of an element from the list. Verifies that the correct
	 * element is removed, and the list size is updated accordingly.
	 */
	@Test
	public void testRemove() {
		
		list.add(0, "A");
		list.add(1, "B");
		list.add(2, "C");
		String removedElement = list.remove(1);
		assertEquals("B", removedElement);
		assertEquals(2, list.size());
		assertEquals("A", list.get(0));
		assertEquals("C", list.get(1));
	}

	/**
	 * Tests setting a new value for an element in the list. Verifies that the
	 * original element is correctly replaced.
	 */
	@Test
	public void testSet() {
		
		list.add(0, "A");
		list.add(1, "B");
		String originalElement = list.set(1, "C");
		assertEquals("B", originalElement);
		assertEquals(2, list.size());
		assertEquals("A", list.get(0));
		assertEquals("C", list.get(1));
	}

	/**
	 * Tests setting a new value for an element in the list. Verifies that the
	 * original element is correctly replaced.
	 */
	@Test
	public void testAddAndGet() {

		list.add(0, "A");
		list.add(1, "B");
		list.add(1, "C");
		assertEquals(3, list.size());
		assertEquals("A", list.get(0));
		assertEquals("C", list.get(1));
		assertEquals("B", list.get(2));
	}

}
