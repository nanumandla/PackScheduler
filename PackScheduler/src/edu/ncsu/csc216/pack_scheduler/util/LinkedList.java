package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

//import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList.ListNode;

/**
 * Custom LinkedList implementation that is doubled, with front and back nodes. also has an interator
 * @param <E> is the generic object of the LinkedList
 * @author David Giang
 * @author Ashwin Mahesh
 * @author Nirvan Reddy Anumandla
 */
public class LinkedList<E>  extends AbstractSequentialList<E> {
	
	/** Front of the LinkedList */
	private ListNode front;
	
	/** Back of the LinkedList */
	private ListNode back;
	
	/** Size of the LinkedList */
	private int size;
	
	/**
	 * Constructs a LinkedList
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.prev = front;
		size = 0;		
	}
	
	/**
	 * returns the size of the linkedlist
	 * @return int representation of the size
	 */
	public int size() {
		return size;
	}
	
	
	/**
	 * adds the object at a specified index
	 * @param index is the index we will be adding at
	 * @param element is the element we will be adding at the index
	 * @throws IllegalArgumentException if the element is already in the linked list
	 */
	@Override
	public void add(int index, E element) {
		if(contains(element)) {
			throw new IllegalArgumentException();
		}
		super.add(index, element);
	}
	
	/**
	 * sets the node at the specified index to hold the data element
	 * @param index is the index we are changing
	 * @param element is the element we will be changing at the node a the specified index
	 */
	@Override
	public E set(int index, E element) {
		if(this.contains(element)) {
			throw new IllegalArgumentException();
		}
		return super.set(index, element);
	}

	/**
	 * method that returns a listIterator that is iterated to the inputted index
	 * @param index is the index we want to iterate to
	 * @return a listIterator that is iterated to the index we specify
	 */
	public ListIterator<E> listIterator(int index){
		return new LinkedListIterator(index);
	}
	
	/**
	 * private class that manages the nodes inside of the LinkedList
	 * @author David Giang
	 * @author Ashwin Mahesh
	 * @author Nirvan Reddy Anumandla
	 */
	private class ListNode {
		/** stores the object data inside of the linked node */
		public E data;
		
		/** stores the next node in the linkedlist */
		public ListNode next;
		
		/** stores the previous node in the linked list */
		public ListNode prev;
		
		/**
		 * constructor for the ListNode class. This class only accepts an object for data to be stored inside of the list node. 
		 * @param data is the data we will store inside of the ListNode
		 */
		public ListNode(E data) {
			this.data = data;
		}
		
		/**
		 * constructor for the list node class which accepts data to store, the previous node of this node, and the next node after this node
		 * @param data is the data we will store inside of the list node
		 * @param prev is the node before this node inside of the linked lsit
		 * @param next is the node after this node inside of the linked list 
		 */
		public ListNode(E data, ListNode prev, ListNode next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
	}
	/**
	 * this class interates through the LinkedList. It also provides functionality for the linked list. 
	 * @author David Giang
	 * @author Ashwin Mahesh
	 * @author Nirvan Reddy Anumandla
	 */
	private class LinkedListIterator implements ListIterator<E> {
		/** stores the instance of the list node that is called when next is called */
		public ListNode next = new ListNode(null);
		/** stores the instance of the listnode that is called when previous is called */
		public ListNode previous = new ListNode(null);
		/** stores the instance of the list node that was just called from either previous or next */
		private ListNode lastRetrieved;
		/** stores the index of the list node retrieved when previous is called */
		private int previousIndex = -1;
		/** stores the index of the list node retrieved when next is called */
		private int nextIndex = 0;
		
		public LinkedListIterator(int index) { 
			if(index > size || index < 0) {
				throw new IndexOutOfBoundsException();
			}
			
			//Iterate until you hit the index you need
			ListNode current = front;
			for(int i = 0; i < index; i++) {
				current = current.next;
			}
			
			// set previous and next based on the index you iterate to. your iterator
			// will be between previous and next so you set previous to current and next to current.next
			previous = current;
			next = current.next;
			nextIndex = index;
			previousIndex = index  - 1;
			
			
			lastRetrieved = null;
		}
		
		public boolean hasNext() {
			boolean isNext = true;
			
			if(next.data == null) {
				isNext = false; 
			}
		
			return isNext;
			
		}
		
		public boolean hasPrevious() {
			boolean isPrevious = true;
			
			if(previous.data == null) {
				isPrevious = false;
			}
			
			return isPrevious;
		}
		
		public int nextIndex() {
			return nextIndex;
		}
		
		public int previousIndex() {
			return previousIndex;
		}
		
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			// sets the lastRetrieved to be the next node
			lastRetrieved = next;
			//previous = lastRetrieved;
			next = next.next;
			previous = next;
			//previous = next;
			nextIndex++;
			previousIndex++;
			return lastRetrieved.data;
		}
		
		public E previous() {
			if(!hasPrevious()) {
				throw new NoSuchElementException();
			}
			
			lastRetrieved = previous;
			previous = previous.prev;
			next = previous;
			//next = previous;
			nextIndex--;
			previousIndex--;
			return lastRetrieved.data;
		}
		
		@Override
		public void add(E element) {
			if(element == null) {
				throw new NullPointerException("Element is null");
			}
			ListNode newNode = new ListNode(element, previous, next);
			previous.next = newNode;
			next.prev = newNode;
			
			lastRetrieved = null;
			size++;
			next = newNode;
			nextIndex++;
		}
		
		/**
		 * sets last retrieved to the element parameter
		 * @param element is the element we wish to set lastRetrieved to
		 * @throws IllegalStateException if lastRetrieved is null
		 * @throws NullPointerException if the element parameter is null
		 */
		@Override
		public void set(E element){
			if(lastRetrieved == null) {
				throw new IllegalStateException();
			}
			if(element == null) {
				throw new NullPointerException();
			}
			if(lastRetrieved.data.equals(previous.data)) {
				ListNode current = front;
				for (int i = 0; i < previousIndex; i++) {
					current = current.next;
				}
				current.data = element;
			}
			else {
				ListNode current = front;
				for (int i = 0; i < nextIndex; i++) {
					current = current.next;
				}
				current.data = element;
			}
		}
		
		/**
		 * removes the lastRetrieved element
		 * @throws IllegalStateException if the lastRetrieved node is null
		 */
		@Override
		public void remove() {
			if(lastRetrieved == null) {
				throw new IllegalStateException();
			}
			int index;
			if(lastRetrieved.data.equals(previous.data)) {
				index = previousIndex;
			}
			else {
				index = nextIndex;
			}
			if (index == 0) {
				front = front.next;
				if (size == 1) {
	                back = null;
	            }	
			} else {
				ListNode current = front;
				for (int i = 0; i < index - 1; i++) {
					current = current.next;
				}
				current.next = current.next.next;
				if (index == size - 1) {
	                back = current;
	            }
			}
			size--;
		}
		
	}
}
