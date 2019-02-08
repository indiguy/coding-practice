/**
 *
 */
package com.pritam.coding.practice;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @author pribiswas
 *
 */
public class SetOfStacks<T> {
	private final Stack<Stack<T>> stacks;
	private final int threshold;

	/**
	 * @param threshold
	 */
	public SetOfStacks(int threshold) {
		this.stacks = new Stack<>();
		this.threshold = threshold;
	}

	/**
	 * Push the data into the stack
	 *
	 * @param data
	 */
	public void push(T data) {
		Stack<T> current = currentStack();
		if (current.size() == threshold) {
			current = new Stack<>();
			stacks.push(current);
		}
		current.push(data);
	}

	/**
	 * Pop from top of the stack and return it
	 *
	 * @return
	 */
	public T pop() {
		Stack<T> current = currentStack();
		return popFrom(current);
	}

	/**
	 * Pop from specified sub-stack
	 *
	 * @param stackIndex
	 *            The zero based index
	 * @return
	 */
	public T popAt(int stackIndex) {
		if (stacks.size() - 1 < stackIndex) {
			throw new IllegalArgumentException();
		}
		Stack<T> specifiedStack = stacks.get(stackIndex);
		return popFrom(specifiedStack);
	}

	/**
	 * Pop from given stack
	 *
	 * @param stack
	 * @return
	 */
	private T popFrom(Stack<T> stack) {
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		}
		T poped = stack.pop();
		if (stack.isEmpty()) {
			stacks.pop();
		}
		return poped;
	}

	/**
	 * Return the current stack
	 *
	 * @return
	 */
	private Stack<T> currentStack() {
		if (stacks.isEmpty()) {
			stacks.push(new Stack<T>());
		}
		return stacks.peek();
	}

	@Override
	public String toString() {
		return stacks.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SetOfStacks<Integer> stack = new SetOfStacks<>(2);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(6);
		stack.push(7);

		System.out.println(stack);

		stack.pop();

		System.out.println(stack);

		stack.popAt(2);

		System.out.println(stack);
	}

}
