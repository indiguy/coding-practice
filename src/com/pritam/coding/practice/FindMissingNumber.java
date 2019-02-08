/**
 *
 */
package com.pritam.coding.practice;

import java.util.ArrayList;

/**
 * Given an array consisting of 0 to n(as binary format), except one number,
 * find the missing one. Consider the operation of finding jth bit of number i
 * take constant time. The overall algo should run in O(n) time
 *
 * @author pribiswas
 *
 */
public class FindMissingNumber {

	private static class BitInteger {
		private final String binary;

		public BitInteger(String binary) {
			for (char ch : binary.toCharArray()) {
				if (!Character.isDigit(ch) || Character.getNumericValue(ch) > 1) {
					throw new IllegalArgumentException("need binary string");
				}
			}
			this.binary = binary;
		}

		public int getBit(int i) {
			if (i < 0 || i >= binary.length())
				return 0;
			return Character.getNumericValue(binary.charAt(i));
		}
	}

	private final ArrayList<BitInteger> array;

	public FindMissingNumber(ArrayList<BitInteger> array) {
		this.array = array;
	}

	public int findMissingNumber() {
		return findMissingNumber(array, 0);
	}

	private int findMissingNumber(ArrayList<BitInteger> arr, int bitIndex) {
		int size = arr.size();
		if (size == 0) {
			return 0;
		}
		ArrayList<BitInteger> zeros = new ArrayList<>(size / 2);
		ArrayList<BitInteger> ones = new ArrayList<>(size / 2);

		for (BitInteger num : arr) {
			if (num.getBit(bitIndex) == 0) {
				zeros.add(num);
			} else {
				ones.add(num);
			}
		}
		if (zeros.size() <= ones.size()) {
			int v = findMissingNumber(zeros, bitIndex + 1);
			return (v << 1) | 0;
		} else {
			int v = findMissingNumber(ones, bitIndex + 1);
			return (v << 1) | 1;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
