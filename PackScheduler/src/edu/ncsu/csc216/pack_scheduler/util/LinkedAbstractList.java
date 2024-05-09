package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * This class represents the LinkedList and its functions in PackScheduler This
 * class can add, remove, get, or set elements
 * 
 * @param <E> the elements in the LinkedList
 * @author Ashwin Mahesh
 * @author Taylor Liu
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	/** Size of current LinkedList */
	int size;
	/** Maximum number of elements in the list */
	int capacity;
	/** The front of the LinkedList */
	ListNode front;
	/** The back of the LinkedList */
    ListNode back;

	/**
	 * Constructs a LinkedAbstractList with new capacity
	 * 
	 * @param capacity maximum number of elements in the list
	 * @throws IllegalArgumentException if the capacity is smaller than the capacity already set
	 */
	public LinkedAbstractList(int capacity) {
		if (capacity >= 0) {
			this.capacity = capacity;
		} else if (this.capacity > capacity) {
			throw new IllegalArgumentException("capacity can't be less than 0");
		} else {
			throw new IllegalArgumentException("capacity can't be less than 0");
		}
		this.size = 0;
		this.front = null;
		this.back = null;
	}

	/**
	 * Adds an element at an index in the LinkedList
	 * 
	 * @param index   the index of an element in LinkedList
	 * @param element the element in a LinkedList
	 * @throws NullPointerException      if element is null
	 * @throws IllegalArgumentException  if list is in maximum capacity of element
	 *                                   already exists in list
	 * @throws IndexOutOfBoundsException if index is invalid
	 */
	@Override
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException("Element cannot be null.");
		}
		if (contains(element)) {
			throw new IllegalArgumentException("Element already exists in the list.");
		}
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		if (size == capacity) {
			throw new IllegalArgumentException("List is at maximum capacity.");
		}

		ListNode newNode = new ListNode(element);
		if (index == 0) {
			newNode.next = front;
			front = newNode;
		} else {
			ListNode current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			newNode.next = current.next;
			current.next = newNode;
			if (index == size) {
                back = newNode;
			}
		}
		size++;
	}

	/**
	 * Removes element in a LinkedList
	 * 
	 * @param index index in LinkedList
	 * @return removedElement the element that was removed
	 * @throws IndexOutOfBoundsException if index is invalid
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}

		E removedElement;
		if (index == 0) {
			removedElement = front.data;
			front = front.next;
			if (size == 1) {
                back = null;
            }	
		} else {
			ListNode current = front;
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			removedElement = current.next.data;
			current.next = current.next.next;
			if (index == size - 1) {
                back = current;
            }
		}
		size--;
		return removedElement;
	}

	/**
	 * Returns the size of LinkedList
	 * 
	 * @return size current size of the LinkedList
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Gets an element from a LinkedList
	 * 
	 * @return current.data the data that will be extracted from LinkedList
	 * @throws IndexOutOfBoundsException if index is invalid
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}

		ListNode current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.data;
	}

	/**
	 * Sets an element in a LinkedList
	 * 
	 * @param index   the index of LinkedList
	 * @param element element in LinkedList
	 * @return overwrittenElement element in LinkedList
	 * @throws NullPointerException      if element is null
	 * @throws IndexOutOfBoundsException if index is invalid
	 * @throws IllegalArgumentException  if element already exists in the list
	 * 
	 */
	public E set(int index, E element) {
		if (element == null) {
			throw new NullPointerException("Element cannot be null.");
		}
		if (contains(element)) {
			throw new IllegalArgumentException("Element already exists in the list.");
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}

		ListNode current = front;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		E overwrittenElement = current.data;
		current.data = element;
		return overwrittenElement;
	}
	
	/**
	 * Sets the capacity in the LinkedAbstractList
	 * @param capacity capacity of the list
	 * @throws IllegalArgumentException if capacity is less than 0
	 * or current size
	 */
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < size) {
			throw new IllegalArgumentException();
		}
		
		this.capacity = capacity;
	}
	
	/**
	 * private class that represents the nodes that will be added to the LinkedAbstractList. the nodes also hold object data. 
	 * @author David Giang
	 * @author Ashwin Mahesh
	 * @author Nirvan Reddy Anumandla
	 */
	private class ListNode {
		/** The data stored in the node */
		E data;
		/** The next node in LinkedList */
		ListNode next;

		/**
		 * Constructs a new ListNode with data
		 * 
		 * @param data the data stored in the node
		 */
		ListNode(E data) {
			this(data, null);
		}

		/**
		 * Constructs a new ListNode with data and next node
		 * 
		 * @param data the data stored in the node
		 * @param next the next node in LinkedList
		 */
		ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
