/**
 *
 */
package com.pritam.coding.practice;

/**
 * @author pribiswas
 *
 */
public class ReverseLinkedList {

	private static class Node<T> {
		private final T value;
		private Node<T> next;

		public Node(T value) {
			this.value = value;
		}

		@Override
		public String toString() {
			if (next == null) {
				return value.toString();
			}
			return new StringBuilder(value.toString()).append("->").append(next.toString()).toString();
		}
	}

	private static class LinkedList<T> {
		private Node<T> head;
		private Node<T> tail;
		private int size;

		public LinkedList() {
			head = null;
			tail = null;
		}

		public void add(T value) {
			if (head == null) {
				head = new Node<>(value);
				tail = head;
			} else {
				Node<T> newNode = new Node<>(value);
				tail.next = newNode;
				tail = newNode;
			}
			size++;
		}

		/**
		 * Reverse the linked list by k elements
		 *
		 * @param k
		 */
		public void reverse(int k) {
			if (head == null || size < k) {
				return;
			}
			Node<T> temp = head, prev = null;
			int counter = 0;
			while (temp != null && counter < k) {
				Node<T> next = temp.next;
				temp.next = prev;
				prev = temp;
				temp = next;

				counter++;
			}
			head.next = temp;
			head = prev;
		}

		@Override
		public String toString() {
			if (head == null) {
				return "";
			}
			return head.toString();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(5);
		list.add(7);
		list.add(2);
		list.add(4);

		System.out.println(list);

		list.reverse(3);

		System.out.println(list);
	}

}
