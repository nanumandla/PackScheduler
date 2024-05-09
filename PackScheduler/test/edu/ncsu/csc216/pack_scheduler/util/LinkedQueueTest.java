package edu.ncsu.csc216.pack_scheduler.util;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 * Tests LinkedQueue method
 * @author Ashwin Mahesh
 * @author Nirvan Reddy Anumandla
 * @author David Giang
 */
public class LinkedQueueTest {
	/**
	 * Tests the constructor for LinkedQueue
	 */
	@Test
	public void testLinkedQueue() {
		assertDoesNotThrow(() -> new LinkedQueue<String>(10));
		LinkedQueue<String> queue = new LinkedQueue<>(5);
		assertTrue(queue.isEmpty());
	}
	
	/**
	 * Tests Enqueue and Dequeue methods in LinkedQueue
	 */
	@Test
	public void testEnqueueDequeue(){
		LinkedQueue<String> queue = new LinkedQueue<>(5);
		assertThrows(NoSuchElementException.class, () -> queue.dequeue());
		queue.enqueue("apple");
		assertEquals(1, queue.size());
		queue.enqueue("blueberry");
		queue.enqueue("mango");
		assertEquals(3, queue.size());
		assertFalse(queue.isEmpty());
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
		LinkedQueue<String> list = new LinkedQueue<>(5);
		list.enqueue("check");
		list.enqueue("check2");
		list.enqueue("check3");
		list.enqueue("check4");
		list.enqueue("check5");
		assertThrows(IllegalArgumentException.class, () -> list.setCapacity(4));
		assertThrows(IllegalArgumentException.class, () -> list.setCapacity(-1));
	}
}
