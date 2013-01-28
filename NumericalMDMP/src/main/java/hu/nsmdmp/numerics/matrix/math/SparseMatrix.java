package hu.nsmdmp.numerics.matrix.math;

import static hu.nsmdmp.numerics.matrix.operations.OperationFactory.selectOperation;
import hu.nsmdmp.numerics.matrix.Matrix;
import hu.nsmdmp.numerics.matrix.operations.IOperations;

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

	private <T> void assign(final Matrix<T> matrix) {
		int m = matrix.getRowDimension();
		int n = matrix.getColumnDimension();
		aval = new double[n][0];
		asub = new int[n][0];

		IOperations<T> op = selectOperation(matrix.getElementType());

		T[][] M = matrix.getArray();
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < m; i++) {
				if (op.compareTo(op.zero(), M[i][j]) != 0) {
					int s = aval[j].length;

					double[] a = new double[s + 1];
					System.arraycopy(aval[j], 0, a, 0, s);
					aval[j] = a;
					aval[j][s] = op.toDouble(M[i][j]);

					int[] b = new int[s + 1];
					System.arraycopy(asub[j], 0, b, 0, s);
					asub[j] = b;
					asub[j][s] = i;
				}
			}
		}
	}
}
