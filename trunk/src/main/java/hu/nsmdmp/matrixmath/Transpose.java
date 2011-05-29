package hu.nsmdmp.matrixmath;

import hu.nsmdmp.matrices.IMatrix;
import hu.nsmdmp.matrices.Matrix;

import org.apfloat.Apfloat;

final class Transpose {

	/**
	 * Matrix transpose.
	 * 
	 * @return A'
	 */
	static IMatrix transpose(final IMatrix A) {
		int m = A.getRowDimension();
		int n = A.getColumnDimension();
		Apfloat[][] M = A.getMatrix();

		Matrix X = new Matrix(n, m);
		Apfloat[][] C = X.getMatrix();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[j][i] = M[i][j];
			}
		}

		return X;
	}
}
