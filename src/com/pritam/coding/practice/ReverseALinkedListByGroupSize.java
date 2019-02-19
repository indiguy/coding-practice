/**
 * 
 */
package com.pritam.coding.practice;

/**
 * Reverse a linked list by group size
 * 
 * @author pribiswas
 *
 */
public class ReverseALinkedListByGroupSize {

	private static class Node<T> {
		private final T data;
		private Node<T> next;

		/**
		 * @param data
		 */
		Node(T data) {
			this.data = data;
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

		/**
		 * @return the data
		 */
		public T getData() {
			return data;
		}

		@Override
		public String toString() {
			return data.toString();
		}

	}

	private static class LinkedList<T> {
		private Node<T> head;
		private Node<T> tail;

		LinkedList() {
			this(null, null);
		}

		/**
		 * @param head
		 * @param tail
		 */
		LinkedList(Node<T> head, Node<T> tail) {
			this.head = head;
			this.tail = tail;
		}

		public void add(T data) {
			Node<T> node = new Node<>(data);
			if (head == null) {
				head = node;
				tail = head;
			} else {
				tail.setNext(node);
				tail = node;
			}
		}

		/**
		 * Reverse the linked list per group size
		 * 
		 * @param groupSize
		 */
		public void reverse(int groupSize) {
			Node<T> tailOfCurrentSublist = null;
			Node<T> tailOfPrevSublist = null;
			Node<T> prev = null;
			Node<T> current = head;
			head = null; // reset
			int groupCount = 0;
			while (current != null) {
				if (groupCount == 0) {
					tailOfCurrentSublist = current;
				}
				Node<T> next = current.getNext();
				current.setNext(prev);
				prev = current;
				if (++groupCount == groupSize) {
					if (head == null) {
						head = current;
					} else {
						tailOfPrevSublist.setNext(current);
					}
					// reset everything
					tailOfPrevSublist = tailOfCurrentSublist;
					groupCount = 0;
					prev = null;
				}
				current = next;
			}
			if (tailOfPrevSublist != null) {
				tailOfPrevSublist.setNext(prev);
			}
		}

		@Override
		public String toString() {
			if (head == null)
				return "";
			Node<T> current = head;
			final StringBuilder builder = new StringBuilder();
			while (current != null) {
				builder.append(current.getData());
				current = current.getNext();
				if (current != null) {
					builder.append("->");
				}
			}
			return builder.toString();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);

		System.out.println(list);
		list.reverse(3);
		System.out.println(list);

	}

}
