package hu.nsmdmp2.tools;

import hu.nsmdmp2.numerics.Value;

public class VectorSet {

	/**
	 * n=2, m=3 => {{0, 1, 2, 3}, {0, 1, 2, 3}}
	 */
	@SuppressWarnings("unchecked")
	public static Value[][] createVectorSet(final int n, final int m, final Class<? extends Comparable<?>> type) {

		Value[][] vectorSet = new Value[n][m + 1];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= m; j++) {
				vectorSet[i][j] = Value.valueOf((Class<Comparable<?>>) type, j);
			}
		}

		return vectorSet;
	}
}
