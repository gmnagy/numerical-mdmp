package hu.nsmdmp2.numerics.matrix;

import hu.nsmdmp2.numerics.Value;

public final class SparseMatrix {

	/**
	 * Contains the non-zero values.
	 */
	public double[][] aval;

	/**
	 * Contains the row index of these non-zeros.
	 */
	public int[][] asub;

	public SparseMatrix(final Value[][] matrix) {
		assign(matrix);
	}

	private void assign(final Value[][] M) {
		int m = M.length;
		int n = M[0].length;
		aval = new double[n][0];
		asub = new int[n][0];

		Class<? extends Comparable<?>> valueType = M[0][0].getType();

		for (int j = 0; j < n; j++) {
			for (int i = 0; i < m; i++) {
				if (Value.zero(valueType).compareTo(M[i][j]) != 0) {
					int s = aval[j].length;

					double[] a = new double[s + 1];
					System.arraycopy(aval[j], 0, a, 0, s);
					aval[j] = a;
					aval[j][s] = M[i][j].toDouble();

					int[] b = new int[s + 1];
					System.arraycopy(asub[j], 0, b, 0, s);
					asub[j] = b;
					asub[j][s] = i;
				}
			}
		}
	}
}
