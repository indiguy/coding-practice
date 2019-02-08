/**
 *
 */
package com.pritam.coding.practice;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author pribiswas
 *
 */
public class ReverseAStack {

	private static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		Integer poped = stack.pop();
		reverse(stack);
		insertAtBottom(stack, poped);
	}

	private static void insertAtBottom(Stack<Integer> stack, Integer elem) {
		if (stack.isEmpty()) {
			stack.push(elem);
		} else {
			Integer poped = stack.pop();
			insertAtBottom(stack, elem);
			stack.push(poped);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.addAll(Arrays.asList(1, 2, 3, 4, 5));

		System.out.println(stack.toString());

		reverse(stack);

		System.out.println(stack);
	}

}
