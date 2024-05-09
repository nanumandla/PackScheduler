package edu.ncsu.csc216.pack_scheduler.util;

/**
 * public interface that stores the methods to be implemented in the ArrayStack and LinkedStack classes
 * @param <E> is the generic object we will be inserting into the stacks
 */
public interface Stack<E> {
	/** 
	 * this method will push an object into the stack
	 * @param element is the element we wish to push into the stack
	 */
	void push(E element);
	/** 
	 * this method will pop an object from the stack 
	 * @return the object we pop from the end of the stack
	 */
	E pop();
	/** 
	 * this method checks for if the stack is empty with zero elements
	 * @return true if empty, false otherwise
	 */
	boolean isEmpty();
	/** 
	 * this method returns the size of the stack
	 * @return the integer representation of the size of the stack
	 */
	int size();
	/** 
	 * this method sets the capacity of the stack
	 * @param capacity is the capacity we wish to set for the stack
	 */
	void setCapacity(int capacity);
}
