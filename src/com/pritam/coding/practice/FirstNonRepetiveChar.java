package com.pritam.coding.practice;

/**
 * Find the first non repeat character
 *
 * @author pribiswas
 *
 */
public class FirstNonRepetiveChar {

	public static void main(String[] args) {
		final String input = "Salesforce is a best company to work for";

		int[] charArr = new int[128];
		String inputLowerCase = input.toLowerCase();
		for (int i = 0; i < inputLowerCase.length(); i++) {
			int ascii = Character.getNumericValue(inputLowerCase.charAt(i));
			if (ascii >= 0) {
				charArr[ascii]++;
			}
		}

		for (int i = 0; i < inputLowerCase.length(); i++) {
			int ascii = Character.getNumericValue(inputLowerCase.charAt(i));
			if (ascii >= 0 && charArr[ascii] == 1) {
				System.out.println("First non repeative: " + input.charAt(i));
				break;
			}
		}

	}

}
