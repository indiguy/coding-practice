/**
 * 
 */
package com.pritam.coding.practice;

import java.math.BigInteger;

/**
 * Write a program to calculate power(x, n) in log(n) time
 * 
 * @author pribiswas
 *
 */
public class ToThePowerOfN {

	private static BigInteger power(int x, int n) {
		if (n == 0)
			return BigInteger.ONE;
		if (n == 1)
			return BigInteger.valueOf(x);

		if (n % 2 == 0) {
			BigInteger half = power(x, n / 2);
			return half.multiply(half);
		} else {
			return BigInteger.valueOf(x).multiply(power(x, n - 1));
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(power(2, 0));
	}

}
