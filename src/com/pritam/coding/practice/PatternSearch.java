/**
 * 
 */
package com.pritam.coding.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all occurrences of a pattern inside a string
 * 
 * @author pribiswas
 *
 */
public class PatternSearch {

	/**
	 * Inspired by KMP algorithm, whenever there is a mismatch lps will tell where
	 * to resume the search
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 */
	private static List<Integer> indicesOf(String str, String pattern) {
		int n = str.length();
		int m = pattern.length();
		int[] lps = createLps(pattern);
		List<Integer> indices = new ArrayList<>();
		for (int i = 0, j = 0; i < n;) {
			if (str.charAt(i) == pattern.charAt(j)) {
				i++;
				j++;
				if (j == m) {
					indices.add(i - j);
					j = lps[j - 1];
				}
			} else {
				if (j > 0) {
					j = lps[j - 1];
				} else {
					i++;
				}
			}
		}
		return indices;
	}

	/**
	 * Create an auxiliary array from the pattern which holds the length of the
	 * longest proper prefix which is also suffix in the pattern for each index
	 * position. So, lps[i] = length of the longest prefix of pattern[0, i] which is
	 * also of suffix of pattern[0,i].
	 * 
	 * @param pattern
	 * @return
	 */
	private static int[] createLps(String pattern) {
		int m = pattern.length();
		int[] lps = new int[m];
		lps[0] = 0;
		int j = 0;
		for (int i = 1; i < m;) {
			if (pattern.charAt(i) == pattern.charAt(j)) {
				j++;
				lps[i] = j;
				i++;
			} else if (j != 0) {
				j = lps[j - 1];
			} else {
				lps[i] = j;
				i++;
			}
		}
		return lps;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(indicesOf("aabadeabadc", "abad"));

	}

}
