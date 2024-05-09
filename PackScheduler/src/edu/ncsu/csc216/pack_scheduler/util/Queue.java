package edu.ncsu.csc216.pack_scheduler.util;
/**
 * Represents Queue interface which will be implemented in ArrayQueue and LinkedQueue
 * @author Ashwin Mahesh
 * @author David Giang
 * @author Nirvan Reddy Anumandla
 * @param <E> object that will be used in Queue
 */
public interface Queue<E> {
	/**
	 * Adds element to the back of queue
	 * @param element element to be added
	 */
	void enqueue(E element);
	
	/**
	 * Removes element at front of queue
	 * @return element removed at front of queue
	 */
	E dequeue();
	
	/**
	 * Determines whether queue is empty
	 * @return true if queue is empty else it is false
	 */
	boolean isEmpty();
	
	/**
	 * Gets the size of the queue
	 * @return size of queue
	 */
	int size();
	
	/**
	 * Sets the capacity of the queue
	 * @param capacity capacity of the queue
	 */
	void setCapacity(int capacity);
}
