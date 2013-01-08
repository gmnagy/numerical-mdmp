package hu.nsmdmp.math;

import java.util.LinkedList;
import java.util.List;

public final class TotalOrder {

	/**
	 * Get the total order of the moment members.
	 * 
	 * {a(1), ..., a(s)}, where a(1) + ... + a(s) <= maxOrder
	 * 
	 */
	public static List<int[]> getTotalOrderOfMomentMembers(final int maxOrder, final int s) {
		List<int[]> expList = new LinkedList<int[]>();

		int[] expRow = new int[s];
		// 0 row
		expList.add(clone(expRow));
		int sum = 0;

		while (expRow[s - 1] < maxOrder) {
			expRow[0]++;

			if (sum == maxOrder && expRow[s - 1] < maxOrder) {
				sum = stepper(maxOrder, sum, expRow, 0);
			}
			sum++;

			expList.add(clone(expRow));
		}

		return expList;
	}

	private static int stepper(final int maxOrder, final int sum, final int[] expRow, final int i) {
		int summ = sum - (expRow[i] - 1);
		expRow[i] = 0;

		expRow[i + 1]++;

		if (summ == maxOrder) {
			summ = stepper(maxOrder, summ, expRow, i + 1);
		}

		return summ;
	}

	private static int[] clone(int[] array) {
		int[] newArray = new int[array.length];

		int i = 0;
		for (int x : array) {
			newArray[i] = x;
			i++;
		}

		return newArray;
	}

}
