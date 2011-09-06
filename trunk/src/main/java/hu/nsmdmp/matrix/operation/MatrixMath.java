package hu.nsmdmp.matrix.operation;

import hu.nsmdmp.ApfloatUtils;
import hu.nsmdmp.matrix.Matrix;
import hu.nsmdmp.vector.Vector;

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

	/**
	 * Solve A * X = B
	 * 
	 * @param B
	 *            right hand side
	 */
	public static Matrix getTransformationMatrix(final Matrix A, final Matrix B) {
		if (A.getColumnDimension() >= A.getRowDimension()) {
			return calcTransformationMatrix(A, B);
		} else {
			return calcTransformationMatrix(completeWithIdentity(A), completeWithIdentity(B));
		}
	}

	private static Matrix calcTransformationMatrix(final Matrix A, final Matrix B) {
		Matrix T = new Matrix(B.getRowDimension(), A.getRowDimension());

		Matrix tA = A.transpose();

		for (int i = 0; i < B.getRowDimension(); i++) {
			Vector b = new Vector(tA.getColumnDimension());
			for (int j = 0; j < tA.getColumnDimension(); j++) {
				b.set(j, B.get(i, j));
			}

			GaussJordanElimination gaussian = new GaussJordanElimination(tA, b);
			Vector t = gaussian.primal();
			T.setRow(i, t.getArray());
		}

		return T;
	}

	public static Matrix completeWithIdentity(final Matrix A) {
		Matrix M = identity(A.getRowDimension(), A.getRowDimension());

		for (int i = 0; i < A.getRowDimension(); i++) {
			for (int j = 0; j < A.getColumnDimension(); j++) {
				M.set(i, j, A.get(i, j));
			}
		}

		return M;
	}

//	/**
//	 * Solve A * X = B
//	 * 
//	 * @param B
//	 *            right hand side
//	 * @return solution if A is square, least squares solution otherwise
//	 */
//	public static Matrix solve(final Matrix A, final Matrix B) {
//		return (A.isSquare() ? A.getLU().solve(B) : A.getQR().solve(B));
//	}

//	/**
//	 * Solve X * A = B, which is also A' * X' = B'
//	 * 
//	 * @param B
//	 *            right hand side
//	 * @return solution if A is square, least squares solution otherwise.
//	 */
//	public static Matrix solveTranspose(final Matrix A, final Matrix B) {
//		return solve(A.transpose(), B.transpose());
//	}
}
