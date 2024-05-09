package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * Class implement LinkedQueue from custom ArrayList. Implements Queue interface
 * @author Ashwin Mahesh
 * @author David Giang
 * @author Nirvan Reddy Anumandla
 * @param <E> object that will be used in Queue object
 */
public class LinkedQueue<E> implements Queue<E> {
	/** LinkedAbstractList queue*/
	private LinkedAbstractList<E> list;
	
	/**
	 * Constructs empty LinkedAbstractList
	 * Sets capacity to size
	 * @param size size of the list
	 */
	public LinkedQueue(int size) {
		list = new LinkedAbstractList<E>(size);
		list.setCapacity(size);
	}
	
	/**
	 * Adds the element to the back of the Linked queue
	 * @param element element to be added
	 * @throws IllegalArgumentException if capacity is equal to size
	 */
	public void enqueue(E element) {
		if(list.capacity == list.size()) {
			throw new IllegalArgumentException();
		}
		
		list.add(list.size(), element);
	}
	
	/**
	 * Removes the element in front of the list
	 * @return the element removed from the front of the list
	 * @throws NoSuchElementException if list is empty
	 */
	public E dequeue() {
		if(list.isEmpty()) {
			throw new NoSuchElementException();
		}
		
		
		return list.remove(0);
	}
	
	/**
	 * Checks whether list is empty
	 * @return true if list is empty else list is false
	 */
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
	/**
	 * Gets the size of the list
	 * @return list.size() size of list
	 */
	public int size() {
		return list.size();
	}
	
	/**
	 * Sets the capacity of the list
	 * @param capacity capcity of the list
	 * @throws IllegalArgumentException if capacity is less than size or is less than 0
	 */
	public void setCapacity(int capacity) {
		if(capacity < list.size() || capacity < 0) {
			throw new IllegalArgumentException();
		}
		list.setCapacity(capacity);
	}
	
	/**
	 * Checks if an element exists in the linked queue
	 * @param element element to be checks
	 * @return true if element is there else it is false
	 */
	public boolean contains(E element) {
		return list.contains(element);
	}
}
