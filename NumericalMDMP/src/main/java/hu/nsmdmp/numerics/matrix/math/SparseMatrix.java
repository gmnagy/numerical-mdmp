package hu.nsmdmp.numerics.matrix.math;

import static hu.nsmdmp.operations.Operations.operation;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.operations.IOperation;

public final class SparseMatrix {

	/**
	 * Contains the non-zero values.
	 */
	public double[][] aval;

	/**
	 * Contains the row index of these non-zeros.
	 */
	public int[][] asub;

	public <T> SparseMatrix(final Matrix<T> matrix) {
		assign(matrix);
	}

	private <T> void assign(final Matrix<T> M) {
		int m = M.getRowDimension();
		int n = M.getColumnDimension();
		aval = new double[n][0];
		asub = new int[n][0];

		IOperation<T> op = operation(M.getValueType());

		for (int j = 0; j < n; j++) {
			for (int i = 0; i < m; i++) {
				if (op.compareTo(op.zero(), M.get(i, j)) != 0) {
					int s = aval[j].length;

					double[] a = new double[s + 1];
					System.arraycopy(aval[j], 0, a, 0, s);
					aval[j] = a;
					aval[j][s] = op.toDouble(M.get(i, j));

					int[] b = new int[s + 1];
					System.arraycopy(asub[j], 0, b, 0, s);
					asub[j] = b;
					asub[j][s] = i;
				}
			}
		}
	}
}
