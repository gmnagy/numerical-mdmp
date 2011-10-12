package hu.nsmdmp.math;

import hu.nsmdmp.utils.Converters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Variation {

	public static <T> T[] getVariation(final int j, final List<T[]> vectorSet) {
		int s = vectorSet.size();
		T[] variation = Arrays.copyOf(vectorSet.get(0), s);

		int a = 1;
		int i = 0;
		for (T[] vector : vectorSet) {
			int x = (j / a) % vector.length;
			variation[i] = vector[x];

			a *= vector.length;
			i++;
		}

		return variation;
	}

	public static <T> T[] getVariation(final int j, final T[][] vectorSet) {
		return getVariation(j, Converters.convert(vectorSet));
	}

	public static <T> int getVariationsNumber(final List<T[]> vectorSet) {
		int n = 1;
		for (T[] row : vectorSet) {
			n *= row.length;
		}

		return n;
	}

	public static <T> int getVariationsNumber(final T[][] vectorSet) {
		return getVariationsNumber(Converters.convert(vectorSet));
	}

	public static <T> List<T[]> createVariation(final List<T[]> vectorSet) {
		int n = getVariationsNumber(vectorSet);

		List<T[]> variations = new ArrayList<T[]>();

		for (int j = 0; j < n; j++) {
			variations.add(getVariation(j, vectorSet));
		}

		return variations;
	}

	public static <T> List<T[]> createVariation(final T[][] vectorSet) {
		return createVariation(Converters.convert(vectorSet));
	}
}
