package com.pritam.coding.practice;

import java.util.LinkedList;

public class ConceptTest {

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		list.addLast(1);
		list.addLast(2);
		list.addLast(3);
		list.addFirst(4);

		System.out.println(list);

		while (list.size() > 0) {
			System.out.println(list.pop());
		}

	}

}
