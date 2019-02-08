/**
 *
 */
package com.pritam.coding.practice;

/**
 * Add node to sorted circular linked list
 *
 * @author pribiswas
 *
 */
public class AddNodeToSortedCircularLinkedList {

	private static class Node<T extends Comparable<T>> {
		private final T value;
		private Node<T> next;

		public Node(T value) {
			this.value = value;
		}

		/**
		 * Set the next
		 *
		 * @param next
		 */
		public void setNext(Node<T> next) {
			this.next = next;
		}

		@Override
		public String toString() {
			return value.toString();
		}
	}

	private static class SortedCircularLinkedList<T extends Comparable<T>> {
		private Node<T> head;

		public SortedCircularLinkedList(Node<T> head) {
			if (head == null) {
				throw new IllegalArgumentException("Provide valid head");
			}
			this.head = head;
		}

		/**
		 * Add a node with a given value
		 *
		 * @param value
		 */
		public void addNode(T value) {
			// less than head, make it new head and return
			if (value.compareTo(head.value) < 0) {
				Node<T> newNode = new Node<>(value);
				// find the tail of the list, update to new head and update next pointer of tail
				// to maintain the circular behavior
				Node<T> tail = findTail();
				newNode.next = head;
				head = newNode;
				tail.next = head;
				return;
			}
			Node<T> temp = head;
			while (!addInBetween(temp, value)) {
				temp = temp.next;
			}
		}

		/**
		 * Find the tail of the circular linked list
		 *
		 * @return
		 */
		private Node<T> findTail() {
			Node<T> temp = head;
			while (temp.next != head) {
				temp = temp.next;
			}
			return temp;
		}

		/**
		 * Add the given value in between the given node and its next node if sorting
		 * condition matched
		 *
		 * @param node
		 * @param value
		 * @return true if added, false otherwise
		 */
		private boolean addInBetween(Node<T> node, T value) {
			Node<T> newNode = new Node<>(value);
			if (node.value.compareTo(value) <= 0 && (node.next.value.compareTo(value) > 0 || node.next == head)) {
				newNode.setNext(node.next);
				node.setNext(newNode);
				return true;
			}
			return false;
		}

		@Override
		public String toString() {
			StringBuilder toStringBuilder = new StringBuilder(head.toString());
			Node<T> temp = head.next;
			while (temp != head) {
				toStringBuilder.append("->").append(temp.toString());
				temp = temp.next;
			}
			toStringBuilder.append("->").append(temp.toString());
			return toStringBuilder.toString();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node<Integer> head = new Node<>(2);
		head.next = new Node<Integer>(3);
		head.next.next = new Node<Integer>(6);
		head.next.next.next = head;
		SortedCircularLinkedList<Integer> list = new SortedCircularLinkedList<>(head);
		System.out.println(list.toString());

		list.addNode(4);
		System.out.println(list.toString());
		list.addNode(7);
		System.out.println(list.toString());
		list.addNode(1);
		System.out.println(list.toString());
	}

}
