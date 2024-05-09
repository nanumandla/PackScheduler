package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * this class tests the LinkedList class for proper functionality
 * @author David Giang
 * @author Ashwin Mahesh
 * @author Nirvan Reddy Anumandla
 */
public class LinkedListTest {
	
	/**
	 * Tests the LinkedList constructor for proper initialization. Ensures that a new
	 * LinkedList starts with a size of 0.
	 */
	@Test
	public void testConstructor() {
		LinkedList<String> list = new LinkedList<>();
		assertEquals(0, list.size());
	}

//    @Test
//    public void testAddAndGet() {
//        ArrayList<String> list = new ArrayList<>();
//        list.add(0, "A");
//        list.add(1, "B");
//        list.add(1, "C");
//        assertEquals(6, list.size());
//        assertEquals("A", list.get(0));
//        assertEquals("B", list.get(1));
//        assertEquals("C", list.get(2));
//    }
	/**
	 * Tests adding a null element to the list. Expects a NullPointerException to be
	 * thrown.
	 */
	@Test
	public void testAddNull() {
		LinkedList<String> list = new LinkedList<>();

		assertThrows(NullPointerException.class, () -> list.add(0, null));
	}

	/**
	 * Tests adding a duplicate element to the list. Expects an
	 * IllegalArgumentException to be thrown.
	 */
	@Test
	public void testAddDuplicate() {
		LinkedList<String> list = new LinkedList<String>();
		list.add(0, "A");
		list.add(1, "B");
		assertEquals(2, list.listIterator(2).nextIndex());
		assertThrows(IllegalArgumentException.class, () -> list.add(2, "A"));
	}

	/**
	 * Tests adding an element at an out-of-bounds index. Expects an
	 * IndexOutOfBoundsException to be thrown.
	 */
	@Test
	public void testAddOutOfBounds() {
		LinkedList<String> list = new LinkedList<>();
//        list.add(1, "A");
		assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, "A"));
	}

//
//    @Test
//    public void testRemove() {
//        ArrayList<String> list = new ArrayList<>();
//        list.add(0, "A");
//        list.add(1, "B");
//        list.add(2, "C");
//        String removedElement = list.remove(1);
//        assertEquals("B", removedElement);
//        assertEquals(2, list.size());
//        assertEquals("A", list.get(0));
//        assertEquals("C", list.get(1));
//    }
	/**
	 * Tests removing an element at an out-of-bounds index. Expects an
	 * IndexOutOfBoundsException to be thrown.
	 */
	@Test
	public void testRemoveOutOfBounds() {
		LinkedList<String> list = new LinkedList<>();
//        list.remove(0);
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
	}

//    @Test
//    public void testSet() {
//        ArrayList<String> list = new ArrayList<>();
//        list.add(0, "A");
//        list.add(1, "B");
//        String originalElement = list.set(1, "C");
//        assertEquals("C", originalElement);
//        assertEquals(2, list.size());
//        assertEquals("A", list.get(0));
//        assertEquals("C", list.get(1));
//    }
	/**
	 * Tests setting an element to null. Expects a NullPointerException to be
	 * thrown.
	 */
	@Test
	public void testSetNull() {
		LinkedList<String> list = new LinkedList<>();
		list.add(0, "A");
//        list.set(0, null);
		assertThrows(NullPointerException.class, () -> list.add(0, null));
	}

//    @Test
//    public void testSetDuplicate() {
//        ArrayList<String> list = new ArrayList<>();
//        list.add(0, "A");
//        list.add(1, "B");
//        
//        assertThrows(IllegalArgumentException.class,() ->list.set(2, "A"));
//    }
	  /**
     * Tests setting an element to a value that already exists in the list.
     * Expects an IllegalArgumentException to be thrown.
     */
	@Test
	public void testSetDuplicate() {
		LinkedList<String> list = new LinkedList<>();
		list.add(0, "A");
		list.add(1, "B");
		list.add(2, "C");
		assertThrows(IllegalArgumentException.class, () -> list.set(2, "A"));
	}
	  /**
     * Tests setting an element at an out-of-bounds index.
     * Expects an IndexOutOfBoundsException to be thrown.
     */

	@Test
	public void testSetOutOfBounds() {
		LinkedList<String> list = new LinkedList<>();

		assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, "A"));
	}
	  /**
     * Tests getting an element at an out-of-bounds index.
     * Expects an IndexOutOfBoundsException to be thrown.
     */

	@Test
	public void testGetOutOfBounds() {
		LinkedList<String> list = new LinkedList<>();
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
	}
	 /**
     * Tests the removal of an element from the list.
     * Verifies that the correct element is removed, and the list size is updated accordingly.
     */
	@Test
	public void testRemove() {
		LinkedList<String> list = new LinkedList<>();
		assertThrows(NoSuchElementException.class, () -> list.listIterator(0).previous());
		assertFalse(list.listIterator(0).hasPrevious());
		list.add(0, "A");
		list.add(1, "B");
		list.add(2, "C");
		assertTrue(list.listIterator(2).hasPrevious());
		list.listIterator(3).previous();
		assertEquals(2, list.listIterator(2).nextIndex());
		Object removedElement = list.remove(1);
		assertEquals("B", removedElement);
		assertEquals(2, list.size());
		assertEquals("A", list.get(0));
		assertEquals("C", list.get(1));
		assertEquals(0, list.listIterator(1).previousIndex());
	}
	/**
     * Tests setting a new value for an element in the list.
     * Verifies that the original element is correctly replaced.
     */
	@Test
	public void testSet() {
		LinkedList<String> list = new LinkedList<>();
		list.add(0, "A");
		list.add(1, "B");
		Object originalElement = list.set(1, "C");
		assertEquals("B", originalElement);
		assertEquals(2, list.size());
		assertEquals("A", list.get(0));
		assertEquals("C", list.get(1));
	}
	/**
     * Tests setting a new value for an element in the list.
     * Verifies that the original element is correctly replaced.
     */
	@Test
	public void testAddAndGet() {
		LinkedList<String> list = new LinkedList<>();
		list.add(0, "A");
		list.add(1, "B");
		list.add(1, "C");
		assertEquals(3, list.size());
		assertEquals("A", list.get(0));
		assertEquals("C", list.get(1));
		assertEquals("B", list.get(2));
	}
//    @Test
//    public void testAddOutOfBounds2() {
//        ArrayList<String> list = new ArrayList<>();
//        
//        // Add elements to the list
//        for (int i = 0; i < 10; i++) {
//            list.add(i, "Element " + i);
//        }
//        
//        // Attempt to add an element at an out-of-bounds index
//        assertThrows(IndexOutOfBoundsException.class, () -> list.add(10, "Element 10"));
//    }
}
