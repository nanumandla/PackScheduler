/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * this class implements a stack using a custom array list. 
 * @author David Giang
 * @author Ashwin Mahesh
 * @author Nirvan Reddy Anumandla
 * @param <E> is the generic object that will be added to the stack object
 * 
 */
public class ArrayStack<E> implements Stack<E> {
	/** array list object that stores the stack **/
	private ArrayList<E> list;
	/** integer that stores the capacity of the stack**/
	private int capacity;
	
	/**
	 * constructor for the stack, takes in an integer that will give the capacity of the stack
	 * @param size is the capacity of the stack to be set
	 */
	public ArrayStack(int size) {
		list = new ArrayList<E>();
		setCapacity(size);
	}
	
	/**
	 * pushes the element into the stack
	 * @param element is the element to be pushed
	 * @throws IllegalArgumentException if the size is equal to capacity
	 */
	public void push(E element) {
		if(list.size() == capacity) {
			throw new IllegalArgumentException();
		}
		list.add(list.size(), element);
	}
	
	/**
	 * pops an element from the stack and returns the popped element
	 * @return the popped element
	 * @throws EmptyStackException if the stack is empty
	 */
	public E pop() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		E removed = list.get(list.size() - 1);
		list.remove(list.size() - 1);
		return removed;
	}
	
	/**
	 * checks if the stack is empty
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
	/**
	 * returns the size of the stack
	 * @return the size of the stack 
	 */
	public int size() {
		return list.size();
	}
	
	/**
	 * sets the capacity of the stack
	 * @param capacity is the capacity we wish to set for the stack
	 * @throws IllegalArgumentException if capacity less than size or capacity less than zero
	 */
	public void setCapacity(int capacity) {
		if(capacity < list.size() || capacity < 0) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}
	
	
}
