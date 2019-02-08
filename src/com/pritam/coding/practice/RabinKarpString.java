/**
 *
 */
package com.pritam.coding.practice;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * The string matching using rabin karp algorithm
 *
 * @author pribiswas
 *
 */
public class RabinKarpString {

	private static final int ALPHA_NUMERIC = 36;

	private final String string;

	public RabinKarpString(String string) {
		this.string = string;
	}

	public List<Integer> indexesOf(String pattern) {
		List<Integer> result = new ArrayList<>();

		BigInteger patternHash = hashCode(pattern);

		int patternLen = pattern.length();
		int strLen = string.length();

		String subStr = string.substring(0, patternLen);
		BigInteger hash = hashCode(subStr);
		if (hash.equals(patternHash) && subStr.equals(pattern)) {
			result.add(0);
		}

		for (int i = 1; i < strLen - patternLen + 1; i++) {
			subStr = string.substring(i, i + patternLen);
			hash = hash.subtract(hashValueAtIndex(string.charAt(i - 1), 0, patternLen))
					.multiply(BigInteger.valueOf(ALPHA_NUMERIC))
					.add(hashValueAtIndex(string.charAt(i + patternLen - 1), patternLen - 1, patternLen));
			if (hash.equals(patternHash) && subStr.equals(pattern)) {
				result.add(i);
			}
		}
		return result;
	}

	private BigInteger hashCode(String number) {
		BigInteger result = BigInteger.ZERO;
		int length = number.length();

		for (int i = length - 1; i >= 0; i--) {
			BigInteger valAtPos = hashValueAtIndex(number.charAt(i), i, length);
			result = result.add(valAtPos);
		}
		return result;
	}

	private BigInteger hashValueAtIndex(char character, int index, int length) {
		int digitAtPos = character;
		int exp = length - index - 1;
		BigInteger valAtPos = BigDecimal.valueOf(Math.pow(ALPHA_NUMERIC, exp)).toBigInteger()
				.multiply(BigInteger.valueOf(digitAtPos));
		return valAtPos;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RabinKarpString problem = new RabinKarpString("aadacead");
		System.out.println(problem.indexesOf("ad"));
	}

}
