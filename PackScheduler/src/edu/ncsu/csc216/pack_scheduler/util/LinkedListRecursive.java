package edu.ncsu.csc216.pack_scheduler.util;

public class LinkedListRecursive<E> {
	/** Size of the LinkedList */
	private int size;
	/** Front of LinkedList */
	private ListNode front;

	public LinkedListRecursive() {
		this.front = null;
		this.size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public boolean add(E element) {
		if (this.contains(element)) {
			throw new IllegalArgumentException();
		}
		if (this.isEmpty()) {
			front = new ListNode(element, front);
			size++;
			return true;
		}
		front.add(size, element);
		return true;
	}

	public void add(int idx, E element) {
		if (this.contains(element)) {
			throw new IllegalArgumentException();
		}
		if (element == null) {
			throw new IllegalArgumentException();
		}
		if (idx > size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		if(idx == 0) {
			front = new ListNode(element, front);
			size++;
		}
		else {
			front.add(idx, element);
		}
	}

	public E get(int idx) {
		if (idx > size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		return front.get(idx);
	}

	public boolean remove(E element) {
		if(this.isEmpty()) {
			return false;
		}
		if(!this.contains(element)) {
			return false;
		}
		if(front.data.equals(element)) {
			front = front.next;
			size--;
			return true;
		}
		return front.remove(element);
	}

	public E remove(int idx) {
		if (idx > size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		E removedElement;
		if (idx == 0) {
			removedElement = front.data;
			front = front.next;
			size--;
			return removedElement;
		} else {
			return front.remove(idx);
		}

	}

	public E set(int idx, E element) {
		if(idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException();
		}
		
		return front.set(idx, element);
	}

	public boolean contains(E element) {
		if(this.isEmpty()) {
			return false;
		}
		
		return front.contains(element);
	}

	private class ListNode {
		public E data;

		public ListNode next;

		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}

		private void add(int idx, E element) {
			/**
			if (element == null) {
				throw new NullPointerException();
			}
			ListNode newNode = new ListNode(element, null);
			if (idx == 0) {
				newNode.next = front;
				front = newNode;
			} else {
				ListNode current = front;
				for (int i = 0; i < idx - 1; i++) {
					current = current.next;
				}
				newNode.next = current.next;
				current.next = newNode;
			}
			**/
			if (element == null) {
				throw new NullPointerException();
			}
			
			if(idx == 1) {
				next = new ListNode(element, next);
				size++;
			}
			
			else {
				this.next.add(idx - 1, element);
			}
//			if(idx == 1) {
//				ListNode newNode = new ListNode(element, next);
//				ListNode current = this;
//				newNode.next = current.next;
//				current.next = newNode;
//				size++;
//			}
//			else{
//				this.next.add(idx - 1, element);
//			}
		}

		private E get(int idx) {
			if(idx == 0) {
				return this.data;
			}
			return this.next.get(idx - 1);
		}

		private E remove(int idx) {
			E removedElement;
			if(idx == 1) {
				removedElement = this.next.data;
				this.next = this.next.next;
				size--;
				return removedElement;
			}
			
			return this.next.remove(idx - 1);
		}

		private boolean remove(E element) {
			if(element == this.next.data) {
				this.next = this.next.next;
				size--;
				return true;
			}
			
			return this.next.remove(element);
		}

		public E set(int idx, E element) {
			if(idx == 0) {
				E newElement = this.data;
				this.data = element;
				return newElement;
			}
			
			return this.next.set(idx - 1, element);
		}

		private boolean contains(E element) {
			if (this.data.equals(element)) {
				return true;
			}
			
			if(this.next == null) {
				return false;
			}
			
			return this.next.contains(element);
		}

	}

}
