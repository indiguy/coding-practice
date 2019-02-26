/**
 * 
 */
package com.pritam.coding.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Generate all permutations of a given string
 * 
 * @author pribiswas
 *
 */
public class Permutations {

	private static List<String> permutations(String str) {
		if (str == null || str.trim().isEmpty()) {
			return Collections.emptyList();
		}
		if (str.length() == 1) {
			return Arrays.asList(str);
		}
		List<String> result = Arrays.asList(str.substring(0, 1));
		for (int i = 1; i < str.length(); i++) {
			List<String> currentParams = new ArrayList<>();
			for (String sub : result) {
				currentParams.addAll(stichAtEachIndex(sub, str.charAt(i)));
			}
			result = currentParams;
		}
		return result;
	}

	private static List<String> stichAtEachIndex(String str, char ch) {
		List<String> perms = new ArrayList<>(str.length() + 1);
		// at the beginning and end
		perms.add(ch + str);
		// in-between
		for (int i = 1; i < str.length(); i++) {
			perms.add(str.substring(0, i) + ch + str.substring(i));
		}
		// At the end
		perms.add(str + ch);
		return perms;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(permutations("abcd"));

	}

}
