package hu.nsmdmp.tools;

import static hu.nsmdmp.numerics.matrix.operations.OperationFactory.selectOperation;
import hu.nsmdmp.numerics.matrix.operations.IOperations;

import java.lang.reflect.Array;
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
	 * n = 6, l = 2, dim = 2
	 * 
	 * @return {0, 1}{0, 1, 2, 3}
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[][] getSubSequences2(final int n, final int l, final int dim, final Class<T> type) {
		T[][] result = (T[][]) Array.newInstance(type, dim, 0);
		IOperations<T> op = selectOperation(type);

		int d = 0;
		int j = 0;
		T[] subSequence = null;

		for (int i = 0; i < n; i++) {

			if (i % l == 0 && dim != d) {
				d++;
				j = 0;

				int size = l;
				if (dim == d)
					size = n - i;

				subSequence = (T[]) Array.newInstance(type, size);
				result[d - 1] = subSequence;
			}

			subSequence[j] = op.valueOf(j);
			j++;
		}

		return result;
	}

	/**
	 * n = 6, l = 2, dim = 2
	 * 
	 * @return {0, 1}{0, 1, 2, 3}
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[][] getSubSequences3(final int n, final int l, final int dim, final Class<T> type) {
		T[][] result = (T[][]) Array.newInstance(type, dim, 0);
		IOperations<T> op = selectOperation(type);

		int d = 0;
		int j = 0;
		T[] subSequence = null;

		for (int i = 0; i < n; i++) {

			if (i % l == 0 && dim != d) {
				d++;
				j = 0;

				int size = l;
				if (dim == d)
					size = n - i;

				subSequence = (T[]) Array.newInstance(type, size);
				result[d - 1] = subSequence;
			}

			subSequence[j] = op.valueOf(i);
			j++;
		}

		return result;
	}
}
