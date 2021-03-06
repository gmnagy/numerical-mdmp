package hu.nsmdmp2.tools;

import hu.nsmdmp2.numerics.Value;

import java.util.ArrayList;
import java.util.List;

public final class SubSequencesGenerator {

	/**
	 * n = 6, l = 2, dim = 2
	 * 
	 * @return {0, 1}{2, 3, 4, 5}
	 */
	public static List<int[]> getSubSequences(final int n, final int l, final int dim) {
		List<int[]> list = new ArrayList<int[]>();

		int d = 0;
		int j = 0;
		int[] subSequence = null;

		for (int i = 0; i < n; i++) {

			if (i % l == 0 && dim != d) {
				d++;
				j = 0;

				int size = l;
				if (dim == d)
					size = n - i;

				subSequence = new int[size];
				list.add(subSequence);
			}

			subSequence[j] = i;
			j++;
		}

		return list;
	}

	/**
	 * n = 6, l = 2, dim = 2 <br />
	 * result: {0, 1}{0, 1, 2, 3}
	 */
	public static Value[][] getSubSequences2(final int n, final int l, final int dim, final Class<Comparable<?>> type) {
		Value[][] result = new Value[dim][0];

		int d = 0;
		int j = 0;
		Value[] subSequence = null;

		for (int i = 0; i < n; i++) {

			if (i % l == 0 && dim != d) {
				d++;
				j = 0;

				int size = l;
				if (dim == d)
					size = n - i;

				subSequence = new Value[size];
				result[d - 1] = subSequence;
			}

			subSequence[j] = Value.valueOf(type, j);
			j++;
		}

		return result;
	}

	/**
	 * n = 6, l = 2, dim = 2
	 * 
	 * @return {0, 1}{0, 1, 2, 3}
	 */
	public static Value[][] getSubSequences3(final int n, final int l, final int dim, final Class<Comparable<?>> type) {
		Value[][] result = new Value[dim][0];

		int d = 0;
		int j = 0;
		Value[] subSequence = null;

		for (int i = 0; i < n; i++) {

			if (i % l == 0 && dim != d) {
				d++;
				j = 0;

				int size = l;
				if (dim == d)
					size = n - i;

				subSequence = new Value[size];
				result[d - 1] = subSequence;
			}

			subSequence[j] = Value.valueOf(type, i);
			j++;
		}

		return result;
	}
}
