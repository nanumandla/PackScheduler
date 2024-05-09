package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;
/*
 * @author weixuan liu
 */
/**
 * ArrayList is a generic dynamic array implementation of the List interface.
 * It supports adding, removing, setting, and getting elements.
 * This implementation prohibits null elements and duplicates.
 *
 * @param <E> the type of elements in this list
 */
public class ArrayList<E> extends AbstractList<E> {
	 /**
     * The array buffer into which the elements of the ArrayList are stored.
     * The capacity of the ArrayList is the length of this array buffer.
     */
	private E[] list;
	 /**
     * The size of the ArrayList (the number of elements it contains).
     */
	private int size;
	  /**
     * The default initial capacity of the ArrayList.
     */
	private static final int INT_SIZE = 10;
	

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
	@SuppressWarnings("unchecked")
	public ArrayList() {
//		if(INT_SIZE <= 0) {
//			throw new IllegalArgumentException("Invaild capacity.");
//		}
		list = (E[]) new Object[INT_SIZE];
		size = 0;
//		this.size=size;
	}
	/**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent
     * elements to the right (adds one to their indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws NullPointerException if the specified element is null
     * @throws IllegalArgumentException if the list already contains the specified element
     * @throws IndexOutOfBoundsException if the index is out of range
     */

	@Override
	public void add(int index, E element) {
	    if (element == null) {
	        throw new NullPointerException("Element cannot be null");
	    }
	    if (contains(element)) {
	        throw new IllegalArgumentException("Duplicate element not allowed");
	    }
	    if (index < 0 || index > size) {
	        throw new IndexOutOfBoundsException("Index out of range");
	    }
	    if (size == list.length) {
	        growArray();
	    }
	    for (int i = size - 1; i >= index; i--) {
	        list[i + 1] = list[i];
	    }
	    list[index] = element;
	    size++;
	}

	
	

//	@SuppressWarnings("unchecked")
//	private void growArray() {
//		E[] list2 = (E[]) new Object[INT_SIZE * 2];
//		for (int i = 0; i < size; i++) {
//			list2[i] = list[i];
//		}
//		list2 = list;
//	}

    /**
     * Doubles the size of the array when capacity is reached.
     */
	@SuppressWarnings("unchecked")
	private void growArray() {
	    E[] list2 = (E[]) new Object[list.length * 2];
	    for (int i = 0; i < size; i++) {
	        list2[i] = list[i];
	    }
	    list = list2;
	}
	/**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invaild capacity.");
		}

		return list[index];
	}
	  /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invaild capacity.");
		}
		E removedElement = list[index];
		for (int i = index; i < size - 1; i++) {
			list[i] = list[i + 1];
		}

		list[size - 1] = null;
		size--;
		return removedElement;

	}

	//

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws NullPointerException if the specified element is null
     * @throws IllegalArgumentException if the list already contains the specified element (and it's not the one being replaced)
     * @throws IndexOutOfBoundsException if the index is out of range
     */
	@Override
	public E set(int index, E element) {
		if (element == null) {
			throw new NullPointerException("Element cannot be null");
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of range");
		}
		E originalElement = list[index];
		if (contains(element) && !element.equals(originalElement)) {
			throw new IllegalArgumentException("Duplicate element not allowed");
		}
		list[index] = element;
		return originalElement;
	}
	 /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

}
