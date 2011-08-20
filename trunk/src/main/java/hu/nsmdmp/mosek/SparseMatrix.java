package hu.nsmdmp.mosek;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.matrix.Matrix;

import org.apfloat.Apfloat;

public final class SparseMatrix {

	/**
	 * Contains the non-zero values.
	 */
	double[][] aval;

	/**
	 * Contains the row index of these non-zeros.
	 */
	int[][] asub;

	SparseMatrix(final Matrix matrix) {
		assign(matrix);
	}

	private void assign(final Matrix matrix) {
		int m = matrix.getRowDimension();
		int n = matrix.getColumnDimension();
		aval = new double[n][0];
		asub = new int[n][0];

		Apfloat[][] M = matrix.getArray();
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < m; i++) {
				if (ApfloatUtils.ZERO.compareTo(M[i][j]) != 0) {
					int s = aval[j].length;

					double[] a = new double[s + 1];
					System.arraycopy(aval[j], 0, a, 0, s);
					aval[j] = a;
					aval[j][s] = M[i][j].doubleValue();

					int[] b = new int[s + 1];
					System.arraycopy(asub[j], 0, b, 0, s);
					asub[j] = b;
					asub[j][s] = i;
				}
			}
		}
	}
}
