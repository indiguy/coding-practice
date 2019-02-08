package com.pritam.coding.practice;

public class StringManipulation {

	private static String compress(String input) {
		if (input == null || input.trim().isEmpty())
			return "";

		char prev = input.charAt(0);
		int currentCount = 1;
		StringBuilder outBuilder = new StringBuilder().append(prev);
		for (int i = 1; i < input.length(); i++) {
			char current = input.charAt(i);
			if (current == prev) {
				currentCount++;
			} else {
				outBuilder.append(currentCount);
				outBuilder.append(current);
				currentCount = 1;
				prev = current;
			}
		}
		outBuilder.append(currentCount);
		return outBuilder.toString();
	}

	public static void main(String[] args) {
		System.out.println(compress("aaabbcddddfff"));
	}

}
