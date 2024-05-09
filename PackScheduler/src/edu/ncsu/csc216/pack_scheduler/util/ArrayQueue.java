package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;
/**
 * Class implement ArrayQueue from custom ArrayList. Implements Queue interface
 * @author Ashwin Mahesh
 * @author David Giang
 * @author Nirvan Reddy Anumandla
 * @param <E> object that will be used in Queue object
 */
public class ArrayQueue<E> implements Queue<E> {
	/** ArrayList queue*/
	private ArrayList<E> list;
	
	/** Capacity of the queue */
	private int capacity;
	
	/**
	 * Creates an empty queue and sets
	 * capacity to size of queue
	 * @param size size of queue
	 */
	public ArrayQueue(int size) {
		list = new ArrayList<E>();
		setCapacity(size);
	}
	
	/**
	 * Adds an element to the back of the queue
	 * @param element element to be added in the queue
	 * @throws IllegalArgumentException if there is no capacity in queue
	 */
	public void enqueue(E element) {
		if(list.size() == capacity) {
			throw new IllegalArgumentException();
		}
		
		list.add(list.size(), element);
	}
	
	/**
	 * Removes an element at the front of the queue
	 * @return the element at front of queue
	 * @throws NoSuchElementException if queue is empty
	 */
	public E dequeue() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		return list.remove(0);
		
	}
	
	/**
	 * Checks whether queue is empty
	 * @return true is queue is empty, else it is false
	 */
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
	/**
	 * Returns the size of the queue
	 * @return list.size() size of queue
	 */
	public int size() {
		return list.size();
	}
	
	/**
	 * Sets the capacity of the queue
	 * @param capacity capacity of the queue
	 * @throws IllegalArgumentException if capacity is less than 0
	 * or is less than the size of the queue
	 */
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

}
