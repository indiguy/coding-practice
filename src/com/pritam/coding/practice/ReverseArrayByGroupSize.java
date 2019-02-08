package com.pritam.coding.practice;

import java.util.Arrays;

public class ReverseArrayByGroupSize {

	public static void main(String[] args) {
		final int[] input = new int[] { 1, 2, 3, 4, 5, 6 };
		final int groupSize = 3;

		for (int i = 0; i < input.length; i = i + groupSize) {
			reverse(input, i, groupSize);
		}
		System.out.println(Arrays.toString(input));
	}

	private static void reverse(int[] arr, int startIndex, int noOfElements) {
		for (int i = 0; i < noOfElements / 2; i++) {
			int temp = arr[i + startIndex];
			arr[i + startIndex] = arr[startIndex + noOfElements - 1 - i];
			arr[startIndex + noOfElements - 1 - i] = temp;
		}
	}

}
