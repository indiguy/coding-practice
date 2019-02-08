/**
 *
 */
package com.pritam.coding.practice;

/**
 * @author pribiswas
 *
 */
public class AddNodeToSortedCircularLinkedList {

	private static class Node<T extends Comparable<T>> {
		private T value;
		private Node<T> next;

		public Node(T value) {
			this.value = value;
			this.next = null;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}

		@Override
		public String toString() {
			return value.toString();
		}
	}

	private static class AscSortedCircularLinkedList<T extends Comparable<T>> {
		private Node<T> head;

		public AscSortedCircularLinkedList(Node<T> head) {
			if (head == null) {
				throw new IllegalArgumentException("Provide valid head");
			}
			this.head = head;
		}

		/**
		 * Add a node with agven value
		 *
		 * @param value
		 */
		public void addNode(T value) {
			// less than head, make it new head and return
			if (value.compareTo(head.value) < 0) {
				Node<T> newNode = new Node<>(value);
				// find the tail of the list and update to new head
				Node<T> temp = head;
				while (temp.next != head) {
					temp = temp.next;
				}
				newNode.next = head;
				head = newNode;
				temp.next = head;
				return;
			}
			Node<T> temp = head;
			while (!addInBetween(temp, value)) {
				temp = temp.next;
			}
		}

		/**
		 * Add the given value in between the given node and its next node if
		 * sorting condition matched
		 *
		 * @param node
		 * @param value
		 * @return true if added, false otherwise
		 */
		private boolean addInBetween(Node<T> node, T value) {
			Node<T> newNode = new Node<>(value);
			if (node.value.compareTo(value) <= 0
					&& (node.next.value.compareTo(value) > 0 || node.next.value.compareTo(node.value) < 0)) {
				newNode.setNext(node.next);
				node.setNext(newNode);
				return true;
			}
			return false;
		}

		@Override
		public String toString() {
			if (head == null) {
				return "";
			}
			StringBuilder linkedListBuilder = new StringBuilder(head.toString());
			Node<T> temp = head.next;
			while (temp != head) {
				linkedListBuilder.append("->").append(temp.toString());
				temp = temp.next;
			}
			linkedListBuilder.append("->").append(temp.toString());
			return linkedListBuilder.toString();
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
		AscSortedCircularLinkedList<Integer> list = new AscSortedCircularLinkedList<>(head);
		System.out.println(list.toString());

		list.addNode(4);
		System.out.println(list.toString());
		list.addNode(7);
		System.out.println(list.toString());
		list.addNode(1);
		System.out.println(list.toString());
	}

}
