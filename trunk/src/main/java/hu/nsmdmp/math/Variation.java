package hu.nsmdmp.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Variation {

	public static <T> int getVariationsNumber(final T[][] vectorSet) {
		int n = 1;
		for (T[] row : vectorSet) {
			n *= row.length;
		}

		return n;
	}

	public static <T> List<T[]> createVariation(final T[][] vectorSet) {
		int n = getVariationsNumber(vectorSet);

		List<T[]> variations = new ArrayList<T[]>();

		for (int j = 0; j < n; j++) {
			variations.add(getVariation(j, vectorSet));
		}

		return variations;
	}

	public static <T> T[] getVariation(final int j, final T[][] vectorSet) {
		int s = vectorSet.length;
		T[] variation = Arrays.copyOf(vectorSet[0], s);

		int a = 1;
		for (int i = 0; i < s; i++) {
			int x = (j / a) % vectorSet[i].length;
			variation[i] = vectorSet[i][x];

			a *= vectorSet[i].length;
		}

		return variation;
	}
}
