package com.pritam.coding.practice;

import java.util.Arrays;

/**
 * Reverse an array by given group size
 * 
 * @author pribiswas
 *
 */
public class ReverseArrayByGroupSize {

	private static void reverse(int[] arr, int groupSize) {
		for (int i = 0; i < arr.length; i = i + groupSize) {
			reverse(arr, i, groupSize);
		}
	}

	private static void reverse(int[] arr, int start, int noOfElements) {
		int i = start, j = start + noOfElements - 1;
		while (i < j && inBound(arr.length, i) && inBound(arr.length, j)) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
	}

	private static boolean inBound(int length, int index) {
		return index >= 0 && index < length;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
		System.out.println(Arrays.toString(arr));
		reverse(arr, 3);
		System.out.println(Arrays.toString(arr));
	}

}
