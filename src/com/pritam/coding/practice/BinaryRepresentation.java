/**
 *
 */
package com.pritam.coding.practice;

import java.util.Arrays;

/**
 * @author pribiswas
 *
 */
public class BinaryRepresentation {

	private static String binary(int number) {
		char[] bin = new char[8];
		Arrays.fill(bin, Character.forDigit(0, 10));
		int index = bin.length - 1;
		int dividend = number;
		while (dividend > 0) {
			int remainder = dividend % 2;
			bin[index--] = Character.forDigit(remainder, 10);
			dividend = dividend / 2;
		}
		return new String(bin);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(binary(127));

	}

}
