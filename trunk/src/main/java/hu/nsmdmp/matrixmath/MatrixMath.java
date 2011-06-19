package hu.nsmdmp.matrixmath;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.matrices.Matrix;
import hu.nsmdmp.vectors.Vector;

import org.apfloat.Apfloat;

public final class MatrixMath {

	public static Matrix normalize(final Matrix M) {
		return Normalization.normalize(M);
	}

	/**
	 * Generate identity matrix
	 * 
	 * @param m
	 *            Number of rows.
	 * @param n
	 *            Number of colums.
	 * @return An m-by-n matrix with ones on the diagonal and zeros elsewhere.
	 */
	public static Matrix identity(int m, int n) {
		Matrix A = new Matrix(m, n);
		Apfloat[][] X = A.getArray();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				X[i][j] = (i == j ? ApfloatUtils.ONE : ApfloatUtils.ZERO);
			}
		}
		return A;
	}

	private static void checkMatrixDimensions(final Matrix A, final Matrix B) {
		if (A.getRowDimension() != B.getRowDimension() || A.getColumnDimension() != B.getColumnDimension()) {
			throw new IllegalArgumentException("Matrix dimensions must agree.");
		}
	}

	/**
	 * C = A + B
	 * 
	 * @param A
	 *            one {@link Matrix}
	 * @param B
	 *            another {@link Matrix}
	 * @return A + B
	 */
	public static Matrix add(final Matrix A, final Matrix B) {
		checkMatrixDimensions(A, B);

		int m = A.getRowDimension();
		int n = A.getColumnDimension();
		Matrix X = new Matrix(m, n);
		Apfloat[][] C = X.getArray();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = A.get(i, j).add(B.get(i, j));
			}
		}

		return X;
	}

	/**
	 * C = A - B
	 * 
	 * @param A
	 *            one {@link Matrix}
	 * @param B
	 *            another {@link Matrix}
	 * @return A - B
	 */
	public static Matrix subtract(final Matrix A, final Matrix B) {
		checkMatrixDimensions(A, B);

		int m = A.getRowDimension();
		int n = A.getColumnDimension();
		Matrix X = new Matrix(m, n);
		Apfloat[][] C = X.getArray();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				C[i][j] = A.get(i, j).subtract(B.get(i, j));
			}
		}
		return X;
	}

	/**
	 * Matrix-Vector multiplication.
	 * 
	 * @param A
	 *            {@link Matrix}
	 * @param V
	 *            {@link Vector}
	 * @return A * V
	 * @exception IllegalArgumentException
	 */
	public static Vector multiply(final Matrix A, final Vector V) {
		return Multiplication.multiply(A, V);
	}

	/**
	 * Linear algebraic matrix multiplication, A * B.
	 * 
	 * @param A
	 *            one {@link Matrix}
	 * @param B
	 *            another {@link Matrix}
	 * 
	 * @return Matrix product, A * B
	 * @exception IllegalArgumentException
	 *                Matrix inner dimensions must agree.
	 */
	public static Matrix multiply(final Matrix A, final Matrix B) {
		return Multiplication.multiply(A, B);
	}
}
