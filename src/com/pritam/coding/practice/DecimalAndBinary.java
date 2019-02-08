/**
 * 
 */
package com.pritam.coding.practice;

import java.util.Arrays;

/**
 * Program for binary to decimal and vice versa
 * 
 * @author pribiswas
 *
 */
public class DecimalAndBinary {

	/**
	 * Convert given binary string to decimal integer
	 * 
	 * @param binary
	 * @return
	 */
	private static int toDecimal(String binary) {
		int decimal = 0, base = 1;
		for (int index = binary.length() - 1; index >= 0; index--) {
			decimal += base * Integer.parseInt(String.valueOf(binary.charAt(index)));
			base *= 2;
		}
		return decimal;
	}

	/**
	 * Convert given decimal to binary string
	 * 
	 * @param decimal
	 * @return
	 */
	private static String toBinary(int decimal) {
		int number = decimal, index = 7;
		char[] binary = new char[index + 1];
		Arrays.fill(binary, '0');
		while (number > 0) {
			binary[index] = number % 2 == 0 ? '0' : '1';
			index--;
			number = number / 2;
		}

		return new String(binary);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(toDecimal("00011011"));
		System.out.println(toBinary(27));
	}

}
