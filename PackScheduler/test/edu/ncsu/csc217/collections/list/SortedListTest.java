package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * SortedListTest testing check that library methods is correctly implemented into the project
 * checking add(), remove(), and get() methods
 * 
 * @author Marco Torres
 * @author Ashwin Mahesh
 * @author Jack Farrell
 */
public class SortedListTest {
	
	/**
	 * Tests if programs sorts items in a list
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		
		list.add("apple");
		list.add("banana");
		list.add("pear");
		list.add("orange");
		list.add("pineapple");
		list.add("guava");
		list.add("strawberry");
		list.add("blueberry");
		list.add("avacado");
		list.add("tomato");
		list.add("watermelon");
		
		
		//TODO Test that the list grows by adding at least 11 elements
		//Remember the list's initial capacity is 10
		assertEquals(11, list.size());
		
	}
	
	/**
	 * Tests if programs sorts items in a list when items are added and checks for duplicates
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		//TODO Test adding to the front, middle and back of the list
		list.add("apple");
		list.add("apricot");
		list.add("watermelon");
		//TODO Test adding a null element
		list.add("");
		
		//TODO Test adding a duplicate element
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> list.add("apple"));
		assertEquals("Element already in list.", e1.getMessage());
		
	}
	
	/**
	 * Tests the edge and error cases for get()
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//TODO Test getting an element from an empty list
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
		
		
		//TODO Add some elements to the list
		list.add("apple");
		list.add("tomato");
		list.add("avocado");
		
		//TODO Test getting an element at an index < 0
		try {
			list.get(-1);
		} catch(IndexOutOfBoundsException e) {
		//	
		}
		//TODO Test getting an element at size
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()));
		
	}
	
	/**
	 * Tests if program sorts the items when items are removed
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test removing from an empty list
		 assertThrows(IndexOutOfBoundsException.class, () -> {
	            list.remove(0);
	        });

		
		//TODO Add some elements to the list - at least 4
		list.add("apple");
		list.add("bananas");
		list.add("berry");
		list.add("orange");
		
		//TODO Test removing an element at an index < 0
		 assertThrows(IndexOutOfBoundsException.class, () -> {
	            list.remove(-1);
	        });
		
		//TODO Test removing an element at size
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
		
		//TODO Test removing a middle element
		assertEquals("bananas", list.remove(1));
        assertEquals(3, list.size());
        assertEquals("apple", list.get(0));
        assertEquals("berry", list.get(1));
        assertEquals("orange", list.get(2));
		
		//TODO Test removing the last element
        assertEquals("orange", list.remove(2));
        assertEquals(2, list.size());
        assertEquals("apple", list.get(0));
        assertEquals("berry", list.get(1));
		//TODO Test removing the first element
        assertEquals("apple", list.remove(0));
        assertEquals(1, list.size());
        assertEquals("berry", list.get(0));
		//TODO Test removing the last element
        
	}
	
	/**
	 * Tests if programs gets elements in a sorted list
	 * Tests if program gets index of items not in list and null items
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test indexOf on an empty list
		assertEquals(-1, list.indexOf(""));
		
		//TODO Add some elements
		list.add("apple");
		list.add("bananas");
		list.add("berry");
		list.add("orange");
		
		//TODO Test various calls to indexOf for elements in the list
		//and not in the list
		assertEquals(-1, list.indexOf("stuff"));
		assertEquals(0, list.indexOf("apple"));
		assertEquals(-1, list.indexOf("nuts"));
		assertEquals(2, list.indexOf("berry"));
		
		//TODO Test checking the index of null
		assertThrows(NullPointerException.class, () -> list.indexOf(null));		
	}
	
	/**
	 * Tests if list is cleared and is empty
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//TODO Add some elements
		list.add("apple");
		list.add("bananas");
		list.add("berry");
		list.add("orange");
		
		//TODO Clear the list
		list.clear();
		
		//TODO Test that the list is empty
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests if new list is empty
	 * Tests that if items are added, list is not empty anymore
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test that the list starts empty
		assertTrue(list.isEmpty());
		
		//TODO Add at least one element
		list.add("apple");
		
		//TODO Check that the list is no longer empty
		 assertFalse(list.isEmpty());

	}
	
	/**
	 * Tests if program checks if list contains an element
	 * Tests if list contains an element that is not on the list
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//TODO Test the empty list case
		assertFalse(list.contains(""));
		
		//TODO Add some elements
		list.add("apple");
		list.add("bananas");
		list.add("berry");
		list.add("orange");
		
		//TODO Test some true and false cases
		assertTrue(list.contains("apple"));
		assertFalse(list.contains("watermelon"));
		assertTrue(list.contains("bananas"));
		assertFalse(list.contains("nuts"));
		
	}
	
	/**
	 * Tests Student.toEquals() 
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		list1.add("apple");
		list1.add("bananas");
		list1.add("berry");
		list1.add("orange");
		
		list2.add("apple");
		list2.add("bananas");
		list2.add("berry");
		list2.add("orange");
		
		list3.add("frieChicken");
		list3.add("nuts");
		
		//TODO Test for equality and non-equality
		assertEquals(list1, list2);
		assertNotEquals(list1, list3);
		assertNotEquals(list3, list2);
	}
	
	/**
	 * Tests Student.hashCode()
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		//TODO Make two lists the same and one list different
		list1.add("apple");
		list1.add("bananas");
		list1.add("berry");
		list1.add("orange");
		
		list2.add("apple");
		list2.add("bananas");
		list2.add("berry");
		list2.add("orange");
		
		list3.add("frieChicken");
		list3.add("nuts");
		
		//TODO Test for the same and different hashCodes
		int hashcode1 = list1.hashCode();
		int hashcode2 = list2.hashCode();
		int hashcode3 = list3.hashCode();
		
		assertEquals(hashcode1, hashcode2);
		assertNotEquals(hashcode2, hashcode3);
		assertNotEquals(hashcode3, hashcode1);

	}

}
 