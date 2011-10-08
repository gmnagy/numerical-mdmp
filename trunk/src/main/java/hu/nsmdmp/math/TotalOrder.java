package hu.nsmdmp.math;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class TotalOrder {

	/**
	 * a1 + a2 + ... <= maxOrder.
	 * 
	 */
	public static List<int[]> getOrders(final int maxOrder, final int s) {
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

	public static boolean equals(List<int[]> aList, List<int[]> bList) {

		if (aList.size() != bList.size()) {
			return false;
		}

		Iterator<int[]> itA = aList.iterator();
		Iterator<int[]> itB = bList.iterator();

		while (itB.hasNext()) {
			int[] a = itA.next();
			int[] b = itB.next();

			if (a.length != b.length) {
				return false;
			}

			for (int i = 0; i < a.length; i++) {
				if (a[i] != b[i]) {
					return false;
				}
			}
		}

		return true;
	}
}
