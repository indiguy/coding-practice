/**
 * 
 */
package com.pritam.coding.practice;

import java.util.Arrays;

/**
 * @author pribiswas
 *
 */
public class BudgetShopping {

	private static class NotebookBundle {
		private final int noteBookCount;
		private final int price;

		/**
		 * @param noteBookCount
		 * @param price
		 */
		public NotebookBundle(int noteBookCount, int price) {
			this.noteBookCount = noteBookCount;
			this.price = price;
		}

		@Override
		public String toString() {
			return String.format("[%d, %d]", Integer.valueOf(noteBookCount), Integer.valueOf(price));
		}
	}

	public int budgetShopping(int budget, int[] counts, int[] prices) {
		NotebookBundle[] byCount = new NotebookBundle[counts.length];
		for (int i = 0; i < counts.length; i++) {
			NotebookBundle bundle = new NotebookBundle(counts[i], prices[i]);
			byCount[i] = bundle;
		}

		// sort by notebook count in descending order
		Arrays.sort(byCount, this::compareByNotebookCount);

		return makeGreedyChoices(budget, byCount, 0);
	}

	/**
	 * Make greedy choices from given array starting from given start index
	 * until budget reached
	 * 
	 * @param budget
	 * @param byCount
	 * @param startIndex
	 * @return
	 */
	private int makeGreedyChoices(int budget, NotebookBundle[] byCount, int startIndex) {
		// Greedy choice would be pick the items from sorted array
		// until budget reached
		int maxCount = 0;
		for (int i = startIndex; i < byCount.length; i++) {
			if (budget < byCount[i].price) {
				continue;
			}
			int currentCount = (budget / byCount[i].price) * byCount[i].noteBookCount;
			int remainingBal = budget % byCount[i].price;

			int countOnRemainingBal = 0;
			if (remainingBal > 0) {

				// Apply greedy choice on rest of the array for remaining budget
				countOnRemainingBal = makeGreedyChoices(remainingBal, byCount, i + 1);
			}
			currentCount += countOnRemainingBal;

			if (currentCount > maxCount) {
				maxCount = currentCount;
			}
		}
		return maxCount;
	}

	/**
	 * Compare two bundles by notebook count in desc order
	 * 
	 * @param b1
	 * @param b2
	 * @return
	 */
	private int compareByNotebookCount(NotebookBundle b1, NotebookBundle b2) {
		if (b1.noteBookCount == b2.noteBookCount) {
			return b1.price - b2.price;
		}
		return b2.noteBookCount - b1.noteBookCount;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BudgetShopping shopping = new BudgetShopping();
		System.out.println(shopping.budgetShopping(55, new int[] { 15, 20, 9, 18 }, new int[] { 20, 25, 10, 12 }));

	}

}
