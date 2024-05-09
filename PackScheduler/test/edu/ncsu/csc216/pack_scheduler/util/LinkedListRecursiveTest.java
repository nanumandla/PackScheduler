package edu.ncsu.csc216.pack_scheduler.util;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LinkedListRecursiveTest {
	
	@Test
	public void testLinkedListRecursive() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void testAdd() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		list.add("apple");
		list.add("watermelon");
		list.add("strawberry");
		
		assertEquals(3, list.size());
		assertTrue(list.contains("apple"));
		assertTrue(list.contains("watermelon"));
		assertTrue(list.contains("strawberry"));
		
	}
	
	@Test
	public void testIndexAdd() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		
		list.add(0, "apple");
		assertEquals(1, list.size());
		
		list.add(0, "blueberry");
		assertEquals(2, list.size());
		
		list.add(2, "cherry");
		assertEquals(3, list.size());
		
		list.add(1, "orange");
		assertEquals(4, list.size());
		
		list.add(4, "pineapple");
		assertEquals(5, list.size());
		
		assertEquals("blueberry", list.get(0));
		assertEquals("orange", list.get(1));
		assertEquals("apple", list.get(2));
		assertEquals("cherry", list.get(3));
		assertEquals("pineapple", list.get(4));
		
	}
	
	@Test
	public void testRemove() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		
		list.add("apple");
		list.add("watermelon");
		list.add("strawberry");
		
		assertEquals(3, list.size());
		
		list.remove("apple");
		assertEquals(2, list.size());
		assertFalse(list.contains("apple"));
		
		list.remove("watermelon");
		assertEquals(1, list.size());
		assertFalse(list.contains("watermelon"));
	}
	
	@Test
	public void testRemoveIndex() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		
		list.add("apple");
		list.add("watermelon");
		list.add("strawberry");
		list.add("cherry");
		list.add("orange");
		
		assertEquals(5, list.size());
		
		list.remove(3);
		assertFalse(list.contains("cherry"));
		assertEquals(4, list.size());
		
		list.remove(0);
		assertFalse(list.contains("apple"));
		assertEquals(3, list.size());
		
		list.remove(0);
		assertFalse(list.contains("watermelon"));
		assertEquals(2, list.size());
		
	}
	
	@Test
	public void testSet() {
		LinkedListRecursive<String> list = new LinkedListRecursive<String>();
		
		list.add("apple");
		list.add("watermelon");
		list.add("strawberry");
		list.add("cherry");
		list.add("orange");
		
		assertEquals(5, list.size());
		
		list.set(0, "mango");
		list.set(2, "coconut");
		list.set(4, "honeydew");
		
		assertEquals("mango", list.get(0));
		assertEquals("coconut", list.get(2));
		assertEquals("honeydew", list.get(4));
	}
}
