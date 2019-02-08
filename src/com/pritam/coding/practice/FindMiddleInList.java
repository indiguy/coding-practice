/**
 * 
 */
package com.pritam.coding.practice;

/**
 * Find middle in the linked list
 * 
 * @author pribiswas
 *
 */
public class FindMiddleInList<T> {

	private Node<T> head;
	private Node<T> tail;

	/**
	 * Add new node at the end of the list
	 * 
	 * @param data
	 */
	public void add(T data) {
		if (head == null) {
			Node<T> node = new Node<T>(data);
			head = node;
			head.setNext(null);
			tail = head;
		} else {
			Node<T> node = new Node<T>(data);
			tail.setNext(node);
			tail = node;
		}
	}

	/**
	 * Find the middle element of the list
	 * 
	 * @return
	 */
	public T findMiddle() {
		Node<T> slowPtr = head;
		Node<T> fastPtr = slowPtr;

		while (fastPtr != null && fastPtr.getNext() != null) {
			slowPtr = slowPtr.getNext();
			fastPtr = fastPtr.getNext().getNext();
		}
		return slowPtr.getData();
	}

	private static class Node<T> {
		private final T data;
		private Node<T> next;

		/**
		 * Constructor
		 * 
		 * @param data
		 */
		Node(T data) {
			this.data = data;
		}

		/**
		 * @return the data
		 */
		public T getData() {
			return data;
		}

		/**
		 * @return the next
		 */
		public Node<T> getNext() {
			return next;
		}

		/**
		 * @param next
		 *            the next to set
		 */
		public void setNext(Node<T> next) {
			this.next = next;
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FindMiddleInList<Integer> list = new FindMiddleInList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);

		System.out.println(list.findMiddle());

	}

}
