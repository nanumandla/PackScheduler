package edu.ncsu.csc216.pack_scheduler.util;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Tests the ArrayQueue class
 * @author Ashwin Mahesh
 * @author David Giang
 * @author Nirvan Reddy Anumandla
 */
public class ArrayQueueTest {
	/**
	 * Tests ArrayQueue constructor
	 */
	@Test
	public void testArrayQueue() {
		assertDoesNotThrow(() -> new ArrayQueue<String>(10));
		ArrayQueue<String> queue = new ArrayQueue<>(5);
		assertTrue(queue.isEmpty());
	}
	
	/**
	 * Tests the enqueue and dequeue method for ArrayQueue
	 */
	@Test
	public void testEnqueueDequeue() {
		ArrayQueue<String> queue = new ArrayQueue<>(5);
		assertThrows(NoSuchElementException.class, () -> queue.dequeue());
		queue.enqueue("apple");
		assertEquals(1, queue.size());
		queue.enqueue("blueberry");
		queue.enqueue("mango");
		assertEquals(3, queue.size());
		queue.dequeue();
		queue.dequeue();
		assertEquals(1, queue.size());
		queue.dequeue();
		assertTrue(queue.isEmpty());
	}
	
	/**
	 * Tests the ArrayQueue.setCapacity
	 */
	@Test
	public void testSetCapacity() {
		ArrayQueue<String> list = new ArrayQueue<>(5);
		list.enqueue("check");
		list.enqueue("check2");
		list.enqueue("check3");
		list.enqueue("check4");
		list.enqueue("check5");
		assertThrows(IllegalArgumentException.class, () -> list.setCapacity(4));
		assertThrows(IllegalArgumentException.class, () -> list.setCapacity(-1));
	}
	
	
}
