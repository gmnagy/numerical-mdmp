package hu.nsmdmp.tools;

import static hu.nsmdmp.numerics.matrix.operations.OperationFactory.selectOperation;
import hu.nsmdmp.numerics.matrix.operations.IOperations;

import java.lang.reflect.Array;

public class VectorSet {

	/**
	 * n=2, m=3 => {{0, 1, 2, 3}, {0, 1, 2, 3}}
	 */
	public static <T> T[][] createVectorSet(final int n, final int m, final Class<T> type) {
		IOperations<T> op = selectOperation(type);

		@SuppressWarnings("unchecked")
		T[][] vectorSet = (T[][]) Array.newInstance(type, n, m + 1);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= m; j++) {
				vectorSet[i][j] = op.valueOf(j);
			}
		}

		return vectorSet;
	}
}
