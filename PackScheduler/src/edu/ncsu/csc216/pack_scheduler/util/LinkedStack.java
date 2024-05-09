package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * is the linked list implementation of the stack. 
 * @param <E> is the object we will be inserting into the stack
 */
public class LinkedStack<E> implements Stack<E> {
	/** this object stores the linkedlist we will be using for the stack **/
	private LinkedAbstractList<E> list;
	
	/**
	 * linkedstack constructor, reads in an integer to use for the capacity
	 * @param size is the capacity of the linked list stack we wish to set
	 */
	public LinkedStack(int size) {
		list = new LinkedAbstractList<E>(size);
		list.setCapacity(size);
	}
	
	/**
	 * pushes an element to the stack
 	 * @param element is the element we wish to push into the stack
 	 * @throws IllegalArgumentException if the capacity of the stack has been reached
	 */
	public void push(E element) {
		if(list.capacity == list.size()) {
			throw new IllegalArgumentException();
		}
		list.add(element);
	}
	
	/**
	 * pops an element from the stack and returns the element just popped
	 * @return the object from the stack that was just popped
	 * @throws EmptyStackException if the stack is empty
	 */
	public E pop() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		return list.remove(list.size() - 1);
	}
	
	/**
	 * checks if the stack is empty with zero elements
	 * @return true if the stack is empty, false otherwise
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
	 * @throws IllegalArgumentException if the capacity is smaller than the list size or if capacity is less than zero
	 */
	public void setCapacity(int capacity) {
		if(capacity < list.size() || capacity < 0) {
			throw new IllegalArgumentException();
		}
		list.setCapacity(capacity);
	}
}
