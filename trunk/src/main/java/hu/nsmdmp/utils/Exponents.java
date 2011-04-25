package hu.nsmdmp.utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class Exponents {

	public static List<int[]> getExponents(final int m, final int s) {
		List<int[]> expList = new LinkedList<int[]>();

		int[] expRow = new int[s];
		// 0 row
		expList.add(clone(expRow));
		int sum = 0;

		while (expRow[s - 1] < m) {
			expRow[0]++;

			if (sum == m && expRow[s - 1] < m) {
				sum = stepper(m, sum, expRow, 0);
			}
			sum++;

			expList.add(clone(expRow));
		}

		return expList;
	}

	private static int stepper(int m, int sum, int[] expRow, int i) {
		int summ = sum - (expRow[i] - 1);
		expRow[i] = 0;

		expRow[i + 1]++;

		if (summ == m) {
			summ = stepper(m, summ, expRow, i + 1);
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
